package ch.bfh.mle.backend.service.dto;

import ch.bfh.mle.backend.model.Patient;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * Das PatientListItemDto dient der Uebergabe von Paientendaten mit der zugehoerigen 
 * Behandlungsfallnummer ueber eine REST-Schnittstelle. Die Items werden verwendet fuer
 * die Anzeige der Patientenliste (resp. Behandlungsfallliste mit Patientenangaben).
 * Der Status gibt an, ob es fuer den Behandlungsfall bereits erfasste Leistungen gibt oder nicht.
 * @author Stefan Walle
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class PatientListItemDto implements Comparable<PatientListItemDto>{

    /**
     * Konstruktor fuer JAXB
     */
    public PatientListItemDto(){
    }
    
    /**
     * Konstruktor fuer die Uebergabe aller Merkmale des DTO.
     * Er kann direkt in JPQL-Queries zum Erstellen des DTO mit NEW(...) verwendet werden.
     * @param patientId
     * @param patientNumber
     * @param lastName
     * @param firstName
     * @param dateOfBirth
     * @param treatmentId
     * @param treatmentNumber
     * @param status 
     */
    public PatientListItemDto(Long patientId, Long patientNumber, String lastName, String firstName, Date dateOfBirth, Long treatmentId, Long treatmentNumber, Integer status) {
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

    @Override
    public int compareTo(PatientListItemDto p) {
        int result;
        result = lastName.toUpperCase().compareTo(p.lastName.toUpperCase());
        result = (result != 0 ? result : firstName.toUpperCase().compareTo(p.firstName.toUpperCase()));
        return (result != 0 ? result : status.compareTo(p.status));
    }
    
 }
