package xyz.codingmentor.beanvalidation05.service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.interceptor.ExcludeClassInterceptors;
import xyz.codingmentor.beanvalidation05.beans.DeviceEntity;
import xyz.codingmentor.beanvalidation05.database.DeviceDB;
import xyz.codingmentor.beanvalidation05.interceptor.BeanValidation;

/**
 *
 * @author Tibor Kun
 */
@BeanValidation
public class DeviceDBService {

    private static final Logger LOGGER = Logger.getLogger(DeviceDBService.class.getName());

    @ExcludeClassInterceptors
    public DeviceEntity addDevice(DeviceEntity device) {
        LOGGER.log(Level.INFO, "\n\tAdded device: {0} {1} {2}", new Object[]{device.getCount(),device.getManufacturer(), device.getType()});
        return DeviceDB.INSTANCE.addDevice(device);
    }

    public DeviceEntity editDevice(DeviceEntity device) {
        LOGGER.log(Level.INFO, "\n\tEdited device: {0}", device.getType());
        return DeviceDB.INSTANCE.editDevice(device);
    }

    public DeviceEntity getDevice(String id) {
        return DeviceDB.INSTANCE.getDevice(id);
    }

    public DeviceEntity deleteDevice(DeviceEntity device) {
         LOGGER.log(Level.INFO, "\n\tDeleted device: {0}", device.getType());
        return DeviceDB.INSTANCE.deleteDevice(device);
    }

    public List<DeviceEntity> getAllDevice() {
        return DeviceDB.INSTANCE.getAllDevice();
    }

}
