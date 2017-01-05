package xyz.codingmentor.beanvalidation05.userexpection;

/**
 *
 * @author teiep
 */
public class UserDBIsEmptyExpection extends RuntimeException {

    public UserDBIsEmptyExpection() {
        //nothing to initialize
    }

    public UserDBIsEmptyExpection(String message) {
        super(message);
    }

    public UserDBIsEmptyExpection(String message, Throwable cause) {
        super(message, cause);
    }

    public UserDBIsEmptyExpection(Throwable cause) {
        super(cause);
    }

    public UserDBIsEmptyExpection(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
