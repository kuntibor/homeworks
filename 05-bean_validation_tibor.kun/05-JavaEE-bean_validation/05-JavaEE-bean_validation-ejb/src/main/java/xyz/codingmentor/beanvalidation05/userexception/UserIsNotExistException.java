package xyz.codingmentor.beanvalidation05.userexception;

/**
 *
 * @author teiep
 */
public class UserIsNotExistException extends RuntimeException {

    public UserIsNotExistException(String message) {
        super("UserName: " + message);
    }

}
