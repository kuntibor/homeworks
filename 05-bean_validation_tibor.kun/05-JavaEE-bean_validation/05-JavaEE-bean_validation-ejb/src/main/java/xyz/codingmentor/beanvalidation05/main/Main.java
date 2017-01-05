package xyz.codingmentor.beanvalidation05.main;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import xyz.codingmentor.beanvalidation05.beans.DeviceEntity;
import xyz.codingmentor.beanvalidation05.beans.UserEntity;
import xyz.codingmentor.beanvalidation05.database.DeviceDB;
import xyz.codingmentor.beanvalidation05.database.UserDB;

/**
 *
 * @author teiep
 */
public class Main {

    private Main() {
        // private constructor to hide the implicit public one. 
    }

    public static void main(String[] args) {

        List<UserEntity> userListFromJson = null;
        List<DeviceEntity> deviceListFromJson = null;
        ObjectMapper mapper = new ObjectMapper();
        JavaType userType = mapper.getTypeFactory().constructCollectionType(List.class, UserEntity.class);
        JavaType deviceType = mapper.getTypeFactory().constructCollectionType(List.class, DeviceEntity.class);

        try {
            deviceListFromJson = mapper.readValue(new File("devices.json"), deviceType);
            userListFromJson = mapper.readValue(new File("users.json"), userType);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (UserEntity user : userListFromJson) {
            UserDB.INSTANCE.addUser(user);
        }

        for (DeviceEntity device : deviceListFromJson) {
            DeviceDB.INSTANCE.addDevice(device);
        }
    }

}
