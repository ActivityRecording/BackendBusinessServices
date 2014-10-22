/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.mle.backend.rest;

import ch.bfh.mle.backend.model.StandardActivity;
import ch.bfh.mle.backend.model.TimePeriod;
import ch.bfh.mle.backend.service.StandardActivityService;
import ch.bfh.mle.backend.service.TimePeriodService;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
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

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<TimePeriod> getAll() {
        List<TimePeriod> activities = srv.read();
        return activities;
    }
    
}
