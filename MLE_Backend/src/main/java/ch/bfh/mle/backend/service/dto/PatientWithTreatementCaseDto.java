package ch.bfh.mle.backend.service.dto;

import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 *
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
