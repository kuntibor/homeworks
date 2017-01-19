package xyz.codingmentor.tiborkun.webshop.config;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Tibor Kun
 */
@javax.ws.rs.ApplicationPath("/rest")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(xyz.codingmentor.tiborkun.webshop.exception.AuthentaticationExceptionMapper.class);
        resources.add(xyz.codingmentor.tiborkun.webshop.exception.GeneralExceptionMapper.class);
        resources.add(xyz.codingmentor.tiborkun.webshop.rest.CartRESTService.class);
        resources.add(xyz.codingmentor.tiborkun.webshop.rest.DeviceRESTService.class);
        resources.add(xyz.codingmentor.tiborkun.webshop.rest.UserRESTService.class);
    }

}
