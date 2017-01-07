package xyz.codingmentor.tiborkun.carpartfactory.entities;

import xyz.codingmentor.tiborkun.carpartfactory.annotation.WrongPart;
import xyz.codingmentor.tiborkun.carpartfactory.factory.CarPartType;
import xyz.codingmentor.tiborkun.carpartfactory.factory.CarType;

/**
 *
 * @author teiep
 */
@WrongPart(defect = "Bad color", madeFor = CarType.AUDI)
public class RearViewWindow extends CarPart {

    public RearViewWindow(CarType carType, int count) {
        super(carType, count, CarPartType.REARVIEWMIRROR.getProductID());
    }

    @Override
    public String getType() {
        return CarPartType.REARVIEWMIRROR.getName();
    }

}
