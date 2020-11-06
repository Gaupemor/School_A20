package RobolException;

//In case an unexpected identifier string was passed

public class NoSuchIdentifierException extends RobolException {

    public NoSuchIdentifierException(String identifierString) {
        super("Tried to call an uninitialised variable: " + identifierString);
    }
}
