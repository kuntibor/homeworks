package xyz.codingmentor.tiborkun.movie.catalog.repository.jpa;

import javax.ejb.Stateless;
import xyz.codingmentor.tiborkun.movie.catalog.api.generic.CRUDRepository;
import xyz.codingmentor.tiborkun.movie.catalog.entity.CategoryEntity;
import xyz.codingmentor.tiborkun.movie.catalog.qualifier.CRUDRepositoryQualifier;
import xyz.codingmentor.tiborkun.movie.catalog.qualifier.EntityModel;

/**
 *
 * @author Tibor Kun
 */
@Stateless
@CRUDRepositoryQualifier(EntityModel.CATEGORY)
public class CategoryCRUDRepositoryJPA extends AbstractCRUDRepositoryJPA<CategoryEntity> implements CRUDRepository<CategoryEntity> {

    @Override
    protected Class<CategoryEntity> getEntityClass() {
        return CategoryEntity.class;
    }

}
