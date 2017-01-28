package xyz.codingmentor.tiborkun.database.service;

import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.inject.Inject;
import xyz.codingmentor.tiborkun.database.api.MovieRepository;
import xyz.codingmentor.tiborkun.database.exception.RepositoryException;
import xyz.codingmentor.tiborkun.database.entity.MovieEntity;
import xyz.codingmentor.tiborkun.database.entity.MovieId;

/**
 *
 * @author Tibor Kun
 */
@Stateless
public class MovieService {

    @Inject
    private MovieRepository movieRepository;

    public MovieService() {
        // nothing to initialize
    }

    public MovieEntity createMovie(MovieId id) throws RepositoryException {
        return movieRepository.createMovie(id);
    }

    public MovieEntity findMovie(MovieId id) throws RepositoryException {
        return movieRepository.findMovie(id);
    }

    public void updateMovie(MovieEntity movie) throws RepositoryException {
        movieRepository.updateMovie(movie);
    }

    public void removeMovie(MovieId id) throws RepositoryException {
        movieRepository.removeMovie(id);
    }

    @PreDestroy
    private void preDestroy() {
        movieRepository.close();
    }

}
