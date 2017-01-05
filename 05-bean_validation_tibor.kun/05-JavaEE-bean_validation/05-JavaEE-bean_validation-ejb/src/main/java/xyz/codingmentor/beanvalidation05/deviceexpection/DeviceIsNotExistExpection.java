package xyz.codingmentor.beanvalidation05.deviceexpection;

/**
 *
 * @author teiep
 */
public class DeviceIsNotExistExpection extends RuntimeException {

    public DeviceIsNotExistExpection(String message) {
        super("ID: " + message);
    }

}
