package xyz.codingmentor.tiborkun.webshop.services;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import xyz.codingmentor.tiborkun.webshop.entities.DeviceEntity;
import static xyz.codingmentor.tiborkun.webshop.entities.DeviceEntity.Manufacturer.*;
import xyz.codingmentor.tiborkun.webshop.exceptions.DeviceIsNotInCartException;
import xyz.codingmentor.tiborkun.webshop.exceptions.NotEnoughQuantityException;

/**
 *
 * @author Tibor Kun
 */
public class CartServiceTest {
    
    private static DeviceDBSingleton deviceDBSingleton;
    private static CartService cartService;
    private static DeviceEntity firstDevice;
    private static DeviceEntity secondDevice;
    private static String firstID;
    private static String secondID;
    private static int firstCount = 10;
    private static int secondCount = 20;
    private static int firstPrice = 100000;
    private static int secondPrice = 200000;

    private static int testCount = 2;

    @BeforeClass
    public static void init() {
        deviceDBSingleton = new DeviceDBSingleton();
        cartService = new CartService(deviceDBSingleton);
        firstDevice = new DeviceEntity.Builder()
                .manufacturer(APPLE)
                .type("phone")
                .price(firstPrice)
                .build();
        secondDevice = new DeviceEntity.Builder()
                .manufacturer(SAMSUNG)
                .type("tablet")
                .price(secondPrice)
                .build();
        deviceDBSingleton.addDevice(firstDevice);
        firstID = firstDevice.getId();
        firstDevice.setCount(firstCount);
        deviceDBSingleton.addDevice(secondDevice);
        secondID = secondDevice.getId();
        secondDevice.setCount(secondCount);
        cartService.addDevice(firstID, testCount);
    }


    @Test
    public void rightAddDevice() {
        cartService.addDevice(secondID, testCount);
        Assert.assertEquals(secondCount - testCount, deviceDBSingleton.getDevice(secondID).getCount());
        Assert.assertEquals(cartService.getTotalPrice(), testCount * (firstPrice + secondPrice));
        cartService.deleteDevice(secondID, testCount);
    }

    @Test(expected = NotEnoughQuantityException.class)
    public void wrongAddDevice() {
        cartService.addDevice(firstID, firstCount);
    }

    @Test
    public void rightDeleteDevice() {
        cartService.addDevice(secondID, testCount);
        cartService.deleteDevice(secondID, testCount);
        Assert.assertFalse(cartService.getShoppingCart().containsKey(secondDevice));
        Assert.assertEquals(cartService.getTotalPrice(), testCount * firstPrice);
    }

    @Test(expected = DeviceIsNotInCartException.class)
    public void wrongDeleteDevice() {
        cartService.deleteDevice(secondID, testCount);
    }

    @Test
    public void rightClearCart() {
        cartService.addDevice(secondID, testCount);
        cartService.clearCart();
        Assert.assertTrue(cartService.getShoppingCart().isEmpty());
        Assert.assertEquals(0, cartService.getTotalPrice());
        cartService.addDevice(firstID, testCount);
    }

    @Test
    public void rightPurchase() {
        cartService.addDevice(secondID, testCount);
        Assert.assertTrue(cartService.purchase());
        Assert.assertEquals(0, cartService.getTotalPrice());
        Assert.assertTrue(cartService.getShoppingCart().isEmpty());
        cartService.addDevice(firstID, testCount);
    }

    @Test
    public void wrongPurchase() {
        cartService.deleteDevice(firstID, testCount);
        Assert.assertFalse(cartService.purchase());
        cartService.addDevice(firstID, testCount);
    }

}
