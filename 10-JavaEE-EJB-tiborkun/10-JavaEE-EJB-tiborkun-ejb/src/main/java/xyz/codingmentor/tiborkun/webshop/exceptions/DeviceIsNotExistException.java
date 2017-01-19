package xyz.codingmentor.tiborkun.webshop.exceptions;

/**
 *
 * @author Tibor Kun
 */
public class DeviceIsNotExistException extends RuntimeException {

    public DeviceIsNotExistException(String message) {
        super("ID: " + message);
    }

}
