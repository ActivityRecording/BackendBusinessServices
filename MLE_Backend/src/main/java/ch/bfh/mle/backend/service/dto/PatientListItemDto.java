package ch.bfh.mle.backend.service.dto;

import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 *
 * @author Stefan Walle
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class PatientListItemDto {

    public PatientListItemDto(){
        //required for JAXB
    }
    
    public PatientListItemDto(
        Long patientId, 
        Long patientNumber,
        String lastName, 
        String firstName, 
        Date dateOfBirth, 
        Long treatmentId, 
        Long treatmentNumber, 
        Integer status
    ) {
        
            this.patientId = patientId;
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
    private Long patientId;
    
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

    public Long getPatientId() {
        return patientId;
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

 }
