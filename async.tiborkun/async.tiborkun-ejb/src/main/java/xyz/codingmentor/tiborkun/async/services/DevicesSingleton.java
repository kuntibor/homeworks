package xyz.codingmentor.tiborkun.async.services;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Future;
import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Singleton;
import org.jboss.logging.Logger;
import xyz.codingmentor.tiborkun.async.entities.DeviceEntity;

/**
 *
 * @author Tibor Kun
 */
@Singleton
public class DevicesSingleton {

    private final Map<String, DeviceEntity> devices = new HashMap<>();
    private List<DeviceEntity> deviceListFromJson;
    private final ObjectMapper mapper = new ObjectMapper();
    private final JavaType deviceType = mapper.getTypeFactory().constructCollectionType(List.class, DeviceEntity.class);
    private static final Logger LOGGER = Logger.getLogger(DevicesSingleton.class.getName());

    public DeviceEntity addDevice(DeviceEntity device) {
        if (!devices.containsKey(device.getId())) {
            device.setId(UUID.randomUUID().toString());
            devices.put(device.getId(), device);
            return device;
        }
        throw new IllegalArgumentException();
    }

    public Map<String, DeviceEntity> getDevices() {
        LOGGER.info("\n\tGet all dvice");
        return devices;
    }

    @Asynchronous
    public void loadDevicesFromJson() {
        LOGGER.info("\n\tLoading devices from json...");
        try {
            deviceListFromJson = mapper.readValue(new File(getClass().getClassLoader().getResource("devices.json").getFile()), deviceType);
        } catch (IOException ex) {
            LOGGER.error(ex);
        }
        for (DeviceEntity device : deviceListFromJson) {
            LOGGER.info("\n\tAdd new device:" + device.getType());
            addDevice(device);
            waiting();
        }
        LOGGER.info("\n\tLoading complete");
    }

    @Asynchronous
    public Future<List<DeviceEntity>> getMatchedDevices(String match) {
        List<DeviceEntity> matchedDevices = new ArrayList<>();
        LOGGER.info("\n\tSearch matching devices...");
        for (Map.Entry<String, DeviceEntity> entry : devices.entrySet()) {
            if (entry.getValue().getType().contains(match)) {
                matchedDevices.add(entry.getValue());
                LOGGER.info("\n\tMatched device: " + entry.getValue().getType());
                waiting();
            }
        }
        LOGGER.info("\n\tSearching complete");
        return new AsyncResult<>(matchedDevices);
    }

    public void waiting() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            LOGGER.error(ex);
        }
    }

}
