package xyz.codingmentor.tiborkun.movie.catalog.rest.crud;

import javax.inject.Inject;
import javax.ws.rs.Path;
import xyz.codingmentor.tiborkun.movie.catalog.api.generic.CRUDService;
import xyz.codingmentor.tiborkun.movie.catalog.entity.ActorEntity;
import xyz.codingmentor.tiborkun.movie.catalog.qualifier.CRUDServiceQualifier;
import xyz.codingmentor.tiborkun.movie.catalog.qualifier.EntityModel;

/**
 *
 * @author Tibor Kun
 */
@Path("/actors")
public class ActorCRUDResource extends GeneralCRUDResource<ActorEntity> {

    @Inject
    public ActorCRUDResource(@CRUDServiceQualifier(EntityModel.ACTOR) CRUDService<ActorEntity> crudService) {
        super(crudService);
    }

}
