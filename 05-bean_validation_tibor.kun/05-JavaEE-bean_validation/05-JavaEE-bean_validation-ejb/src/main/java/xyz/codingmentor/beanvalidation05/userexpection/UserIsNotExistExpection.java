package xyz.codingmentor.beanvalidation05.userexpection;

/**
 *
 * @author teiep
 */
public class UserIsNotExistExpection extends RuntimeException {

    public UserIsNotExistExpection() {
        //nothing to initialize
    }

    public UserIsNotExistExpection(String message) {
        super(message);
    }

    public UserIsNotExistExpection(String message, Throwable cause) {
        super(message, cause);
    }

    public UserIsNotExistExpection(Throwable cause) {
        super(cause);
    }

    public UserIsNotExistExpection(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
