package ch.bfh.mle.backend.service;

import ch.bfh.mle.backend.model.TreatmentCase;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

/**
 * Die Klasse TreatmentCaseService stellt Applikationsfunktionalitaeten 
 * fuer die Behandlungsfaelle zur Verfuegung,
 * @author Stefan Walle
 */
@Stateless
@Named
public class TreatmentCaseService extends GenericService<TreatmentCase>{
    
    /**
     * Kontruktor zum Erstellen eines TreatmentCaseService
     */
    public TreatmentCaseService(){
        super(TreatmentCase.class);
    }

    /**
     * Gibt einen Behandlungsfall mit der Behandlungsfallnummer treatmentNumber zurueck.
     * Die Behandlungsfallnummer ist unique.
     * @param treatmentNumber  Behandlungsfallnummer - darf nicht null sein.
     * @return TratementCase
     */
    public TreatmentCase readByTreatmentNumber(@NotNull Long treatmentNumber){
        TypedQuery<TreatmentCase> query = entityManager.createNamedQuery("TreatmentCase.FindByTreatmentNumber", TreatmentCase.class);
        query.setParameter("treatmentNumber", treatmentNumber);
        List<TreatmentCase> result;
	result = query.getResultList();
        if (result.size() > 1){
            // Die Mitarbeiternummer ist unique.
            throw new IllegalStateException("More than one Treatmentcase found ");
        }
        if (result.isEmpty()){
            return null;
        } else {
            return result.get(0);
        }
    }
}
