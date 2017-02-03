package xyz.codingmentor.tiborkun.movie.catalog.rest.init;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import xyz.codingmentor.tiborkun.movie.catalog.loader.Loader;

/**
 *
 * @author Tibor Kun
 */
@Path("")
public class Init {

    private Loader loader;

    public Init() {
        // nothing to initialize
    }

    @Inject
    public Init(Loader loader) {
        this.loader = loader;
    }

    @GET
    @Path("/load")
    public Response start() {
        loader.init();
        return Response.ok().build();
    }

}
