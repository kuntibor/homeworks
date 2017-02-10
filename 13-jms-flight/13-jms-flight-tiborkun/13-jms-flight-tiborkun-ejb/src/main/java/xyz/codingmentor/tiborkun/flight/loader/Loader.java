package xyz.codingmentor.tiborkun.flight.loader;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.util.Calendar;
import xyz.codingmentor.tiborkun.flight.api.CRUDService;
import xyz.codingmentor.tiborkun.flight.dto.Flight;

/**
 *
 * @author Tibor Kun
 */
@Singleton
@Startup
public class Loader {

    @Inject
    private CRUDService<Flight> flightService;

    private List<Flight> flightListFromJson = null;
    private ObjectMapper mapper;
    private JavaType flightType;
    private Calendar departureDate;
    private Calendar arrivalDate;
    private static final Logger LOGGER = Logger.getLogger(Loader.class.getName());

    @PostConstruct
    public void init() {
        LOGGER.info("Loading entities from json...");
        mapper = new ObjectMapper();
        flightType = mapper.getTypeFactory().constructCollectionType(List.class, Flight.class);
        loadEntitiesFromJson();
        fillEntities();
        LOGGER.info("Loading completed");
    }

    private void loadEntitiesFromJson() {
        try {
            flightListFromJson = mapper.readValue(new File(getClass().getClassLoader().getResource("flights.json").getFile()), flightType);
        } catch (IOException ex) {
            Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void fillEntities() {
        departureDate = Calendar.getInstance();
        departureDate.add(Calendar.MINUTE, 60);
        arrivalDate = Calendar.getInstance();
        for (Flight flight : flightListFromJson) {
            departureDate.add(Calendar.MINUTE, 1);
            arrivalDate.add(Calendar.HOUR, 1);
            flight.setDepartureDate(departureDate.getTime());
            flight.setArrivalDate(arrivalDate.getTime());
            flightService.createEntity(flight);
        }
    }

}
