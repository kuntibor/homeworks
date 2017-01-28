package xyz.codingmentor.tiborkun.database.service;

import java.util.Date;
import java.util.List;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.inject.Inject;
import xyz.codingmentor.tiborkun.database.entity.MovieEntity;
import xyz.codingmentor.tiborkun.database.entity.MovieStyleEnum;
import xyz.codingmentor.tiborkun.database.exception.RepositoryException;
import xyz.codingmentor.tiborkun.database.jpa.JPAMovieQuery;

/**
 *
 * @author Tibor Kun
 */
@Stateless
public class MovieQueryService {

    @Inject
    private JPAMovieQuery movieQuery;

    public MovieQueryService() {
        // nothing to initialize
    }

    public List<MovieEntity> getMoviesByTitle(String title) throws RepositoryException {
        return movieQuery.getMoviesByTitle(title);
    }

    public List<MovieEntity> getMoviesAfterDate(Date date) throws RepositoryException {
        return movieQuery.getMoviesAfterDate(date);
    }

    public List<MovieEntity> orderMoviesByDate(Date date) throws RepositoryException {
        return movieQuery.orderMoviesByDate(date);
    }

    public void removeMoviesByDirector(String director) throws RepositoryException {
        movieQuery.removeMoviesByDirector(director);
    }

    public void updateMoviesStyle(MovieStyleEnum original, MovieStyleEnum goal) throws RepositoryException {
        movieQuery.updateMoviesStyle(original, goal);
    }

    @PreDestroy
    private void preDestroy() {
        movieQuery.close();
    }

}
