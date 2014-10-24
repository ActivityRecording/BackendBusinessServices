package ch.bfh.mle.backend.service;

import ch.bfh.mle.backend.model.Supplier;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

/**
 * Die Klasse SupplierService stellt Applikationsfunktionalitaeten 
 * fuer Leistungserbringer zur Verfuegung,
  * @author Stefan Walle
 */
@Stateless
@Named
public class SupplierService extends GenericService{

    /**
     * Kontruktor zum Erstellen eines SupplierService
     */
    public SupplierService() {
        super(Supplier.class);
    }
    
    /**
     * Gibt einen Leistungserbringer mit der Mitarbeiternummer employeeId zurueck.
     * Die Mitarbeiternummer ist unique. Es darf nur ein Leistungserbringer gefunden werden.
     * @param employeeId Mitarbeiternummer - darf nicht null sein.
     * @return 
     */
    public Supplier readByEmployeeId(@NotNull Long employeeId){
        TypedQuery<Supplier> query = entityManager.createNamedQuery("Supplier.FindByEmployeeId", Supplier.class);
        query.setParameter("employeeId", employeeId);
        List<Supplier> result;
        result = query.getResultList();
        if (result.size() > 1){
            // Die Mitarbeiternummer ist unique.
            throw new IllegalStateException("More than one Supplier found ");
        }
        if (result.isEmpty()){
            return null;
        } else {
            return result.get(0);
        }
     }
    
}
