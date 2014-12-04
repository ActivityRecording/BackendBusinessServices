package ch.bfh.mle.backend.service;

import ch.bfh.mle.backend.model.Activity;
import ch.bfh.mle.backend.model.Supplier;
import ch.bfh.mle.backend.model.TarmedActivity;
import ch.bfh.mle.backend.model.TreatmentCase;
import ch.bfh.mle.backend.service.dto.ActivityContainerDto;
import ch.bfh.mle.backend.service.dto.ActivityDto;
import ch.bfh.mle.backend.service.dto.SimpleActivityDto;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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
    
    @Inject
    private TimePeriodService timePeriodService;


    private final String REPORT_ID = "00.2285";
    private final String CONSULTATION_1_ID = "00.0010";
    private final String CONSULTATION_2_ID = "00.0020";
    private final String CONSULTATION_3_ID = "00.0030";

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
    
    /**
     * Gibt die kumulierte Zeit von erbrachten Leistungen eines Leistungserbringers 
     * fuer einen Behandlungsfall zurueck. 
     * @param treatmentNumber
     * @param employeeId
     * @return 
     */
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

    /**
     * Gibt die kumulierte Zeit (Minuten) von erbrachten Leistungen fuer einen Behandlungsfall
     * zurueck. 
     * @param treatmentNumber
     * @return Long Kumulierte Zeit in Minuten
     */
    public Long getCumulatedTimeForTreatmentCase(@NotNull Long treatmentNumber){
        TypedQuery<Long> query = entityManager.createNamedQuery("Activity.CumulatedTimesByTreatmentCase", Long.class);
        query.setParameter("treatmentNumber", treatmentNumber);
        Long measuredTime;
        measuredTime = query.getSingleResult();
        if (measuredTime == null){
            measuredTime = 0L;
        }
        return measuredTime;
    }
    
    /**
     * Gibt eine Liste von Leistungen fuer einen Behandlungsfall zurueck, als Uebersicht 
     * fuer die Freigabe. Die nicht verbuchte Zeit wird auf generierte Leistungen verteilt.
     * @param treatmentNumber
     * @return List<ActivityDto>
     */
    public List<ActivityDto> getActivitiesForApproval(@NotNull Long treatmentNumber){
        TreatmentCase treatment;
        treatment = treatmentService.readByTreatmentNumber(treatmentNumber);
        if (treatment == null){
            throw new IllegalArgumentException("No Treatmentcase found with treatmentNumber " + treatmentNumber);
        }
        if (treatment.isReleased()){
            // Der Behandlungsfall darf nicht freigegeben sein.
            throw new IllegalArgumentException("Treatmentcase must not be released");
        }
        
        List<ActivityDto> activities = readAllByTreatmentNumber(treatmentNumber);
        List<ActivityDto> calculatedActivities = getCalculatedActivities(treatmentNumber);
        activities.addAll(calculatedActivities);
        return activities;
    }

    /**
     * Gibt die berechneten Leistungen aufgrund der nicht verbuchten Restzeit (Geleistete - Verrechnete Zeit)
     * fuer einen Behandlungsfall zurueck
     * @param treatmentNumber
     * @return List<ActivityDto>
     */
    public List<ActivityDto> getCalculatedActivities(Long treatmentNumber){
        
        // Verrechnete Zeit in Minuten
        Long treatmentTime = getCumulatedTimeForTreatmentCase(treatmentNumber);
        // Geleistete Zeit in Minuten (aufgerundet)
        Long measuredTime = (timePeriodService.getCumulatedTimeForTreatmentCase(treatmentNumber) + 60 - 1) / 60;
        // Zu verteilende Restzeit
        Long timeDiff = measuredTime - treatmentTime;
        List<ActivityDto> result = new ArrayList<>();
        // Differenz ist 0 -> Keine Verteilung mehr moeglich
        if (timeDiff <= 0){
            return result;
        }
        // Lies die Tarmedleistungen, die automatisch auf die Restzeit verteilt werden sollen
        List<String> idList = Arrays.asList(REPORT_ID, CONSULTATION_1_ID, CONSULTATION_2_ID, CONSULTATION_3_ID);
        List<TarmedActivity> automaticActivities = tarmedService.readIdList(idList);
        HashMap<String, TarmedActivity> autActMap = new HashMap<>();
        for (TarmedActivity a : automaticActivities) {
            autActMap.put(a.getId(), a);
        }
        // Bericht
        TarmedActivity report = autActMap.get(REPORT_ID);
        if (report == null){
            throw new IllegalStateException("Tarmed activity " + REPORT_ID + " not found");
        }
// TODO: technischer User ?
        Long employeeId = 10101L;
        ActivityDto reportActivity = new ActivityDto(null, 1, employeeId, report.getId(), treatmentNumber, report.getDescription(), report.getDuration());
        result.add(reportActivity);
        timeDiff = timeDiff - report.getDuration();
        if (timeDiff <= 0){
            return result;
        }
        // Konsultation erste 5 Minuten
        TarmedActivity consultation1 = autActMap.get(CONSULTATION_1_ID);
        if (consultation1 == null){
            throw new IllegalStateException("Tarmed activity " + CONSULTATION_1_ID + " not found");
        }
        ActivityDto consultationActivity1 = new ActivityDto(null, 1, employeeId, consultation1.getId(), treatmentNumber, consultation1.getDescription(), consultation1.getDuration());
        result.add(consultationActivity1);
        timeDiff = timeDiff - consultation1.getDuration();
        if (timeDiff <= 0){
            return result;
        }
        // Konsultation letzte 5 Minuten
        TarmedActivity consultation3 = autActMap.get(CONSULTATION_3_ID);
        if (consultation3 == null){
            throw new IllegalStateException("Tarmed activity " + CONSULTATION_3_ID + " not found");
        }
        ActivityDto consultationActivity3 = new ActivityDto(null, 1, employeeId, consultation3.getId(), treatmentNumber, consultation3.getDescription(), consultation3.getDuration());
        result.add(consultationActivity3);
        timeDiff = timeDiff - consultation3.getDuration();
        if (timeDiff <= 0){
            return result;
        }
        // Konsultation jede weitere 5 Minuten
        TarmedActivity consultation2 = autActMap.get(CONSULTATION_2_ID);
        if (consultation2 == null){
            throw new IllegalStateException("Tarmed activity " + CONSULTATION_2_ID + " not found");
        }
        Long count = (timeDiff + consultation2.getDuration() - 1) / consultation2.getDuration();
        ActivityDto consultationActivity2 = new ActivityDto(null, count.intValue(), employeeId, consultation2.getId(), treatmentNumber, consultation2.getDescription(), consultation2.getDuration());
        result.add(consultationActivity2);

        return result;
    }

}
