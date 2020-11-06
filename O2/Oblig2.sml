(*Declaring grammar elements*)

exception OutOfBounds;

datatype direction = north | south | east | west;
datatype grid = Size of int * int;
type position = int * int;

datatype exp =
    Number of int
    | Identifier of string
    | Boolean of boolean
    | Arithmetic of arithmetic
    and arithmetic = 
    Add of exp list
    | Subtract of exp list
    | Multiply of exp list
    and boolean =
    Equals of exp * exp
    | BiggerThan of exp * exp
    | LessThan of exp * exp;

datatype stmt =
    Move of direction * exp
    | Assignment of assignment
    | Loop of stmt list * boolean
    | Stop
    and assignment = 
    Increment of string
    | Decrement of string

datatype decl = Declaration of string * exp;
datatype start = Start of exp * exp;
datatype robot = Robot of decl list * start * stmt list;
datatype program = Program of grid * robot;


(*Association list functionality*)
type assocList = (string * int) list;
fun lookup (key:string) ((k, v)::list : assocList) =
    if key = k then v
    else lookup key list;
fun add (key:string) (value:int) (list:assocList) : assocList =
    list @ [(key, value)];
fun change (key:string) (value:int) (list:assocList) : assocList =
    map(fn (k, v) =>
        if key = k then (k, value)
        else (k, v)) list;


(*Information about program carry through interpretation*)
type info = {grid: grid, bindings: assocList, position: position};


(*Evaluation of expressions*)
fun evalExp bindings (Number(n)) : int = n
| evalExp bindings (Identifier(i)) : int = lookup i bindings
| evalExp bindings (Arithmetic(Add(e))) =
    let val eval = evalExp bindings
    fun sum(nil) = 0
        | sum(s::ss : exp list) = (eval s) + sum(ss)
    in sum(e) end
| evalExp bindings (Arithmetic(Subtract(e::es))) =
    let val eval = evalExp bindings 
    fun diff(res, nil) = res
        | diff(res, d::ds : exp list) = diff(res - eval d, ds)
    in diff((eval e)*2, e::es) end
| evalExp bindings (Arithmetic(Multiply(e))) =
    let val eval = evalExp bindings
    fun prod(nil) = 1
        | prod(p::ps : exp list) = (eval p) * prod(ps)
    in prod(e) end
| evalExp bindings (Boolean(BiggerThan(e1, e2))) =
    let val eval = evalExp bindings in
    if eval e1 > eval e2
        then 1
        else 0 end
| evalExp bindings (Boolean(LessThan(e1, e2))) =
    let val eval = evalExp bindings in
    if eval e1 < eval e2
        then 1
        else 0 end
| evalExp bindings (Boolean(Equals(e1, e2))) =
    let val eval = evalExp bindings in
    if eval e1 = eval e2
        then 1
        else 0 end;


(*Generate association list from binding declarations*)
fun getAssocList decls : assocList = 
    let fun aLst nil bindings : assocList = bindings
    |   aLst (Declaration(i, e)::decls) bindings =
        aLst decls (add i (evalExp bindings e) bindings)
    in aLst decls [] end;


(*Generates record and checks not OutOfBounds*)
fun place (Size(size_x, size_y)) (x, y) (bindings): info =
    if x >= size_x orelse x < 0 orelse y >= size_y orelse y < 0 then raise OutOfBounds
    else {grid = Size(size_x, size_y), bindings = bindings, position = (x, y)};


(*Interpret program statement list*)
fun interpret inf (nil) : info = inf
| interpret inf (Stop::_) = interpret inf nil
| interpret inf  (Move(d, e)::stmtList) = 
    let val value = (evalExp (#bindings inf) e)
        fun move ({grid = Size(size_x, size_y), bindings = bindings, position = (x_pos, y_pos)} : info) x_rel y_rel : info =
        let val x = x_pos + x_rel
            val y = y_pos + y_rel
        in place (Size(size_x, size_y)) (x, y) bindings end in
        (case d of
            north => interpret (move inf 0 value) stmtList
            | south => interpret (move inf 0 (~value)) stmtList
            | east => interpret (move inf value 0) stmtList
            | west => interpret (move inf (~value) 0) stmtList
        ) end
| interpret inf (Assignment(Increment(i))::stmtList) =
    let val value = (lookup i (#bindings inf)) + 1
        val lst = change i value (#bindings inf)
        val inf = {grid = #grid inf, bindings = lst, position = #position inf}
    in interpret inf stmtList
    end
| interpret inf (Assignment(Decrement(i))::stmtList) =
    let val value = (lookup i (#bindings inf)) - 1
        val lst = change i value (#bindings inf)
        val inf = {grid = #grid inf, bindings = lst, position = #position inf}
    in interpret inf stmtList
    end
| interpret inf (Loop(stmts, b)::stmtList) = 
    let val res = interpret inf stmts in
        if (evalExp (#bindings inf) (Boolean(b))) = 1 then interpret res stmtList
        else interpret res (Loop(stmts, b)::stmtList) end;
    

(*Run program -> returns position of robot upon termination*)
fun run(Program(Size(x_size, y_size), Robot(decls, Start(xe_pos, ye_pos), stmtList))) : (int * int) =
    let val bindings : assocList = getAssocList decls
        val eval = evalExp bindings
        val x = eval xe_pos
        val y = eval ye_pos
        val start = place (Size(x_size, y_size)) (x, y) bindings
        val result = interpret start stmtList
    in #position result end;