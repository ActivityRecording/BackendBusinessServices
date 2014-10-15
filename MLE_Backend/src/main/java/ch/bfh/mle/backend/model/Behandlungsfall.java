/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.mle.backend.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Repraesentiert einen Zeitraum, waehrend dem ein Patient im Spital in Behandlung ist.
 *  
 * @author Stefan Walle
 */
@Entity
@Access(AccessType.FIELD)
public class Behandlungsfall implements Serializable {

   /**
     * Default Konstruktor für JPA. Der Konstruktor darf von aussen nicht verwendet werden.
     */
    protected Behandlungsfall(){
        this.zeitraeume = new ArrayList<>();
        this.leistungen = new ArrayList<>();
    }
 
    /**
     * Konstruktor zum Erstellen eines Bahandlungsfalles zu einem Patienten
     * @param patient 
     */
    public Behandlungsfall(Patient patient){
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
    private Long fallId;
    
    /**
     * Datum und Zeit des Eintritts des Patienten. <br />
     * Sollte nich null sein.
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date beginn;
    
    /**
     * Datum und Zeit des Austritts des Patienten. <br />
     * Das Datum kann null sein.
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date ende;
    
    /**
     * Patient, der behandelt wurde.
     */
    @ManyToOne
    private Patient patient;

    /**
     * Behandlungszeitraeume oder Pflegeunterbrueche
     */
    @OneToMany(mappedBy = "behandlungsfall", fetch=FetchType.LAZY, cascade = CascadeType.ALL )
    private List<Zeitraum> zeitraeume;
    
    /**
     * Erbrachte Leistungen
     */
    @OneToMany(mappedBy = "behandlungsfall", fetch=FetchType.LAZY, cascade = CascadeType.ALL )
    private List<Leistung> leistungen;
    
    /**
     * Gibt die technische ID zurueck.
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Gibt die fachliche ID zurueck.
     * @return id
     */
    public Long getFallId() {
        return fallId;
    }

    /**
     * Setzt die Fachliche ID.
     * Die Fall-ID wird von der Patientenadministration vergeben und 
     * kann nicht direkt gesetzt werden.
     * @param fallId
     */
    public void setFallId(Long fallId) {
        this.fallId = fallId;
    }

    /**
     * Gibt den Zeitpunkt des Eintritts zurueck.
     * @return beginn
     */
    public Date getBeginn() {
        return beginn;
    }

    /**
     * Setzt den Eintrittszeitpunkt.
     * @param beginn 
     */
    public void setBeginn(Date beginn) {
        this.beginn = beginn;
    }

    /**
     * Gibt den Austrittszeitpunkt zurueck.
     * @return ende
     */
    public Date getEnde() {
        return ende;
    }
    /**
     * Setzt den Eintrittszeitpunkt.
     * @param ende 
     */
    public void setEnde(Date ende) {
        this.ende = ende;
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
    public List<Zeitraum> getZeitraeume() {
        return zeitraeume;
    }

    /**
     * Fuegt einen Zeitraum zum Behandlungsfall hinzu.
     * @param zeitraum 
     */
    public void addZeitraum(Zeitraum zeitraum){
        this.zeitraeume.add(zeitraum);
    }

    /**
     * Gibt die Leistungen zum Behandlungsfall zurueck;
     * @return leistungen
     */
    public List<Leistung> getLeistungen() {
        return leistungen;
    }

    /**
     * Fuegt eine Leistung zum Behandlungsfall hinzu.
     * @param leistung 
     */
    public void addLeistung(Leistung leistung){
        this.leistungen.add(leistung);
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
        if (!(object instanceof Behandlungsfall)) {
            return false;
        }
        Behandlungsfall other = (Behandlungsfall) object;
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
        return "Behandlungsfall{" + "Fall-ID=" + fallId + '}';
    }
    
}
