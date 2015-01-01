package ch.bfh.mle.backend.service;

import ch.bfh.mle.backend.model.Approval;
import ch.bfh.mle.backend.model.Patient;
import ch.bfh.mle.backend.service.dto.PatientListItemDto;
import ch.bfh.mle.backend.service.dto.PatientWithTreatementCaseDto;
import java.util.Calendar;
import java.util.Date;
import java.util.Collections;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

/**
 * Die Klasse PatientService stellt Applikationsfunktionalitaeten zur Verfuegung,
 * welche auf der Entitaet Patient basieren.
 * @author Stefan Walle
 */
@Stateless
@Named
public class PatientService extends GenericService<Patient> {
   
    /**
     * Kontruktor zum Erstellen eines Patientenservice
     */
     public PatientService() {
        super(Patient.class);
    }
     
    @Inject
    private ApprovalService approvalService;
     
    /**
     * Gibt alle Patienten mit noch nicht freigegebenen Behandlungsfaellen, gefiltert nach Status zurueck.
     * @param state Status 0 = Faelle der letzten 24 Stunden, 1 = Faelle der aktuellen Woche, 2 = Alle offenen Faelle - darf nicht null sein
     * @return Liste von PatientListItemDto
     */ 
    public List<PatientListItemDto> readAll(@NotNull Integer state) {
        TypedQuery<PatientListItemDto> query;
        List<PatientListItemDto> result;
        if (state.equals(0)) {
            // Status 0 - Gib alle Behandlungsfaelle von heute zurueck. 
            // Es werden per Status jeweils 2 einzelne Queries ausgefuhrt, da Union nicht funktioniert
            query = entityManager.createNamedQuery("Patient.FindAllWithOpenTreatmentWithoutActivitiesToday", PatientListItemDto.class);
            query.setParameter("today", getToday());
            result = query.getResultList();
            List<PatientListItemDto> result2;
            query = entityManager.createNamedQuery("Patient.FindAllWithOpenTreatmentAndActivitiesToday", PatientListItemDto.class);
            query.setParameter("today", getToday());
            result2 = query.getResultList();
            result.addAll(result2);
        } else if (state.equals(1)) {
            // Status 1 Es werden die Behandlungsfälle der laufenden Woche zurueckgegeben
            query = entityManager.createNamedQuery("Patient.FindAllWithOpenTreatmentWithoutActivitiesCurrentWeek", PatientListItemDto.class);
            query.setParameter("weekbeginning", getFirstDayOfCurrentWeek());
            result = query.getResultList();
            List<PatientListItemDto> result2;
            query = entityManager.createNamedQuery("Patient.FindAllWithOpenTreatmentAndActivitiesCurrentWeek", PatientListItemDto.class);
            query.setParameter("weekbeginning", getFirstDayOfCurrentWeek());
            result2 = query.getResultList();
            result.addAll(result2);
        } else if (state.equals(2)){
            // Status 2 Es werden alle Behandlungsfälle zurueckgebeben
            query = entityManager.createNamedQuery("Patient.FindAllWithOpenTreatmentWithoutActivitiesAllTime", PatientListItemDto.class);
            result = query.getResultList();
            List<PatientListItemDto> result2;
            query = entityManager.createNamedQuery("Patient.FindAllWithOpenTreatmentAndActivitiesAllTime", PatientListItemDto.class);
            result2 = query.getResultList();
            result.addAll(result2);
        } else {
            // Untgueltiger Status
            throw new IllegalArgumentException("Unknown state " + state);
        }
        Collections.sort(result);
        return result;
   }
    
    /**
     * Gibt alle Patienten mit noch nicht freigegebenen Behandlungsfaellen, zu denen ein Leistungserbringer mit
     * Mitarbeiternummer employeeid Zeitraeume erfasst hat, gefiltert nach Status zurueck.
     * Wird nichts gefunden, wird eine leere Liste zurueckgegeben.
     * @param employeeId Mitarbeiternummer eines Leistungserbringers - darf nicht null sein
     * @param state Status 0 = Faelle der letzten 24 Stunden, 1 = Faelle der aktuellen Woche, 2 = Alle offenen Faelle - darf nicht null sein
     * @return Liste von PatientListItemDto
     */
    public List<PatientListItemDto> readByEmployeeId(@NotNull Long employeeId, @NotNull Integer state){
        TypedQuery<PatientListItemDto> query;
        List<PatientListItemDto> result;
        if (state.equals(0)){
            // Status 0 - Gib alle Behandlungsfaelle eines Leistungserbringers der letzten 24 Stunden zurueck. 
            // Es werden jeweil 2 einzelne Queries ausgefuhrt um die Erfassungsstatus "neu" und "mit Leistungen" zu ermitteln, da Union nicht funktioniert
            query = entityManager.createNamedQuery("Patient.FindByEmployeeWithOpenTreatmentWithoutActivitiesToday", PatientListItemDto.class);
            query.setParameter("employeeId", employeeId);
            query.setParameter("today", getToday(), TemporalType.TIMESTAMP);
            result = query.getResultList();
            List<PatientListItemDto> result2;
            query = entityManager.createNamedQuery("Patient.FindByEmployeeWithOpenTreatmentAndActivitiesToday", PatientListItemDto.class);
            query.setParameter("employeeId", employeeId);
            query.setParameter("today", getToday(),TemporalType.TIMESTAMP);
            result2 = query.getResultList();
            result.addAll(result2);
        } else if (state.equals(1)){
            // Status 1 Es werden nur Behandlungsfaelle beruecksichtigt, die seit Anfang der laufenden Woche gestartet wurden
            query = entityManager.createNamedQuery("Patient.FindByEmployeeWithOpenTreatmentWithoutActivitiesCurrentWeek", PatientListItemDto.class);
            query.setParameter("employeeId", employeeId);
            query.setParameter("weekbeginning", getFirstDayOfCurrentWeek(),TemporalType.TIMESTAMP);
            result = query.getResultList();
            List<PatientListItemDto> result2;
            query = entityManager.createNamedQuery("Patient.FindByEmployeeWithOpenTreatmentAndActivitiesCurrentWeek", PatientListItemDto.class);
            query.setParameter("employeeId", employeeId);
            query.setParameter("weekbeginning", getFirstDayOfCurrentWeek(), TemporalType.TIMESTAMP);
            result2 = query.getResultList();
            result.addAll(result2);
        } else if (state.equals(2)){
            // Status 2 Es werden alle offenen Behandlungsfaelle beruecksichtigt, die jemals angelegt wurden
            query = entityManager.createNamedQuery("Patient.FindByEmployeeWithOpenTreatmentWithoutActivitiesAllTime", PatientListItemDto.class);
            query.setParameter("employeeId", employeeId);
            result = query.getResultList();
            List<PatientListItemDto> result2;
            query = entityManager.createNamedQuery("Patient.FindByEmployeeWithOpenTreatmentAndActivitiesAllTime", PatientListItemDto.class);
            query.setParameter("employeeId", employeeId);
            result2 = query.getResultList();
            result.addAll(result2);
        } else {
            // Ungueltiger Status
            throw new IllegalArgumentException("Unknown state " + state);
        }
        Collections.sort(result);
	return result;
    }

