package Expression;

import java.util.HashMap;

// terminal representation of identifier string

public class IdentifierExp extends Expression {

    String identifierString;

    public IdentifierExp(String identifierString) {
        this.identifierString = identifierString;
    }

    // Returns the integer value assigned to the identifier
    @Override
    public int value(HashMap<String, Integer> v) {
        return v.get(identifierString);
    }

    // Returns the String value of the identifier
    public String getName() {
        return identifierString;
    }
}
