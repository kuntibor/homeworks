package xyz.codingmentor.tiborkun.movie.catalog.rest.crud;

import javax.inject.Inject;
import javax.ws.rs.Path;
import xyz.codingmentor.tiborkun.movie.catalog.api.generic.CRUDService;
import xyz.codingmentor.tiborkun.movie.catalog.entity.CategoryEntity;
import xyz.codingmentor.tiborkun.movie.catalog.qualifier.CRUDServiceQualifier;
import xyz.codingmentor.tiborkun.movie.catalog.qualifier.EntityModel;

/**
 *
 * @author Tibor Kun
 */
@Path("/categories")
public class CategoryCRUDResource extends GeneralCRUDResource<CategoryEntity> {

    @Inject
    public CategoryCRUDResource(@CRUDServiceQualifier(EntityModel.CATEGORY) CRUDService<CategoryEntity> crudService) {
        super(crudService);
    }

}
