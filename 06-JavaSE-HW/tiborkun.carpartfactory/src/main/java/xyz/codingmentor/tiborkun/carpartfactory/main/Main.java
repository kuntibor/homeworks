package xyz.codingmentor.tiborkun.carpartfactory.main;

import java.util.ArrayList;
import java.util.List;
import xyz.codingmentor.tiborkun.carpartfactory.entities.CarPart;
import xyz.codingmentor.tiborkun.carpartfactory.factory.CarPartFactory;
import static xyz.codingmentor.tiborkun.carpartfactory.factory.CarPartType.*;
import static xyz.codingmentor.tiborkun.carpartfactory.factory.CarType.*;
import xyz.codingmentor.tiborkun.carpartfactory.reflection.Reflection;

/**
 *
 * @author teiep
 */
public class Main {

    private static final List<CarPart> CAR_PART_LIST = new ArrayList<>();

    private Main() {
        //hide the implicit public constructor
    }

    public static void main(String[] args) {
        CarPart firstPart = CarPartFactory.INSTANCE.create(GEARSHIFT, FORD);
        CarPart secondPart = CarPartFactory.INSTANCE.create(GEARSHIFT, AUDI);
        CarPart thirdPart = CarPartFactory.INSTANCE.create(POWERWWINDOW, MAZDA);
        CarPart fourthPart = CarPartFactory.INSTANCE.create(POWERWWINDOW, TOYOTA);
        CarPart fifthPart = CarPartFactory.INSTANCE.create(REARVIEWMIRROR, AUDI);
        CarPart sixthPart = CarPartFactory.INSTANCE.create(REARVIEWMIRROR, MAZDA);
        CarPart seventhPart = CarPartFactory.INSTANCE.create(TURNSIGNAL, TOYOTA);

        CAR_PART_LIST.add(firstPart);
        CAR_PART_LIST.add(secondPart);
        CAR_PART_LIST.add(thirdPart);
        CAR_PART_LIST.add(fourthPart);
        CAR_PART_LIST.add(fifthPart);
        CAR_PART_LIST.add(sixthPart);
        CAR_PART_LIST.add(seventhPart);

        Reflection.listWrongCarPart(CAR_PART_LIST);
    }

}
