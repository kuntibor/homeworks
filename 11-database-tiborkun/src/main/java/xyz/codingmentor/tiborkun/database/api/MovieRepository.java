package xyz.codingmentor.tiborkun.database.api;

import xyz.codingmentor.tiborkun.database.exception.RepositoryException;
import xyz.codingmentor.tiborkun.database.entity.MovieEntity;
import xyz.codingmentor.tiborkun.database.entity.MovieId;

/**
 *
 * @author Tibor Kun
 */
public interface MovieRepository {

    MovieEntity createMovie(MovieId id) throws RepositoryException;

    MovieEntity findMovie(MovieId id) throws RepositoryException;

    void updateMovie(MovieEntity movie) throws RepositoryException;

    void removeMovie(MovieId id) throws RepositoryException;

    void close();

}
