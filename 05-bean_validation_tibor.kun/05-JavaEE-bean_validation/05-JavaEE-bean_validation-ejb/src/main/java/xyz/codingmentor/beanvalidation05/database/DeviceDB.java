package xyz.codingmentor.beanvalidation05.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import xyz.codingmentor.beanvalidation05.beans.DeviceEntity;
import xyz.codingmentor.beanvalidation05.exception.DeviceIsAlreadyExistException;
import xyz.codingmentor.beanvalidation05.exception.DeviceIsNotExistException;

/**
 *
 * @author Tibor Kun
 */
public enum DeviceDB {
    INSTANCE;

    private final Map<String, DeviceEntity> devices = new HashMap<>();

    public DeviceEntity addDevice(DeviceEntity device) {
        if (!devices.containsKey(device.getId())) {
            device.setId(UUID.randomUUID().toString());
            device.setCount(0);
            devices.put(device.getId(), device);
            return devices.get(device.getId());
        }
        throw new DeviceIsAlreadyExistException(device.getId());
    }

    public DeviceEntity editDevice(DeviceEntity device) {
        if (devices.containsKey(device.getId())) {
            devices.put(device.getId(), device);
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
            return devices.remove(device.getId());
        }
        throw new DeviceIsNotExistException(device.getId());
    }

    public List<DeviceEntity> getAllDevice() {
        return new ArrayList<>(devices.values());
    }

}
