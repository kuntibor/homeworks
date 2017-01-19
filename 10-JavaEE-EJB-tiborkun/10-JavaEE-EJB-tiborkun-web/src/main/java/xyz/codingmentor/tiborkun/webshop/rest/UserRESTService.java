package xyz.codingmentor.tiborkun.webshop.rest;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import xyz.codingmentor.tiborkun.webshop.entities.UserEntity;
import xyz.codingmentor.tiborkun.webshop.exception.AuthenticationFailureException;
import xyz.codingmentor.tiborkun.webshop.services.UserDBSingleton;

/**
 *
 * @author Tibor Kun
 */
@Path("/users")
@SessionScoped
@Produces(MediaType.APPLICATION_JSON)
public class UserRESTService implements Serializable {

    public static final String USER_KEY = "user";

    @Inject
    private UserDBSingleton usersSingleton;

    private static final Logger LOGGER = Logger.getLogger(UserRESTService.class.getName());

    /**
     * http://localhost:8080/10-JavaEE-EJB-tiborkun-web/rest/users/login
     *
     * @param request
     * @param user
     * @return json a bejelentkezett user-ről
     */
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(@Context HttpServletRequest request, UserEntity user) {
        if (usersSingleton.authenticate(user.getUsername(), user.getPassword())) {
            HttpSession session = request.getSession(true);
            session.setMaxInactiveInterval(2000);
            session.setAttribute(USER_KEY, usersSingleton.getUser(user.getUsername()));
            LOGGER.log(Level.INFO, "Successful login: {0}", user.getUsername());
            return Response.ok(usersSingleton.getUser(user.getUsername()), MediaType.APPLICATION_JSON).build();
        }
        throw new AuthenticationFailureException(user.getUsername());
    }

    /**
     * http://localhost:8080/10-JavaEE-EJB-tiborkun-web/rest/users/logout
     *
     * @param request
     * @return json a kijelentkezett user-ről
     */
    @POST
    @Path("/logout")
    public Response logout(@Context HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserEntity user = (UserEntity) session.getAttribute(UserRESTService.USER_KEY);
        LOGGER.log(Level.INFO, "Successful logout: {0}", user.getUsername());
        request.getSession().invalidate();
        return Response.ok(user, MediaType.APPLICATION_JSON).build();
    }

    /**
     * http://localhost:8080/10-JavaEE-EJB-tiborkun-web/rest/users/add csak
     * adminnal bejelenktezve használható
     *
     * @param request
     * @param user
     * @return json a hozzáadott user-ről
     * @throws IllegalAccessException
     */
    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addNewUser(@Context HttpServletRequest request, UserEntity user) throws IllegalAccessException {
        HttpSession session = request.getSession();
        UserEntity actualUser = (UserEntity) session.getAttribute(UserRESTService.USER_KEY);
        if (actualUser != null && actualUser.isAdmin()) {
            usersSingleton.addUser(user);
            return Response.ok(user, MediaType.APPLICATION_JSON).build();
        }
        throw new IllegalAccessException();
    }

    /**
     * http://localhost:8080/10-JavaEE-EJB-tiborkun-web/rest/users/delete csak
     * adminnal bejelenktezve használható
     *
     * @param request
     * @param user
     * @return json a törölt user-ről
     * @throws IllegalAccessException
     */
    @POST
    @Path("/delete")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteUser(@Context HttpServletRequest request, UserEntity user) throws IllegalAccessException {
        HttpSession session = request.getSession();
        UserEntity deletedUser = usersSingleton.getUser(user.getUsername());
        UserEntity actualUser = (UserEntity) session.getAttribute(UserRESTService.USER_KEY);
        if (null != deletedUser && null != actualUser && actualUser.isAdmin()) {
            usersSingleton.deleteUser(deletedUser);
            return Response.ok(deletedUser, MediaType.APPLICATION_JSON).build();
        }
        throw new IllegalAccessException();
    }

    /**
     * http://localhost:8080/10-JavaEE-EJB-tiborkun-web/rest/users
     *
     * @return json az összes user-ről
     */
    @GET
    public Response getAllUser() {
        LOGGER.log(Level.INFO, "Get all user.");
        return Response.ok(usersSingleton.getAllUser(), MediaType.APPLICATION_JSON).build();
    }

    /**
     * http://localhost:8080/10-JavaEE-EJB-tiborkun-web/rest/users/{username}
     *
     * @param username
     * @return json usernam-nek megfelelő user-ről
     */
    @GET
    @Path("/{username}")
    public Response getUserByUsername(@PathParam("username") String username) {
        LOGGER.log(Level.INFO, "Get user by username: {0}", username);
        return Response.ok(usersSingleton.getUser(username), MediaType.APPLICATION_JSON).build();
    }

}
