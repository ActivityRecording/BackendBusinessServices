package ch.bfh.mle.backend.rest;

import ch.bfh.mle.backend.service.ApprovalService;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

/**
 * REST Web Service fuer die Ressource Freigabe durch den Leistungserbringer
 * 
 * @author Stefan Walle
 */
@Stateless
@Path("approval")
public class ApprovalRessource {

    /**
     * Konstruktor zum Erzeugen einer ApprovalRessource
     */
    public ApprovalRessource() {}
    
    @Context
    private UriInfo context;

    @Inject
    private ApprovalService approvalSrv;

    /**
     * Speichert einen Zeitraum in der Datenbank.
     * @param employeeId - darf nicht null sein
     * @param treatmentNumber - darf nicht null sein
      */
    @POST
    @Path("/{employeeId}/{treatmentNumber}")
    public void create(@PathParam("employeeId") @NotNull Long employeeId, @PathParam("treatmentNumber")@NotNull Long treatmentNumber) {
        approvalSrv.approve(employeeId, treatmentNumber);
    }
    
    
}
