package RobolException;

//In case no Stop statement was reached when running the program.

public class InvalidTerminationException extends RobolException {

    public InvalidTerminationException() {
        super("Program terminated before a Stop statement was reached.");
    }
}
