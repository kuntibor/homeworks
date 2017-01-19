package xyz.codingmentor.tiborkun.webshop.exceptions;

/**
 *
 * @author Tibor Kun
 */
public class DeviceIsAlreadyExistException extends RuntimeException {

    public DeviceIsAlreadyExistException(String message) {
        super("ID: " + message);
    }

}
