package xyz.codingmentor.beanvalidation05.service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.interceptor.ExcludeClassInterceptors;
import xyz.codingmentor.beanvalidation05.beans.UserEntity;
import xyz.codingmentor.beanvalidation05.database.UserDB;
import xyz.codingmentor.beanvalidation05.interceptor.BeanValidation;

/**
 *
 * @author Tibor Kun
 */
@BeanValidation
public class UserDBService {

  private static final Logger LOGGER = Logger.getLogger(UserDBService.class.getName());

    @ExcludeClassInterceptors
    public UserEntity addUser(UserEntity user) {
        LOGGER.log(Level.INFO, "\n\tAdded user: {0}", user.getUsername());
        return UserDB.INSTANCE.addUser(user);
    }

    public UserEntity getUser(String username) {
        return UserDB.INSTANCE.getUser(username);
    }

    public boolean authenticate(String username, String password) {
        return UserDB.INSTANCE.authenticate(username, password);
    }

    public UserEntity modifyUser(UserEntity user) {
        LOGGER.log(Level.INFO, "\n\tMidified user: {0}", user.getUsername());
        return UserDB.INSTANCE.modifyUser(user);
    }

    public UserEntity deleteUser(UserEntity user) {
        LOGGER.log(Level.INFO, "\n\tDeleted user: {0}", user.getUsername());
        return UserDB.INSTANCE.deleteUser(user);
    }

    public List<UserEntity> getAllUser() {
        return UserDB.INSTANCE.getAllUser();
    }
}
