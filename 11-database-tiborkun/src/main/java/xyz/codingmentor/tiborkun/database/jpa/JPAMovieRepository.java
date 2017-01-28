package xyz.codingmentor.tiborkun.database.jpa;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import xyz.codingmentor.tiborkun.database.api.MovieRepository;
import xyz.codingmentor.tiborkun.database.exception.RepositoryException;
import xyz.codingmentor.tiborkun.database.entity.MovieEntity;
import xyz.codingmentor.tiborkun.database.entity.MovieId;

/**
 *
 * @author Tibor Kun
 */
@Stateless
public class JPAMovieRepository implements MovieRepository {

    private final EntityManagerFactory factory;
    private final EntityManager entityManager;

    public JPAMovieRepository() {
        factory = Persistence.createEntityManagerFactory("homeworkDatabasePU");
        entityManager = factory.createEntityManager();
    }

    @Override
    public MovieEntity createMovie(MovieId id) throws RepositoryException {
        MovieEntity movie = new MovieEntity();
        movie.setId(id);
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.persist(movie);
        tx.commit();
        return movie;
    }

    @Override
    public MovieEntity findMovie(MovieId id) throws RepositoryException {
        MovieEntity movie = entityManager.find(MovieEntity.class, id);
        if (movie != null) {
            return movie;
        }
        return null;
    }

    @Override
    public void updateMovie(MovieEntity movie) throws RepositoryException {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.merge(movie);
        tx.commit();
    }

    @Override
    public void removeMovie(MovieId id) throws RepositoryException {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        MovieEntity movie = entityManager.find(MovieEntity.class, id);
        if (movie != null) {
            entityManager.remove(movie);
        }
        tx.commit();
    }

    @Override
    public void close() {
        entityManager.close();
        factory.close();
    }

}
