package ch.bfh.mle.backend.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * Repraesentiert eine Leistung aus dem Tarmedkatalog
 * @author Stefan Walle
 */
@Entity
@Access(AccessType.FIELD)
@Table(name="TARMEDACTIVITY")
@XmlAccessorType(XmlAccessType.FIELD)
public class TarmedActivity implements Serializable{

    /**
     * Serial-ID
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Fachliche Tarmed-ID.
     */
    @Id
    private String id;
    
    /**
     * Bezeichung der Tarmedleistung.
     */
    private String description;
    
    /**
     * Vorgegebene Dauer der Leistung in Minuten.
     */
    private Integer duration;
    
    /**
     * Medizinische Information
     */
    @Lob
    @Size(max = 16777215)
    private String medicalInformation;
    
    /**
     * Gueltig ab
     */
    @Temporal(TemporalType.DATE)
    private Date validFrom;
    
    /**
     * Gueltig bis
     */
    @Temporal(TemporalType.DATE)
    private Date validTo;

    /**
     * Gibt die Tarmed-ID zurueck.
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * Setzt die Tarmed-ID.
     * @param id 
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gibt die Bezeichung zurueck.
     * @return bezeichnung
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setzt die description der Tarmedleistung.
     * @param description 
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gibt die Dauer der Tamedleistung in Minuten zurueck.
     * @return duration
     */
    public Integer getDuration() {
        return duration;
    }

    /**
     * Setzt die Dauer der Tarmedleistung in Minuten.
     * @param duration 
     */
    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    /**
     * Gibt die Medizinischen Informationen zurueck
     * @return medicalInformation
     */
    public String getMedicalInformatinon() {
        return medicalInformation;
    }

    /**
     * Setzt die die Medizinischen Informationen
     * @param medicalInformatinon 
     */
    public void setMedicalInformatinon(String medicalInformatinon) {
        this.medicalInformation = medicalInformatinon;
    }

    /**
     * Gibt das Gueltig-ab-Datum zurueck
     * @return validFrom
     */
    public Date getValidFrom() {
        return validFrom;
    }

    /**
     * Setzt das Gueltig-ab-Datum
     * @param validFrom 
     */
    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    /**
     * Gibt das Gueltig-bis-Datum zurueck
     * @return validTo
     */
    public Date getValidTo() {
        return validTo;
    }

    /**
     * Setzt das Gueltig-bis-Datum
     * @param validTo 
     */
    public void setValidTo(Date validTo) {
        this.validTo = validTo;
    }

    /**
     * Ueberschreibt die Standard hashCode Methode.
     * @return hash
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.id);
        return hash;
    }

    /**
     * Ueberschreibt die Standard equals Methode und vergeicht zwei Tarmedleistungen.
     * @param obj
     * @return boolean
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        final TarmedActivity other = (TarmedActivity) obj;
        if (this.id == null || other.id == null){
            return false;
        }
        return this.id.equals(other.id);
    }

    /**
     * Gibt eine String-Raepresentation einer Rolle zurueck.
     * @return string
     */
    @Override
    public String toString() {
        return "TarmedActivity{" + "Id=" + id + ", description=" + description + ", duration=" + duration + '}';
    }

    
}
