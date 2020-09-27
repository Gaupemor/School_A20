package Statement;

import Robol.Robot;
import RobolException.RobolException;

import java.util.HashMap;

/*
    (* statements control the robot's movement *)
    <stmt> ::= <stop>       Stop
             | <move> <exp> Move, Direction
             | <assignment> Assignment, AssignmentOp
             | <loop> ;     Loop

    Also contains Start and Binding as statements.
 */

public abstract class Statement {

    public abstract void interpret(Robot robot, HashMap<String, Integer> variables) throws RobolException;

}
