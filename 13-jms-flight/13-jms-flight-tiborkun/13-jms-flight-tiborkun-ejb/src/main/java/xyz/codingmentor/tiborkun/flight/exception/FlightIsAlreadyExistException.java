package xyz.codingmentor.tiborkun.flight.exception;

/**
 *
 * @author Tibor Kun
 */
public class FlightIsAlreadyExistException  extends RuntimeException{

    public FlightIsAlreadyExistException(String message) {
        super(message);
    }

}
