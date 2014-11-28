package ch.bfh.mle.backend.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * Die Klasse repraesentiert eine Freigabe eines Behandlungsfalles durch 
 * einen Leistungserbringer.
 * @author Stefan Walle
 */
@Entity
@Access(AccessType.FIELD)
@XmlAccessorType(XmlAccessType.FIELD)
public class Approval implements Serializable{
    
    /**
     * Default-Konstruktor fuer JPA
     */
    protected Approval() {
    }

    /**
     * Konstruktor fuer die Erstellung einer Freigabe durch den Leistungserbringer
     * supplier zum Behandlungsfall treatmentCase.
     * @param supplier
     * @param treatmentCase 
     */
    public Approval(Supplier supplier, TreatmentCase treatmentCase) {
        this.supplier = supplier;
        this.treatmentCase = treatmentCase;
    }
    
    /**
     * Serial-ID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Technische Datenbank-ID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Erbringer der Leistung.
     */
    @ManyToOne
    private Supplier supplier;

    /**
     * Behandlungsfall.
     */
    @ManyToOne
    private TreatmentCase treatmentCase;
    
    /**
     * Freigabedatum
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date approvedAt;

    /**
     * Gibt die technische Datenbank-ID zurueck
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Gibt den Leistungserbringer zurueck
     * @return supplier
     */
    public Supplier getSupplier() {
        return supplier;
    }

    /**
     * Setzt den Leistungserbringer
     * @param supplier 
     */
    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    /**
     * Gibt den Behandlungsfall zurueck.
     * @return treatmentCase
     */
    public TreatmentCase getTreatmentCase() {
        return treatmentCase;
    }

    /**
     * Setzt den Behandlungsfall
     * @param treatmentCase 
     */
    public void setTreatmentCase(TreatmentCase treatmentCase) {
        this.treatmentCase = treatmentCase;
    }

    /**
     * Gibt den Freigabezeitpunkt zurueck
     * @return approvedAt
     */
    public Date getApprovedAt() {
        return approvedAt;
    }

    /**
     * Setzt den Freigabezeitpunkt
     * @param approvedAt 
     */
    public void setApprovedAt(Date approvedAt) {
        this.approvedAt = approvedAt;
    }
    
    /**
     * Ueberschreibt die Standard hashCode Methode.
     * @return hash
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.id);
        return hash;
    }

    /**
     * Vergleicht zwei Freigaben, ob sie gleich sind. <br />
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
        if (!(object instanceof Approval)) {
            return false;
        }
        Approval other = (Approval) object;
        if (this.id == null || other.id == null){
            return false;
        }
        return this.id.equals(other.id);
    }

    /**
     * Gibt eine String-Raepresentation einer Freigabe zurueck.
     * @return string
     */
    @Override
    public String toString() {
        return "Approval{" + "id=" + id + ", approvedAt=" + approvedAt + '}';
    }
    
}
