/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.mle.backend.service;

import ch.bfh.mle.backend.model.Patient;
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
    public Collection<Patient> readAll(Integer state) {
        TypedQuery<Patient> query;
        if (state.equals(0)) {
            query = entityManager.createNamedQuery("Patient.FindAllWithOpenTreatment", Patient.class);
        } else if (state.equals(1)) {
            query = entityManager.createNamedQuery("Patient.FindAllWithOpenTreatmentWithoutActivities", Patient.class);
        } else {
            query = entityManager.createNamedQuery("Patient.FindAllWithOpenTreatmentAndActivities", Patient.class);
        }
        return query.getResultList();
   }
    
    public List<Patient> readByEmployeeId(Long employeeId, Integer state){
        TypedQuery<Patient> query;
        if (state.equals(0)){
            query = entityManager.createNamedQuery("Patient.FindByEmployeeWithOpenTreatment", Patient.class);
        } else if (state.equals(0)){
            query = entityManager.createNamedQuery("Patient.FindByEmployeeWithOpenTreatmentWithoutActivities", Patient.class);
        } else {
            query = entityManager.createNamedQuery("Patient.FindByEmployeeWithOpenTreatmentAndActivities", Patient.class);
        }
        query.setParameter("employeeId", employeeId);
	return query.getResultList();
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
