package xyz.codingmentor.beanvalidation05.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import xyz.codingmentor.beanvalidation05.beans.DeviceEntity;
import xyz.codingmentor.beanvalidation05.deviceexpection.DeviceDBIsEmptyExpection;
import xyz.codingmentor.beanvalidation05.deviceexpection.DeviceIsAlreadyExistExpection;
import xyz.codingmentor.beanvalidation05.deviceexpection.DeviceIsNotExistExpection;

/**
 *
 * @author teiep
 */
public enum DeviceDB {
    INSTANCE;

    private static final Map<String, DeviceEntity> devices = new HashMap<>();

    public DeviceEntity addDevice(DeviceEntity device) {
        if (!devices.containsKey(device.getId())) {
            device.setId(UUID.randomUUID().toString());
            device.setCount(0);
            devices.put(device.getId(), device);
            return devices.get(device.getId());
        }
        throw new DeviceIsAlreadyExistExpection();
    }

    public DeviceEntity editDevice(DeviceEntity device) {
        if (devices.containsKey(device.getId())) {
            devices.put(device.getId(), device);
            return devices.get(device.getId());
        }
        throw new DeviceIsNotExistExpection();
    }

    public DeviceEntity getDevice(String id) {
        if (devices.containsKey(id)) {
            return devices.get(id);
        }
        throw new DeviceIsNotExistExpection();
    }

    public DeviceEntity deleteDevice(DeviceEntity device) {
        if (devices.containsKey(device.getId())) {
            return devices.remove(device.getId());
        }
        throw new DeviceIsNotExistExpection();
    }

    public List<DeviceEntity> getAllDevice() {
        if (!devices.isEmpty()) {
            return new ArrayList<>(devices.values());
        }
        throw new DeviceDBIsEmptyExpection();
    }

}
