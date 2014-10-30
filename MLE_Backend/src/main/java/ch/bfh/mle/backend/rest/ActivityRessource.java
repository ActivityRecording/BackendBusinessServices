package ch.bfh.mle.backend.rest;

import ch.bfh.mle.backend.model.Activity;
import ch.bfh.mle.backend.service.ActivityService;
import ch.bfh.mle.backend.service.dto.ActivityContainerDto;
import ch.bfh.mle.backend.service.dto.ActivityDto;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
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
 * REST Web Service fuer die Ressource Leistung
 *
 * @author Stefan Walle
 */
@Stateless
@Path("activities")
public class ActivityRessource {
 
    /**
     * Konstruktor zum Erzeugen einer ActivityRessource
     */
    public ActivityRessource() {}

    @Context
    private UriInfo context;

    @Inject
    private ActivityService srv;
    
    /**
     * Speichert eine Liste von Leistungen in der Datenbank.
     * @param List<ActivityDto> - darf nicht null sein
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(@NotNull List<ActivityDto> dtos) {
        for (ActivityDto dto : dtos){
            srv.create(dto);
        }
    }

    /**
     * Speichert eine Liste von Leistungen in der Datenbank.
     * @param ActivityContainerDto - darf nicht null sein
     */
    @POST
    @Path("/container")
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(@NotNull ActivityContainerDto dto) {
            srv.create(dto);
    }
    
    /**
     * Gibt alle Leistungen zurueck.
     * @return List<Activity>
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Activity> getAll() {
        return srv.read();
    }
    
    /**
     * Gibt die Leistung mit der entsprechden id zurueck.
     * @param id - darf nicht null sein
     * @return Activity
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Activity get(@PathParam("id") @NotNull Long id) {
        return (Activity) srv.read(id);
    }    
   
}
