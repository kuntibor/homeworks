package xyz.codingmentor.beanvalidation05.service;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import xyz.codingmentor.beanvalidation05.beans.DeviceEntity;
import static xyz.codingmentor.beanvalidation05.beans.DeviceEntity.Manufacturer.APPLE;
import static xyz.codingmentor.beanvalidation05.beans.DeviceEntity.Manufacturer.SAMSUNG;
import xyz.codingmentor.beanvalidation05.exception.DeviceIsAlreadyInCartException;
import xyz.codingmentor.beanvalidation05.exception.DeviceIsNotInCartException;

/**
 *
 * @author Tibor Kun
 */
public class CartServiceTest {

    private static CartService cartService;
    private static DeviceEntity firstDevice;
    private static DeviceEntity secondDevice;
    private static String secondID;
    private static int firstCount = 10;
    private static int secondCount = 20;
    private static int firstPrice = 100000;
    private static int secondPrice = 200000;

    private static int testCount = 2;
    private static DeviceDBService deviceDBService;

    @BeforeClass
    public static void init() {
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
        cartService = new CartService();
        deviceDBService.addDevice(firstDevice);
        firstDevice.setCount(firstCount);
        deviceDBService.addDevice(secondDevice);
        secondID = secondDevice.getId();
        secondDevice.setCount(secondCount);
        cartService.addDevice(firstDevice, testCount);
    }

    @Test
    public void rightAddDevice() {
        cartService.addDevice(secondDevice, testCount);
        Assert.assertEquals(secondCount - testCount, deviceDBService.getDevice(secondID).getCount());
        Assert.assertEquals(cartService.getTotalPrice(), testCount * (firstPrice + secondPrice));
        cartService.deleteDevice(secondDevice, testCount);
    }

    @Test(expected = DeviceIsAlreadyInCartException.class)
    public void wrongAddDevice() {
        cartService.addDevice(firstDevice, testCount);
    }

    @Test
    public void rightDeleteDevice() {
        cartService.addDevice(secondDevice, testCount);
        cartService.deleteDevice(secondDevice, testCount);
        Assert.assertFalse(cartService.getCartList().contains(secondDevice));
        Assert.assertEquals(cartService.getTotalPrice(), testCount * firstPrice);
    }

    @Test(expected = DeviceIsNotInCartException.class)
    public void wrongDeleteDevice() {
        cartService.deleteDevice(secondDevice, testCount);
    }

    @Test
    public void rightClearCart() {
        cartService.addDevice(secondDevice, testCount);
        cartService.clearCart();
        Assert.assertNull(cartService.getCartList());
        Assert.assertEquals(0, cartService.getTotalPrice());
        cartService.addDevice(firstDevice, testCount);
    }

    @Test
    public void rightPurchase() {
        cartService.addDevice(secondDevice, testCount);
        Assert.assertTrue(cartService.purchase());
        Assert.assertEquals(0, cartService.getTotalPrice());
        Assert.assertNull(cartService.getCartList());
        cartService.addDevice(firstDevice, testCount);
    }

    @Test
    public void wrongPurchase() {
        cartService.deleteDevice(firstDevice, testCount);
        Assert.assertFalse(cartService.purchase());
        cartService.addDevice(firstDevice, testCount);
    }

}
