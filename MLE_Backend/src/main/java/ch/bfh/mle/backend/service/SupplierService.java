package ch.bfh.mle.backend.service;

import ch.bfh.mle.backend.model.Supplier;
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
public class SupplierService extends GenericService{

    public SupplierService() {
        super(Supplier.class);
    }
    
    public List<Supplier> readByEmployeeId(Long employeeId){
        TypedQuery<Supplier> query = entityManager.createNamedQuery("Supplier.FindByEmployeeId", Supplier.class);
        query.setParameter("employeeId", employeeId);
	return query.getResultList();
    }
    
}
