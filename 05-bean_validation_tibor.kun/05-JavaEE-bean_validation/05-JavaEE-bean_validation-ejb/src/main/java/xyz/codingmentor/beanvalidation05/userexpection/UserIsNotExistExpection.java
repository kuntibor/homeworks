package xyz.codingmentor.beanvalidation05.userexpection;

/**
 *
 * @author teiep
 */
public class UserIsNotExistExpection extends RuntimeException {

    public UserIsNotExistExpection(String message) {
        super("UserName: " + message);
    }

}
