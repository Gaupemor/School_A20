package Robol;

import RobolException.InvalidTerminationException;
import RobolException.OutOfBoundsException;
import RobolException.RobolException;
import Statement.Binding;
import Statement.Start;
import Statement.Statement;
import Statement.Stop;

import java.util.ArrayList;
import java.util.HashMap;

/*
    (* the robot has a list of variable bindings, a starting point, and a
    a set of statements that control its movement *)
    <robot> ::= <binding>* <start> <stmt>* ;
 */

public class Robot {

    Grid grid;  //Needs a reference to Grid to check if Robot is out of bounds
    Start start;
    ArrayList<Binding> bindings;
    ArrayList<Statement> statements;
    HashMap<String,Integer> variables = new HashMap<>(); //Variables in program at runtime
    int x, y; //Current coordinates

    public Robot(Grid grid, ArrayList<Binding> bindings, Start start, ArrayList<Statement> statements) {
        this.grid = grid;
        this.start = start;
        this.bindings = bindings;
        this.statements = statements;
    }

    //Places the robot on a coordinate
    public void place(int x, int y) throws OutOfBoundsException {
        this.x = x;
        this.y = y;

        if(grid.outOfBounds(x, y)) throw new OutOfBoundsException(x, y);
    }

    //Moves the robot by given vector
    public void move(int x, int y) throws OutOfBoundsException {
        x += this.x;
        y += this.y;
        place(x, y);
    }

    //Interpret bindings, start statement, and the rest of the program statements until Stop
    public void interpret() throws RobolException {

        //<binding>*
        for(Binding b : bindings) b.interpret(this, variables);

        //<start>
        start.interpret(this, variables);

        //<stmt>*
        for(Statement s : statements) {
            s.interpret(this, variables);
            if(s.getClass() == Stop.class) return;
        }

        //If no Stop statement terminated the program, throw exception.
        throw new InvalidTerminationException();
    }

    //Prints current coordinates
    @Override
    public String toString() {
        return String.format("[%d %d]", x, y);
    }
}
