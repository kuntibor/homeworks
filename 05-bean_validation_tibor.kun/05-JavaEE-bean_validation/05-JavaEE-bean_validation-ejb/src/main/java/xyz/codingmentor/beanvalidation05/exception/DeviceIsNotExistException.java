package xyz.codingmentor.beanvalidation05.exception;

/**
 *
 * @author Tibor Kun
 */
public class DeviceIsNotExistException extends RuntimeException {

    public DeviceIsNotExistException(String message) {
        super("\n\tID: " + message);
    }

}
