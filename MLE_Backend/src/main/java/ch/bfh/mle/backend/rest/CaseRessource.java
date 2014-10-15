/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.mle.backend.rest;

import ch.bfh.mle.backend.model.Behandlungsfall;
import ch.bfh.mle.backend.service.CaseService;
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
public class CaseRessource {
    
    @Context
    private UriInfo context;

    @Inject
    private CaseService srv;

    /**
     * Creates a new instance of PatientResource
     */
    public CaseRessource() {}

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Behandlungsfall create(Behandlungsfall entity) {
        return srv.create();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Behandlungsfall update(Behandlungsfall entity) {
        return srv.update(entity);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Behandlungsfall get(@PathParam("id") long id) {
        return srv.read(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Behandlungsfall> getAll() {
        return srv.read();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public void delete(Behandlungsfall entity) {
        srv.delete(entity);
    }
}
