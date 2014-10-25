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
import javax.validation.constraints.NotNull;

/**
 * Der Service stellt Applikationsfunktionen fuer Leistungen zur Verfuegung.<br>
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

    /**
     * Kontruktor zum Erstellen eines Ativityservice
     */
    public ActivityService() {
        super(Activity.class);
    }
    
    @Inject
    private SupplierService supplierService;

    @Inject
    private TarmedActivityService tarmedService;

    @Inject
    private TreatmentCaseService treatmentService;

    /**
     * Speichert eine neue Leistung auf der Datenbank. Es muessen folgende 
     * Voraussetzungen gegeben sein:<br>
     * - Der Leistungserbringer mit der Mitarbeiternummer employeeId muss auf
     *   der Datenbank vorhanden sein. <br>
     * - Der Behandlungsfall mit der Behandlungsfallnummer treatmentNumber muss
     *   auf der Datenbank vorhanden sein <br>
     * - Die Tarmedleistung mit der ID tarmedId muss auf der Datenbank
     *   vorhanden sein. <br>
     * - Der Behandlungsfall darf nicht freigegeben sein <br>
     * - Die Anzahl der Leistungen darf nicht kleiner als 1 sein.
     * 
     * @param ActivityDto 
     */
    public void create(@NotNull ActivityDto dto){
        // Prüfe die Input-Daten
        Long employeeId = dto.getEmployeeId();
        String tarmedId = dto.getTarmedActivityId();
        Long treatmentNumber = dto.getTreatmentNumber();
        if (employeeId == null || tarmedId == null || treatmentNumber == null){
            // Schlüssel für den Leistungserbringer, die Tarmedleistung oder den Behandlungsfall ist null
            throw new IllegalArgumentException("EmployeeId, Treatmentnumber and TarmedActivityId must not be null");
        }
        if (dto.getNumber() == null || dto.getNumber() < 1){
            // Die Anzahl Leistungen muss > 0 sein.
            throw new IllegalArgumentException("Number of activities must be greater than 0");
        }
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
        
        // Finde die Tarmedleistung
        TarmedActivity tarmedActivity;
        tarmedActivity = entityManager.find(TarmedActivity.class, tarmedId);
        if (tarmedActivity == null){
            throw new IllegalArgumentException("No Tarmedactivity found with id " + tarmedId);
        }
        
        // Erstelle und speichere die Leistung
        Activity activity = new Activity(tarmedActivity, supplier, treatment);
        activity.setNumber(dto.getNumber());
        this.create(activity);
    }
    
}
