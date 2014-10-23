package ch.bfh.mle.backend.service;

import ch.bfh.mle.backend.model.TreatmentCase;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.TypedQuery;

/**
 *
 * @author haueb1@students.bfh.ch
 */

@Stateless
@Named
public class TreatmentCaseService extends GenericService<TreatmentCase>{
    
    public TreatmentCaseService(){
        super(TreatmentCase.class);
    }
    
    public List<TreatmentCase> readByTreatmentNumber(Long treatmentNumber){
        TypedQuery<TreatmentCase> query = entityManager.createNamedQuery("TreatmentCase.FindByTreatmentNumber", TreatmentCase.class);
        query.setParameter("treatmentNumber", treatmentNumber);
	return query.getResultList();
    }
    
}
