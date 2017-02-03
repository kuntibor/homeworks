package xyz.codingmentor.tiborkun.movie.catalog.repository.jpa;

import javax.ejb.Stateless;
import xyz.codingmentor.tiborkun.movie.catalog.api.generic.CRUDRepository;
import xyz.codingmentor.tiborkun.movie.catalog.entity.ActorEntity;
import xyz.codingmentor.tiborkun.movie.catalog.qualifier.CRUDRepositoryQualifier;
import xyz.codingmentor.tiborkun.movie.catalog.qualifier.EntityModel;

/**
 *
 * @author Tibor Kun
 */
@Stateless
@CRUDRepositoryQualifier(EntityModel.ACTOR)
public class ActorCRUDRepositoryJPA extends AbstractCRUDRepositoryJPA<ActorEntity> implements CRUDRepository<ActorEntity> {

    @Override
    protected Class<ActorEntity> getEntityClass() {
        return ActorEntity.class;
    }

}
