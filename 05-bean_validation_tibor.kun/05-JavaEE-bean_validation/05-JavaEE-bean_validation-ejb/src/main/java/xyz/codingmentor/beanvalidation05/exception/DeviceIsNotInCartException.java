package xyz.codingmentor.beanvalidation05.exception;

/**
 *
 * @author Tibor Kun
 */
public class DeviceIsNotInCartException extends RuntimeException {

    public DeviceIsNotInCartException(String message) {
        super("\n\tID: " + message);
    }

}
