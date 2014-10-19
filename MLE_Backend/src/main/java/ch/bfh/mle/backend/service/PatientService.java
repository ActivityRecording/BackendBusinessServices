/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.mle.backend.service;

import ch.bfh.mle.backend.model.Patient;
import ch.bfh.mle.backend.service.dto.PatientListItemDto;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Boris
 */
@Stateless
@Named
public class PatientService extends GenericService<Patient>{
   
     public PatientService() {
        super(Patient.class);
    }
    /**
     * Gibt Patienten mit noch nicht freigegebenen Behandlungsfaellen, gefiltert nach Status zurück.
     * @param state Status 0 = Alle, 1 = Patienten ohne Leistungen, 2 = Patienten mit Leistungen
     * @return List von Patienten
     */ 
    public List<PatientListItemDto> readAll(Integer state) {
        TypedQuery<PatientListItemDto> query;
        List<PatientListItemDto> result;
        if (state.equals(0)) {
//            query = entityManager.createNamedQuery("Patient.FindAllWithOpenTreatment", PatientListItemDto.class);
            query = entityManager.createNamedQuery("Patient.FindAllWithOpenTreatmentWithoutActivities", PatientListItemDto.class);
            result = query.getResultList();
            List<PatientListItemDto> result2;
            query = entityManager.createNamedQuery("Patient.FindAllWithOpenTreatmentAndActivities", PatientListItemDto.class);
            result2 = query.getResultList();
            result.addAll(result2);
        } else if (state.equals(1)) {
            query = entityManager.createNamedQuery("Patient.FindAllWithOpenTreatmentWithoutActivities", PatientListItemDto.class);
            result = query.getResultList();
        } else {
            query = entityManager.createNamedQuery("Patient.FindAllWithOpenTreatmentAndActivities", PatientListItemDto.class);
            result = query.getResultList();
        }
        return result;
   }
    
    public List<PatientListItemDto> readByEmployeeId(Long employeeId, Integer state){
        TypedQuery<PatientListItemDto> query;
        List<PatientListItemDto> result;
        if (state.equals(0)){
//            query = entityManager.createNamedQuery("Patient.FindByEmployeeWithOpenTreatment", PatientListItemDto.class);
            query = entityManager.createNamedQuery("Patient.FindByEmployeeWithOpenTreatmentWithoutActivities", PatientListItemDto.class);
            query.setParameter("employeeId", employeeId);
            result = query.getResultList();
            List<PatientListItemDto> result2;
            query = entityManager.createNamedQuery("Patient.FindByEmployeeWithOpenTreatmentAndActivities", PatientListItemDto.class);
            query.setParameter("employeeId", employeeId);
            result2 = query.getResultList();
            result.addAll(result2);
        } else if (state.equals(1)){
            query = entityManager.createNamedQuery("Patient.FindByEmployeeWithOpenTreatmentWithoutActivities", PatientListItemDto.class);
            query.setParameter("employeeId", employeeId);
            result = query.getResultList();
        } else {
            query = entityManager.createNamedQuery("Patient.FindByEmployeeWithOpenTreatmentAndActivities", PatientListItemDto.class);
            query.setParameter("employeeId", employeeId);
            result = query.getResultList();
        }
	return result;
    }

    public List<Patient> readByTreatmentNumber(Long treatmentNumber) {
        TypedQuery<Patient> query = entityManager.createNamedQuery("Patient.FindByTreatmentNumber", Patient.class);
        query.setParameter("treatmentNumber", treatmentNumber);
	return query.getResultList();
    }

    public List<Patient> readByPatientNumber(Long patientNumber) {
        TypedQuery<Patient> query = entityManager.createNamedQuery("Patient.FindByPatientNumber", Patient.class);
        query.setParameter("patientNumber", patientNumber);
	return query.getResultList();
    }
    
}
