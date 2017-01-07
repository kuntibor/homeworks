package xyz.codingmentor.tiborkun.carpartfactory.entities;

import xyz.codingmentor.tiborkun.carpartfactory.factory.CarPartType;
import xyz.codingmentor.tiborkun.carpartfactory.factory.CarType;
import xyz.codingmentor.tiborkun.carpartfactory.annotation.WrongPart;

/**
 *
 * @author teiep
 */
@WrongPart(madeFor = CarType.FORD)
public class GearShift extends CarPart {

    public GearShift(CarType carType, int count) {
        super(carType, count, CarPartType.GEARSHIFT.getProductID());
    }

    @Override
    public String getType() {
        return CarPartType.GEARSHIFT.getName();
    }

}
