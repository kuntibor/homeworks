package xyz.codingmentor.beanvalidation05.deviceexpection;

/**
 *
 * @author teiep
 */
public class DeviceDBIsEmptyExpection extends RuntimeException {

    public DeviceDBIsEmptyExpection() {
        //nothing to initialize
    }

    public DeviceDBIsEmptyExpection(String message) {
        super(message);
    }

    public DeviceDBIsEmptyExpection(String message, Throwable cause) {
        super(message, cause);
    }

    public DeviceDBIsEmptyExpection(Throwable cause) {
        super(cause);
    }

    public DeviceDBIsEmptyExpection(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
