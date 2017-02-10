package xyz.codingmentor.tiborkun.flight.service;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import xyz.codingmentor.tiborkun.flight.api.CRUDRepository;
import xyz.codingmentor.tiborkun.flight.api.CRUDService;
import xyz.codingmentor.tiborkun.flight.dto.Flight;

/**
 *
 * @author Tibor Kun
 */
@Stateless
public class FlightService implements CRUDService<Flight> {

    @Inject
    private CRUDRepository<Flight> repository;

    @Override
    public void createEntity(Flight flight) {
        repository.persist(flight);
    }

    @Override
    public Flight getEntityById(Long flightNumber) {
        return repository.find(flightNumber);
    }

    @Override
    public Flight updateEntity(Flight flight) {
        return repository.merge(flight);
    }

    @Override
    public void removeEntity(Long flightNumber) {
        repository.remove(flightNumber);
    }

    @Override
    public List<Flight> getAll() {
        return repository.getAll();
    }

}
