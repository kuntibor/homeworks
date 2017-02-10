package xyz.codingmentor.tiborkun.flight.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Singleton;
import xyz.codingmentor.tiborkun.flight.api.CRUDRepository;
import xyz.codingmentor.tiborkun.flight.dto.Flight;
import xyz.codingmentor.tiborkun.flight.exception.FlightIsAlreadyExistException;
import xyz.codingmentor.tiborkun.flight.exception.FlightIsNotExistException;

/**
 *
 * @author Tibor Kun
 */
@Singleton
public class FlightRepository implements CRUDRepository<Flight> {

    private final Map<Long, Flight> flights = new HashMap<>();

    @Override
    public void persist(Flight flight) {
        if (!flights.containsKey(flight.getFlightNumber())) {
            flights.put(flight.getFlightNumber(), flight);
        } else {
            throw new FlightIsAlreadyExistException(flight.getFlightNumber().toString());
        }
    }

    @Override
    public Flight find(Long flightNumber) {
        if (flights.containsKey(flightNumber)) {
            return flights.get(flightNumber);
        }
        throw new FlightIsNotExistException(flightNumber.toString());
    }

    @Override
    public Flight merge(Flight flight) {
        if (flights.containsKey(flight.getFlightNumber())) {
            flights.put(flight.getFlightNumber(), flight);
            return flights.get(flight.getFlightNumber());
        }
        throw new FlightIsNotExistException(flight.getFlightNumber().toString());
    }

    @Override
    public void remove(Long flightNumber) {
        if (flights.containsKey(flightNumber)) {
            flights.remove(flightNumber);
        } else {
            throw new FlightIsNotExistException(flightNumber.toString());
        }
    }

    @Override
    public List<Flight> getAll() {
        return new ArrayList<>(flights.values());
    }

}
