package xyz.codingmentor.rest.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import xyz.codingmentor.rest.dto.UserEntity;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import xyz.codingmentor.rest.exception.IDisNotMatchException;

/**
 *
 * @author Tibor Kun
 */
@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserRestService {

    private static final Map<String, UserEntity> USERS = new HashMap<>();

    /**
     * http://localhost:8080/08-JavaEE-rest-web-1.0-SNAPSHOT/rest/users
     *
     * @return
     */
    @GET
    public List<UserEntity> getAllUser() {
        return new ArrayList(USERS.values());
    }

    /**
     * http://localhost:8080/08-JavaEE-rest-web-1.0-SNAPSHOT/rest/users/
     * {"email": "new@new.com","password": "1234","address": "1234 XYZ,
     * Codingmentor 1"}
     *
     * @param user
     * @return
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(UserEntity user) {
        user.setId(UUID.randomUUID().toString());
        return Response.ok(USERS.put(user.getId(), user), MediaType.APPLICATION_JSON).build();
    }

    /**
     * http://localhost:8080/08-JavaEE-rest-web-1.0-SNAPSHOT/rest/users/{id} ID
     * for example: 11cd8c8f-9ecb-47fd-b4d4-ec8b21136c3c Use an existing ID
     *
     * @param id
     * @return
     */
    @GET
    @Path("/{id}")
    public Response getUserByID(@PathParam("id") String id) {
        return Response.ok(USERS.get(id), MediaType.APPLICATION_JSON).build();
    }

    /**
     * http://localhost:8080/08-JavaEE-rest-web-1.0-SNAPSHOT/rest/users/{id} Use
     * an existing ID {"email": "new@new.com","password": "1234","address":
     * "1234 XYZ, Codingmentor 1"}
     *
     * @param id
     */
    @PUT
    @Path("/{id}")
    public Response putZserByID(@PathParam("id") String id, UserEntity user) {
        if (user.getId().equals(id)) {
            USERS.replace(id, user);
            return Response.ok(USERS.get(id), MediaType.APPLICATION_JSON).build();
        }
        throw new IDisNotMatchException("Not existing ID");
    }

    /**
     * http://localhost:8080/08-JavaEE-rest-web-1.0-SNAPSHOT/rest/users/{id} Use
     * an existing ID
     *
     * @param id
     * @return
     */
    @DELETE
    @Path("/{id}")
    public Response deleteUserByID(@PathParam("id") String id) {
        return Response.ok(USERS.remove(id), MediaType.APPLICATION_JSON).build();
    }

}
