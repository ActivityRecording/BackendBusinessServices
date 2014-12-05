package ch.bfh.mle.backend.rest;

import ch.bfh.mle.backend.model.TimePeriod;
import ch.bfh.mle.backend.service.TimePeriodService;
import ch.bfh.mle.backend.service.dto.TimePeriodDto;
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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

/**
 * REST Web Service fuer die Ressource Zeitraum
 *
 * @author Stefan Walle
 */
@Stateless
@Path("timePeriods")
public class TimePeriodRessource {

    /**
     * Konstruktor fuer die Erzeugung einer TimePeriodRessource
     */
    public TimePeriodRessource() {}

    @Context
    private UriInfo context;

    @Inject
    private TimePeriodService srv;
    
    /**
     * Speichert einen Zeitraum in der Datenbank.
     * @param TimePeriodDto - darf nicht null sein
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(@NotNull TimePeriodDto dto) {
        srv.create(dto);
    }

    /**
     * Gibt alle Zeitraeume zurueck.
     * @return List<TimePeriod>
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<TimePeriod> getAll() {
        List<TimePeriod> timePeriods = srv.read();
        return timePeriods;
    }

    /**
     * Löscht einen TimeDuration-Eintrag in der Datenbank anhand technischen ID
     */
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public void deleteTimePeriodeByID(@PathParam("id") @NotNull Long id) {
        srv.deleteTimePeriodeByID(id);
    }
    
    /**
     * Gibt eine Liste erfasster Zeitraeume fuer einen Behandlungsfall zurueck.
     * @param treatmentNumber - darf nicht null sein
     * @return List<TimePeriodDto>
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{treatmentNumber}")
    public List<TimePeriodDto> getByTreatmentNumber(@PathParam("treatmentNumber") @NotNull Long treatmentNumber) {
        List<TimePeriodDto> timePeriods = srv.readByTreatmentNumber(treatmentNumber);
        return timePeriods;
    }
    
    /**
     * Gibt eine Liste erfasster Zeitraeume fuer einen Behandlungsfall und einen
     * Leistungserbringer zurueck.
     * @param treatmentNumber - darf nicht null sein
     * @param employeeId - darf nicht null sein
     * @return List<TimePeriodDto>
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{treatmentNumber}/{employeeId}")
    public List<TimePeriodDto> getByTreatmentNumberAndEmployee(@PathParam("treatmentNumber") @NotNull Long treatmentNumber, @PathParam("employeeId") @NotNull Long employeeId) {
        List<TimePeriodDto> timePeriods = srv.readByTreatmentNumberAndEmployee(treatmentNumber, employeeId);
        return timePeriods;
    }

}
