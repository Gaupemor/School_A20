package Expression;

import RobolException.NoSuchOperatorException;
import RobolException.RobolException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

//  <arithmetic_exp> ::= "(" <arithmetic_op> <args> ")" ;

public class ArithmeticExp extends Expression {

    ArithmeticOp operator;
    ArrayList<Expression> expressions; //Replaced <args> with java vararg functionality

    public ArithmeticExp(ArithmeticOp operator, Expression exp1, Expression exp2, Expression ...expressions) {
        this.operator = operator;
        this.expressions = new ArrayList<>();
        this.expressions.add(exp1);
        this.expressions.add(exp2);
        Collections.addAll(this.expressions, expressions);
    }

    @Override
    public int value(HashMap<String, Integer> v) throws RobolException {
        int result;

        switch (operator) {
            case plus :
                result = 0;
                for (Expression e : expressions) result += e.value(v);
                break;
            case minus:
                result = expressions.get(0).value(v);
                expressions.remove(0);
                for (Expression e : expressions) result -= e.value(v);
                break;
            case multiply:
                result = 1;
                for (Expression e : expressions) result *= e.value(v);
                break;
            default:
                throw new NoSuchOperatorException(operator);
        }

        return result;
    }
}
