package ch.bfh.mle.backend.service;

import ch.bfh.mle.backend.model.StandardActivity;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.TypedQuery;

/**
 *
 * @author Stefan Walle
 */
@Stateless
@Named
public class StandardActivityService extends GenericService<StandardActivity>{
    
    public StandardActivityService() {
        super(StandardActivity.class);
    }
    
    public List<StandardActivity> readByEmployeeId(Long employeeId){
        TypedQuery<StandardActivity> query = entityManager.createNamedQuery("StandardActivity.FindByEmployeeId", StandardActivity.class);
        query.setParameter("employeeId", employeeId);
	return query.getResultList();
    }
    
}
