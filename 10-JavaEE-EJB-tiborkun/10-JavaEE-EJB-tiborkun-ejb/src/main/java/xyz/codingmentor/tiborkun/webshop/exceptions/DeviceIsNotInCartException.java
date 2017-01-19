package xyz.codingmentor.tiborkun.webshop.exceptions;

/**
 *
 * @author Tibor Kun
 */
public class DeviceIsNotInCartException extends RuntimeException {

    public DeviceIsNotInCartException(String message) {
        super("ID: " + message);
    }

}
