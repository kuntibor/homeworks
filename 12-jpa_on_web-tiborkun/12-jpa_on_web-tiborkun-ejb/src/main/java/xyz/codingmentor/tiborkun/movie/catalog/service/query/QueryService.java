package xyz.codingmentor.tiborkun.movie.catalog.service.query;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import xyz.codingmentor.tiborkun.movie.catalog.entity.ActorEntity;
import xyz.codingmentor.tiborkun.movie.catalog.entity.MovieEntity;
import xyz.codingmentor.tiborkun.movie.catalog.entity.TrailerEntity;

/**
 *
 * @author Tibor Kun
 */
@Stateless
public class QueryService {

    @PersistenceContext(unitName = "JPAOnWebPU")
    private EntityManager entityManager;

    private String movieIdparameter = "movieId";

    public List<MovieEntity> getMovie(Long categoryId, String title) {
        if (null != categoryId && null != title) {
            return getMovieByCatANDTitle(categoryId, title);
        }
        if (null != categoryId) {
            return getMovieByCategory(categoryId);
        }
        if (null != title) {
            return getMovieByTitle(title);
        }
        throw new IllegalArgumentException("You schould give some criterias");
    }

    private List<MovieEntity> getMovieByCatANDTitle(Long categoryId, String title) {
        String selectQuery = "SELECT m FROM MovieEntity m WHERE m.category.id = :categoryId AND m.title = :title";
        TypedQuery<MovieEntity> query = entityManager.createQuery(selectQuery, MovieEntity.class);
        query.setParameter("categoryId", categoryId);
        query.setParameter("title", title);
        return query.getResultList();
    }

    private List<MovieEntity> getMovieByCategory(Long categoryId) {
        String selectQuery = "SELECT m FROM MovieEntity m WHERE m.category.id = :categoryId";
        TypedQuery<MovieEntity> query = entityManager.createQuery(selectQuery, MovieEntity.class);
        query.setParameter("categoryId", categoryId);
        return query.getResultList();
    }

    private List<MovieEntity> getMovieByTitle(String title) {
        String selectQuery = "SELECT m FROM MovieEntity m WHERE m.title = :title";
        TypedQuery<MovieEntity> query = entityManager.createQuery(selectQuery, MovieEntity.class);
        query.setParameter("title", title);
        return query.getResultList();
    }

    public List<ActorEntity> getActor(Long movieId, String firstName, String lastName) {
        boolean isName = null != firstName && null != lastName;
        boolean isMovieId = null != movieId;
        if (isName) {
            return actorQuerySelector(isMovieId, movieId, firstName, lastName);
        }
        if (isMovieId) {
            return getActorByMovie(movieId);
        }
        throw new IllegalArgumentException("You schould give some criterias");
    }

    public List<ActorEntity> actorQuerySelector(boolean isMovieId, Long movieId, String firstName, String lastName) {
        if (isMovieId) {
            return getActorByMovieAndName(movieId, firstName, lastName);
        } else {
            return getActorByName(firstName, lastName);
        }
    }

    public List<ActorEntity> getActorByMovieAndName(Long movieId, String firstName, String lastName) {
        String selectQuery = "select distinct a from ActorEntity a inner join a.movies m where m.id = :movieId and a.firstName = :firstName and a.lastName = :lastName";
        TypedQuery<ActorEntity> query = entityManager.createQuery(selectQuery, ActorEntity.class
        );
        query.setParameter(movieIdparameter, movieId);
        query.setParameter("firstName", firstName);
        query.setParameter("lastName", lastName);
        return query.getResultList();
    }

    public List<ActorEntity> getActorByName(String firstName, String lastName) {
        String selectQuery = "select distinct a from ActorEntity a inner join a.movies m where a.firstName = :firstName and a.lastName = :lastName";
        TypedQuery<ActorEntity> query = entityManager.createQuery(selectQuery, ActorEntity.class
        );
        query.setParameter("firstName", firstName);
        query.setParameter("lastName", lastName);
        return query.getResultList();
    }

    public List<ActorEntity> getActorByMovie(Long movieId) {
        String selectQuery = "select distinct a from ActorEntity a inner join a.movies m where m.id = :movieId";
        TypedQuery<ActorEntity> query = entityManager.createQuery(selectQuery, ActorEntity.class
        );
        query.setParameter(movieIdparameter, movieId);
        return query.getResultList();
    }

    public List<ActorEntity> getActorByNat(String nationality) {
        String selectQuery = "select a from ActorEntity a where a.nationality = :nationality";
        TypedQuery<ActorEntity> query = entityManager.createQuery(selectQuery, ActorEntity.class
        );
        query.setParameter("nationality", nationality);
        return query.getResultList();
    }

    public List<TrailerEntity> getTrailer(Long movieId) {
        String selectQuery = "select t from TrailerEntity t where t.movie.id = :movieId";
        TypedQuery<TrailerEntity> query = entityManager.createQuery(selectQuery, TrailerEntity.class
        );
        query.setParameter(movieIdparameter, movieId);
        return query.getResultList();
    }

}
