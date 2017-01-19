package xyz.codingmentor.tiborkun.webshop.services;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;
import javax.inject.Inject;
import xyz.codingmentor.tiborkun.webshop.entities.DeviceEntity;
import xyz.codingmentor.tiborkun.webshop.exceptions.DeviceIsNotInCartException;
import xyz.codingmentor.tiborkun.webshop.exceptions.NotEnoughQuantityException;

/**
 *
 * @author Tibor Kun
 */
@Stateful
@StatefulTimeout(value = 2000, unit = TimeUnit.SECONDS)
public class CartService implements Serializable {

    private DeviceDBSingleton deviceDBSingleton;
    private static final Logger LOGGER = Logger.getLogger(CartService.class.getName());
    private final Map<DeviceEntity, Integer> shoppingCart = new HashMap<>();

    private int totalPrice = 0;

    public CartService() {
        //nothing to initialize
    }

    @Inject
    public CartService(DeviceDBSingleton deviceDBSingleton) {
        this.deviceDBSingleton = deviceDBSingleton;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public Map<DeviceEntity, Integer> getShoppingCart() {
        return shoppingCart;
    }

    public DeviceEntity addDevice(String id, int quantity) {
        DeviceEntity device = deviceDBSingleton.getDevice(id);
        if (quantity <= device.getCount()) {
            shoppingCart.put(device, quantity);
            device.setCount(device.getCount() - quantity);
            deviceDBSingleton.editDevice(device);
            totalPrice += quantity * device.getPrice();
            LOGGER.log(Level.INFO, "Add device to cart: " + id);
            return device;
        }
        throw new NotEnoughQuantityException(device.getId());
    }

    public DeviceEntity deleteDevice(String id, int quantity) {
        DeviceEntity device = deviceDBSingleton.getDevice(id);
        if (shoppingCart.containsKey(device)) {
            if (quantity < shoppingCart.get(device)) {
                totalPrice -= quantity * device.getPrice();
                device.setCount(device.getCount() + quantity);
                deviceDBSingleton.editDevice(device);
                shoppingCart.put(device, shoppingCart.get(device) - quantity);
            } else {
                totalPrice -= shoppingCart.get(device) * device.getPrice();
                device.setCount(device.getCount() + shoppingCart.get(device));
                deviceDBSingleton.editDevice(device);
                shoppingCart.remove(device, quantity);
            }
            return device;
        }
        throw new DeviceIsNotInCartException(device.getId());
    }

    public void clearCart() {
        shoppingCart.entrySet().forEach((entry) -> {
            DeviceEntity device = deviceDBSingleton.getDevice(entry.getKey().getId());
            device.setCount(device.getCount() + entry.getValue());
            deviceDBSingleton.editDevice(device);
        });
        shoppingCart.clear();
        totalPrice = 0;
    }

    @Remove
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
        String purchaseMessage = "Purchase: ";
        purchaseMessage = shoppingCart
                .entrySet()
                .stream()
                .map((entry) -> entry.getValue() + " piece " + entry.getKey().getManufacturer() + " " + entry.getKey().getType() + ". ")
                .reduce(purchaseMessage, String::concat);
        purchaseMessage += "Total price: " + totalPrice;
        LOGGER.log(Level.INFO, purchaseMessage);
    }

}
