package xyz.codingmentor.tiborkun.movie.catalog.rest.crud;

import javax.inject.Inject;
import javax.ws.rs.Path;
import xyz.codingmentor.tiborkun.movie.catalog.api.generic.CRUDService;
import xyz.codingmentor.tiborkun.movie.catalog.entity.TrailerEntity;
import xyz.codingmentor.tiborkun.movie.catalog.qualifier.CRUDServiceQualifier;
import xyz.codingmentor.tiborkun.movie.catalog.qualifier.EntityModel;

/**
 *
 * @author Tibor Kun
 */
@Path("/trailers")
public class TrailerCRUDResource extends GeneralCRUDResource<TrailerEntity> {

    @Inject
    public TrailerCRUDResource(@CRUDServiceQualifier(EntityModel.TRAILER) CRUDService<TrailerEntity> crudService) {
        super(crudService);
    }

}
