package xyz.codingmentor.tiborkun.webshop.services;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import xyz.codingmentor.tiborkun.webshop.entities.DeviceEntity;
import xyz.codingmentor.tiborkun.webshop.entities.UserEntity;

/**
 *
 * @author Tibor Kun
 */
@Singleton
@Startup
public class Loader {

    @Inject
    private UserDBSingleton userDBSingleton;
    @Inject
    private DeviceDBSingleton deviceDBSingleton;
    private List<UserEntity> userListFromJson = null;
    private List<DeviceEntity> deviceListFromJson = null;
    private ObjectMapper mapper;
    private JavaType userType;
    private JavaType deviceType;

    private static final Logger LOGGER = Logger.getLogger(Loader.class.getName());

    @PostConstruct
    private void init() {
        LOGGER.info("Loading users and devices...");
        mapper = new ObjectMapper();
        userType = mapper.getTypeFactory().constructCollectionType(List.class, UserEntity.class);
        deviceType = mapper.getTypeFactory().constructCollectionType(List.class, DeviceEntity.class);
        loadEntitiesFromJson();
        fillEntities();
        LOGGER.info("Loading completed");
    }

    private void loadEntitiesFromJson() {
        try {
            userListFromJson = mapper.readValue(new File(getClass().getClassLoader().getResource("users.json").getFile()), userType);
            deviceListFromJson = mapper.readValue(new File(getClass().getClassLoader().getResource("devices.json").getFile()), deviceType);
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    private void fillEntities() {
        userListFromJson.forEach((user) -> {
            userDBSingleton.addUser(user);
        });
        int count;
        for (DeviceEntity device : deviceListFromJson) {
            count = device.getCount();
            deviceDBSingleton.addDevice(device);
            device.setCount(count);
        }
    }

}
