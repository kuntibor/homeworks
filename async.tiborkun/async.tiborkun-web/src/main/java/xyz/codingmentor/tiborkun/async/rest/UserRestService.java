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
import xyz.codingmentor.tiborkun.async.entities.UserEntity;
import xyz.codingmentor.tiborkun.async.services.UsersSingleton;

/**
 *
 * @author Tibor Kun
 */
@Path("/users")
public class UserRestService {

    @Inject
    private UsersSingleton usersSingleton;

    /**
     * http://localhost:8080/async.tiborkun-web/async/users visszaadja az összes
     * felhasználót
     *
     * @return json az össszes felhasználóról
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUser() {
        return Response.ok(new ArrayList(usersSingleton.getUsers().values()), MediaType.APPLICATION_JSON).build();
    }

    /**
     * http://localhost:8080/async.tiborkun-web/async/users/load betölti a
     * felhasználókat egy json fájlból. Aszinkron, így míg fut, kezdeményezhető
     * más folyamat is a DeviceRestService-en keresztül.
     *
     */
    @POST
    @Path("/load")
    @Asynchronous
    public void loadUsers() {
        usersSingleton.loadUsersFromJson();
    }

    /**
     * http://localhost:8080/async.tiborkun-web/async/users/{match} {match}
     * helyére írt string alapján keres a felhasználók Aszinkron, így míg fut,
     * kezdeményezhető más folyamat is a DeviceRestService-en keresztül.
     *
     * @param match
     * @return json a találatokról
     */
    @GET
    @Path("/{match}")
    @Produces(MediaType.APPLICATION_JSON)
    @Asynchronous
    public Response getMatchedUsers(@PathParam("match") String match) {
        List<UserEntity> users = null;
        try {
            users = usersSingleton.getMatchedUsers(match).get();
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(UserRestService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.ok(users, MediaType.APPLICATION_JSON).build();
    }
}
