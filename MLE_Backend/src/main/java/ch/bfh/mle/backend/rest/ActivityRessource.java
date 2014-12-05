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
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
     * @param ActivityContainerDto - darf nicht null sein
     */
    @POST
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
     * Gibt alle Leistungen per Behandlungsfall zurueck.
     * @param fid
     * @return List<ActivityDto>
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{fid}")
    public Response get(@PathParam("fid") @NotNull Long fid) {
        List<ActivityDto> dtos = srv.readAllByTreatmentNumber(fid);
        GenericEntity entity = new GenericEntity<List<ActivityDto>>(dtos) {};
        return Response.ok(entity).build();
    }

    /**
     * Gibt alle Leistungen per Behandlungsfall und Leistungsebringer zurueck.
     * @param treatmentNumber
     * @param employeeId
     * @return List<ActivityDto>
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{treatmentNumber}/{employeeId}")
    public Response get(@PathParam("treatmentNumber") @NotNull Long treatmentNumber, @PathParam("employeeId") @NotNull Long employeeId) {
        List<ActivityDto> dtos = srv.readAllByTreatmentAndEmployee(treatmentNumber, employeeId);
        GenericEntity entity = new GenericEntity<List<ActivityDto>>(dtos) {};
        return Response.ok(entity).build();
    }

    /**
     * Gibt alle Leistungen inklusive der automatisch generierten fuer die
     * Freigabe zurueck.
     * @param fid
     * @return List<ActivityDto>
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/approval/{treatmentNr}")
    public Response getApprovalActivites(@PathParam("treatmentNr") @NotNull Long treatmentNr) {
        List<ActivityDto> dtos = srv.getActivitiesForApproval(treatmentNr);
        GenericEntity entity = new GenericEntity<List<ActivityDto>>(dtos) {};
        return Response.ok(entity).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/approval/{treatmentNr}")
    public void approve(@PathParam("treatmentNr") @NotNull Long treatementNr) {
        srv.approveTreatmentCase(treatementNr);
    }
    

   /*
    * Löscht einen Activity-Eintrag in der Datenbank anhand technischen ID
    */
    @DELETE
    @Path("/{aid}")
    public void deleteActivity(@PathParam("aid") @NotNull Long aid){
        srv.deleteActivityById(aid);
    }
    
}
