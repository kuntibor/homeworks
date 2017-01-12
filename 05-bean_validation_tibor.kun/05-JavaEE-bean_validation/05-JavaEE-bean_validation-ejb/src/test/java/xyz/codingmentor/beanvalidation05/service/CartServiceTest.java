package xyz.codingmentor.beanvalidation05.service;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import xyz.codingmentor.beanvalidation05.beans.DeviceEntity;
import static xyz.codingmentor.beanvalidation05.beans.DeviceEntity.Manufacturer.APPLE;
import static xyz.codingmentor.beanvalidation05.beans.DeviceEntity.Manufacturer.SAMSUNG;
import xyz.codingmentor.beanvalidation05.exception.NotEnoughQuantityException;
import xyz.codingmentor.beanvalidation05.exception.DeviceIsNotInCartException;

/**
 *
 * @author Tibor Kun
 */
public class CartServiceTest {

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
    private static DeviceDBService deviceDBService;

    @BeforeClass
    public static void init() {
        cartService = new CartService();
        deviceDBService = new DeviceDBService();
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
        deviceDBService.addDevice(firstDevice);
        firstID = firstDevice.getId();
        firstDevice.setCount(firstCount);
        deviceDBService.addDevice(secondDevice);
        secondID = secondDevice.getId();
        secondDevice.setCount(secondCount);
        cartService.addDevice(firstID, testCount);
    }

    @Test
    public void rightAddDevice() {
        cartService.addDevice(secondID, testCount);
        Assert.assertEquals(secondCount - testCount, deviceDBService.getDevice(secondID).getCount());
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
