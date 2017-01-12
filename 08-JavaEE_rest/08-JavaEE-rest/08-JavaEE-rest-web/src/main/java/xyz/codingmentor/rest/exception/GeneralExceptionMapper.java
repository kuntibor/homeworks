package xyz.codingmentor.rest.exception;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Tibor Kun
 */
@Provider
public class GeneralExceptionMapper implements ExceptionMapper<IDisNotMatchException> {

    private static final Logger LOGGER = Logger.getLogger(GeneralExceptionMapper.class.getSimpleName());

    @Override
    public Response toResponse(IDisNotMatchException exception) {
        LOGGER.log(Level.SEVERE, "General Exception", exception);
        return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new ErrorDTO(exception.getMessage() + " - " + exception.getCause()))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

}
