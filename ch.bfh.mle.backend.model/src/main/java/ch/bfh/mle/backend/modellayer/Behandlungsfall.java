/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.mle.backend.modellayer;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Repraesentiert einen Zeitraum, während dem ein Patient im Spital in Behandlung ist.
 *  
 * @author Stefan Walle
 */
@Entity
@Access(AccessType.FIELD)
public class Behandlungsfall implements Serializable {
    
    /**
     * Default Konstruktor für JPA. Der Konstruktor kann von aussen nicht verwendet werden.
     */
    private Behandlungsfall(){
        
    }
 
    /**
     * Konstruktor zum Erstellen eines Bahandlungsfalles zu einem Patienten
     * @param patient 
     */
    public Behandlungsfall(Patient patient){
        this.patient = patient;
    }
    
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
    @OneToMany(mappedBy = "behandlungsfall", cascade = CascadeType.ALL )
    private Set<Zeitraum> zeitraeume;
    
    /**
     * Gibt die technische ID zurück.
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Setzt die technische Datenbank-ID. <br />
     * Die ID wird automasich generiert und kann nicht direkt gesetzt werden.
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gibt die fachliche ID zurück.
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
    protected void setFallId(Long fallId) {
        this.fallId = fallId;
    }

    /**
     * Gibt den Zeitpunkt des Eintritts zurück.
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
     * Gibt den Austrittszeitpunkt zurück.
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
     * Gibt den Patienten der behandelt wurde zurück.
     * @return patient
     */
    public Patient getPatient() {
        return patient;
    }

    /**
     * Setzt den Patienten
     * @param patient 
     */
    private void setPatient(Patient patient) {
        this.patient = patient;
    }
 
    /**
     * Gibt die Zeitraeume des Behandlungsfalls zurueck.
     * @return 
     */
    public Set<Zeitraum> getZeitraeume() {
        return zeitraeume;
    }

    /**
     * Setzt die Zeitraeume des Behandlungsfalls.
     * Direktes Setzen ist von aussen nicht möglich. Zeitraeume werden mit addzeitraum() hinzugefügt.
     * @param zeitraeume 
     */
    private void setZeitraeume(Set<Zeitraum> zeitraeume) {
        this.zeitraeume = zeitraeume;
    }
    
    /**
     * Fuegt einen Zeitraum zum Behandlungsfall hinzu.
     * @param zeitraum 
     */
    public void addZeitraum(Zeitraum zeitraum){
        this.zeitraeume.add(zeitraum);
    }
    
}
