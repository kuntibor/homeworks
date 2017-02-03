package xyz.codingmentor.tiborkun.movie.catalog.service.crud;

import javax.ejb.Stateless;
import javax.inject.Inject;
import xyz.codingmentor.tiborkun.movie.catalog.api.generic.CRUDRepository;
import xyz.codingmentor.tiborkun.movie.catalog.api.generic.CRUDService;
import xyz.codingmentor.tiborkun.movie.catalog.entity.TrailerEntity;
import xyz.codingmentor.tiborkun.movie.catalog.qualifier.CRUDRepositoryQualifier;
import xyz.codingmentor.tiborkun.movie.catalog.qualifier.CRUDServiceQualifier;
import xyz.codingmentor.tiborkun.movie.catalog.qualifier.EntityModel;

/**
 *
 * @author Tibor Kun
 */
@Stateless
@CRUDServiceQualifier(EntityModel.TRAILER)
public class TrailerCRUDService extends GeneralCRUDService<TrailerEntity> implements CRUDService<TrailerEntity> {

    public TrailerCRUDService() {
        super(null);
    }

    @Inject
    public TrailerCRUDService(@CRUDRepositoryQualifier(EntityModel.TRAILER) CRUDRepository<TrailerEntity> repository) {
        super(repository);
    }

}
