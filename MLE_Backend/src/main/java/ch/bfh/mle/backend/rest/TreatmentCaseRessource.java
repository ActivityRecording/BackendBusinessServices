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
 * @author Stefan Walle - Boris Haueter
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
     * @param entity
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(@NotNull TreatmentCase entity) {
        treatmentSrv.create(entity);
    }

    /**
     * Mutiert einen Behandlungsfall auf der Datenbank.
     * Wird für die Freigabe der Leistungen verwendet
     * @param entity
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
     * @return Liste von TreatmentCase
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<TreatmentCase> getAll() {
        return treatmentSrv.read();
    }

    /**
     * Gibt die kumulierten geleisteten und erfassten Zeiten fuer einen
     * Leistungserbringer zu einem Behandlungsfall zurueck.
     * @param treatementNr
     * @param employeeId
     * @return CumulatedTimeDto
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/times/{treatmentNr}/{employeeId}")
    public CumulatedTimeDto getTimeForTreatmentCaseAndEmployee(@PathParam("treatmentNr") @NotNull Long treatementNr, @PathParam("employeeId") @NotNull Long employeeId) {
        Long measuredTime;
        Long allocatedTime;
        measuredTime = timePeriodSrv.getCumulatedTime(treatementNr, employeeId);
        allocatedTime = activitySrv.getCumulatedTime(treatementNr, employeeId);
        return new CumulatedTimeDto(measuredTime, allocatedTime); 
    }

    /**
     * Gibt die kumulierten geleisteten und erfassten Zeiten fuer einen
     * Behandlungsfall zurueck.
     * @param treatementNr
     * @return CumulatedTimeDto
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/times/{treatmentNr}")
    public CumulatedTimeDto getTimeForTreatmentCase(@PathParam("treatmentNr") @NotNull Long treatementNr) {
        Long measuredTime;
        Long allocatedTime;
        measuredTime = timePeriodSrv.getCumulatedTimeForTreatmentCase(treatementNr);
        allocatedTime = activitySrv.getCumulatedTimeForTreatmentCase(treatementNr);
        return new CumulatedTimeDto(measuredTime, allocatedTime); 
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
