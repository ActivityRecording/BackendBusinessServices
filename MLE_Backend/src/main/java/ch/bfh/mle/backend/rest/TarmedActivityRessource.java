/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.mle.backend.rest;

import ch.bfh.mle.backend.model.TarmedActivity;
import ch.bfh.mle.backend.service.TarmedActivityService;
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
 * REST Web Service fuer  Tarmed Leistungen
 *
 * @author  Boris Haueter
 */    
@Stateless
@Path("tarmedActivities")
public class TarmedActivityRessource {
    /**
     * Konstruktor fuer die Erzeugung einer TimePeriodRessource
     */
    public TarmedActivityRessource() {}

    @Context
    private UriInfo context;
    
    @Inject
    private TarmedActivityService srv;;

    /**
     * Gibt alle Zeitraeume zurueck.
     * @return Liste von TimePeriod
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<TarmedActivity> getAll() {
        List<TarmedActivity> tarmedActivities = srv.read();
        return tarmedActivities;
    }
}
