package xyz.codingmentor.tiborkun.webshop.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Singleton;
import javax.interceptor.ExcludeClassInterceptors;
import javax.interceptor.Interceptors;
import xyz.codingmentor.tiborkun.webshop.entities.DeviceEntity;
import xyz.codingmentor.tiborkun.webshop.exceptions.DeviceIsAlreadyExistException;
import xyz.codingmentor.tiborkun.webshop.exceptions.DeviceIsNotExistException;
import xyz.codingmentor.tiborkun.webshop.interceptors.ValidatorInterceptor;

/**
 *
 * @author Tibor Kun
 */
@Singleton
@Interceptors(ValidatorInterceptor.class)
public class DeviceDBSingleton implements Serializable {

    private final Map<String, DeviceEntity> devices = new HashMap<>();
    private static final Logger LOGGER = Logger.getLogger(DeviceDBSingleton.class.getName());

    @ExcludeClassInterceptors
    public DeviceEntity addDevice(DeviceEntity device) {
        if (!devices.containsKey(device.getId())) {
            device.setId(UUID.randomUUID().toString());
            device.setCount(0);
            devices.put(device.getId(), device);
            LOGGER.log(Level.INFO, "Added device: {0} ", device);
            return devices.get(device.getId());
        }
        throw new DeviceIsAlreadyExistException(device.getId());
    }

    public DeviceEntity editDevice(DeviceEntity device) {
        if (devices.containsKey(device.getId())) {
            devices.put(device.getId(), device);
            LOGGER.log(Level.INFO, "Edited device: {0} ", device);
            return devices.get(device.getId());
        }
        throw new DeviceIsNotExistException(device.getId());
    }

    public DeviceEntity getDevice(String id) {
        if (devices.containsKey(id)) {
            return devices.get(id);
        }
        throw new DeviceIsNotExistException(id);
    }

    public DeviceEntity deleteDevice(DeviceEntity device) {
        if (devices.containsKey(device.getId())) {
            LOGGER.log(Level.INFO, "Deleted device: {0} ", device);
            return devices.remove(device.getId());
        }
        throw new DeviceIsNotExistException(device.getId());
    }

    public List<DeviceEntity> getAllDevice() {
        return new ArrayList<>(devices.values());
    }

}
