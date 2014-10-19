/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.mle.backend.service.dto;

import java.util.Date;

/**
 *
 * @author Stefan Walle
 */
public class PatientListItemDto {

    public PatientListItemDto(){
        //required for JAXB
    }
    
    public PatientListItemDto(
        Long id, 
        Long patientNumber,
        String lastName, 
        String firtName, 
        Date dateOfBirth, 
        Long treatmentId, 
        Long treatmentNumber, 
        Integer status
    ) {
        
            this.id = id;
            this.patientNumber = patientNumber;
            this.lastName = lastName;
            this.firstName = firstName;
            this.dateOfBirth = dateOfBirth;
            this.treatmentId = treatmentId;
            this.treatmentNumber = treatmentNumber;
            this.status = status;
    }

    /**
     * Technische Datenbenk-ID
     */
    private Long id;
    
    /**
     * Fachliche Patienten-ID
     */
    private Long patientNumber;
    
    /**
     * Nachname
     */
    private String lastName;
 
    /**
     * Vorname
     */
    private String firstName;
    
    /**
     * Geburtsdatum
     */
    private Date dateOfBirth;
    
    /**
     * Technische Datenbank-ID des Behandlungsfalls
     */
    private Long treatmentId;
            
    /**
     * Behandlungsfallnummer
     */
    private Long treatmentNumber;
    /**
     * Status des Patienten
     * 1 = Hat noch keine erfassten Leistungen
     * 2 = Hat erfasste Leistungen
     */
    private Integer status;

    public Long getId() {
        return id;
    }

    public Long getPatientNumber() {
        return patientNumber;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public Long getTreatmentNumber() {
        return treatmentNumber;
    }

    public Long getTreatmentId() {
        return treatmentId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPatientNumber(Long patientNumber) {
        this.patientNumber = patientNumber;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setTreatmentId(Long treatmentId) {
        this.treatmentId = treatmentId;
    }

    public void setTreatmentNumber(Long treatmentNumber) {
        this.treatmentNumber = treatmentNumber;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    
}
