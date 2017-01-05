package xyz.codingmentor.beanvalidation05.userexpection;

/**
 *
 * @author teiep
 */
public class UserIsAlreadyExistException extends RuntimeException {

    public UserIsAlreadyExistException(String message) {
        super("Username: " + message);
    }

}
