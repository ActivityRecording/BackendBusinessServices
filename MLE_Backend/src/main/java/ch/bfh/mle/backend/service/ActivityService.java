package ch.bfh.mle.backend.service;

import ch.bfh.mle.backend.model.Activity;
import ch.bfh.mle.backend.model.Supplier;
import ch.bfh.mle.backend.model.TarmedActivity;
import ch.bfh.mle.backend.model.TreatmentCase;
import ch.bfh.mle.backend.service.dto.ActivityContainerDto;
import ch.bfh.mle.backend.service.dto.ActivityDto;
import ch.bfh.mle.backend.service.dto.SimpleActivityDto;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

/**
 * Der Service stellt Applikationsfunktionen fuer Leistungen zur Verfuegung.<br>
 * Es werden folgende weitere Services verwendet: <br>
 * - SupplierService (Leistungserbringer)<br>
 * - TarmedActivityService (Tarmedleistung)<br>
 * - TreatmentCaseService (Behandlungsfall)<br>
 * 
 * @author Stefan Walle, Boris Haueter
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
     * Speichert erbrachte Leistungen auf der Datenbank. Es muessen folgende 
     * Voraussetzungen gegeben sein:<br>
     * - Der Leistungserbringer mit der Mitarbeiternummer employeeId muss auf
     *   der Datenbank vorhanden sein. <br>
     * - Der Behandlungsfall mit der Behandlungsfallnummer treatmentNumber muss
     *   auf der Datenbank vorhanden sein <br>
     * - Die Tarmedleistung mit der ID tarmedId muss auf der Datenbank
     *   vorhanden sein. <br>
     * - Der Behandlungsfall darf nicht freigegeben sein <br>
     * - Die Anzahl der Leistungen darf nicht 0 sein 
     *   und die negativen Leistungen nicht grösser als die Summe der Leistung.
     *   Dies wir aber bereits im UI abgefangen.   
     * @param ActivityContainerDto 
     */
    public void create(@NotNull ActivityContainerDto dto){
        // Prüfe die Input-Daten
        Long employeeId = dto.getEmployeeId();
        Long treatmentNumber = dto.getTreatmentNumber();
        if (employeeId == null || treatmentNumber == null){
            // Schlüssel für den Leistungserbringer oder den Behandlungsfall ist null
            throw new IllegalArgumentException("EmployeeId and Treatmentnumber must not be null");
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
        
        for ( SimpleActivityDto activityDto : dto.getActivities()){
            
            // Finde die Tarmedleistung
            String tarmedId = activityDto.getTarmedActivityId();
            if (tarmedId == null){
            // Schlüssel die Tarmedleistung ist null
                throw new IllegalArgumentException("Tarmedid must not be null");
            }
            if (activityDto.getNumber() == null || activityDto.getNumber() == 0){
                // Die Anzahl Leistungen muss <> 0 sein.
                throw new IllegalArgumentException("Number of activities must be greater than 0");
            }
            TarmedActivity tarmedActivity;
            tarmedActivity = entityManager.find(TarmedActivity.class, tarmedId);
            if (tarmedActivity == null){
                throw new IllegalArgumentException("No Tarmedactivity found with id " + tarmedId);
            }

            // Erstelle und speichere die Leistung
            Activity activity = new Activity(tarmedActivity, supplier, treatment);
            activity.setNumber(activityDto.getNumber());
            this.create(activity);
        }
    }
    
    /**
    * Liefert eine Liste von ActivityDto's zurück, welche
    * via Named Query und den Parameter FID ermittelt werden
    */
    public List<ActivityDto> readAllByTreatmentNumber(@NotNull Long treatmentNumber) {
        TypedQuery<ActivityDto> query = entityManager.createNamedQuery("Activity.FindAllActivitiesByTreatmentNumber", ActivityDto.class);
        query.setParameter("treatmentNumber", treatmentNumber);
        List<ActivityDto> result;
        result = query.getResultList();
        return result;
    }
    
   /**
    * Findet eine Activity Entity anhand der ID und löscht diese anschliessend
    */
     public void deleteActivityById(@NotNull Long id){
         TypedQuery<Activity> query = entityManager.createNamedQuery("Activity.FindActivitiyById", Activity.class);
         query.setParameter("id", id);
         Activity result;
         result = query.getSingleResult();
         if(result != null){
             entityManager.remove(result);
         }
     }
    
     public Long getCumulatedTime(@NotNull Long treatmentNumber, @NotNull Long employeeId){
         TypedQuery<Long> query = entityManager.createNamedQuery("Activity.CumulatedTimesByEmployeeAndTreatmentCase", Long.class);
         query.setParameter("treatmentNumber", treatmentNumber);
         query.setParameter("employeeId", employeeId);
         Long measuredTime;
         measuredTime = query.getSingleResult();
         if (measuredTime == null){
             measuredTime = 0L;
         }
         return measuredTime;
     }
     
}
