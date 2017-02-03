package xyz.codingmentor.tiborkun.movie.catalog.repository.jpa;

import javax.ejb.Stateless;
import xyz.codingmentor.tiborkun.movie.catalog.api.generic.CRUDRepository;
import xyz.codingmentor.tiborkun.movie.catalog.entity.MovieEntity;
import xyz.codingmentor.tiborkun.movie.catalog.qualifier.CRUDRepositoryQualifier;
import xyz.codingmentor.tiborkun.movie.catalog.qualifier.EntityModel;

/**
 *
 * @author Tibor Kun
 */
@Stateless
@CRUDRepositoryQualifier(EntityModel.MOVIE)
public class MovieCRUDRepositoryJPA extends AbstractCRUDRepositoryJPA<MovieEntity> implements CRUDRepository<MovieEntity> {

    @Override
    protected Class<MovieEntity> getEntityClass() {
        return MovieEntity.class;
    }

}
