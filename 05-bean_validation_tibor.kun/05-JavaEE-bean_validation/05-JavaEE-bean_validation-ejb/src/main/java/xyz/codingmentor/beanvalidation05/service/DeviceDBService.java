package xyz.codingmentor.beanvalidation05.service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.interceptor.ExcludeClassInterceptors;
import javax.interceptor.Interceptors;
import xyz.codingmentor.beanvalidation05.beans.DeviceEntity;
import xyz.codingmentor.beanvalidation05.database.DeviceDB;
import xyz.codingmentor.beanvalidation05.interceptor.ValidatorInterceptor;

/**
 *
 * @author Tibor Kun
 */
@Interceptors(ValidatorInterceptor.class)
public class DeviceDBService {

    private static final Logger LOGGER = Logger.getLogger(DeviceDBService.class.getName());
    private DeviceEntity returnedDevice;

    @ExcludeClassInterceptors
    public DeviceEntity addDevice(DeviceEntity device) {
        returnedDevice = DeviceDB.INSTANCE.addDevice(device);
        if (null != returnedDevice) {
            LOGGER.log(Level.INFO, "\n\tAdded device: {0} {1}", new Object[]{device.getManufacturer(), device.getType()});
        }
        return returnedDevice;
    }

    public DeviceEntity editDevice(DeviceEntity device) {
        returnedDevice = DeviceDB.INSTANCE.editDevice(device);
        if (null != returnedDevice) {
            LOGGER.log(Level.INFO, "\n\tEdited device: {0} {1}", new Object[]{device.getManufacturer(), device.getType()});
        }
        return returnedDevice;
    }

    public DeviceEntity getDevice(String id) {
        return DeviceDB.INSTANCE.getDevice(id);
    }

    public DeviceEntity deleteDevice(DeviceEntity device) {
        returnedDevice = DeviceDB.INSTANCE.deleteDevice(device);
        if (null != returnedDevice) {
            LOGGER.log(Level.INFO, "\n\tDeleted device: {0} {1}", new Object[]{device.getManufacturer(), device.getType()});
        }
        return returnedDevice;
    }

    public List<DeviceEntity> getAllDevice() {
        return DeviceDB.INSTANCE.getAllDevice();
    }

}
