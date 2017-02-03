package xyz.codingmentor.tiborkun.movie.catalog.rest.crud;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import xyz.codingmentor.tiborkun.movie.catalog.api.generic.CRUDService;
import xyz.codingmentor.tiborkun.movie.catalog.exception.RepositoryException;
import xyz.codingmentor.tiborkun.movie.catalog.api.CRUDResource;

/**
 *
 * @author Tibor Kun
 * @param <T>
 */
public class GeneralCRUDResource<T> implements CRUDResource<T> {

    private final CRUDService<T> crudService;

    public GeneralCRUDResource(CRUDService<T> crudService) {
        this.crudService = crudService;
    }

    @Override
    public Response createEntity(T entity) throws RepositoryException {
        crudService.createEntity(entity);
        return Response.ok(entity, MediaType.APPLICATION_JSON).build();
    }

    @Override
    public Response getEntityById(Long entityId) throws RepositoryException {
        return Response.ok(crudService.getEntityById(entityId), MediaType.APPLICATION_JSON).build();
    }

    @Override
    public Response updateEntity(T entity) throws RepositoryException {
        crudService.updateEntity(entity);
        return Response.ok(entity, MediaType.APPLICATION_JSON).build();
    }

    @Override
    public Response deleteEntityById(Long entityId) throws RepositoryException {
        crudService.removeEntity(entityId);
        return Response.ok().build();
    }

}
