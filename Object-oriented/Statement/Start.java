package Statement;

import Expression.Expression;
import Robol.Robot;
import RobolException.RobolException;

import java.util.HashMap;

//  <start> ::= "start" "(" <exp> "," <exp> ")" ;

public class Start extends Statement {
    Expression left;
    Expression right;

    public Start(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public void interpret(Robot robot, HashMap<String,Integer> variables) throws RobolException {
       robot.place(left.value(variables), right.value(variables));
    }
}
