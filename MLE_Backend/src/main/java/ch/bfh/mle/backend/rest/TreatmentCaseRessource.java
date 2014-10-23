package ch.bfh.mle.backend.rest;

import ch.bfh.mle.backend.model.TreatmentCase;
import ch.bfh.mle.backend.service.TreatmentCaseService;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
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
 *
 * @author haueb1@students.bfh.ch
 */
@Stateless
@Path("treatmentCases")
public class TreatmentCaseRessource {
    
    @Context
    private UriInfo context;

    @Inject
    private TreatmentCaseService srv;

    /**
     * Creates a new instance of PatientResource
     */
    public TreatmentCaseRessource() {}

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(TreatmentCase entity) {
        srv.create(entity);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public TreatmentCase update(TreatmentCase entity) {
        return srv.update(entity);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public TreatmentCase get(@PathParam("id") Long id) {
        return srv.read(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<TreatmentCase> getAll() {
        return srv.read();
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") Long id) {
        srv.delete(id);
    }
}
