package ch.bfh.mle.backend.service;

import ch.bfh.mle.backend.model.Activity;
import ch.bfh.mle.backend.model.Supplier;
import ch.bfh.mle.backend.model.TarmedActivity;
import ch.bfh.mle.backend.model.TreatmentCase;
import ch.bfh.mle.backend.service.dto.ActivityDto;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Der Service stellt Applikationsfunktionen fuer Leistungen zur Verfuegung.
 * Es werden folgende weitere Services verwendet: <br>
 * - SupplierService (Leistungserbringer)<br>
 * - TarmedActivityService (Tarmedleistung)<br>
 * - TreatmentCaseService (Behandlungsfall)<br>
 * 
 * @author Stefan Walle
 */
@Stateless
@Named
public class ActivityService extends GenericService{
    
    @Inject
    private SupplierService supplierService;

    @Inject
    private TarmedActivityService tarmedService;

    @Inject
    private TreatmentCaseService treatmentService;

    public ActivityService() {
        super(Activity.class);
    }
    
    public void create(ActivityDto dto){
        
        // Finde den Leistungserbringer und den Behandlungsfall zur übergebenen Leistung
        Long employeeId = dto.getEmployeeId();
        Long tarmedId = dto.getTarmedActivityId();
        Long treatmentNumber = dto.getTreatmentNumber();
        if (employeeId == null || tarmedId == null || treatmentNumber == null){
            // Schlüssel für den Leistungserbringer oder die Tarmedleistung ist nicht vorhanden
            throw new IllegalArgumentException("EmployeeId, Treatmentnumber and TarmedActivityId must not be null");
        }
        // Finde den Listungserbringer
        List<Supplier> suppliers;
        suppliers = supplierService.readByEmployeeId(employeeId);
        if (suppliers.isEmpty()){
            throw new IllegalArgumentException("No supplier found with employeeId " + employeeId);
        }
        if (suppliers.size() > 1){
            throw new IllegalArgumentException("More than one Supplier found for employeeId " + employeeId);
        }
        Supplier supplier = suppliers.get(0);
        
        // Finde den Behandlungsfall 
        List<TreatmentCase> treatments;
        treatments = treatmentService.readByTreatmentNumber(treatmentNumber);
         if (treatments.isEmpty()){
            throw new IllegalArgumentException("No treatmentcase found with treatmentnumber " + treatmentNumber);
        }
        if (treatments.size() > 1){
            throw new IllegalArgumentException("More than one treatmentcase found for treatmentnumber " + treatmentNumber);
        }
        TreatmentCase treatment = treatments.get(0);
        
        // Finde die Tarmedleistung
        TarmedActivity tarmedActivity;
        tarmedActivity = (TarmedActivity) tarmedService.read(tarmedId);
        if (tarmedActivity == null){
            throw new IllegalArgumentException("No Tarmedactivity found with id " + tarmedId);
        }
        
        // Erstelle und speichere die Leistung
        Activity activity = new Activity(tarmedActivity, supplier, treatment);
        activity.setNumber(dto.getNumber());
        this.create(activity);
    }
    
}
