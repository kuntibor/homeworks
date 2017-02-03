package xyz.codingmentor.tiborkun.movie.catalog.repository.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import xyz.codingmentor.tiborkun.movie.catalog.api.generic.CRUDRepository;
import xyz.codingmentor.tiborkun.movie.catalog.exception.RepositoryException;

/**
 *
 * @author Tibor Kun
 * @param <T>
 */
public abstract class AbstractCRUDRepositoryJPA<T> implements CRUDRepository<T> {

    @PersistenceContext(unitName = "JPAOnWebPU")
    private EntityManager entityManager;

    public AbstractCRUDRepositoryJPA() {
        //nothing to initialize
    }

    @Override
    public void persist(T entity) throws RepositoryException {
        getEntityManager().persist(entity);
    }

    @Override
    public T find(Long entityId) throws RepositoryException {
        return getEntityManager().find(getEntityClass(), entityId);
    }

    @Override
    public T merge(T entity) throws RepositoryException {
        return getEntityManager().merge(entity);
    }

    @Override
    public void remove(Long entityId) throws RepositoryException {
        getEntityManager().remove(find(entityId));
    }

    protected EntityManager getEntityManager() {
        return entityManager;
    }

    protected abstract Class<T> getEntityClass();

}
