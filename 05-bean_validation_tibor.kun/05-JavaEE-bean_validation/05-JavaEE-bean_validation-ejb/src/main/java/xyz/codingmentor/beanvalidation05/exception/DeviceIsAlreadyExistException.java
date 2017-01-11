package xyz.codingmentor.beanvalidation05.exception;

/**
 *
 * @author Tibor Kun
 */
public class DeviceIsAlreadyExistException extends RuntimeException {

    public DeviceIsAlreadyExistException(String message) {
        super("\n\tID: " + message);
    }

}
