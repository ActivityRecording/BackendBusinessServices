package ch.bfh.mle.backend.service;

import ch.bfh.mle.backend.model.StandardActivity;
import ch.bfh.mle.backend.service.dto.ActivityCountDto;
import ch.bfh.mle.backend.service.dto.StandardActivitiyListItemDto;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
     * @return List von StandardActivitiyListItemDto
     */
    public List<StandardActivitiyListItemDto> readByEmployeeId(@NotNull Long employeeId){
        if (employeeId == null){
            throw new IllegalArgumentException("EmployeeId cannot be null");
        }
        TypedQuery<StandardActivitiyListItemDto> query = entityManager.createNamedQuery("StandardActivity.FindByEmployeeId", StandardActivitiyListItemDto.class);
        query.setParameter("employeeId", employeeId);
	return query.getResultList();
    }
    
    /**
     * Gibt die Standardleistungen fuer den Leistungserbringer mit der
     * Mitarbeiternummer employeeId zurueck. Die Standardleistungen enthalten
     * die Anzahl bereits erfasster Leistungen fuer den Behandlungsfall treatmentNumber.
     * Der Leisungserbringer und der Bahendlungsfall muessen auf der Datenbank vorhanden sein.
     * @param employeeId Mitarbeiternummer - darf nicht null sein.
     * @param treatmentNumber Behandlungsfall- dar nicht null sein.
     * @return List von StandardActivitiyListItemDto
     */
    public List<StandardActivitiyListItemDto> readByEmployeeAndTreatment(@NotNull Long employeeId, @NotNull Long treatmentNumber){
        if (employeeId == null){
            throw new IllegalArgumentException("EmployeeId cannot be null");
        }
        if (treatmentNumber == null){
            throw new IllegalArgumentException("TreatmentNumber cannot be null");
        }
        
        // Lies den Standardkatalog fuer die gegebene Mitarbeiternummer employeeId
        TypedQuery<StandardActivitiyListItemDto> query1 = entityManager.createNamedQuery("StandardActivity.FindByEmployeeId", StandardActivitiyListItemDto.class);
        query1.setParameter("employeeId", employeeId);
	List<StandardActivitiyListItemDto> listItems = query1.getResultList();
        
        // Lies die Anzahl erbrachter Leistungen fuer den gegebenen Behandlungsfall treatmentcaseId
        TypedQuery<ActivityCountDto> query2 = entityManager.createNamedQuery("Activity.ActivitiesWithCountForTratmendCaseAndEmployee", ActivityCountDto.class);
        query2.setParameter("employeeId", employeeId);
        query2.setParameter("treatmentNumber", treatmentNumber);
	List<ActivityCountDto> activities = query2.getResultList();
        
        // Erstelle eine Hashmap der gelesenen Leistungen
        Map<String,Long> map = new HashMap<>();
        for (ActivityCountDto a : activities) map.put(a.getTarmedId(),a.getCapturedCount());
        
        // Ergaenze den Standard-Leistungskatalog mit der Azahl erfasster Leistungen
        for (StandardActivitiyListItemDto item: listItems){
            if (map.containsKey(item.getTarmedId())){
                item.setCapturedCount(map.get(item.getTarmedId()));
            }
        }
        return listItems;
    }
}
