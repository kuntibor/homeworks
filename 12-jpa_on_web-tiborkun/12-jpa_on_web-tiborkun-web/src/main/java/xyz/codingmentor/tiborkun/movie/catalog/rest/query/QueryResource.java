package xyz.codingmentor.tiborkun.movie.catalog.rest.query;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import xyz.codingmentor.tiborkun.movie.catalog.entity.ActorEntity;
import xyz.codingmentor.tiborkun.movie.catalog.entity.MovieEntity;
import xyz.codingmentor.tiborkun.movie.catalog.entity.TrailerEntity;
import xyz.codingmentor.tiborkun.movie.catalog.service.query.QueryService;

/**
 *
 * @author Tibor Kun
 */
@Path("/query")
public class QueryResource {

    private QueryService queryService;

    public QueryResource() {
        // nothing to initialize
    }

    @Inject
    public QueryResource(QueryService queryService) {
        this.queryService = queryService;
    }

    @GET
    @Path("/movies")
    public List<MovieEntity> getMovie(@QueryParam("title") String title, @QueryParam("categoryId") Long categoryId) {
        return queryService.getMovie(categoryId, title);
    }

    @GET
    @Path("/actors")
    public List<ActorEntity> getActor(@QueryParam("movieId") Long movieId, @QueryParam("firstName") String firstName, @QueryParam("lastName") String lastName) {
        return queryService.getActor(movieId, firstName, lastName);
    }

    @GET
    @Path("/actors/nationality/{nat}")
    public List<ActorEntity> getActorByNat(@PathParam("nat") String nat) {
        return queryService.getActorByNat(nat);
    }

    @GET
    @Path("/trailers")
    public List<TrailerEntity> getTrailer(@QueryParam("movieId") Long movieId) {
        return queryService.getTrailer(movieId);
    }

}
