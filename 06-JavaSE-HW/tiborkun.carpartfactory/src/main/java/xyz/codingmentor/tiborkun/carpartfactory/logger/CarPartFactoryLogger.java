package xyz.codingmentor.tiborkun.carpartfactory.logger;

import java.util.logging.Level;
import java.util.logging.Logger;
import xyz.codingmentor.tiborkun.carpartfactory.entities.CarPart;
import xyz.codingmentor.tiborkun.carpartfactory.annotation.WrongPart;

/**
 *
 * @author teiep
 */
public class CarPartFactoryLogger {

    private static final Logger LOGGER = Logger.getAnonymousLogger();

    private CarPartFactoryLogger() {
        //hide the implicit public constructor
    }

    public static void productionLog(CarPart carPart) {
        LOGGER.log(Level.INFO,
                "{0} {1} were manufactured to {2} at {3}",
                new Object[]{carPart.getCount(), carPart.getType(), carPart.getMadeFor(), carPart.getProductionDate()});
    }

    public static void wrongCarPartLog(WrongPart annotation, CarPart carPart) {
        LOGGER.log(Level.WARNING,
                "{0}: {1} {2}. Made for {3}.",
                new Object[]{annotation.defect(), carPart.getCount(), carPart.getType(), carPart.getMadeFor()});
    }

}
