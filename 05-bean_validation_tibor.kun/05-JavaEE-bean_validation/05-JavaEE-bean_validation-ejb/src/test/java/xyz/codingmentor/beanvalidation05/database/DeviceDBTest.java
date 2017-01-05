package xyz.codingmentor.beanvalidation05.database;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import xyz.codingmentor.beanvalidation05.beans.DeviceEntity;
import static xyz.codingmentor.beanvalidation05.beans.DeviceEntity.Manufacturer.APPLE;
import static xyz.codingmentor.beanvalidation05.beans.DeviceEntity.Manufacturer.SAMSUNG;
import xyz.codingmentor.beanvalidation05.deviceexception.DeviceIsAlreadyExistException;
import xyz.codingmentor.beanvalidation05.deviceexception.DeviceIsNotExistException;

/**
 *
 * @author teiep
 */
public class DeviceDBTest {

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

        DeviceDB.INSTANCE.addDevice(firstDevice);
    }

    @Test
    public void rightAddDevice() {
        DeviceDB.INSTANCE.addDevice(secondDevice);
        String id = secondDevice.getId();
        Assert.assertEquals(secondDevice, DeviceDB.INSTANCE.getDevice(id));
        DeviceDB.INSTANCE.deleteDevice(secondDevice);
    }

    @Test(expected = DeviceIsAlreadyExistException.class)
    public void wrongAddDeviceAlreadyExist() {
        String existingId = firstDevice.getId();
        DeviceEntity existindDevice = new DeviceEntity.Builder().id(existingId).build();
        DeviceDB.INSTANCE.addDevice(existindDevice);
    }

    @Test
    public void rightGetDevice() {
        String id = firstDevice.getId();
        Assert.assertEquals(firstDevice, DeviceDB.INSTANCE.getDevice(id));
    }

    @Test(expected = DeviceIsNotExistException.class)
    public void wrongGetDevice() {
        String wrongId = "Not exist";
        DeviceDB.INSTANCE.getDevice(wrongId);
    }

    @Test
    public void rightEditDevice() {
        DeviceDB.INSTANCE.addDevice(secondDevice);
        String newType = "newType";
        secondDevice.setType(newType);
        DeviceEntity editedDevice = secondDevice;
        Assert.assertEquals(editedDevice, DeviceDB.INSTANCE.editDevice(editedDevice));
        DeviceDB.INSTANCE.deleteDevice(editedDevice);
    }

    @Test
    public void rightDeleteDevice() {
        DeviceDB.INSTANCE.addDevice(secondDevice);
        Assert.assertEquals(secondDevice, DeviceDB.INSTANCE.deleteDevice(secondDevice));
    }

    @Test
    public void rightGetAllDevices() {
        List<DeviceEntity> sampleList = new ArrayList<>();
        sampleList.add(firstDevice);
        Assert.assertEquals(sampleList, DeviceDB.INSTANCE.getAllDevice());
    }

}
