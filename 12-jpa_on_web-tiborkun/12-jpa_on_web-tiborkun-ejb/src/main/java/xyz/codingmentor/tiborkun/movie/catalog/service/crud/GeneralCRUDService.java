package xyz.codingmentor.tiborkun.movie.catalog.service.crud;

import xyz.codingmentor.tiborkun.movie.catalog.exception.RepositoryException;
import xyz.codingmentor.tiborkun.movie.catalog.api.generic.CRUDRepository;
import xyz.codingmentor.tiborkun.movie.catalog.api.generic.CRUDService;

/**
 *
 * @author Tibor Kun
 * @param <T>
 */
public class GeneralCRUDService<T> implements CRUDService<T> {

    private final CRUDRepository<T> repository;

    public GeneralCRUDService(CRUDRepository<T> repository) {
        this.repository = repository;
    }

    @Override
    public void createEntity(T entity) throws RepositoryException {
        repository.persist(entity);
    }

    @Override
    public T getEntityById(Long entityId) throws RepositoryException {
        return repository.find(entityId);
    }

    @Override
    public T updateEntity(T entity) throws RepositoryException {
        return repository.merge(entity);
    }

    @Override
    public void removeEntity(Long entityId) throws RepositoryException {
        repository.remove(entityId);
    }

}
