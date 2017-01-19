package xyz.codingmentor.tiborkun.webshop.exceptions;

/**
 *
 * @author Tibor Kun
 */
public class UserIsNotExistException extends RuntimeException {

    public UserIsNotExistException(String message) {
        super("Username: " + message);
    }

}
