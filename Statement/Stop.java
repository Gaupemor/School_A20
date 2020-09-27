package Statement;

import Robol.Robot;

import java.util.HashMap;

//  <stop>  ::= "stop" ;

public class Stop extends Statement {

    @Override
    public void interpret(Robot robot, HashMap<String, Integer> variables) {
        System.out.println(robot.toString());
    }

}
