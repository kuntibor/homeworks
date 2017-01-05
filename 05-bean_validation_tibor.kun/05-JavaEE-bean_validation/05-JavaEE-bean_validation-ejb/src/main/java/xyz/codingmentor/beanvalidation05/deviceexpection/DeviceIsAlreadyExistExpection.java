package xyz.codingmentor.beanvalidation05.deviceexpection;

/**
 *
 * @author teiep
 */
public class DeviceIsAlreadyExistExpection extends RuntimeException {

    public DeviceIsAlreadyExistExpection(String message) {
        super("ID: " + message);
    }

}
