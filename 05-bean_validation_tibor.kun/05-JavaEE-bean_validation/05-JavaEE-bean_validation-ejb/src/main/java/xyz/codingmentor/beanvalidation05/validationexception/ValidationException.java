package xyz.codingmentor.beanvalidation05.validationexception;

/**
 *
 * @author teiep
 */
public class ValidationException extends RuntimeException {

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException() {
        super();
    }

}
