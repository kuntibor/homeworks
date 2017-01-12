package xyz.codingmentor.beanvalidation05.service;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import xyz.codingmentor.beanvalidation05.beans.DeviceEntity;
import xyz.codingmentor.beanvalidation05.exception.NotEnoughQuantityException;
import xyz.codingmentor.beanvalidation05.exception.DeviceIsNotInCartException;

/**
 *
 * @author Tibor Kun
 */
public class CartService {

    private static final Logger LOGGER = Logger.getLogger(CartService.class.getName());
    private final Map<DeviceEntity, Integer> shoppingCart = new HashMap<>();
    private final DeviceDBService deviceDBService = new DeviceDBService();
    private int totalPrice = 0;

    public DeviceEntity addDevice(String id, int quantity) {
        DeviceEntity device = deviceDBService.getDevice(id);
        if (quantity <= device.getCount()) {
            shoppingCart.put(device, quantity);
            device.setCount(device.getCount() - quantity);
            deviceDBService.editDevice(device);
            totalPrice += quantity * device.getPrice();
            return device;
        }
        throw new NotEnoughQuantityException(device.getId());
    }

    public DeviceEntity deleteDevice(String id, int quantity) {
        DeviceEntity device = deviceDBService.getDevice(id);
        if (shoppingCart.containsKey(device)) {
            if (quantity < shoppingCart.get(device)) {
                totalPrice -= quantity * device.getPrice();
                device.setCount(device.getCount() + quantity);
                deviceDBService.editDevice(device);
                shoppingCart.put(device, shoppingCart.get(device) - quantity);
            } else {
                totalPrice -= shoppingCart.get(device) * device.getPrice();
                device.setCount(device.getCount() + shoppingCart.get(device));
                deviceDBService.editDevice(device);
                shoppingCart.remove(device, quantity);
            }
            return device;
        }
        throw new DeviceIsNotInCartException(device.getId());
    }

    public void clearCart() {
        shoppingCart.entrySet().forEach((entry) -> {
            DeviceEntity device = deviceDBService.getDevice(entry.getKey().getId());
            device.setCount(device.getCount() + entry.getValue());
            deviceDBService.editDevice(device);
        });
        shoppingCart.clear();
        totalPrice = 0;
    }

    public boolean purchase() {
        if (!shoppingCart.isEmpty()) {
            logPurchase();
            shoppingCart.clear();
            totalPrice = 0;
            return true;
        }
        return false;
    }

    public void logPurchase() {
        String purchaseMessage = "\n\tPurchase: ";
        purchaseMessage = shoppingCart
                .entrySet()
                .stream()
                .map((entry) -> "\n\t" + entry.getValue() + " piece " + entry.getKey().getManufacturer() + " " + entry.getKey().getType())
                .reduce(purchaseMessage, String::concat);
        LOGGER.log(Level.INFO, purchaseMessage);
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public Map<DeviceEntity, Integer> getShoppingCart() {
        return shoppingCart;
    }

}
