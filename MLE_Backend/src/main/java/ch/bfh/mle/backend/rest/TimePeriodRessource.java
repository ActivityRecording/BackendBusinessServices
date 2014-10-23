package ch.bfh.mle.backend.rest;

import ch.bfh.mle.backend.model.TimePeriod;
import ch.bfh.mle.backend.service.TimePeriodService;
import ch.bfh.mle.backend.service.dto.TimePeriodDto;
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
@Path("timePeriods")
public class TimePeriodRessource {

    @Context
    private UriInfo context;

    @Inject
    private TimePeriodService srv;

    /**
     * Creates a new instance of TimePeriod
     */
    public TimePeriodRessource() {}
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(TimePeriodDto dto) {
        srv.create(dto);
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<TimePeriod> getAll() {
        List<TimePeriod> timePeriods = srv.read();
        return timePeriods;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public TimePeriod get(@PathParam("id") long id) {
        TimePeriod timePeriod = srv.read(id);
        return timePeriod;
    }    
}
