/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.mle.backend.resttest;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;


import ch.bfh.mle.backend.servicelayer.*;
import org.codehaus.jackson.map.ObjectMapper;
//import ch.bfh.mle.backend.modellayer.Patient;

/**
 * REST Web Service
 *
 * @author Grandmaster
 */
@Path("patient")
public class PatientResource {

    @Context
    private UriInfo context;
    
    private ObjectMapper mapper = new ObjectMapper();

    /**
     * Creates a new instance of PatientResource
     */
    public PatientResource() {
    }

    /**
     * Retrieves representation of an instance of ch.bfh.mle.backend.resttest.PatientResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String getJson() {
      return "<h1>Get some REST!</h1>";
//        try {  
//            PatientDao patients = new PatientDao();          
//            return  mapper.writeValueAsString(patients.read());
//        } catch (Exception e) {
//          throw new UnsupportedOperationException();
//        }  
    }
        

    /**
     * PUT method for updating or creating an instance of PatientResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(String content) {
    }
}
