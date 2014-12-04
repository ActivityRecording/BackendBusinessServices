package ch.bfh.mle.backend.rest;

import ch.bfh.mle.backend.model.Supplier;
import ch.bfh.mle.backend.service.SupplierService;
import java.util.Collections;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service fuer die Ressource Leistungserbringer
 *
 * @author Stefan Walle
 */
@Stateless
@Path("suppliers")
public class SupplierRessource {

    /**
     * Erstellt eine neue Instanz von SupplierRessource
     */
    public SupplierRessource() {
    }

    @Inject
    private SupplierService srv;

    /**
     * Gibt alle Leistungserbringer zurueck.
     * @return List<Supplier>
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Supplier> getAll() {
        List<Supplier> result = srv.read();
        Collections.sort(result);
        return result;
    }
    
}
