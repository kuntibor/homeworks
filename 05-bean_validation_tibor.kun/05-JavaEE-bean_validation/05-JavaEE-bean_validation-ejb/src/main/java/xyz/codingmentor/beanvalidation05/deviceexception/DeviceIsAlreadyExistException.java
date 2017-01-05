package xyz.codingmentor.beanvalidation05.deviceexception;

/**
 *
 * @author teiep
 */
public class DeviceIsAlreadyExistException extends RuntimeException {

    public DeviceIsAlreadyExistException(String message) {
        super("ID: " + message);
    }

}
