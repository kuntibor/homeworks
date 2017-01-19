package xyz.codingmentor.tiborkun.webshop.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Singleton;
import javax.interceptor.ExcludeClassInterceptors;
import javax.interceptor.Interceptors;
import java.util.logging.Level;
import java.util.logging.Logger;
import xyz.codingmentor.tiborkun.webshop.entities.UserEntity;
import xyz.codingmentor.tiborkun.webshop.exceptions.UserIsAlreadyExistException;
import xyz.codingmentor.tiborkun.webshop.exceptions.UserIsNotExistException;
import xyz.codingmentor.tiborkun.webshop.interceptors.ValidatorInterceptor;

/**
 *
 * @author Tibor Kun
 */
@Singleton
@Interceptors(ValidatorInterceptor.class)
public class UserDBSingleton implements Serializable {

    private final Map<String, UserEntity> users = new HashMap<>();
    private static final Logger LOGGER = Logger.getLogger(UserDBSingleton.class.getName());

    @ExcludeClassInterceptors
    public UserEntity addUser(UserEntity user) {
        if (!users.containsKey(user.getUsername())) {
            user.setRegistrationDate(Calendar.getInstance().getTime());
            user.setLastModifiedDate(user.getRegistrationDate());
            users.put(user.getUsername(), user);
            LOGGER.log(Level.INFO, "Added user: {0}", user);
            return users.get(user.getUsername());
        }
        throw new UserIsAlreadyExistException(user.getUsername());
    }

    public UserEntity getUser(String username) {
        if (users.containsKey(username)) {
            return users.get(username);
        }
        throw new UserIsNotExistException(username);
    }

    public boolean authenticate(String username, String password) {
        if (users.containsKey(username)) {
            return users.get(username).getPassword().equals(password);
        }
        return false;
    }

    public UserEntity modifyUser(UserEntity user) {
        if (users.containsKey(user.getUsername())) {
            user.setLastModifiedDate(Calendar.getInstance().getTime());
            users.put(user.getUsername(), user);
            LOGGER.log(Level.INFO, "Modified user: {0}", user);
            return users.get(user.getUsername());
        }
        throw new UserIsNotExistException(user.getUsername());
    }

    public UserEntity deleteUser(UserEntity user) {
        if (users.containsKey(user.getUsername())) {
            LOGGER.log(Level.INFO, "Deleted user: {0}", user);
            return users.remove(user.getUsername());
        }
        throw new UserIsNotExistException(user.getUsername());
    }

    @ExcludeClassInterceptors
    public List<UserEntity> getAllUser() {
        return new ArrayList<>(users.values());
    }

}
