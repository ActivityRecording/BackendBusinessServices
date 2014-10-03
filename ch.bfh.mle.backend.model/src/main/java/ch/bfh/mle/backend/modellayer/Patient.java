/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.mle.backend.modellayer;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
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
    
    private static final long serialVersionUID = 1L;
    
    /**
     * Technische Datenbank-ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
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
    @OneToMany(mappedBy = "patient", cascade=CascadeType.ALL)
    private List<Behandlungsfall> behandlungsfaelle;

    /**
     * Gibt die technische Datenbank-ID zurück.
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Setzt die technische Datenbank-ID. <br />
     * Die ID wird automatisch generiert und kann micht manuell gesetzt werden.
     * @param id 
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * gibt den Vornamen zurück.
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
     * Gibt den Nachnamen zurück.
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
    public void setBehandlungsfaelle(List<Behandlungsfall> behandlungsfaelle) {
        this.behandlungsfaelle = behandlungsfaelle;
    }
    
    /**
     * Fügt einen Behandlungsfall zum Patienten hinzu.
     * @param behandlungsfall 
     */
    public void addBehandlungsfall(Behandlungsfall behandlungsfall){
        this.behandlungsfaelle.add(behandlungsfall);
    }

    /**
     * Überschreibt die Standard hashCode Methode.
     * @return 
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    /**
     * Vergleicht zwei Patienten, ob sie gleich sind. <br />
     * Für den Vergleich wird die technische Datenbank-ID verwendet.
     * @param object
     * @return boolean
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Patient)) {
            return false;
        }
        Patient other = (Patient) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    /**
     * Gibt eine String-Repraesentation des Patienten zurück
     * @return 
     */
    @Override
    public String toString() {
        return "ch.bfh.mle.backend.modellayer.Patient[ id= " + id + " name= "+ lastName + " " + firstName +" ]";
    }
    
}
