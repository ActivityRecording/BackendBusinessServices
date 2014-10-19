/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.mle.backend.rest;

import ch.bfh.mle.backend.model.Patient;
import ch.bfh.mle.backend.service.PatientService;
import ch.bfh.mle.backend.service.dto.PatientListItemDto;
import java.util.Collection;
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
import javax.ws.rs.NotFoundException;
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
        List<PatientListItemDto> dtos = srv.readAll(state);
        GenericEntity entity = new GenericEntity<List<PatientListItemDto>>(dtos) {};
        return Response.ok(entity).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/patient/{nr}")
    public Patient getByPatientNumber(@PathParam("nr") Long nr ){
        List<Patient> patients = srv.readByPatientNumber(nr);
        if (patients.isEmpty()) throw new NotFoundException();
//        if (patients.size() > 0) throw new NotFoundException();
        return patients.get(0);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/treatment/{nr}")
    public Patient getByTreatment(@PathParam("nr") Long nr ) {
        List<Patient> patients = srv.readByTreatmentNumber(nr);
//        if (patients.isEmpty()) throw new NotFoundException();
//        if (patients.size() > 0) throw new BadRequestException();
        return patients.get(0);
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
