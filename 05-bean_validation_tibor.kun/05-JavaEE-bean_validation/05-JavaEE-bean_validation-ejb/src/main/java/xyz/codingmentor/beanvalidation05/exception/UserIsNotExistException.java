package xyz.codingmentor.beanvalidation05.exception;

/**
 *
 * @author teiep
 */
public class UserIsNotExistException extends RuntimeException {

    public UserIsNotExistException(String message) {
        super("UserName: " + message);
    }

}
