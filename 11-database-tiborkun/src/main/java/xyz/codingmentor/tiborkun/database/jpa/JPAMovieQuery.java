package xyz.codingmentor.tiborkun.database.jpa;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import xyz.codingmentor.tiborkun.database.api.MovieQuery;
import xyz.codingmentor.tiborkun.database.entity.MovieEntity;
import xyz.codingmentor.tiborkun.database.entity.MovieStyleEnum;
import xyz.codingmentor.tiborkun.database.exception.RepositoryException;

/**
 *
 * @author Tibor Kun
 */
@Stateless
public class JPAMovieQuery implements MovieQuery {

    private final EntityManagerFactory factory;
    private final EntityManager entityManager;

    public JPAMovieQuery() {
        factory = Persistence.createEntityManagerFactory("homeworkDatabasePU");
        entityManager = factory.createEntityManager();
    }

    @Override
    public List<MovieEntity> getMoviesByTitle(String title) throws RepositoryException {
        String selectQuery = "SELECT m FROM MovieEntity m WHERE m.title LIKE :title";
        TypedQuery<MovieEntity> query = entityManager.createQuery(selectQuery, MovieEntity.class);
        query.setParameter("title", "%" + title + "%");
        return query.getResultList();
    }

    @Override
    public List<MovieEntity> getMoviesAfterDate(Date date) throws RepositoryException {
        String selectQuery = "SELECT m FROM MovieEntity m WHERE m.releaseDate >:date";
        TypedQuery<MovieEntity> query = entityManager.createQuery(selectQuery, MovieEntity.class);
        query.setParameter("date", date);
        return query.getResultList();
    }

    @Override
    public List<MovieEntity> orderMoviesByDate(Date date) throws RepositoryException {
        String selectQuery = "SELECT m FROM MovieEntity m WHERE m.releaseDate >:date ORDER BY m.releaseDate ASC";
        TypedQuery<MovieEntity> query = entityManager.createQuery(selectQuery, MovieEntity.class);
        query.setParameter("date", date);
        return query.getResultList();
    }

    @Override
    public void removeMoviesByDirector(String director) throws RepositoryException {
        String deleteQuery = "DELETE FROM MovieEntity m WHERE m.director = :director";
        Query query = entityManager.createQuery(deleteQuery);
        query.setParameter("director", director);
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        query.executeUpdate();
        tx.commit();
    }

    @Override
    public void updateMoviesStyle(MovieStyleEnum original, MovieStyleEnum goal) throws RepositoryException {
        String updatQuery = "UPDATE MovieEntity m SET m.style = :goal WHERE m.style = :original";
        Query query = entityManager.createQuery(updatQuery);
        query.setParameter("original", original);
        query.setParameter("goal", goal);
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        query.executeUpdate();
        tx.commit();
    }

    @Override
    public void close() {
        entityManager.close();
        factory.close();
    }

}
