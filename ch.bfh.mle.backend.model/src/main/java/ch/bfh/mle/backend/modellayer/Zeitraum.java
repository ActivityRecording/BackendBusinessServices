/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.mle.backend.modellayer;

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
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Die Klasse repraesentiert eine gemessene Zeit in der eine Behandlung erfolgt ist
 * oder in der ein Patient nicht gepflegt wurde. <br />
 * Die Art des Zeitraumes wird durch das Merkmal art bestimmt. <br />
 * Das Ende des Zeitraumes kann null sein, solange ein Pflegeunterbruch nicht abgeschlossen ist.
 * @author Stefan Walle
 */
@Entity
@Access(AccessType.FIELD)
public class Zeitraum implements Serializable {
    
    /**
     * Default Konstruktor fuer JPA
     */
    private Zeitraum(){
        
    }
    
    /**
     * Konstruktor zum Erstellen einer Leistung mit einem Leistungserbringer.
     * @param leistungserbringer 
     */
    public Zeitraum(Leistungserbringer leistungserbringer){
        this.leistungserbringer = leistungserbringer;
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
    private Zeitraumart art;
    
    /**
     * Startzeitpunkt
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date start;
    
    /**
     * Endzeitpunkt
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date ende;
    
    /**
     * Behandlungsfall zu dem dieser Zeitraum gehoert.
     */
    @ManyToOne
    private Behandlungsfall behandlungsfall;
    
    /**
     * Leistungserbringer, die diesen Zeitraum gemessen hat.
     */
    @OneToOne
    private Leistungserbringer leistungserbringer;

    /**
     * Gibt die technische Datenbank-ID zurueck.
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Setzt die technische Datenbank-ID. <br />
     * Die ID wird generiert und kann nicht manuell gesetzt werden.
     * @param id 
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gibt die Art des Zeitraumes zurueck.
     * @return art
     */
    public Zeitraumart getArt() {
        return art;
    }

    /**
     * Setzt die Zeitraumart.
     * @param art 
     */
    public void setArt(Zeitraumart art) {
        this.art = art;
    }

    /**
     * Gibt den Startzeitpunkt des Zeitraumes zurueck.
     * @return start
     */
    public Date getStart() {
        return start;
    }

    /**
     * Setzt den Startzeitpunkt des Zeitraumes.
     * @param start 
     */
    public void setStart(Date start) {
        this.start = start;
    }

    /**
     * Gibt das Ende des Zeitraumes zurueck. <br />
     * Das Ende kann fuer Pflegeunterbrueche null sein, solange der Patient nicht zurueck in der Pflege ist.
     * @return ende oder null
     */
    public Date getEnde() {
        return ende;
    }

    /**
     * Setzt das Ende des Zeitraumes
     * @param ende 
     */
    public void setEnde(Date ende) {
        this.ende = ende;
    }

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
        if (!(object instanceof Zeitraum)) {
            return false;
        }
        Zeitraum other = (Zeitraum) object;
        return this.id == other.id ;
    }
    
    /**
     * Gibt eine String-Repraesentation des Zeitraums zurueck
     * @return 
     */
    @Override
    public String toString() {
        return "Zeitraum{" + "id=" + id + ", art=" + art + '}';
    }
   
}
