package ch.bfh.mle.backend.rest;

import ch.bfh.mle.backend.model.Patient;
import ch.bfh.mle.backend.service.PatientService;
import ch.bfh.mle.backend.service.dto.PatientListItemDto;
import ch.bfh.mle.backend.service.dto.PatientWithTreatementCaseDto;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
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
 * REST Web Service
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
    public Patient get(@PathParam("id") long id) {
        Patient patient = srv.read(id);
//        if (patient == null) throw new NotFoundException();
        return patient;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(
        @DefaultValue("0") @QueryParam("state") Integer state) {
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
    public Patient getByPatientNumber(@PathParam("patientNr") Long patientNr ){
        List<Patient> patients;
        patients = srv.readByPatientNumber(patientNr);
        // Patient nicht gefunden
        // if (patients.isEmpty()) return Response.status(Response.Status.NOT_FOUND).build();
        // Mehr als einen Patienten gefunden
        // if (patients.size() > 0) return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        Patient patient = patients.get(0);
        return patient;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/treatment/{treatmentNr}")
    public PatientWithTreatementCaseDto getByTreatment(@PathParam("treatmentNr") Long treatementNr ) {
        List<PatientWithTreatementCaseDto> result;
        result = srv.readByTreatmentNumber(treatementNr);
//        if (result.isEmpty()) throw new NotFoundException();
//        if (result.size() > 0) throw new BadRequestException();
        return result.get(0);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/supplier/{id}")
    public Response getBySupplier(
        @PathParam("id") Long employeeId,
        @DefaultValue("0") @QueryParam("state") Integer state) {
        List<PatientListItemDto> dtos = srv.readByEmployeeId(employeeId, state);
        GenericEntity entity = new GenericEntity<List<PatientListItemDto>>(dtos) {};
        return Response.ok(entity).build();
    }
    
    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        srv.delete(id);
    }
}
