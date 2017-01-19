package xyz.codingmentor.tiborkun.webshop.exceptions;

/**
 *
 * @author Tibor Kun
 */
public class UserIsAlreadyExistException extends RuntimeException {

    public UserIsAlreadyExistException(String message) {
        super("Username: " + message);
    }

}
