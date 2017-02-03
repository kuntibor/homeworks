package xyz.codingmentor.tiborkun.movie.catalog.rest.join;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import xyz.codingmentor.tiborkun.movie.catalog.exception.RepositoryException;
import xyz.codingmentor.tiborkun.movie.catalog.service.join.JoinService;

/**
 *
 * @author Tibor Kun
 */
@Path("/join/movie")
public class JoinResource {

    private JoinService specService;

    public JoinResource() {
        // nothing to initialize
    }

    @Inject
    public JoinResource(JoinService specService) {
        this.specService = specService;
    }

    @GET
    @Path("/actor")
    public Response joinMovieToActor(@QueryParam("movieId") Long movieId, @QueryParam("actorId") Long actorId) throws RepositoryException {
        specService.joinMovieToActor(movieId, actorId);
        return Response.ok().build();
    }

    @GET
    @Path("/category")
    public Response joinMovieToCategory(@QueryParam("movieId") Long movieId, @QueryParam("categoryId") Long categoryId) throws RepositoryException {
        specService.joinMovieToCategory(movieId, categoryId);
        return Response.ok().build();
    }

    @GET
    @Path("/trailer")
    public Response joinMovieToTrailer(@QueryParam("movieId") Long movieId, @QueryParam("trailerId") Long trailerId) throws RepositoryException {
        specService.joinMovieToTrailer(movieId, trailerId);
        return Response.ok().build();
    }

}
