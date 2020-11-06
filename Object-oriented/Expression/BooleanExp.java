package Expression;

import RobolException.NoSuchOperatorException;
import RobolException.RobolException;

import java.util.HashMap;

//  <boolean_exp>    ::= "(" <boolean_op> <exp> <exp> ")" ;

public class BooleanExp extends Expression {

    BooleanOp booleanOp;
    Expression left;
    Expression right;

    public BooleanExp(BooleanOp booleanOp, Expression left, Expression right) {
        this.booleanOp = booleanOp;
        this.left = left;
        this.right = right;
    }

    //Boolean value represented by int [0, 1]
    @Override
    public int value(HashMap<String, Integer> v) throws RobolException {
        int result = 1;

        switch (booleanOp) {
            case lessThan:
                if (left.value(v) < right.value(v)) result = 0;
                break;
            case greaterThan:
                if (left.value(v) > right.value(v)) result = 0;
                break;
            case equals:
                if (left.value(v) == right.value(v)) result = 0;
                break;
            default:
                throw new NoSuchOperatorException(booleanOp);
        }

        return result;
    }
}
