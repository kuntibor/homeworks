package xyz.codingmentor.beanvalidation05.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import xyz.codingmentor.beanvalidation05.beans.DeviceEntity;
import xyz.codingmentor.beanvalidation05.exception.DeviceIsAlreadyInCartException;
import xyz.codingmentor.beanvalidation05.exception.DeviceIsNotInCartException;

/**
 *
 * @author Tibor Kun
 */
public class CartService {

    private static final Logger LOGGER = Logger.getLogger(CartService.class.getName());
    private Map<DeviceEntity, Integer> cartList = new HashMap<>();
    private int totalPrice = 0;

    public DeviceEntity addDevice(DeviceEntity device, int count) {
        if (!cartList.containsKey(device)) {
            device.setCount(device.getCount() - count);
            totalPrice += count * device.getPrice();
            cartList.put(device, count);
            return device;
        }
        throw new DeviceIsAlreadyInCartException(device.getId());
    }

    public DeviceEntity deleteDevice(DeviceEntity device, int count) {
        if (cartList.containsKey(device)) {
            device.setCount(device.getCount() + count);
            totalPrice -= count * device.getPrice();
            if (count < cartList.get(device)) {
                cartList.put(device, cartList.get(device) - count);
            } else {
                cartList.remove(device, count);
            }
            return device;
        }
        throw new DeviceIsNotInCartException(device.getId());
    }

    public void clearCart() {
        List<DeviceEntity> deviceList = getCartList();
        if (null != deviceList) {
            for (DeviceEntity device : deviceList) {
                deleteDevice(device, cartList.get(device));
            }
        }
    }

    public List<DeviceEntity> getCartList() {
        ArrayList<DeviceEntity> returnedList = null;
        if (!cartList.isEmpty()) {
            returnedList = new ArrayList<>();
            for (Map.Entry<DeviceEntity, Integer> entry : cartList.entrySet()) {
                returnedList.add(entry.getKey());
            }
        }
        return returnedList;
    }

    public boolean purchase() {
        if (!cartList.isEmpty()) {
            logPurchase();
            cartList.clear();
            totalPrice = 0;
            return true;
        }
        return false;
    }

    public void logPurchase() {
        String purchaseMessages = "\n\tPurchase: ";
        for (Map.Entry<DeviceEntity, Integer> entry : cartList.entrySet()) {
            purchaseMessages += "\n\t" + entry.getValue() + " piece " + entry.getKey().getManufacturer() + " " + entry.getKey().getType();
        }
        LOGGER.log(Level.INFO, purchaseMessages);
    }

    public int getTotalPrice() {
        return totalPrice;
    }

}
