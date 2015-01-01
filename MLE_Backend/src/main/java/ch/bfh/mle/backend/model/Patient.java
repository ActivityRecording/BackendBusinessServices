package ch.bfh.mle.backend.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Repraesentiert einen Patienten.
 * @author Boris Haueter, Stefan Walle
 */
@Entity
@Access(AccessType.FIELD)
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"patientNumber"})})
@NamedQueries({
    @NamedQuery(
            name="Patient.FindByTreatmentNumber", 
            query="SELECT NEW ch.bfh.mle.backend.service.dto.PatientWithTreatementCaseDto(p.id, p.patientNumber, p.lastName, p.firstName, p.dateOfBirth, t.id, t.treatmentNumber, t.startTime, t.endTime, t.released, false) FROM Patient AS p JOIN p.treatmentCases AS t WHERE t.treatmentNumber = :treatmentNumber"),
    @NamedQuery(
            name="Patient.FindByPatientNumber", 
            query="SELECT p FROM Patient AS p WHERE p.patientNumber = :patientNumber"),
    @NamedQuery(
            name="Patient.FindAllWithOpenTreatmentWithoutActivitiesToday", 
            query="SELECT NEW ch.bfh.mle.backend.service.dto.PatientListItemDto(p.id, p.patientNumber, p.lastName, p.firstName, p.dateOfBirth, t.id, t.treatmentNumber, 1) FROM Patient AS p JOIN p.treatmentCases AS t WHERE t.released = FALSE AND t.activities IS EMPTY AND t.startTime >= :today"),
    @NamedQuery(
            name="Patient.FindAllWithOpenTreatmentAndActivitiesToday", 
            query="SELECT NEW ch.bfh.mle.backend.service.dto.PatientListItemDto(p.id, p.patientNumber, p.lastName, p.firstName, p.dateOfBirth, t.id, t.treatmentNumber, 2) FROM Patient AS p JOIN p.treatmentCases AS t WHERE t.released = FALSE AND t.activities IS NOT EMPTY AND t.startTime >= :today"), 
    @NamedQuery(
            name="Patient.FindAllWithOpenTreatmentWithoutActivitiesCurrentWeek", 
            query="SELECT NEW ch.bfh.mle.backend.service.dto.PatientListItemDto(p.id, p.patientNumber, p.lastName, p.firstName, p.dateOfBirth, t.id, t.treatmentNumber, 1) FROM Patient AS p JOIN p.treatmentCases AS t WHERE t.released = FALSE AND t.activities IS EMPTY AND t.startTime >= :weekbeginning"),
    @NamedQuery(
            name="Patient.FindAllWithOpenTreatmentAndActivitiesCurrentWeek", 
            query="SELECT NEW ch.bfh.mle.backend.service.dto.PatientListItemDto(p.id, p.patientNumber, p.lastName, p.firstName, p.dateOfBirth, t.id, t.treatmentNumber, 2) FROM Patient AS p JOIN p.treatmentCases AS t WHERE t.released = FALSE AND t.activities IS NOT EMPTY AND t.startTime >= :weekbeginning"),
    @NamedQuery(
            name="Patient.FindAllWithOpenTreatmentWithoutActivitiesAllTime", 
            query="SELECT NEW ch.bfh.mle.backend.service.dto.PatientListItemDto(p.id, p.patientNumber, p.lastName, p.firstName, p.dateOfBirth, t.id, t.treatmentNumber, 1) FROM Patient AS p JOIN p.treatmentCases AS t WHERE t.released = FALSE AND t.activities IS EMPTY"),
    @NamedQuery(
            name="Patient.FindAllWithOpenTreatmentAndActivitiesAllTime", 
            query="SELECT NEW ch.bfh.mle.backend.service.dto.PatientListItemDto(p.id, p.patientNumber, p.lastName, p.firstName, p.dateOfBirth, t.id, t.treatmentNumber, 2) FROM Patient AS p JOIN p.treatmentCases AS t WHERE t.released = FALSE AND t.activities IS NOT EMPTY"),

    
    @NamedQuery(
            name="Patient.FindByEmployeeWithOpenTreatmentWithoutActivitiesToday", 
            query="SELECT NEW ch.bfh.mle.backend.service.dto.PatientListItemDto(p.id, p.patientNumber, p.lastName, p.firstName, p.dateOfBirth, t.id, t.treatmentNumber, 1) FROM Patient AS p JOIN p.treatmentCases AS t WHERE t.released = FALSE AND t.activities IS EMPTY AND t.startTime >= :today AND EXISTS (SELECT z.id FROM TimePeriod AS z WHERE z.treatmentCase.id = t.id and z.supplier.employeeID = :employeeId) AND NOT EXISTS (SELECT ap.id FROM Approval AS ap WHERE ap.treatmentCase.id = t.id and ap.supplier.employeeID = :employeeId)"),
    @NamedQuery(
            name="Patient.FindByEmployeeWithOpenTreatmentAndActivitiesToday", 
            query="SELECT NEW ch.bfh.mle.backend.service.dto.PatientListItemDto(p.id, p.patientNumber, p.lastName, p.firstName, p.dateOfBirth, t.id, t.treatmentNumber, 2) FROM Patient AS p JOIN p.treatmentCases AS t WHERE t.released = FALSE AND t.activities IS NOT EMPTY AND t.startTime >= :today AND EXISTS (SELECT z.id FROM TimePeriod AS z WHERE z.treatmentCase.id = t.id and z.supplier.employeeID = :employeeId) AND NOT EXISTS (SELECT ap.id FROM Approval AS ap WHERE ap.treatmentCase.id = t.id and ap.supplier.employeeID = :employeeId)"),
    @NamedQuery(
            name="Patient.FindByEmployeeWithOpenTreatmentWithoutActivitiesCurrentWeek", 
            query="SELECT NEW ch.bfh.mle.backend.service.dto.PatientListItemDto(p.id, p.patientNumber, p.lastName, p.firstName, p.dateOfBirth, t.id, t.treatmentNumber, 1) FROM Patient AS p JOIN p.treatmentCases AS t WHERE t.released = FALSE AND t.activities IS EMPTY AND t.startTime >= :weekbeginning AND EXISTS (SELECT z.id FROM TimePeriod AS z WHERE z.treatmentCase.id = t.id and z.supplier.employeeID = :employeeId) AND NOT EXISTS (SELECT ap.id FROM Approval AS ap WHERE ap.treatmentCase.id = t.id and ap.supplier.employeeID = :employeeId)"),
    @NamedQuery(
            name="Patient.FindByEmployeeWithOpenTreatmentAndActivitiesCurrentWeek", 
            query="SELECT NEW ch.bfh.mle.backend.service.dto.PatientListItemDto(p.id, p.patientNumber, p.lastName, p.firstName, p.dateOfBirth, t.id, t.treatmentNumber, 2) FROM Patient AS p JOIN p.treatmentCases AS t WHERE t.released = FALSE AND t.activities IS NOT EMPTY AND t.startTime >= :weekbeginning AND EXISTS (SELECT z.id FROM TimePeriod AS z WHERE z.treatmentCase.id = t.id and z.supplier.employeeID = :employeeId) AND NOT EXISTS (SELECT ap.id FROM Approval AS ap WHERE ap.treatmentCase.id = t.id and ap.supplier.employeeID = :employeeId)"),
    @NamedQuery(
            name="Patient.FindByEmployeeWithOpenTreatmentWithoutActivitiesAllTime", 
            query="SELECT NEW ch.bfh.mle.backend.service.dto.PatientListItemDto(p.id, p.patientNumber, p.lastName, p.firstName, p.dateOfBirth, t.id, t.treatmentNumber, 1) FROM Patient AS p JOIN p.treatmentCases AS t WHERE t.released = FALSE AND t.activities IS EMPTY AND EXISTS (SELECT z.id FROM TimePeriod AS z WHERE z.treatmentCase.id = t.id and z.supplier.employeeID = :employeeId) AND NOT EXISTS (SELECT ap.id FROM Approval AS ap WHERE ap.treatmentCase.id = t.id and ap.supplier.employeeID = :employeeId)"),
    @NamedQuery(
            name="Patient.FindByEmployeeWithOpenTreatmentAndActivitiesAllTime", 
            query="SELECT NEW ch.bfh.mle.backend.service.dto.PatientListItemDto(p.id, p.patientNumber, p.lastName, p.firstName, p.dateOfBirth, t.id, t.treatmentNumber, 2) FROM Patient AS p JOIN p.treatmentCases AS t WHERE t.released = FALSE AND t.activities IS NOT EMPTY AND EXISTS (SELECT z.id FROM TimePeriod AS z WHERE z.treatmentCase.id = t.id and z.supplier.employeeID = :employeeId) AND NOT EXISTS (SELECT ap.id FROM Approval AS ap WHERE ap.treatmentCase.id = t.id and ap.supplier.employeeID = :employeeId)")
        
})       
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Patient implements Serializable {

    /**
     * Default Konstruktor fuer JPA
     */
    public Patient() {
        this.treatmentCases = new ArrayList<>();
    }
    
    /**
     * Serial-ID
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Technische Datenbank-ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * Fachliche Patienten-ID
     */
    @NotNull
    @Column(nullable = false)
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
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    
    /**
     * Behandlungsfaelle des Patienten
     */
    @XmlTransient
    @OneToMany(mappedBy = "patient", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    private List<TreatmentCase> treatmentCases;

    /**
     * Gibt die fachliche Patientennummer zurueck.
     * @return patientNumber
     */
    public Long getPatientNumber() {
        return patientNumber;
    }

    /**
     * Setzt die fachliche PatientanID.
     * @param patientNumber 
     */
    public void setPatientNumber(Long patientNumber) {
        this.patientNumber = patientNumber;
    }

    /**
     * Gibt die technische Datenbank-ID zurueck.
     * @return id
     */
    public Long getId() {
        return id;
    }
    
    /**
     * gibt den Vornamen zurueck.
     * @return firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Setzt den Vornamen.
     * @param firstName 
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gibt den Nachnamen zurueck.
     * @return lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Setzt den Nachnamen.
     * @param lastName 
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gibt das Geburtsdatum zurueck
     * @return dateOfBirth
     */
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Setzt das Geburtsdatum
     * @param dateOfBirth 
     */
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    
    /**
     * Gibt die Behandlungsfaelle des Patienten zurueck
     * @return treatmentCases
     */
    public List<TreatmentCase> getTreatmentCases() {
        return treatmentCases;
    }

    /**
     * Setzt die Hehandlungsfaelle eines Patienten.
     * Darf von aussen nicht gesetzt werden.
     * @param treatmentCases 
     */
    private void setTreatmentCases(List<TreatmentCase> treatmentCases) {
        this.treatmentCases = treatmentCases;
    }
    
    /**
     * Fuegt einen Behandlungsfall zum Patienten hinzu.
     * @param treatmentCase 
     */
    public void addTreatmentCase(TreatmentCase treatmentCase){
        this.treatmentCases.add(treatmentCase);
    }

    /**
     * Ueberschreibt die Standard hashCode Methode.
     * @return hash
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    /**
     * Vergleicht zwei Patienten, ob sie gleich sind. <br>
     * Fuer den Vergleich wird die technische Datenbank-ID erwendet.
     * @param object
     * @return boolean
     */
    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (object == this) {
            return true;
        }
        if (!(object instanceof Patient)) {
            return false;
        }
        Patient other = (Patient) object;
        if (this.id == null || other.id == null){
            return false;
        }
        return this.id.equals(other.id);
    }

    /**
     * Gibt eine String-Repraesentation des Patienten zurueck
     * @return String
     */
    @Override
    public String toString() {
        return "Patient{ id=" + id + " patientNumber=" + patientNumber + " name= "+ lastName + " " + firstName + " dateOfBirth=" + dateOfBirth + " }]";
    }
    
}
