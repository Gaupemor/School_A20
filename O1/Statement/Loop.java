package Statement;

import Expression.BooleanExp;
import Robol.Robot;
import RobolException.RobolException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/*
    (* a loop will keep executing statements until a condition is met *)
    <loop>  ::= "do" "{" <stmt>* "}" <until> ;
    <until> ::= "until" <boolean_exp> ;
 */

public class Loop extends Statement {
    List<Statement> statements;
    BooleanExp booleanExp;

    public Loop(BooleanExp condition, Statement ...statements) {
        this.booleanExp = condition;
        this.statements = new ArrayList<>();
        Collections.addAll(this.statements, statements);
    }

    @Override
    public void interpret(Robot robot, HashMap<String, Integer> variables) throws RobolException {
        while(booleanExp.value(variables) != 0) {
            for(Statement s : statements) {
                s.interpret(robot, variables);
            }
        }
    }
}
