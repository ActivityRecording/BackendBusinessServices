package ch.bfh.mle.backend.rest;

import ch.bfh.mle.backend.model.TreatmentCase;
import ch.bfh.mle.backend.service.ActivityService;
import ch.bfh.mle.backend.service.TimePeriodService;
import ch.bfh.mle.backend.service.TreatmentCaseService;
import ch.bfh.mle.backend.service.dto.CumulatedTimeDto;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

/**
 * REST Web Service fuer die Ressource Behandlungsfall
 * 
 * @author Stefan Walle & Boris Haueter
 */
@Stateless
@Path("treatmentCases")
public class TreatmentCaseRessource {

    /**
     * Konstruktor zum Erzeugen einer TreatmentCaseRessource
     */
    public TreatmentCaseRessource() {}
    
    @Context
    private UriInfo context;

    @Inject
    private TreatmentCaseService treatmentSrv;
    
    @Inject
    private TimePeriodService timePeriodSrv;
    
    @Inject
    private ActivityService activitySrv;

    /**
     * Speichert einen Behandlungsfall in der Datenbank.
     * @param TreatmentCase 
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(@NotNull TreatmentCase entity) {
        treatmentSrv.create(entity);
    }

    /**
     * Mutiert einen Behandlungsfall auf der Datenbank.
     * Wird für die Freigabe der Leistungen verwendet
     * @param TreatmentCase
     * @return TreatmentCase - Mutierter Behandlungsfall
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public TreatmentCase update(@NotNull TreatmentCase entity) {
        return treatmentSrv.update(entity);
    }

    /**
     * Gibt einen Behandlungsfall mit id zurueck.
     * @param id
     * @return TreatmentCase
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public TreatmentCase get(@PathParam("id") @NotNull Long id) {
        return treatmentSrv.read(id);
    }

    /**
     * Gibt alle Behandlungsfaelle zurueck.
     * @return List<TreatmentCase>
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<TreatmentCase> getAll() {
        return treatmentSrv.read();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/times/{treatmentNr}/{employeeId}")
    public CumulatedTimeDto getTimes(@PathParam("treatmentNr") @NotNull Long treatementNr, @PathParam("employeeId") @NotNull Long employeeId) {
        Long measuredTime;
        Long allocatedTime;
        measuredTime = timePeriodSrv.getCumulatedTime(treatementNr, employeeId);
        allocatedTime = activitySrv.getCumulatedTime(treatementNr, employeeId);
        
        Long measuredHours;
        Long measuredMinutes;
        Long measuredSeconds;
        Long allocatedHours;
        Long allocatedMinutes;
        Long allocatedSeconds;
        Long rest;
        
        measuredHours = measuredTime / 3600;
        rest = measuredTime % 3600;
        measuredMinutes = rest / 60;
        measuredSeconds = rest % 60;
        
        allocatedHours = allocatedTime / 60;
        allocatedMinutes = allocatedTime % 60;
        allocatedSeconds = 0L;

        return new CumulatedTimeDto(measuredHours, measuredMinutes, measuredSeconds, allocatedHours, allocatedMinutes, allocatedSeconds); 
    }

    /**
     * Loescht den Behandlungsfall mit id von der Datenbank.
     * @param id 
     */
    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") @NotNull Long id) {
        treatmentSrv.delete(id);
    }
   
}
