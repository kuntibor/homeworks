package xyz.codingmentor.tiborkun.webshop.exception;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import xyz.codingmentor.tiborkun.webshop.dto.ErrorDTO;

/**
 *
 * @author Tibor Kun
 */
@Provider
public class AuthentaticationExceptionMapper implements ExceptionMapper<AuthenticationFailureException> {

    private static final Logger LOGGER = Logger.getLogger(AuthentaticationExceptionMapper.class.getSimpleName());

    @Override
    public Response toResponse(AuthenticationFailureException exception) {
        LOGGER.log(Level.SEVERE, "Authentatication Exception", exception);
        return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new ErrorDTO(exception.getMessage() + " - " + exception.getCause()))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

}
