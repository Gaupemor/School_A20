package RobolException;

//In case an invalid operator was passed

public class NoSuchOperatorException extends RobolException {

    public NoSuchOperatorException(Enum e) {
        super(String.format("Passed an invalid operator: %s", e.name()));
    }
}
