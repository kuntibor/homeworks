package xyz.codingmentor.tiborkun.movie.catalog.repository.jpa;

import javax.ejb.Stateless;
import xyz.codingmentor.tiborkun.movie.catalog.api.generic.CRUDRepository;
import xyz.codingmentor.tiborkun.movie.catalog.entity.TrailerEntity;
import xyz.codingmentor.tiborkun.movie.catalog.qualifier.CRUDRepositoryQualifier;
import xyz.codingmentor.tiborkun.movie.catalog.qualifier.EntityModel;

/**
 *
 * @author Tibor Kun
 */
@Stateless
@CRUDRepositoryQualifier(EntityModel.TRAILER)
public class TrailerCRUDRepositoryJPA extends AbstractCRUDRepositoryJPA<TrailerEntity> implements CRUDRepository<TrailerEntity> {

    @Override
    protected Class<TrailerEntity> getEntityClass() {
        return TrailerEntity.class;
    }

}
