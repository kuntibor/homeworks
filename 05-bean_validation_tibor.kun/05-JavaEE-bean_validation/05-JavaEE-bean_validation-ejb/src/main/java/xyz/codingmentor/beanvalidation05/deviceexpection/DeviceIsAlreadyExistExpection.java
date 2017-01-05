package xyz.codingmentor.beanvalidation05.deviceexpection;

/**
 *
 * @author teiep
 */
public class DeviceIsAlreadyExistExpection extends RuntimeException {

    public DeviceIsAlreadyExistExpection() {
        //nothing to initialize
    }

    public DeviceIsAlreadyExistExpection(String message) {
        super(message);
    }

    public DeviceIsAlreadyExistExpection(String message, Throwable cause) {
        super(message, cause);
    }

    public DeviceIsAlreadyExistExpection(Throwable cause) {
        super(cause);
    }

    public DeviceIsAlreadyExistExpection(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
