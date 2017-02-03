package xyz.codingmentor.tiborkun.movie.catalog.rest.crud;

import javax.inject.Inject;
import javax.ws.rs.Path;
import xyz.codingmentor.tiborkun.movie.catalog.api.generic.CRUDService;
import xyz.codingmentor.tiborkun.movie.catalog.entity.MovieEntity;
import xyz.codingmentor.tiborkun.movie.catalog.qualifier.CRUDServiceQualifier;
import xyz.codingmentor.tiborkun.movie.catalog.qualifier.EntityModel;

/**
 *
 * @author Tibor Kun
 */
@Path("/movies")
public class MovieCRUDResource extends GeneralCRUDResource<MovieEntity> {

    @Inject
    public MovieCRUDResource(@CRUDServiceQualifier(EntityModel.MOVIE) CRUDService<MovieEntity> crudService) {
        super(crudService);
    }

}
