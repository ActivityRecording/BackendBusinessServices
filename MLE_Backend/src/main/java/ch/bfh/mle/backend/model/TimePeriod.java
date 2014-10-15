/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.mle.backend.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Die Klasse repraesentiert eine gemessene Zeit in der eine Behandlung erfolgt ist
 * oder in der ein Patient nicht gepflegt wurde. <br />
 Die Art des Zeitraumes wird durch das Merkmal type bestimmt. <br />
 * Das Ende des Zeitraumes kann null sein, solange ein Pflegeunterbruch nicht abgeschlossen ist.
 * @author Stefan Walle
 */
@Entity
@Access(AccessType.FIELD)
public class TimePeriod implements Serializable {
    
    /**
     * Default Konstruktor fuer JPA
     */
    protected TimePeriod(){
    }
    
    /**
     * Konstruktor zum Erstellen einer Leistung mit einem Leistungserbringer.
     * @param supplier 
     */
    public TimePeriod(Supplier supplier, TreatmentCase treatmentCase){
        this.supplier = supplier;
        this.treatmentCase = treatmentCase;
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
     * Art des Zeitraumes. 
     */
    @Enumerated(EnumType.STRING)
    private TimePeriodType type;
    
    /**
     * Startzeitpunkt
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;
    
    /**
     * Endzeitpunkt
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;
    
    /**
     * Behandlungsfall zu dem dieser Zeitraum gehoert.
     */
    @ManyToOne
    private TreatmentCase treatmentCase;
    
    /**
     * Leistungserbringer, die diesen Zeitraum gemessen hat.
     */
    @ManyToOne
    private Supplier supplier;

    /**
     * Gibt die technische Datenbank-ID zurueck.
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Gibt die Art des Zeitraumes zurueck.
     * @return type
     */
    public TimePeriodType getType() {
        return type;
    }

    /**
     * Setzt die Zeitraumart.
     * @param type 
     */
    public void setType(TimePeriodType type) {
        this.type = type;
    }

    /**
     * Gibt den Startzeitpunkt des Zeitraumes zurueck.
     * @return start
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * Setzt den Startzeitpunkt des Zeitraumes.
     * @param startTime 
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * Gibt das Ende des Zeitraumes zurueck. <br />
     * Das Ende kann fuer Pflegeunterbrueche null sein, solange der Patient nicht zurueck in der Pflege ist.
     * @return endTime oder null
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * Setzt das Ende des Zeitraumes
     * @param endTime 
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * Gibt den Behandlungsfall zurueck
     * @return treatmentCase
     */
    public TreatmentCase getTreatmentCase() {
        return treatmentCase;
    }

    /**
     * Gibt den Leistungserbringer zurueck
     * @return supplier
     */
    public Supplier getSupplier() {
        return supplier;
    }

    
    /**
     * Ueberschreibt die Standard hashCode Methode.
     * @return hash
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.id);
        return hash;
    }

    /**
     * Vergleicht zwei Zeitraeme, ob sie gleich sind. <br />
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
        if (!(object instanceof TimePeriod)) {
            return false;
        }
        TimePeriod other = (TimePeriod) object;
        if (this.id == null || other.id == null){
            return false;
        }
        return this.id.equals(other.id);
    }
    
    /**
     * Gibt eine String-Repraesentation des Zeitraums zurueck
     * @return 
     */
    @Override
    public String toString() {
        return "TimePeriod{" + "id=" + id + ", type=" + type + '}';
    }
   
}
