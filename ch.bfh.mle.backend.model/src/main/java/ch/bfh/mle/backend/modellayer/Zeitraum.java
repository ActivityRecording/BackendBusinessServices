/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.mle.backend.modellayer;

import java.io.Serializable;
import java.util.Date;
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
 * Die Art des Zeitraumes wird durch das Merkmal art bestimmt. <br />
 * Das Ende des Zeitraumes kann null sein, solange ein Pflegeunterbruch nicht abgeschlossen ist.
 * @author Stefan Walle
 */
@Entity
@Access(AccessType.FIELD)
public class Zeitraum implements Serializable {

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
     * Gibt die technische Datenbank-ID zurück.
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
     * Gibt die Art des Zeitraumes zurück.
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
     * Gibt den Startzeitpunkt des Zeitraumes zurück.
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
     * Gibt das Ende des Zeitraumes zurück. <br />
     * Das Ende kann für Pflegeunterbrüche null sein, solange der Patient nicht zurück in der Pflege ist.
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
    
    
   
}
