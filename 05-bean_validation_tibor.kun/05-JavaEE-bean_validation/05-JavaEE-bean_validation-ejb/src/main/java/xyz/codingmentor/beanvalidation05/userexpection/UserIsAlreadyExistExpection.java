package xyz.codingmentor.beanvalidation05.userexpection;

/**
 *
 * @author teiep
 */
public class UserIsAlreadyExistExpection extends RuntimeException {

    public UserIsAlreadyExistExpection() {
        //nothing to initialize
    }

    public UserIsAlreadyExistExpection(String message) {
        super(message);
    }

    public UserIsAlreadyExistExpection(String message, Throwable cause) {
        super(message, cause);
    }

    public UserIsAlreadyExistExpection(Throwable cause) {
        super(cause);
    }

    public UserIsAlreadyExistExpection(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
