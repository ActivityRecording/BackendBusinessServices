package ch.bfh.mle.backend.rest;

import ch.bfh.mle.backend.model.TimePeriod;
import ch.bfh.mle.backend.service.TimePeriodService;
import ch.bfh.mle.backend.service.dto.TimePeriodDto;
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
    @Path("/treatment/")
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
     * Gibt den Zeitraum mit id zurueck.
     * @param id
     * @return TimePeriod
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public TimePeriod get(@PathParam("id") @NotNull Long id) {
        TimePeriod timePeriod = srv.read(id);
        return timePeriod;
    }  
    
    /**
     * Gibt eine Liste erfasster Zeitraeume fuer einen Behandlungsfall zurueck.
     * @param treatmentNumber - darf nicht null sein
     * @return List<TimePeriodDto>
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/treatment/{treatmentNumber}")
    public List<TimePeriodDto> getByTreatmentNumber(@PathParam("treatmentNumber") @NotNull Long treatmentNumber) {
        List<TimePeriodDto> timePeriods = srv.readByTreatmentNumber(treatmentNumber);
        return timePeriods;
    }    
    
}
