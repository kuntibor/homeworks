package xyz.codingmentor.tiborkun.movie.catalog.config;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Tibor Kun
 */
@javax.ws.rs.ApplicationPath("rest")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method. It is automatically
     * populated with all resources defined in the project. If required, comment
     * out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(xyz.codingmentor.tiborkun.movie.catalog.exception.GeneralExceptionMapper.class);
        resources.add(xyz.codingmentor.tiborkun.movie.catalog.exception.RepositoryExceptionMapper.class);
        resources.add(xyz.codingmentor.tiborkun.movie.catalog.rest.crud.ActorCRUDResource.class);
        resources.add(xyz.codingmentor.tiborkun.movie.catalog.rest.crud.CategoryCRUDResource.class);
        resources.add(xyz.codingmentor.tiborkun.movie.catalog.rest.crud.MovieCRUDResource.class);
        resources.add(xyz.codingmentor.tiborkun.movie.catalog.rest.crud.TrailerCRUDResource.class);
        resources.add(xyz.codingmentor.tiborkun.movie.catalog.rest.init.Init.class);
        resources.add(xyz.codingmentor.tiborkun.movie.catalog.rest.join.JoinResource.class);
        resources.add(xyz.codingmentor.tiborkun.movie.catalog.rest.query.QueryResource.class);
    }

}
