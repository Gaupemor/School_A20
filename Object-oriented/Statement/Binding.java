package Statement;

import Expression.Expression;
import Expression.IdentifierExp;
import Robol.Robot;
import RobolException.RobolException;

import java.util.HashMap;

/*
    (* a variable binding consists of a name and an initial value *)
    <binding> ::= "let" <identifier> "=" <exp> ;
 */

public class Binding extends Statement {

    IdentifierExp identifier;
    Expression expression;

    public Binding(IdentifierExp identifier, Expression expression) {
        this.identifier = identifier;
        this.expression = expression;
    }

    @Override
    public void interpret(Robot robot, HashMap<String, Integer> variable) throws RobolException {
        variable.put(identifier.getName(), expression.value(variable));
    }
}
