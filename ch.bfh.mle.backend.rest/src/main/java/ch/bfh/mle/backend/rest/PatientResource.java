package ch.bfh.mle.backend.rest;

import ch.bfh.mle.backend.modellayer.Patient;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Stefan
 */
@Path("patient")
public class PatientResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of PatientResource
     */
    public PatientResource() {
    }
  
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String sayPlainTextHello() {
        return "Hello Stefan";
    }

    /**
     * Retrieves representation of an instance of ch.bfh.mle.backend.rest.PatientResource
     * @return an instance of ch.bfh.mle.backend.modellayer.Patient
     */
//    @GET
//    @Produces("application/json")
//    public Patient getJson() {
//        return new Patient();
//    }

    /**
     * PUT method for updating or creating an instance of PatientResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(Patient content) {
    }
}
