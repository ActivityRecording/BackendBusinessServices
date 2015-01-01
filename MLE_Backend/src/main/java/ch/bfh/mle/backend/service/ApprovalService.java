package ch.bfh.mle.backend.service;

import ch.bfh.mle.backend.model.Approval;
import ch.bfh.mle.backend.model.Supplier;
import ch.bfh.mle.backend.model.TreatmentCase;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

/**
 * Der Service stellt Applikationsfunktionen fuer die Freigabe eines Behandlungsfalles
 * druch einen Leistungserbringer bereit.<br>
 * Es werden folgende weitere Services verwendet: <br>
 * - SupplierService (Leistungserbringer)<br>
 * - TreatmentCaseService (Behandlungsfall)<br>
 * 
 * @author Stefan Walle, Boris Haueter
 */
@Stateless
@Named
public class ApprovalService extends GenericService{

    /**
     * Kontruktor zum Erstellen eines Ativityservice
     */
    public ApprovalService() {
        super(Approval.class);
    }
    
    @Inject
    private SupplierService supplierService;

    @Inject
    private TreatmentCaseService treatmentService;

    /**
     * Erstellt eine Freigabe fuer den Behandlungsfall treatmentNumber und durch
     * den leistungserbringer employeeId
     * @param employeeId
     * @param treatmentNumber
    */
     public void approve(@NotNull Long employeeId, @NotNull Long treatmentNumber){
        // Finde den Listungserbringer
        Supplier supplier;
        supplier = supplierService.readByEmployeeId(employeeId);
        if (supplier == null){
            throw new IllegalArgumentException("No Supplier found with employeeId " + employeeId);
        }
        
        // Finde den Behandlungsfall 
        TreatmentCase treatment;
        treatment = treatmentService.readByTreatmentNumber(treatmentNumber);
        if (treatment == null){
            throw new IllegalArgumentException("No Treatmentcase found with treatmentNumber " + treatmentNumber);
        }
        if (treatment.isReleased()){
            // Der Behandlungsfall darf nicht freigegeben sein.
            throw new IllegalArgumentException("Treatmentcase must not be released");
        }
        
        TypedQuery<Approval> query = entityManager.createNamedQuery("Approval.SelectByTreatmentIdAndEmployeeId", Approval.class);
        query.setParameter("supplierId", supplier.getId());
        query.setParameter("treatmentId", treatment.getId());
        List<Approval> result;
        result = query.getResultList();
        if (result.size() > 0){
            // Es ist bereits eine Freigabe für den Leistungserbringer und den Fall vorhanden
            throw new IllegalStateException("Approval already exists");
        }

        Approval approval = new Approval(supplier, treatment);
        Date actualDate = new Date();
        approval.setApprovedAt(actualDate);
        this.create(approval);
     }

    /**
     * Liest eine Freigabe fuer einen Behandlungsfall und einen Leistungserbringer.
     * @param employeeId
     * @param treatmentNumber
     * @return Approval oder null
    */
     public Approval readByEmpolyeeIdAndTreatmentNumber(@NotNull Long employeeId, @NotNull Long treatmentNumber){
        // Finde den Listungserbringer
        Supplier supplier;
        supplier = supplierService.readByEmployeeId(employeeId);
        if (supplier == null){
            throw new IllegalArgumentException("No Supplier found with employeeId " + employeeId);
        }
        
        // Finde den Behandlungsfall 
        TreatmentCase treatment;
        treatment = treatmentService.readByTreatmentNumber(treatmentNumber);
        if (treatment == null){
            throw new IllegalArgumentException("No Treatmentcase found with treatmentNumber " + treatmentNumber);
        }
        TypedQuery<Approval> query = entityManager.createNamedQuery("Approval.SelectByTreatmentIdAndEmployeeId", Approval.class);
        query.setParameter("supplierId", supplier.getId());
        query.setParameter("treatmentId", treatment.getId());
        List<Approval> result;
        result = query.getResultList();
        if (result.size() > 1){
            // Es sind mehrere Freigaben für den Leistungserbringer und den Fall vorhanden
            throw new IllegalStateException("More than one approval found");
        }
        if (result.isEmpty()){
            return null;
        } else {
            return result.get(0);
        }

     }
     
}
