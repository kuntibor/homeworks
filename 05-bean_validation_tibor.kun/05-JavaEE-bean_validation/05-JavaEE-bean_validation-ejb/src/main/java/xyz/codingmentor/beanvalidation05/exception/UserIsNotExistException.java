package xyz.codingmentor.beanvalidation05.exception;

/**
 *
 * @author Tibor Kun
 */
public class UserIsNotExistException extends RuntimeException {

    public UserIsNotExistException(String message) {
        super("\n\tUserName: " + message);
    }

}
