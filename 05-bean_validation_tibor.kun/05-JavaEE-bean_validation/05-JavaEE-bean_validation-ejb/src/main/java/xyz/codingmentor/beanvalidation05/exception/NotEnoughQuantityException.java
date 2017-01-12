package xyz.codingmentor.beanvalidation05.exception;

/**
 *
 * @author Tibor Kun
 */
public class NotEnoughQuantityException extends RuntimeException {

    public NotEnoughQuantityException(String message) {
        super("\n\tID: " + message);
    }

}
