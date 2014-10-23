package ch.bfh.mle.backend.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
import javax.xml.bind.annotation.XmlTransient;

/**
 * Repraesentiert einen Zeitraum, waehrend dem ein Patient im Spital in Behandlung ist.
 *  
 * @author Stefan Walle
 */
@Entity
@Access(AccessType.FIELD)
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"treatmentNumber"})})
@NamedQueries({
    @NamedQuery(
            name="TreatmentCase.FindByTreatmentNumber", 
            query="SELECT t FROM TreatmentCase AS t WHERE t.treatmentNumber = :treatmentNumber"),
})
@XmlAccessorType(XmlAccessType.FIELD)
public class TreatmentCase implements Serializable {

   /**
     * Default Konstruktor für JPA. Der Konstruktor darf von aussen nicht verwendet werden.
     */
    protected TreatmentCase(){
        this.timePeriods = new ArrayList<>();
        this.activities = new ArrayList<>();
    }
 
    /**
     * Konstruktor zum Erstellen eines Bahandlungsfalles zu einem Patienten
     * @param patient 
     */
    public TreatmentCase(Patient patient){
        this();
        this.patient = patient;
    }
    
    /**
     * Serial-ID
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Technische Datenbank-ID der Entitaet
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * Fachliche Identifikation des Behandlungsfalles. <br />
     * Die Fall-ID wird von der Patientenadministration vergeben.
     */
    @NotNull
    @Column(nullable = false)
    private Long treatmentNumber;
    
    /**
     * Datum und Zeit des Eintritts des Patienten. <br />
     * Sollte nich null sein.
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;
    
    /**
     * Datum und Zeit des Austritts des Patienten. <br />
     * Das Datum kann null sein.
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;
    
    /**
     * Patient, der behandelt wurde.
     */
    @ManyToOne
    private Patient patient;

    /**
     * Behandlungszeitraeume oder Pflegeunterbrueche
     */
    @XmlTransient
    @OneToMany(mappedBy = "treatmentCase", fetch=FetchType.LAZY, cascade = CascadeType.ALL )
    private List<TimePeriod> timePeriods;
    
    /**
     * Erbrachte Leistungen
     */
    @XmlTransient
    @OneToMany(mappedBy = "treatmentCase", fetch=FetchType.LAZY, cascade = CascadeType.ALL )
    private List<Activity> activities;
    
    /**
     * Status Freigegeben
     */
    private Boolean released;
    
    /**
     * Gibt die technische ID zurueck.
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Gibt die fachliche Behandlungsnummer zurueck.
     * @return treatmentNumber
     */
    public Long getTreatmentNumber() {
        return treatmentNumber;
    }

    /**
     * Setzt die Fachliche ID.
     * Die Fall-ID wird von der Patientenadministration vergeben und 
     * kann nicht direkt gesetzt werden.
     * @param treatmentNumber
     */
    public void setTreatmentNumber(Long treatmentNumber) {
        this.treatmentNumber = treatmentNumber;
    }

    /**
     * Gibt den Zeitpunkt des Eintritts zurueck.
     * @return startTime
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * Setzt den Eintrittszeitpunkt.
     * @param startTime 
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * Gibt den Austrittszeitpunkt zurueck.
     * @return endTime
     */
    public Date getEndTime() {
        return endTime;
    }
    /**
     * Setzt den Eintrittszeitpunkt.
     * @param endTime 
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * Gibt den Patienten der behandelt wurde zurueck.
     * @return patient
     */
    public Patient getPatient() {
        return patient;
    }

    /**
     * Gibt die Zeitraeume des Behandlungsfalls zurueck.
     * @return 
     */
    public List<TimePeriod> getTimePeriods() {
        return timePeriods;
    }

    /**
     * Fuegt einen Zeitraum zum Behandlungsfall hinzu.
     * @param zeitraum 
     */
    public void addZeitraum(TimePeriod zeitraum){
        this.timePeriods.add(zeitraum);
    }

    /**
     * Gibt die Leistungen zum Behandlungsfall zurueck;
     * @return activities
     */
    public List<Activity> getActivities() {
        return activities;
    }

    /**
     * Fuegt eine Leistung zum Behandlungsfall hinzu.
     * @param leistung 
     */
    public void addLeistung(Activity leistung){
        this.activities.add(leistung);
    }

    /**
     * Gibt den Freigabestatus des Behandlungsfalls zurueck
     * @return released
     */
    public Boolean isReleased() {
        return released;
    }

    /**
     * Setzt den Freigabestatus des Behandlungsfalls
     * @param released 
     */
    public void setReleased(Boolean released) {
        this.released = released;
    }
    
    
    /**
     * Ueberschreibt die Standard hashCode Methode.
     * @return hash
     */
   @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.id);
        return hash;
    }
 
    /**
     * Vergleicht zwei Behandlungsfaelle, ob sie gleich sind. <br />
     * Fuer den Vergleich wird die technische Datenbank-ID verwendet.
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
        if (!(object instanceof TreatmentCase)) {
            return false;
        }
        TreatmentCase other = (TreatmentCase) object;
        if (this.id == null || other.id == null){
            return false;
        }
        return this.id.equals(other.id);
    }

    /**
     * Gibt eine String-Raepresentation des Behandlungsfalles zurueck.
     * @return string
     */
    @Override
    public String toString() {
        return "Treatment{" + "treatmentId=" + treatmentNumber + '}';
    }
    
}
