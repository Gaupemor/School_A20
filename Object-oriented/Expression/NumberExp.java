package Expression;

import java.util.HashMap;

// terminal representation integer

public class NumberExp extends Expression {

    int numberValue;

    public NumberExp(int numberValue) {
        this.numberValue = numberValue;
    }

    @Override
    public int value(HashMap<String, Integer> v) {
        return numberValue;
    }
}
