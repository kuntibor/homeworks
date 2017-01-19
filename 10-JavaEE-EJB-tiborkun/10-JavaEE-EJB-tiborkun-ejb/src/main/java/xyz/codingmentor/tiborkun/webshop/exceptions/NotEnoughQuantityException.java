package xyz.codingmentor.tiborkun.webshop.exceptions;

/**
 *
 * @author Tibor Kun
 */
public class NotEnoughQuantityException extends RuntimeException {

    public NotEnoughQuantityException(String message) {
        super("ID: " + message);
    }

}
