package ch.bfh.mle.backend.rest;

import ch.bfh.mle.backend.model.Patient;
import ch.bfh.mle.backend.service.PatientService;
import ch.bfh.mle.backend.service.dto.PatientListItemDto;
import ch.bfh.mle.backend.service.dto.PatientWithTreatementCaseDto;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service fuer Ressource Patient
 *
 * @author Stefan & Boris
 */
@Stateless
@Path("patients")
public class PatientResource {

    @Context
    private UriInfo context;

    @Inject
    private PatientService srv;

    /**
     * Creates a new instance of PatientResource
     */
    public PatientResource() {}

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(Patient entity) {
        srv.create(entity);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Patient update(Patient entity) {
        return srv.update(entity);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Patient get(@PathParam("id") @NotNull Long id) {
        return srv.read(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(
        @DefaultValue("0") @QueryParam("state") @NotNull Integer state) {
        if(state < 0 || state > 2) {
            //Status ungueltig -> return HTTP Status 400
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        List<PatientListItemDto> dtos;
        dtos = srv.readAll(state);
        GenericEntity entity = new GenericEntity<List<PatientListItemDto>>(dtos) {};
        return Response.ok(entity).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/patient/{patientNr}")
    public Patient getByPatientNumber(@PathParam("patientNr") @NotNull Long patientNr ){
        return srv.readByPatientNumber(patientNr);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/treatment/{treatmentNr}")
    public PatientWithTreatementCaseDto getByTreatment(@PathParam("treatmentNr") @NotNull Long treatementNr ) {
        return srv.readByTreatmentNumber(treatementNr);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/supplier/{employeeId}")
    public Response getBySupplier(
        @PathParam("employeeId") @NotNull Long employeeId,
        @DefaultValue("0") @QueryParam("state") @NotNull Integer state) {
        if(state < 0 || state > 2) {
            //Status ungueltig -> return HTTP Status 400
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        List<PatientListItemDto> dtos = srv.readByEmployeeId(employeeId, state);
        GenericEntity entity = new GenericEntity<List<PatientListItemDto>>(dtos) {};
        return Response.ok(entity).build();
    }
    
    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") @NotNull Long id) {
        srv.delete(id);
    }
}
