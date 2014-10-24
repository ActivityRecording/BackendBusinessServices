package ch.bfh.mle.backend.rest;

import ch.bfh.mle.backend.model.Activity;
import ch.bfh.mle.backend.service.ActivityService;
import ch.bfh.mle.backend.service.dto.ActivityDto;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
@Path("activities")
public class ActivityRessource {

    @Context
    private UriInfo context;

    @Inject
    private ActivityService srv;
 
    /**
     * Creates a new instance of ActivityRessource
     */
    public ActivityRessource() {}
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(List<ActivityDto> dtos) {
        for (ActivityDto dto : dtos){
            srv.create(dto);
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Activity> getAll() {
        return srv.read();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Activity get(@PathParam("id") Long id) {
        return (Activity) srv.read(id);
    }    
   
}
