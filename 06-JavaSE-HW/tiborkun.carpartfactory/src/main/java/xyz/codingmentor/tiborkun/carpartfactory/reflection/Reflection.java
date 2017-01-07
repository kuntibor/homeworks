package xyz.codingmentor.tiborkun.carpartfactory.reflection;

import java.util.List;
import xyz.codingmentor.tiborkun.carpartfactory.entities.CarPart;
import xyz.codingmentor.tiborkun.carpartfactory.logger.CarPartFactoryLogger;
import xyz.codingmentor.tiborkun.carpartfactory.annotation.WrongPart;

/**
 *
 * @author teiep
 */
public class Reflection {

    private Reflection() {
        //hide the implicit public constructor
    }

    public static void listWrongCarPart(List<CarPart> carParts) {
        for (CarPart carPart : carParts) {
            WrongPart annotation = carPart.getClass().getAnnotation(WrongPart.class);
            if (null != annotation && annotation.madeFor() == carPart.getMadeFor()) {
                CarPartFactoryLogger.wrongCarPartLog(annotation, carPart);
            }
        }
    }

}
