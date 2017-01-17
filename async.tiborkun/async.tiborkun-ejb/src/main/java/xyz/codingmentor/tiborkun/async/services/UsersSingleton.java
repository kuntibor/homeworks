package xyz.codingmentor.tiborkun.async.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Singleton;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Future;
import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import org.jboss.logging.Logger;
import xyz.codingmentor.tiborkun.async.entities.UserEntity;

/**
 *
 * @author Tibor Kun
 */
@Singleton
public class UsersSingleton {

    private final Map<String, UserEntity> users = new HashMap<>();
    private List<UserEntity> userListFromJson;
    private final ObjectMapper mapper = new ObjectMapper();
    private final JavaType userType = mapper.getTypeFactory().constructCollectionType(List.class, UserEntity.class);
    private static final Logger LOGGER = Logger.getLogger(UsersSingleton.class.getName());

    public UserEntity addUser(UserEntity user) {
        if (!users.containsKey(user.getUsername())) {
            users.put(user.getUsername(), user);
            return user;
        }
        throw new IllegalArgumentException();
    }

    public Map<String, UserEntity> getUsers() {
        LOGGER.info("\n\tGet all user");
        return users;
    }

    @Asynchronous
    public void loadUsersFromJson() {
        LOGGER.info("\n\tLoading users from json...");
        try {
            userListFromJson = mapper.readValue(new File(getClass().getClassLoader().getResource("users.json").getFile()), userType);
        } catch (IOException ex) {
            LOGGER.error(ex);
        }
        for (UserEntity user : userListFromJson) {
            LOGGER.info("\n\tAdd new user:" + user.getUsername());
            addUser(user);
            waiting();
        }
        LOGGER.info("\n\tLoading complete");
    }

    @Asynchronous
    public Future<List<UserEntity>> getMatchedUsers(String match) {
        List<UserEntity> matchedUsers = new ArrayList<>();
        LOGGER.info("\n\tSearch matching users...");
        for (Map.Entry<String, UserEntity> entry : users.entrySet()) {
            if (entry.getValue().getUsername().contains(match)) {
                matchedUsers.add(entry.getValue());
                LOGGER.info("\n\tMatched user: " + entry.getValue().getUsername());
                waiting();
            }
        }
        LOGGER.info("\n\tSearching complete");
        return new AsyncResult<>(matchedUsers);
    }

    public void waiting() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            LOGGER.error(ex);
        }
    }

}
