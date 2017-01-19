package xyz.codingmentor.tiborkun.webshop.database;

import xyz.codingmentor.tiborkun.webshop.services.DeviceDBSingleton;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import xyz.codingmentor.tiborkun.webshop.entities.DeviceEntity;
import static xyz.codingmentor.tiborkun.webshop.entities.DeviceEntity.Manufacturer.*;
import xyz.codingmentor.tiborkun.webshop.exceptions.DeviceIsAlreadyExistException;
import xyz.codingmentor.tiborkun.webshop.exceptions.DeviceIsNotExistException;

/**
 *
 * @author Tibor Kun
 */
public class DeviceDBSingletonTest {

    private static DeviceDBSingleton deviceDBSingleton;
    private static DeviceEntity firstDevice;
    private static DeviceEntity secondDevice;

    @BeforeClass
    public static void init() {
        firstDevice = new DeviceEntity.Builder()
                .manufacturer(APPLE)
                .type("phone")
                .price(100000)
                .build();
        secondDevice = new DeviceEntity.Builder()
                .manufacturer(SAMSUNG)
                .type("tablet")
                .price(100000)
                .build();
        deviceDBSingleton = new DeviceDBSingleton();
        deviceDBSingleton.addDevice(firstDevice);
    }

    @Test
    public void rightAddDevice() {
        deviceDBSingleton.addDevice(secondDevice);
        String id = secondDevice.getId();
        Assert.assertEquals(secondDevice, deviceDBSingleton.getDevice(id));
        deviceDBSingleton.deleteDevice(secondDevice);
    }

    @Test(expected = DeviceIsAlreadyExistException.class)
    public void wrongAddDeviceAlreadyExist() {
        String existingId = firstDevice.getId();
        DeviceEntity existindDevice = new DeviceEntity.Builder().id(existingId).build();
        deviceDBSingleton.addDevice(existindDevice);
    }

    @Test
    public void rightGetDevice() {
        String id = firstDevice.getId();
        Assert.assertEquals(firstDevice, deviceDBSingleton.getDevice(id));
    }

    @Test(expected = DeviceIsNotExistException.class)
    public void wrongGetDevice() {
        String wrongId = "Not exist";
        deviceDBSingleton.getDevice(wrongId);
    }

    @Test
    public void rightEditDevice() {
        deviceDBSingleton.addDevice(secondDevice);
        String newType = "newType";
        secondDevice.setType(newType);
        DeviceEntity editedDevice = secondDevice;
        Assert.assertEquals(editedDevice, deviceDBSingleton.editDevice(editedDevice));
        deviceDBSingleton.deleteDevice(editedDevice);
    }

    @Test
    public void rightDeleteDevice() {
        deviceDBSingleton.addDevice(secondDevice);
        Assert.assertEquals(secondDevice, deviceDBSingleton.deleteDevice(secondDevice));
    }

    @Test
    public void rightGetAllDevices() {
        List<DeviceEntity> sampleList = new ArrayList<>();
        sampleList.add(firstDevice);
        Assert.assertEquals(sampleList, deviceDBSingleton.getAllDevice());
    }

}
