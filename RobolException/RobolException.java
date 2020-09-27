package RobolException;

//Abstract exception class: all exceptions thrown by Robol inherits RobolException

public abstract class RobolException extends Exception {

    public RobolException(String message) {
        super(message);
    }
}