    /**
     * Gibt einen Patienten mit Behandlungfall in Form eines PatientWithTreatementCaseDto fuer die 
     * Behandlungsfallnummer treatmentNumber zurueck.
     * Wird nichts gefunden, wird null zurueckgegeben.
     * @param treatmentNumber Behandlungsfallnummer - darf nicht null sein
     * @return PatientWithTreatementCaseDto Patient mit Behandlungsfall
     */
    public PatientWithTreatementCaseDto readByTreatmentNumber(@NotNull Long treatmentNumber) {
        TypedQuery<PatientWithTreatementCaseDto> query = entityManager.createNamedQuery("Patient.FindByTreatmentNumber", PatientWithTreatementCaseDto.class);
        query.setParameter("treatmentNumber", treatmentNumber);
        List<PatientWithTreatementCaseDto> result;
        result = query.getResultList();
        if (result.size() > 1){
            // Die Behandlungsfallnummer ist unique. Es darf nur ein Patient zurueckgegeben werden.
            throw new IllegalStateException("More than one Patient found ");
        }
        if (result.isEmpty()){
            return null;
        } else {
            return result.get(0);
        }
    }

    /**
     * Gibt einen Patienten mit Behandlungfall in Form eines PatientWithTreatementCaseDto fuer die 
     * Behandlungsfallnummer treatmentNumber und den Leistungserbringer employeeId zurueck.
     * Wird nichts gefunden, wird null zurueckgegeben.
     * @param treatmentNumber Behandlungsfallnummer - darf nicht null sein
     * @param employeeId Mitarbeiternummer des Leistungserbringers -darf nicht null sein
     * @return PatientWithTreatementCaseDto Patient mit Behandlungsfall
     */
    public PatientWithTreatementCaseDto readByTreatmentNumberAndEmployeeId(@NotNull Long treatmentNumber, @NotNull Long employeeId) {
        TypedQuery<PatientWithTreatementCaseDto> query = entityManager.createNamedQuery("Patient.FindByTreatmentNumber", PatientWithTreatementCaseDto.class);
        query.setParameter("treatmentNumber", treatmentNumber);
        List<PatientWithTreatementCaseDto> result;
        result = query.getResultList();
        if (result.size() > 1){
            // Die Behandlungsfallnummer ist unique. Es darf nur ein Patient zurueckgegeben werden.
            throw new IllegalStateException("More than one Patient found ");
        }
        if (result.isEmpty()){
            return null;
        } else {
            PatientWithTreatementCaseDto dto = result.get(0);
            Approval approval = approvalService.readByEmpolyeeIdAndTreatmentNumber(employeeId, treatmentNumber);
            if (approval == null){
                dto.setReleasedBySupplier(Boolean.FALSE);
            } else {
                dto.setReleasedBySupplier(Boolean.TRUE);
            }
            return dto;
        }
    }
    
    /**
     * Gibt einen Patienten mit der fachlichen Patientennummer patientNumber zurueck.
     * Wir kein Patient gefunden wird eine leere Liste zurueckgegeben..
     * @param patientNumber Patientennummer - darf nicht null sein.
     * @return Patient
     */
    public Patient readByPatientNumber(@NotNull Long patientNumber) {
        TypedQuery<Patient> query = entityManager.createNamedQuery("Patient.FindByPatientNumber", Patient.class);
        query.setParameter("patientNumber", patientNumber);
	List<Patient> result;
        result = query.getResultList();
        if (result.size() > 1){
            // Die Patientenummer ist unique. Es darf nur ein Patient zurueckgegeben werden.
            throw new IllegalStateException("More than one Patient found ");
        }
        if (result.isEmpty()){
            return null;
        } else {
            return result.get(0);
        }
    }
    
    // Datum von heute Uhrzeit 00
    private static Date getToday(){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE,0);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        return cal.getTime();
    }
    
    // Datum des ersten Wochentages Uhrzeit 00:00
    private static Date getFirstDayOfCurrentWeek(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.DAY_OF_WEEK,  Calendar.MONDAY);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        return cal.getTime();
    }
}
