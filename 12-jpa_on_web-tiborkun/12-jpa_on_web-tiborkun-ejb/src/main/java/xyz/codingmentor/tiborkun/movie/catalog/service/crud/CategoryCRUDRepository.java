package xyz.codingmentor.tiborkun.movie.catalog.service.crud;

import javax.ejb.Stateless;
import javax.inject.Inject;
import xyz.codingmentor.tiborkun.movie.catalog.api.generic.CRUDRepository;
import xyz.codingmentor.tiborkun.movie.catalog.api.generic.CRUDService;
import xyz.codingmentor.tiborkun.movie.catalog.entity.CategoryEntity;
import xyz.codingmentor.tiborkun.movie.catalog.qualifier.CRUDRepositoryQualifier;
import xyz.codingmentor.tiborkun.movie.catalog.qualifier.CRUDServiceQualifier;
import xyz.codingmentor.tiborkun.movie.catalog.qualifier.EntityModel;

/**
 *
 * @author Tibor Kun
 */
@Stateless
@CRUDServiceQualifier(EntityModel.CATEGORY)
public class CategoryCRUDRepository extends GeneralCRUDService<CategoryEntity> implements CRUDService<CategoryEntity> {

    public CategoryCRUDRepository() {
        super(null);
    }

    @Inject
    public CategoryCRUDRepository(@CRUDRepositoryQualifier(EntityModel.CATEGORY) CRUDRepository<CategoryEntity> repository) {
        super(repository);
    }

}
