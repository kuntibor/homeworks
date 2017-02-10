package xyz.codingmentor.tiborkun.flight.rest;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import xyz.codingmentor.tiborkun.flight.api.CRUDService;
import xyz.codingmentor.tiborkun.flight.api.FlightNotice;
import xyz.codingmentor.tiborkun.flight.dto.Flight;

/**
 *
 * @author Tibor Kun
 */
@Path("/flights")
public class FlightResource {

    @Inject
    private CRUDService<Flight> flightService;

    @Inject
    private FlightNotice flightNotice;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createFlight(Flight flight) {
        flightService.createEntity(flight);
        return Response.ok(flight, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFlightyById(@PathParam("id") Long flightNumber) {
        return Response.ok(flightService.getEntityById(flightNumber), MediaType.APPLICATION_JSON).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateEntity(Flight flight) {
        Flight updatedFlight = flightService.updateEntity(flight);
        flightNotice.updatedFlight(updatedFlight);
        return Response.ok(updatedFlight, MediaType.APPLICATION_JSON).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteFlightyById(@PathParam("id") Long flightNumber) {
        flightService.removeEntity(flightNumber);
        flightNotice.deletedFligth(flightNumber);
        return Response.ok().build();
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllFlight() {
        GenericEntity<List<Flight>> flights = new GenericEntity<List<Flight>>(flightService.getAll()) {
        };
        return Response.ok(flights).build();
    }

}
