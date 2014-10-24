package ch.bfh.mle.backend.service;

import ch.bfh.mle.backend.model.StandardActivity;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

/**
 * Die Klasse StandardActivityService stellt Applikationsfunktionalitaeten 
 * fuer den Standard Leistungskatalog zur Verfuegung,
  * @author Stefan Walle
 */
@Stateless
@Named
public class StandardActivityService extends GenericService<StandardActivity>{
    
    /**
     * Kontruktor zum Erstellen eines StandardActivityService
     */
    public StandardActivityService() {
        super(StandardActivity.class);
    }
    
    /**
     * Gibt die Standardleistungen fuer den Leistungserbringer mit der
     * Mitarbeiternummer employeeId zurueck.
     * Der Leisungserbringer muss auf der Datenbank vorhanden sein.
     * @param employeeId Mitarbeiternummer - darf nicht null sein.
     * @return List von Standardleistungen
     */
    public List<StandardActivity> readByEmployeeId(@NotNull Long employeeId){
        TypedQuery<StandardActivity> query = entityManager.createNamedQuery("StandardActivity.FindByEmployeeId", StandardActivity.class);
        query.setParameter("employeeId", employeeId);
	return query.getResultList();
    }
    
}
