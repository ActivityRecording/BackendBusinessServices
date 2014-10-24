package ch.bfh.mle.backend.rest;

import ch.bfh.mle.backend.model.StandardActivity;
import ch.bfh.mle.backend.service.StandardActivityService;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

/**
 * REST Web Service fuer die Ressource Standard-Leistung
 *
 * @author Stefan Walle
 */
@Stateless
@Path("standardActivities")
public class StandardActivityRessource {

    /**
     * Konstruktor zum Erzeugen einer StandardActivityRessource
     */
    public StandardActivityRessource() {}
    
    @Context
    private UriInfo context;

    @Inject
    private StandardActivityService srv;

    /**
     * Gibt alle Standard-Leistungen zurueck.
     * @return List<StandardActivity>
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<StandardActivity> getAll() {
        List<StandardActivity> activities = srv.read();
        return activities;
    }

    /**
     * Gibt die Standard-Leistung mit id zurueck.
     * @param id - dafr nicht null sein.
     * @return StandardActivity
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public StandardActivity get(@PathParam("id") @NotNull Long id) {
        StandardActivity activity = srv.read(id);
        return activity;
    }

    /**
     * Gibt den Standardkatalog fur den Leistungserbringer mit der 
     * Mitarbeiternummer employeeId zurueck.
     * @param employeeId Mitarbeiternummer - darf nicht null sein.
     * @return List<StandardActivity>
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/supplier/{employeeId}")
    public List<StandardActivity> getByEmployeeId(@PathParam("employeeId") @NotNull Long employeeId) {
        List<StandardActivity> activities;
        activities = srv.readByEmployeeId(employeeId);
        return activities;
    }
    
}
