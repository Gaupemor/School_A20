import Robol.*;
import RobolException.RobolException;
import Expression.*;
import Statement.*;

import java.util.ArrayList;

class TestCode {

    void runProgram1() throws RobolException {
        ArrayList<Statement> statements = new ArrayList<>();
        ArrayList<Binding> bindings = new ArrayList<>();

        Grid grid = new Grid(new NumberExp(64), new NumberExp(64));

        Start start = new Start(new NumberExp(23), new NumberExp(30));

        statements.add(new Move(Direction.west, new NumberExp(15)));
        statements.add(new Move(Direction.south, new NumberExp(15)));
        statements.add(new Move(Direction.east, new ArithmeticExp(ArithmeticOp.plus,
                new NumberExp(2), new NumberExp(3))));
        statements.add(new Move(Direction.north, new ArithmeticExp(ArithmeticOp.plus,
                new NumberExp(17), new NumberExp(20))));
        statements.add(new Stop());

        runProgram(grid, bindings, start, statements); //Expected: 13, 52
    }

    void runProgram2() throws RobolException {
        ArrayList<Statement> statements = new ArrayList<>();
        ArrayList<Binding> bindings = new ArrayList<>();

        Grid grid = new Grid(new NumberExp(64), new NumberExp(64));

        bindings.add(new Binding(new IdentifierExp("i"), new NumberExp(5))); //IDENTIFIER?
        bindings.add(new Binding(new IdentifierExp("j"), new NumberExp(3))); //IDENTIFIER?

        Start start = new Start(new NumberExp(23), new NumberExp(6));

        statements.add(new Move(Direction.north, new ArithmeticExp(ArithmeticOp.multiply,
                new NumberExp(3), new IdentifierExp("i"))));
        statements.add(new Move(Direction.east, new NumberExp(15)));
        statements.add(new Move(Direction.south, new ArithmeticExp(ArithmeticOp.minus,
                new NumberExp(12), new IdentifierExp("i"), new IdentifierExp("j"))));
        statements.add(new Move(Direction.west, new ArithmeticExp(ArithmeticOp.plus,
                new ArithmeticExp(ArithmeticOp.multiply, new NumberExp(2), new IdentifierExp("i")),
                new ArithmeticExp(ArithmeticOp.multiply, new NumberExp(3), new IdentifierExp("j")),
                new NumberExp(1))));
        statements.add(new Stop());

        runProgram(grid, bindings, start, statements); //Expected 18, 17
    }
    void runProgram3() throws RobolException {
        ArrayList<Statement> statements = new ArrayList<>();
        ArrayList<Binding> bindings = new ArrayList<>();

        Grid grid = new Grid(new NumberExp(64), new NumberExp(64));

        bindings.add(new Binding(new IdentifierExp("i"), new NumberExp(5)));
        bindings.add(new Binding(new IdentifierExp("j"), new NumberExp(3)));

        Start start = new Start(new NumberExp(23), new NumberExp(6));

        statements.add(new Move(Direction.north, new ArithmeticExp(ArithmeticOp.multiply,
                new NumberExp(3), new IdentifierExp("i"))));
        statements.add(new Move(Direction.west, new NumberExp(15)));
        statements.add(new Move(Direction.east, new NumberExp(4)));
        statements.add(new Loop(new BooleanExp(BooleanOp.lessThan, new IdentifierExp("j"), new NumberExp(1)),
                new Move(Direction.south, new IdentifierExp("j")),
                new Assignment(new IdentifierExp("j"), AssignmentOp.decrement)));
        statements.add(new Stop());

        runProgram(grid, bindings, start, statements); //Expected 12, 15
    }
    void runProgram4() {
        ArrayList<Statement> statements = new ArrayList<>();
        ArrayList<Binding> bindings = new ArrayList<>();

        Grid grid = new Grid(new NumberExp(64), new NumberExp(64));

        bindings.add(new Binding(new IdentifierExp("i"), new NumberExp(8)));

        Start start = new Start(new NumberExp(1), new NumberExp(1));

        statements.add(new Loop(new BooleanExp(BooleanOp.greaterThan, new IdentifierExp("i"), new NumberExp(100)),
                new Move(Direction.north, new IdentifierExp("i"))));
        statements.add(new Stop());

        try {
            runProgram(grid, bindings, start, statements); //Expected: OutOfBoundsException
        } catch (RobolException e) {
            System.out.printf("Caught error:\n" +
                    "   %s\n   %s", e.getClass().toString(), e.getMessage());
        }
    }
    void runAll() throws RobolException {
        runProgram1();
        runProgram2();
        runProgram3();
        runProgram4();
    }

    void runProgram(Grid grid, ArrayList<Binding> bindings, Start start, ArrayList<Statement> statements) throws RobolException {

        //Set up robot and program
        Robot robot = new Robot(grid, bindings, start, statements);
        Program program = new Program(grid, robot);

        //Run program
        program.run();
    }
}