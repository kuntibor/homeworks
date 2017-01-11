package xyz.codingmentor.beanvalidation05.exception;

/**
 *
 * @author Tibor Kun
 */
public class DeviceIsAlreadyInCartException extends RuntimeException {

    public DeviceIsAlreadyInCartException(String message) {
        super("\n\tID: " +message);
    }

}
