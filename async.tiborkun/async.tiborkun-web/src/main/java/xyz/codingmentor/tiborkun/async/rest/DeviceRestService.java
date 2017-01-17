package xyz.codingmentor.tiborkun.async.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Asynchronous;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import xyz.codingmentor.tiborkun.async.entities.DeviceEntity;
import xyz.codingmentor.tiborkun.async.services.DevicesSingleton;

/**
 *
 * @author Tibor Kun
 */
@Path("/devices")
public class DeviceRestService {

    @Inject
    private DevicesSingleton devicesSingleton;

    /**
     * http://localhost:8080/async.tiborkun-web-1.0-SNAPSHOT/async/devices
     * visszaadja az összes eszközt
     *
     * @return json az össszes eszközről
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDevices() {
        return Response.ok(new ArrayList(devicesSingleton.getDevices().values()), MediaType.APPLICATION_JSON).build();
    }

    /**
     * http://localhost:8080/async.tiborkun-web-1.0-SNAPSHOT/async/devices/load
     * betölti a eszközöket egy json fájlból. Aszinkron, így míg fut,
     * kezdeményezhető más folyamat is a UserRestService-en keresztül.
     */
    @POST
    @Path("/load")
    @Asynchronous
    public void loadDevices() {
        devicesSingleton.loadDevicesFromJson();
    }

    /**
     * http://localhost:8080/async.tiborkun-web-1.0-SNAPSHOT/async/devices/{match}
     * {match} helyére írt string alapján keres az eszközök között Aszinkron,
     * így míg fut, kezdeményezhető más folyamat is a UserRestService-en
     * keresztül.
     *
     * @param match
     * @return egy json a match-re illeszkedő eszközökről
     */
    @GET
    @Path("/{match}")
    @Produces(MediaType.APPLICATION_JSON)
    @Asynchronous
    public Response getMatchedDevices(@PathParam("match") String match) {
        List<DeviceEntity> devices = null;
        try {
            devices = devicesSingleton.getMatchedDevices(match).get();
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(DeviceRestService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.ok(devices, MediaType.APPLICATION_JSON).build();
    }

}
