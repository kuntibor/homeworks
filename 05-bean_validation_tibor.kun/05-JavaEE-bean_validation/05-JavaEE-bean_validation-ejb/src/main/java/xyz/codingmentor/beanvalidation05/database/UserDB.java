package xyz.codingmentor.beanvalidation05.database;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import xyz.codingmentor.beanvalidation05.beans.UserEntity;
import xyz.codingmentor.beanvalidation05.userexpection.UserDBIsEmptyExpection;
import xyz.codingmentor.beanvalidation05.userexpection.UserIsAlreadyExistExpection;
import xyz.codingmentor.beanvalidation05.userexpection.UserIsNotExistExpection;

/**
 *
 * @author teiep
 */
public enum UserDB {
    INSTANCE;

    private static final Map<String, UserEntity> users = new HashMap<>();

    public UserEntity addUser(UserEntity user) {
        if (!users.containsKey(user.getUsername())) {
            user.setRegistrationDate(Calendar.getInstance().getTime());
            user.setLastModifiedDate(user.getRegistrationDate());
            users.put(user.getUsername(), user);
            return users.get(user.getUsername());
        }
        throw new UserIsAlreadyExistExpection();
    }

    public UserEntity getUser(String username) {
        if (users.containsKey(username)) {
            return users.get(username);
        }
        throw new UserIsNotExistExpection();
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
        throw new UserIsNotExistExpection();
    }

    public UserEntity deleteUser(UserEntity user) {
        if (users.containsKey(user.getUsername())) {
            return users.remove(user.getUsername());
        }
        throw new UserIsNotExistExpection();
    }

    public List<UserEntity> getAllUser() {
        if (!users.isEmpty()) {
            return new ArrayList<>(users.values());
        }
        throw new UserDBIsEmptyExpection();
    }

}
