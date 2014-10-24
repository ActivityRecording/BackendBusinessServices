package ch.bfh.mle.backend.service.dto;

import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * Das PatientWithTreatmentCaseDto dient der Uebergabe von Patientendaten und dem
 * zugehoerigen Behandlungsfall ueber eine REST-Schnitstelle.
 * @author Stefan Walle
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class PatientWithTreatementCaseDto {

    public PatientWithTreatementCaseDto(){
        //required for JAXB
    }
    
    public PatientWithTreatementCaseDto(
        Long patientId ,
        Long patientNumber,
        String lastName, 
        String firstName, 
        Date dateOfBirth, 
        Long treatmentId, 
        Long treatmentNumber,
        Date startTime,
        Date endTime,
        Boolean released
    ) {
        
            this.patientId = patientId;
            this.patientNumber = patientNumber;
            this.lastName = lastName;
            this.firstName = firstName;
            this.dateOfBirth = dateOfBirth;
            this.treatmentId = treatmentId;
            this.treatmentNumber = treatmentNumber;
            this.startTime = startTime;
            this.endTime = endTime;
            this.released = released;
    }
    
    /**
     * Technische Datenbenk-ID des Patienten
     */
    private Long patientId;
    
    /**
     * Fachliche Patienten-ID
     */
    private Long patientNumber;
    
    /**
     * Nachname des Patienten
     */
    private String lastName;
 
    /**
     * Vorname des Patienten
     */
    private String firstName;
    
    /**
     * Geburtsdatum des Patienten
     */
    private Date dateOfBirth;
    
    /**
     * Technische Datenbank-ID des Behandlungsfalls
     */
    private Long treatmentId;
            
    /**
     * Fachliche Behandlungsfallnummer
     */
    private Long treatmentNumber;
    
    /**
     * Datum und Zeit des Eintritts des Patienten.
     */
    private Date startTime;
    
    /**
     * Datum und Zeit des Austritts des Patienten.
     */
    private Date endTime;
    
    /**
     * Status Freigegeben
     */
    private Boolean released;
    
}
