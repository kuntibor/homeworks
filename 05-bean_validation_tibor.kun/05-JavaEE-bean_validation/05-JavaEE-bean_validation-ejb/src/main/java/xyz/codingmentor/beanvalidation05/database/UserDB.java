package xyz.codingmentor.beanvalidation05.database;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import xyz.codingmentor.beanvalidation05.beans.UserEntity;
import xyz.codingmentor.beanvalidation05.exception.UserIsAlreadyExistException;
import xyz.codingmentor.beanvalidation05.exception.UserIsNotExistException;

/**
 *
 * @author Tibor Kun
 */
public enum UserDB {
    INSTANCE;

    private final Map<String, UserEntity> users = new HashMap<>();

    public UserEntity addUser(UserEntity user) {
        if (!users.containsKey(user.getUsername())) {
            user.setRegistrationDate(Calendar.getInstance().getTime());
            user.setLastModifiedDate(user.getRegistrationDate());
            users.put(user.getUsername(), user);
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
            return users.get(user.getUsername());
        }
        throw new UserIsNotExistException(user.getUsername());
    }

    public UserEntity deleteUser(UserEntity user) {
        if (users.containsKey(user.getUsername())) {
            return users.remove(user.getUsername());
        }
        throw new UserIsNotExistException(user.getUsername());
    }

    public List<UserEntity> getAllUser() {
        return new ArrayList<>(users.values());
    }

}
