package Expression;

import RobolException.RobolException;

import java.util.HashMap;

/*
    (* expressions; number is an integer, identifier is a string of
       letters and numbers, starting with a letter *)
    <exp> ::= <identifier>      IdentifierExp
            | <number>          NumberExp
            | "(" <exp> ")"     Expression (nested)
            | <arithmetic_exp>  ArithmeticExp, ArithmeticOp
            | <boolean_exp> ;   BooleanExp, BooleanOp
 */

public abstract class Expression {

    public abstract int value(HashMap<String, Integer> v) throws RobolException;

}
