package xyz.codingmentor.tiborkun.movie.catalog.api.generic;

import xyz.codingmentor.tiborkun.movie.catalog.exception.RepositoryException;

/**
 *
 * @author Tibor Kun
 * @param <T>
 */
public interface CRUDRepository<T> {

    void persist(T t) throws RepositoryException;

    T find(Long entityId) throws RepositoryException;

    T merge(T t) throws RepositoryException;

    void remove(Long entityId) throws RepositoryException;

}
