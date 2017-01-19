package xyz.codingmentor.tiborkun.webshop.rest;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import xyz.codingmentor.tiborkun.webshop.entities.DeviceEntity;
import xyz.codingmentor.tiborkun.webshop.services.CartService;

/**
 *
 * @author Tibor Kun
 */
@Path("/cart")
@Produces(MediaType.APPLICATION_JSON)
@SessionScoped
public class CartRESTService implements Serializable {

    @Inject
    private CartService cartService;

    /**
     * http://localhost:8080/10-JavaEE-EJB-tiborkun-web/rest/cart csak
     * bejelenktezve használható
     *
     * @param request
     * @param device
     * @return json a kosárhoz adott device-ról
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addToCart(@Context HttpServletRequest request, DeviceEntity device) {
        checkCredentials(request);
        return Response.ok(cartService.addDevice(device.getId(), device.getCount()), MediaType.APPLICATION_JSON).build();
    }

    /**
     * http://localhost:8080/10-JavaEE-EJB-tiborkun-web/rest/cart csak
     * bejelenktezve használható
     *
     * @param request
     * @param device
     * @return json a kosárból törölt device-ról
     */
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteFromCart(@Context HttpServletRequest request, DeviceEntity device) {
        checkCredentials(request);
        return Response.ok(cartService.deleteDevice(device.getId(), device.getCount()), MediaType.APPLICATION_JSON).build();
    }

    /**
     * http://localhost:8080/10-JavaEE-EJB-tiborkun-web/rest/cart/buy csak
     * bejelenktezve használható
     *
     * @param request
     * @return json a vásárolt termékekről
     */
    @POST
    @Path("/buy")
    public Response buyCart(@Context HttpServletRequest request) {
        checkCredentials(request);
        Response response = Response.ok(cartService.getShoppingCart(), MediaType.APPLICATION_JSON).build();
        cartService.purchase();
        request.getSession().invalidate();
        return response;
    }

    /**
     * http://localhost:8080/10-JavaEE-EJB-tiborkun-web/rest/cart csak
     * bejelenktezve használható
     *
     * @param request
     * @return json a kosár tartalmáról
     */
    @GET
    public Response getCart(@Context HttpServletRequest request) {
        checkCredentials(request);
        return Response.ok(cartService.getShoppingCart(), MediaType.APPLICATION_JSON).build();
    }

    private static void checkCredentials(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute(UserRESTService.USER_KEY) == null) {
            throw new IllegalStateException("You should log in first");
        }
    }

}
