package Robol;

import RobolException.RobolException;

/*
    (* a program consists of a robot, and a grid on which it can move around *)
    <program> ::= <grid> <robot> ;
 */

public class Program {

    private final Grid grid; //Not used by Program in this implementation, but useful to illustrate AST
    private final Robot robot;

    public Program(Grid grid, Robot robot) {
        this.grid = grid;
        this.robot = robot;
    }

    //Runs the program
    public void run() throws RobolException {
        robot.interpret();
    }
}
