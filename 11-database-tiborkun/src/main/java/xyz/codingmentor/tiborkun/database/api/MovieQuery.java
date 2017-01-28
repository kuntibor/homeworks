package xyz.codingmentor.tiborkun.database.api;

import java.util.Date;
import java.util.List;
import xyz.codingmentor.tiborkun.database.entity.MovieEntity;
import xyz.codingmentor.tiborkun.database.entity.MovieStyleEnum;
import xyz.codingmentor.tiborkun.database.exception.RepositoryException;

/**
 *
 * @author Tibor Kun
 */
public interface MovieQuery {

    List<MovieEntity> getMoviesByTitle(String title) throws RepositoryException;

    List<MovieEntity> getMoviesAfterDate(Date date) throws RepositoryException;

    List<MovieEntity> orderMoviesByDate(Date date) throws RepositoryException;

    void removeMoviesByDirector(String director) throws RepositoryException;

    void updateMoviesStyle(MovieStyleEnum original, MovieStyleEnum goal) throws RepositoryException;

    void close();

}
