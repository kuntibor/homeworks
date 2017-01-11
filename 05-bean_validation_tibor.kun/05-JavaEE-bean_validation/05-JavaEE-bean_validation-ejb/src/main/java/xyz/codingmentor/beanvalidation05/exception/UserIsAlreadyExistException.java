package xyz.codingmentor.beanvalidation05.exception;

/**
 *
 * @author Tibor Kun
 */
public class UserIsAlreadyExistException extends RuntimeException {

    public UserIsAlreadyExistException(String message) {
        super("\n\tUsername: " + message);
    }

}
