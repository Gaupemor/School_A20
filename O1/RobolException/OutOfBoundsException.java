package RobolException;

//In case robot exits grid

public class OutOfBoundsException extends RobolException {

    public OutOfBoundsException(int x, int y) {
        super(String.format("Robot went out of bounds: [%d %d]", x, y));
    }
}
