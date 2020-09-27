package Statement;

import Expression.Expression;
import Robol.Robot;
import RobolException.RobolException;
import RobolException.NoSuchOperatorException;

import java.util.HashMap;

/*
    stmt ::= <move> <exp> | [...]
    <move> ::= "north" | "south" | "east" | "west" ;
 */

public class Move extends Statement {
    Direction direction;
    Expression expression;

    public Move(Direction direction, Expression expression) {
        this.direction = direction;
        this.expression = expression;
    }

    @Override
    public void interpret(Robot robot, HashMap<String, Integer> variables) throws RobolException {
        int x = 0;
        int y = 0;

        switch (direction) {
            case north:
                y += expression.value(variables);
                break;
            case south:
                y -= expression.value(variables);
                break;
            case east:
                x += expression.value(variables);
                break;
            case west:
                x -= expression.value(variables);
                break;
            default:
                throw new NoSuchOperatorException(direction);
        }

        robot.move(x, y);
    }
}
