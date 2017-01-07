package xyz.codingmentor.tiborkun.carpartfactory.entities;

import xyz.codingmentor.tiborkun.carpartfactory.factory.CarPartType;
import xyz.codingmentor.tiborkun.carpartfactory.factory.CarType;
import xyz.codingmentor.tiborkun.carpartfactory.annotation.WrongPart;

/**
 *
 * @author teiep
 */
@WrongPart(defect = "Not strong enough", madeFor = CarType.MAZDA)
public class PowerWindow extends CarPart {

    public PowerWindow(CarType carType, int count) {
        super(carType, count, CarPartType.POWERWWINDOW.getProductID());
    }

    @Override
    public String getType() {
        return CarPartType.POWERWWINDOW.getName();
    }

}
