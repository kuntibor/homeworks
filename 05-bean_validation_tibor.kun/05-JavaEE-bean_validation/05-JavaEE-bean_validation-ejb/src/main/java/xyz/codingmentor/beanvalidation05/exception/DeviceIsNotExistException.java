package xyz.codingmentor.beanvalidation05.exception;

/**
 *
 * @author teiep
 */
public class DeviceIsNotExistException extends RuntimeException {

    public DeviceIsNotExistException(String message) {
        super("ID: " + message);
    }

}
