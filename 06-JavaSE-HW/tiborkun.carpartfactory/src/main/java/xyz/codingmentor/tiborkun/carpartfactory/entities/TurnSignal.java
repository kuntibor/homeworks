package xyz.codingmentor.tiborkun.carpartfactory.entities;

import xyz.codingmentor.tiborkun.carpartfactory.factory.CarPartType;
import xyz.codingmentor.tiborkun.carpartfactory.factory.CarType;

/**
 *
 * @author teiep
 */
public class TurnSignal extends CarPart {

    public TurnSignal(CarType carType, int count) {
        super(carType, count, CarPartType.TURNSIGNAL.getProductID());
    }

    @Override
    public String getType() {
        return CarPartType.TURNSIGNAL.getName();
    }

}
