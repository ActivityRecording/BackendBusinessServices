package ch.bfh.mle.backend.rest;

import ch.bfh.mle.backend.model.Patient;
import ch.bfh.mle.backend.model.StandardActivity;
import ch.bfh.mle.backend.service.StandardActivityService;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Stefan Walle
 */
@Stateless
@Path("standardActivities")
public class StandardActivityRessource {
    
    @Context
    private UriInfo context;

    @Inject
    private StandardActivityService srv;

    /**
     * Creates a new instance of StandardActivity
     */
    public StandardActivityRessource() {}

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<StandardActivity> getAll() {
        List<StandardActivity> activities = srv.read();
        return activities;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public StandardActivity get(@PathParam("id") long id) {
        StandardActivity activity = srv.read(id);
        return activity;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/employees/{employeeId}")
    public List<StandardActivity> getByEmployeeId(@PathParam("employeeId") Long employeeId) {
        List<StandardActivity> activities;
        activities = srv.readByEmployeeId(employeeId);
        return activities;
    }
    
}
