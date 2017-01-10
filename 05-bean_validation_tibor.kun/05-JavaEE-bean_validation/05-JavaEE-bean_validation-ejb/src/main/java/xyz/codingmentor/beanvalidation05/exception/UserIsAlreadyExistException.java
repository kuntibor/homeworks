package xyz.codingmentor.beanvalidation05.exception;

/**
 *
 * @author teiep
 */
public class UserIsAlreadyExistException extends RuntimeException {

    public UserIsAlreadyExistException(String message) {
        super("Username: " + message);
    }

}
