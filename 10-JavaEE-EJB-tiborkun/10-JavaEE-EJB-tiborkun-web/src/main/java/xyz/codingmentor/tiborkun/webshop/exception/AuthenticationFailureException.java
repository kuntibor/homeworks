package xyz.codingmentor.tiborkun.webshop.exception;

/**
 *
 * @author Tibor Kun
 */
public class AuthenticationFailureException extends RuntimeException {

    public AuthenticationFailureException(String message) {
        super("Wrong username or password: " + message);
    }

}
