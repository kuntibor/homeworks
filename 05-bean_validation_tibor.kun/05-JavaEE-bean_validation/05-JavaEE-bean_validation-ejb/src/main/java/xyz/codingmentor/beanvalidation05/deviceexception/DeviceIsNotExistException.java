package xyz.codingmentor.beanvalidation05.deviceexception;

/**
 *
 * @author teiep
 */
public class DeviceIsNotExistException extends RuntimeException {

    public DeviceIsNotExistException(String message) {
        super("ID: " + message);
    }

}
