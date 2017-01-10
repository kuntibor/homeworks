package xyz.codingmentor.beanvalidation05.main;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
 * @author teiep
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
        rightUserValidateTests();
        rightDeviceValidateTests();
        wrongAddUserTest();
        wrongAddDeviceTest();
        rightModifiedUserTest();
        rightEditedDeviceTest();
        wrongModifiedUserTest();
        wrongEditedDeviceTest();
        rightDeletedUserTest();
        rightDeletedDeviceTest();
        wrongDeletedUserTest();
        wrongDeletedDeviceTest();
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
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    public static void fillEntities() {
        for (UserEntity user : userListFromJson) {
            userDBService.addUser(user);
        }
        for (DeviceEntity device : deviceListFromJson) {
            deviceDBService.addDevice(device);
        }
    }

    public static void rightUserValidateTests() {
        UserEntity rightModifiedUser = userDBService.getUser("firstUser");
        rightModifiedUser.setPassword("bbBB12.,");
        userDBService.modifyUser(rightModifiedUser);
        UserEntity rightDeletedUser = userDBService.getUser("thirdUser");
        userDBService.deleteUser(rightDeletedUser);
    }

    public static void rightDeviceValidateTests() {
        DeviceEntity rightEditedDevice = deviceListFromJson.get(3);
        rightEditedDevice.setColor(BLACK);
        deviceDBService.editDevice(rightEditedDevice);
        DeviceEntity rightDeletedDevice = deviceListFromJson.get(4);
        deviceDBService.deleteDevice(rightDeletedDevice);
    }

    public static void wrongAddUserTest() {
        try {
            userDBService.addUser(new UserEntity.Builder().username("a").build());
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            userDBService.addUser(new UserEntity.Builder().username("firstUser").build());
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    public static void wrongAddDeviceTest() {
        try {
            deviceDBService.addDevice(new DeviceEntity.Builder().manufacturer(APPLE).color(BLUE).build());
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            deviceDBService.addDevice(new DeviceEntity.Builder().manufacturer(SAMSUNG).color(GREEN).build());
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    public static void rightModifiedUserTest() {
        UserEntity modifiedUser = userDBService.getUser("secondUser");
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
            UserEntity wrongModifiedUser = userDBService.getUser("secondUser");
            wrongModifiedUser.setPassword("2");
            userDBService.modifyUser(wrongModifiedUser);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    public static void wrongEditedDeviceTest() {
        try {
            DeviceEntity wrongEditedDevice = deviceListFromJson.get(3);
            wrongEditedDevice.setColor(GREEN);
            deviceDBService.editDevice(wrongEditedDevice);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    public static void rightDeletedUserTest() {
        UserEntity deletedUser = userDBService.getUser("tenthUser");
        userDBService.deleteUser(deletedUser);
    }

    public static void rightDeletedDeviceTest() {
        DeviceEntity deletedDevice = deviceListFromJson.get(9);
        deviceDBService.deleteDevice(deletedDevice);
    }

    public static void wrongDeletedUserTest() {
        try {
            UserEntity wrongDeletedUser = userDBService.getUser("not existing user");
            userDBService.deleteUser(wrongDeletedUser);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    public static void wrongDeletedDeviceTest() {
        try {
            DeviceEntity wrongDeletedDevice = deviceListFromJson.get(4);
            wrongDeletedDevice.setId("0");
            deviceDBService.deleteDevice(wrongDeletedDevice);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    public static void shutDown() {
        weld.shutdown();
    }

}
