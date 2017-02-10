package xyz.codingmentor.tiborkun.flight.exception;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import xyz.codingmentor.tiborkun.flight.dto.ErrorDTO;

/**
 *
 * @author Tibor Kun
 */
@Provider
public class FlightIsAlreadyExistExceptionMapper implements ExceptionMapper<FlightIsAlreadyExistException> {

    private static final Logger LOGGER = Logger.getLogger(FlightIsAlreadyExistException.class.getSimpleName());

    @Override
    public Response toResponse(FlightIsAlreadyExistException exception) {
        LOGGER.log(Level.SEVERE, "Flight is already exist exception ", exception);
        return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new ErrorDTO(exception.getMessage() + " - " + exception.getCause()))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

}
