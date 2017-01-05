package xyz.codingmentor.beanvalidation05.deviceexpection;

/**
 *
 * @author teiep
 */
public class DeviceIsNotExistExpection extends RuntimeException {

    public DeviceIsNotExistExpection() {
        //nothing to initialize
    }

    public DeviceIsNotExistExpection(String message) {
        super(message);
    }

    public DeviceIsNotExistExpection(String message, Throwable cause) {
        super(message, cause);
    }

    public DeviceIsNotExistExpection(Throwable cause) {
        super(cause);
    }

    public DeviceIsNotExistExpection(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
