package xyz.codingmentor.tiborkun.carpartfactory.factory;

import xyz.codingmentor.tiborkun.carpartfactory.entities.CarPart;
import xyz.codingmentor.tiborkun.carpartfactory.entities.GearShift;
import xyz.codingmentor.tiborkun.carpartfactory.entities.PowerWindow;
import xyz.codingmentor.tiborkun.carpartfactory.entities.RearViewWindow;
import xyz.codingmentor.tiborkun.carpartfactory.entities.TurnSignal;
import xyz.codingmentor.tiborkun.carpartfactory.logger.CarPartFactoryLogger;

/**
 *
 * @author teiep
 */
public enum CarPartFactory {
    INSTANCE;

    private CarPart createdCarPart;

    public CarPart create(CarPartType type, CarType carType) {
        switch (type) {
            case GEARSHIFT:
                createdCarPart = new GearShift(carType, 10000);
                break;
            case POWERWWINDOW:
                createdCarPart = new PowerWindow(carType, 5000);
                break;
            case REARVIEWMIRROR:
                createdCarPart = new RearViewWindow(carType, 15000);
                break;
            case TURNSIGNAL:
                createdCarPart = new TurnSignal(carType, 20000);
                break;
            default:
                throw new IllegalArgumentException("Illegal car type: " + carType);
        }
        CarPartFactoryLogger.productionLog(createdCarPart);
        return createdCarPart;
    }

}
