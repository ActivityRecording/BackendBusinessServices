/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.mle.backend.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Repraesentiert einen Patienten.
 * @author Boris Haueter, Stefan Walle
 */
@Entity
@Access(AccessType.FIELD)
public class Patient implements Serializable {

    /**
     * Default Konstruktor fuer JPA
     */
    public Patient() {
        this.behandlungsfaelle = new ArrayList<>();
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
    private Long patientID;
    
    /**
     * Nachname
     */
    private String lastName;
 
    /**
     * Vorname
     */
    private String firstName;
    
    /**
     * Behandlungsfaelle des Patienten
     */
    @OneToMany(mappedBy = "patient", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    private List<Behandlungsfall> behandlungsfaelle;

    /**
     * Gibt die fachliche PatientenID zurueck.
     * @return Long
     */
    public Long getPatientID() {
        return patientID;
    }

    /**
     * Setzt die fachliche PatientanID.
     * @param patientID 
     */
    public void setPatientID(Long patientID) {
        this.patientID = patientID;
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
     * Gibt die Behandlungsfaelle des Patienten zurueck
     * @return behandlungsfaelle
     */
    public List<Behandlungsfall> getBehandlungsfaelle() {
        return behandlungsfaelle;
    }

    /**
     * Setzt die Hehandlungsfaelle eines Patienten.
     * Darf von aussen nicht gesetzt werden.
     * @param behandlungsfaelle 
     */
    private void setBehandlungsfaelle(List<Behandlungsfall> behandlungsfaelle) {
        this.behandlungsfaelle = behandlungsfaelle;
    }
    
    /**
     * Fuegt einen Behandlungsfall zum Patienten hinzu.
     * @param behandlungsfall 
     */
    public void addBehandlungsfall(Behandlungsfall behandlungsfall){
        this.behandlungsfaelle.add(behandlungsfall);
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
     * Vergleicht zwei Patienten, ob sie gleich sind. <br />
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
     * @return 
     */
    @Override
    public String toString() {
        return "Patient{ Patient-ID= " + patientID + " name= "+ lastName + " " + firstName +" }]";
    }
    
}
