package xyz.codingmentor.tiborkun.movie.catalog.service.crud;

import javax.ejb.Stateless;
import javax.inject.Inject;
import xyz.codingmentor.tiborkun.movie.catalog.api.generic.CRUDRepository;
import xyz.codingmentor.tiborkun.movie.catalog.api.generic.CRUDService;
import xyz.codingmentor.tiborkun.movie.catalog.entity.ActorEntity;
import xyz.codingmentor.tiborkun.movie.catalog.qualifier.CRUDRepositoryQualifier;
import xyz.codingmentor.tiborkun.movie.catalog.qualifier.CRUDServiceQualifier;
import xyz.codingmentor.tiborkun.movie.catalog.qualifier.EntityModel;

/**
 *
 * @author Tibor Kun
 */
@Stateless
@CRUDServiceQualifier(EntityModel.ACTOR)
public class ActorCRUDService extends GeneralCRUDService<ActorEntity> implements CRUDService<ActorEntity> {

    public ActorCRUDService() {
        super(null);
    }

    @Inject
    public ActorCRUDService(@CRUDRepositoryQualifier(EntityModel.ACTOR) CRUDRepository<ActorEntity> repository) {
        super(repository);
    }

}
