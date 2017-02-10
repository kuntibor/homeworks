package xyz.codingmentor.tiborkun.flight.api;

import java.util.List;

/**
 *
 * @author Tibor Kun
 * @param <T>
 */
public interface CRUDService<T> {

    void createEntity(T t);

    T getEntityById(Long id);

    T updateEntity(T t);

    List<T> getAll();

    void removeEntity(Long id);

}
