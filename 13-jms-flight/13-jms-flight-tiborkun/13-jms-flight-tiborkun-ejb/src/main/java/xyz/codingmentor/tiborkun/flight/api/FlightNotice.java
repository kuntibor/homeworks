package xyz.codingmentor.tiborkun.flight.api;

import xyz.codingmentor.tiborkun.flight.dto.Flight;

/**
 *
 * @author Tibor Kun
 */
public interface FlightNotice {

    void deletedFligth(Long flightNumber);

    void updatedFlight(Flight flight);

    void startsInAnHour();
}
