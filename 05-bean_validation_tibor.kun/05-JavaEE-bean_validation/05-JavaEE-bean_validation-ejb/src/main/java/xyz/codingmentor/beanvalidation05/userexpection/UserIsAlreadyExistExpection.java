package xyz.codingmentor.beanvalidation05.userexpection;

/**
 *
 * @author teiep
 */
public class UserIsAlreadyExistExpection extends RuntimeException {

    public UserIsAlreadyExistExpection(String message) {
        super("Username: " + message);
    }

}
