package xyz.codingmentor.tiborkun.movie.catalog.service.crud;

import javax.ejb.Stateless;
import javax.inject.Inject;
import xyz.codingmentor.tiborkun.movie.catalog.api.generic.CRUDRepository;
import xyz.codingmentor.tiborkun.movie.catalog.api.generic.CRUDService;
import xyz.codingmentor.tiborkun.movie.catalog.entity.MovieEntity;
import xyz.codingmentor.tiborkun.movie.catalog.qualifier.CRUDRepositoryQualifier;
import xyz.codingmentor.tiborkun.movie.catalog.qualifier.CRUDServiceQualifier;
import xyz.codingmentor.tiborkun.movie.catalog.qualifier.EntityModel;

/**
 *
 * @author Tibor Kun
 */
@Stateless
@CRUDServiceQualifier(EntityModel.MOVIE)
public class MovieCRUDService extends GeneralCRUDService<MovieEntity> implements CRUDService<MovieEntity> {

    public MovieCRUDService() {
        super(null);
    }

    @Inject
    public MovieCRUDService(@CRUDRepositoryQualifier(EntityModel.MOVIE) CRUDRepository<MovieEntity> repository) {
        super(repository);
    }

}
