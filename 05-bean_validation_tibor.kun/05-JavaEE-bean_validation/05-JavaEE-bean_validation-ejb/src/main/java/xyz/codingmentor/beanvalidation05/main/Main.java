package xyz.codingmentor.beanvalidation05.main;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import org.jboss.logging.Logger;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import xyz.codingmentor.beanvalidation05.beans.DeviceEntity;
import static xyz.codingmentor.beanvalidation05.beans.DeviceEntity.Color.*;
import static xyz.codingmentor.beanvalidation05.beans.DeviceEntity.Manufacturer.*;
import xyz.codingmentor.beanvalidation05.beans.UserEntity;
import xyz.codingmentor.beanvalidation05.service.DeviceDBService;
import xyz.codingmentor.beanvalidation05.service.UserDBService;

/**
 *
 * @author Tibor Kun
 */
public class Main {

    private static List<UserEntity> userListFromJson;
    private static List<DeviceEntity> deviceListFromJson;
    private static ObjectMapper mapper;
    private static JavaType userType;
    private static JavaType deviceType;
    private static Weld weld;
    private static WeldContainer container;
    private static UserDBService userDBService;
    private static DeviceDBService deviceDBService;
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    private Main() {
        // private constructor to hide the implicit public one. 
    }

    public static void main(String[] args) {
        setUp();
        loadEntitiesFromJson();
        fillEntities();
        rightModifiedUserTest();
        rightEditedDeviceTest();
        wrongModifiedUserTest();
        wrongEditedDeviceTest();
        wrongUsernameFillTest();
        wrongEditedDeviceCountTest();
        shutDown();
    }

    public static void setUp() {
        userListFromJson = null;
        deviceListFromJson = null;
        mapper = new ObjectMapper();
        userType = mapper.getTypeFactory().constructCollectionType(List.class, UserEntity.class);
        deviceType = mapper.getTypeFactory().constructCollectionType(List.class, DeviceEntity.class);
        weld = new Weld();
        container = weld.initialize();
        userDBService = container.instance().select(UserDBService.class).get();
        deviceDBService = container.instance().select(DeviceDBService.class).get();
    }

    public static void loadEntitiesFromJson() {
        try {
            deviceListFromJson = mapper.readValue(new File("devices.json"), deviceType);
            userListFromJson = mapper.readValue(new File("users.json"), userType);
        } catch (IOException ex) {
            LOGGER.error(ex);
        }
    }

    public static void fillEntities() {
        userListFromJson.forEach((user) -> {
            userDBService.addUser(user);
        });
        deviceListFromJson.forEach((device) -> {
            deviceDBService.addDevice(device);
        });
    }

    public static void wrongUsernameFillTest() {
        UserEntity user = userListFromJson.get(2);
        user.setUsername("testUser");
        user.setLastName("last name");
        try {
            userDBService.modifyUser(user);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    public static void rightModifiedUserTest() {
        UserEntity modifiedUser = userListFromJson.get(5);
        modifiedUser.setEmail("new@test.com");
        userDBService.modifyUser(modifiedUser);
    }

    public static void rightEditedDeviceTest() {
        DeviceEntity editedDevice = deviceListFromJson.get(3);
        editedDevice.setColor(BLACK);
        deviceDBService.editDevice(editedDevice);
    }

    public static void wrongModifiedUserTest() {
        try {
            UserEntity wrongModifiedUser = userListFromJson.get(6);
            wrongModifiedUser.setPassword("6");
            wrongModifiedUser.setEmail("mail");
            wrongModifiedUser.setUsername("a");
            userDBService.modifyUser(wrongModifiedUser);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        try {
            Calendar birthDate = Calendar.getInstance();
            birthDate.add(Calendar.YEAR, 2);
            UserEntity user = userListFromJson.get(4);
            user.setDateOfBirth(birthDate.getTime());
            userDBService.modifyUser(user);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    public static void wrongEditedDeviceTest() {
        try {
            DeviceEntity device = deviceListFromJson.get(2);
            device.setManufacturer(APPLE);
            device.setColor(BLUE);
            deviceDBService.editDevice(device);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        try {
            DeviceEntity device = deviceListFromJson.get(2);
            device.setManufacturer(SAMSUNG);
            device.setColor(GREEN);
            deviceDBService.editDevice(device);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    public static void wrongEditedDeviceCountTest() {
        try {
            DeviceEntity wrongEditedDevice = deviceListFromJson.get(6);
            wrongEditedDevice.setCount(-10);
            deviceDBService.editDevice(wrongEditedDevice);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    public static void shutDown() {
        weld.shutdown();
    }

}
