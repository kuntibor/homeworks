package xyz.codingmentor.tiborkun.webshop.rest;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import xyz.codingmentor.tiborkun.webshop.entities.DeviceEntity;
import xyz.codingmentor.tiborkun.webshop.entities.UserEntity;
import xyz.codingmentor.tiborkun.webshop.exceptions.DeviceIsNotExistException;
import xyz.codingmentor.tiborkun.webshop.services.DeviceDBSingleton;

/**
 *
 * @author Tibor Kun
 */
@Path("/devices")
@Produces(MediaType.APPLICATION_JSON)
public class DeviceRESTService {

    @Inject
    private DeviceDBSingleton devicesSingleton;

    private static final Logger LOGGER = Logger.getLogger(UserRESTService.class.getName());

    /**
     * http://localhost:8080/10-JavaEE-EJB-tiborkun-web/rest/devices csak
     * adminnal bejelenktezve használható
     *
     * @param request
     * @param device
     * @return json a hozzáadott device-ról
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addNewDevice(@Context HttpServletRequest request, DeviceEntity device) {
        checkCredentials(request);
        devicesSingleton.addDevice(device);
        return Response.ok(device, MediaType.APPLICATION_JSON).build();
    }

    /**
     * http://localhost:8080/10-JavaEE-EJB-tiborkun-web/rest/devices csak
     * adminnal bejelenktezve használható
     *
     * @param request
     * @param device
     * @return json a törölt device-ról
     */
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteDevice(@Context HttpServletRequest request, DeviceEntity device) {
        checkCredentials(request);
        DeviceEntity deletedDevice = devicesSingleton.getDevice(device.getId());
        if (null != deletedDevice) {
            devicesSingleton.deleteDevice(deletedDevice);
            return Response.ok(deletedDevice, MediaType.APPLICATION_JSON).build();
        }
        throw new DeviceIsNotExistException(device.getId());
    }

    /**
     * http://localhost:8080/10-JavaEE-EJB-tiborkun-web/rest/devices
     *
     * @return json az összes device-ról
     */
    @GET
    public Response getAllDevice() {
        LOGGER.log(Level.INFO, "Get all device.");
        return Response.ok(devicesSingleton.getAllDevice(), MediaType.APPLICATION_JSON).build();
    }

    /**
     * http://localhost:8080/10-JavaEE-EJB-tiborkun-web/rest/devices/{id}
     *
     * @param id
     * @return json az id-nek megfelelő device-ról
     */
    @GET
    @Path("/{id}")
    public Response getUserByUsername(@PathParam("id") String id) {
        LOGGER.log(Level.INFO, "Get device by id: {0}", id);
        return Response.ok(devicesSingleton.getDevice(id), MediaType.APPLICATION_JSON).build();
    }

    private static void checkCredentials(HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserEntity actualUser = (UserEntity) session.getAttribute(UserRESTService.USER_KEY);
        if (null == actualUser || !actualUser.isAdmin()) {
            throw new IllegalStateException("Not logged in as admin");
        }
    }

}
