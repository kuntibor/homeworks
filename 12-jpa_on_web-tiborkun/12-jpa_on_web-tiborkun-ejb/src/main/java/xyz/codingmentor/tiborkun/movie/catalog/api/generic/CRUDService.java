package xyz.codingmentor.tiborkun.movie.catalog.api.generic;

import xyz.codingmentor.tiborkun.movie.catalog.exception.RepositoryException;

/**
 *
 * @author Tibor Kun
 * @param <T>
 */
public interface CRUDService<T> {

    void createEntity(T entity) throws RepositoryException;

    T getEntityById(Long entityId) throws RepositoryException;

    T updateEntity(T entity) throws RepositoryException;

    void removeEntity(Long entityId) throws RepositoryException;

}
