package ch.bfh.mle.backend.service;

import ch.bfh.mle.backend.model.Patient;
import ch.bfh.mle.backend.service.dto.PatientListItemDto;
import ch.bfh.mle.backend.service.dto.PatientWithTreatementCaseDto;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

/**
 * Die Klasse PatientService stellt Applikationsfunktionalitaeten zur Verfuegung,
 * welche auf der Entitaet Patient basieren.
 * @author Stefan Walle
 */
@Stateless
@Named
public class PatientService extends GenericService<Patient>{
   
    /**
     * Kontruktor zum Erstellen eines Patientenservice
     */
     public PatientService() {
        super(Patient.class);
    }
    /**
     * Gibt alle Patienten mit noch nicht freigegebenen Behandlungsfaellen, gefiltert nach Status zurueck.
     * @param state Status 0 = Alle, 1 = Patienten ohne Leistungen, 2 = Patienten mit Leistungen - darf nicht null sein
     * @return Liste von PatientListItemDto
     */ 
    public List<PatientListItemDto> readAll(@NotNull Integer state) {
        TypedQuery<PatientListItemDto> query;
        List<PatientListItemDto> result;
        if (state.equals(0)) {
            // Status 0 - Gib alle Behandlungsfaelle zurueck. 
            // Es werden 2 einzelne Queries ausgefuhrt, da Union nicht funktioniert
            query = entityManager.createNamedQuery("Patient.FindAllWithOpenTreatmentWithoutActivities", PatientListItemDto.class);
            result = query.getResultList();
            List<PatientListItemDto> result2;
            query = entityManager.createNamedQuery("Patient.FindAllWithOpenTreatmentAndActivities", PatientListItemDto.class);
            result2 = query.getResultList();
            result.addAll(result2);
        } else if (state.equals(1)) {
            // Status 1 Es werden nur Behandlungsfaelle beruecksichtigt, die noch keine Leistungen beinhalten
            query = entityManager.createNamedQuery("Patient.FindAllWithOpenTreatmentWithoutActivities", PatientListItemDto.class);
            result = query.getResultList();
        } else if (state.equals(2)){
            // Status 2 Es werden nur Behandlungsfaelle beruekcsichtigt, die bereits Leistungen beinhalten
            query = entityManager.createNamedQuery("Patient.FindAllWithOpenTreatmentAndActivities", PatientListItemDto.class);
            result = query.getResultList();
        } else {
            // Untgueltiger Status
            throw new IllegalArgumentException("Unknown state " + state);
        }
        return result;
   }
    
    /**
     * Gibt alle Patienten mit noch nicht freigegebenen Behandlungsfaellen, zu denen ein Leistungserbringer mit
     * Mitarbeiternummer employeeid Zeitraeume erfasst hat, gefiltert nach Status zurueck.
     * Wird nichts gefunden, wird eine leere Liste zurueckgegeben.
     * @param employeeId Mitarbeiternummer eines Leistungserbringers - darf nicht null sein
     * @param state Status 0 = Alle, 1 = Patienten ohne Leistungen, 2 = Patienten mit Leistungen - darf nicht null sein
     * @return Liste von PatientListItemDto
     */
    public List<PatientListItemDto> readByEmployeeId(@NotNull Long employeeId, @NotNull Integer state){
        TypedQuery<PatientListItemDto> query;
        List<PatientListItemDto> result;
        if (state.equals(0)){
            // Status 0 - Gib alle Behandlungsfaelle eines Leistungserbringers zurueck. 
            // Es werden 2 einzelne Queries ausgefuhrt, da Union nicht funktioniert
           query = entityManager.createNamedQuery("Patient.FindByEmployeeWithOpenTreatmentWithoutActivities", PatientListItemDto.class);
            query.setParameter("employeeId", employeeId);
            result = query.getResultList();
            List<PatientListItemDto> result2;
            query = entityManager.createNamedQuery("Patient.FindByEmployeeWithOpenTreatmentAndActivities", PatientListItemDto.class);
            query.setParameter("employeeId", employeeId);
            result2 = query.getResultList();
            result.addAll(result2);
        } else if (state.equals(1)){
            // Status 1 Es werden nur Behandlungsfaelle beruecksichtigt, die noch keine Leistungen beinhalten
            query = entityManager.createNamedQuery("Patient.FindByEmployeeWithOpenTreatmentWithoutActivities", PatientListItemDto.class);
            query.setParameter("employeeId", employeeId);
            result = query.getResultList();
        } else if (state.equals(2)){
            // Status 2 Es werden nur Behandlungsfaelle beruekcsichtigt, die bereits Leistungen beinhalten
            query = entityManager.createNamedQuery("Patient.FindByEmployeeWithOpenTreatmentAndActivities", PatientListItemDto.class);
            query.setParameter("employeeId", employeeId);
            result = query.getResultList();
        } else {
            // Ungueltiger Status
            throw new IllegalArgumentException("Unknown state " + state);
        }
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
    
}
