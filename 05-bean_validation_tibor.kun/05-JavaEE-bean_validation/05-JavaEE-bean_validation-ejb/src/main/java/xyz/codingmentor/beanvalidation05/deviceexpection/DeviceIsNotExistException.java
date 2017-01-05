package xyz.codingmentor.beanvalidation05.deviceexpection;

/**
 *
 * @author teiep
 */
public class DeviceIsNotExistException extends RuntimeException {

    public DeviceIsNotExistException(String message) {
        super("ID: " + message);
    }

}
