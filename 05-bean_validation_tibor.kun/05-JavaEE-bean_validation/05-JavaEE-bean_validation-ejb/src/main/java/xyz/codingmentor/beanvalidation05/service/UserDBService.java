package xyz.codingmentor.beanvalidation05.service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.interceptor.ExcludeClassInterceptors;
import javax.interceptor.Interceptors;
import xyz.codingmentor.beanvalidation05.beans.UserEntity;
import xyz.codingmentor.beanvalidation05.database.UserDB;
import xyz.codingmentor.beanvalidation05.interceptor.ValidatorInterceptor;

/**
 *
 * @author Tibor Kun
 */
@Interceptors(ValidatorInterceptor.class)
public class UserDBService {

    private static final Logger LOGGER = Logger.getLogger(UserDBService.class.getName());
    private UserEntity returnedUser;

    @ExcludeClassInterceptors
    public UserEntity addUser(UserEntity user) {
        returnedUser = UserDB.INSTANCE.addUser(user);
        if (null != returnedUser) {
            LOGGER.log(Level.INFO, "\n\tAdded user: {0}", user.getUsername());
        }
        return returnedUser;
    }

    public UserEntity getUser(String username) {
        return UserDB.INSTANCE.getUser(username);
    }

    public boolean authenticate(String username, String password) {
        return UserDB.INSTANCE.authenticate(username, password);
    }

    public UserEntity modifyUser(UserEntity user) {
        returnedUser = UserDB.INSTANCE.modifyUser(user);
        if (null != returnedUser) {
            LOGGER.log(Level.INFO, "\n\tModified user: {0}", user.getUsername());
        }
        return returnedUser;
    }

    public UserEntity deleteUser(UserEntity user) {
        returnedUser = UserDB.INSTANCE.deleteUser(user);
        if (null != returnedUser) {
            LOGGER.log(Level.INFO, "\n\tDeleted user: {0}", user.getUsername());
        }
        return returnedUser;
    }

    public List<UserEntity> getAllUser() {
        return UserDB.INSTANCE.getAllUser();
    }
}
