package xyz.codingmentor.tiborkun.movie.catalog.exception;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import xyz.codingmentor.tiborkun.movie.catalog.dto.ErrorDTO;

/**
 *
 * @author Tibor Kun
 */
@Provider
public class RepositoryExceptionMapper implements ExceptionMapper<RepositoryException> {

    private static final Logger LOGGER = Logger.getLogger(RepositoryException.class.getSimpleName());

    @Override
    public Response toResponse(RepositoryException exception) {
        LOGGER.log(Level.SEVERE, "Repository Exception", exception);
        return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new ErrorDTO(exception.getMessage() + " - " + exception.getCause()))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

}
