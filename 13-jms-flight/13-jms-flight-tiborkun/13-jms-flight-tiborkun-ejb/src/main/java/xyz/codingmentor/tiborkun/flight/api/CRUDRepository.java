package xyz.codingmentor.tiborkun.flight.api;

import java.util.List;

/**
 *
 * @author Tibor Kun
 * @param <T>
 */
public interface CRUDRepository<T> {

    void persist(T t);

    T find(Long id);

    T merge(T t);

    List<T> getAll();

    void remove(Long id);

}
