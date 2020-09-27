package Statement;

import Expression.IdentifierExp;
import RobolException.NoSuchIdentifierException;
import Robol.Robot;
import RobolException.NoSuchOperatorException;

import java.util.HashMap;

//  <assignment> ::= <identifier> "++" | <identifier> "--" ;

public class Assignment extends Statement {

    IdentifierExp identifier;
    AssignmentOp assignmentOp;

    public Assignment(IdentifierExp identifier, AssignmentOp assignmentOp) {
        this.identifier = identifier;
        this.assignmentOp = assignmentOp;
    }


    @Override
    public void interpret(Robot robot, HashMap<String, Integer> variable) throws NoSuchIdentifierException, NoSuchOperatorException {
        if(!variable.containsKey(identifier.getName())) {
            throw new NoSuchIdentifierException(identifier.getName());
        }

        switch (assignmentOp) {
            case increment:
                variable.put(identifier.getName(), variable.get(identifier.getName()) + 1); break;
            case decrement:
                variable.put(identifier.getName(), variable.get(identifier.getName()) - 1); break;
            default:
                throw new NoSuchOperatorException(assignmentOp);
        }
    }
}
