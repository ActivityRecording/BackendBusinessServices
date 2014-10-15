/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.mle.backend.rest;

import ch.bfh.mle.backend.model.TreatmentCase;
import ch.bfh.mle.backend.service.TreatmentCaseService;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.inject.Inject;
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
@Path("cases")
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
    @Produces(MediaType.APPLICATION_JSON)
    public TreatmentCase create(TreatmentCase entity) {
        return srv.create();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public TreatmentCase update(TreatmentCase entity) {
        return srv.update(entity);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public TreatmentCase get(@PathParam("id") long id) {
        return srv.read(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<TreatmentCase> getAll() {
        return srv.read();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public void delete(TreatmentCase entity) {
        srv.delete(entity);
    }
}
