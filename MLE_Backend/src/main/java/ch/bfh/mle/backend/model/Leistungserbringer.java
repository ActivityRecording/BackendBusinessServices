/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.mle.backend.model;

import java.io.Serializable;
import java.util.ArrayList;
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

/**
 * Repraesentiert einen Erbringer einer Leistung. Dies kann ein Arzt oder eine Plegeperson sein.
 * @author Stefan Walle
 */
@Entity
@Access(AccessType.FIELD)
public class Leistungserbringer implements Serializable{

    /**
     * Default Konstruktor fuer JPA
     */
    public Leistungserbringer() {
        this.favoriten = new ArrayList<>();
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
     * Mitarbeiter Identifikation
     */
    private Long mitarbeiterID;
    
    /**
     * Nachname des Mitarbeiters
     */
    private String nachname;
    
    /**
     * Vorname des Mitarbeiters
     */
    private String vorname;
    
    /**
     * Rolle des Mitarbeiters.
     */
    @ManyToOne
    private Rolle rolle;
    
    /**
     * Liste der persoenlichen Favoriten von Leistungen
     */
    @OneToMany(mappedBy = "leistungserbringer", fetch=FetchType.LAZY, cascade = CascadeType.ALL )
    private List<Favorit> favoriten;
    
    /**
     * Gibt die technische Datenbank-ID zurueck.
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Gibt die Mitarbeiter-ID des Leistungserbringers zurueck.
     * @return mitarbeiterID
     */
    public Long getMitarbeiterID() {
        return mitarbeiterID;
    }

    /**
     * Setzt die Mitarbeiter-ID des Leistungserbringers.
     * @param mitarbeiterID 
     */
    public void setMitarbeiterID(Long mitarbeiterID) {
        this.mitarbeiterID = mitarbeiterID;
    }

    /**
     * Gibt den Namen des Leistungserbringers zurueck.
     * @return nachname
     */
    public String getNachname() {
        return nachname;
    }

    /**
     * Setzt den Nachnamen des Mitarbeiters
     * @param nachname 
     */
    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    /**
     * Gibt den Vornamen des Leistungserbringers zurueck.
     * @return vorname
     */
    public String getVorname() {
        return vorname;
    }

    /**
     * Setzt den Vornamen des Leistungserbringers
     * @param vorname 
     */
    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    /**
     * Gibt die Rolle des Leistungserbringers zurueck
     * @return rolle
     */
    public Rolle getRolle() {
        return rolle;
    }

    /**
     * Setzt die Rolle des Leistungserbringers
     * @param rolle 
     */
    public void setRolle(Rolle rolle) {
        this.rolle = rolle;
    }

    /**
     * Gibt die Liste der persoenlichen Favoriten zurueck.
     * @return favoriten
     */
    public List<Favorit> getFavoriten() {
        return favoriten;
    }
    
    /**
     * Setzt die Liste der Favoriten.
     * @param favoriten 
     */
    public void setFavoriten(List<Favorit> favoriten){
        this.favoriten = favoriten;
    }

    /**
     * Fuegt einen Favoriten zur Liste der Favoriten hinzu. 
     * @param favorit 
     */
    public void addFavorit(Favorit favorit) {
        this.favoriten.add(favorit);
    }
    
    /**
     * Ueberschreibt die Standard hashCode Methode.
     * @return hash
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.id);
        return hash;
    }

     /**
     * Vergleicht zwei Leistungserbringer, ob sie gleich sind. <br />
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
        if (!(object instanceof Leistungserbringer)) {
            return false;
        }
        final Leistungserbringer other = (Leistungserbringer) object;
        if (this.id == null || other.id == null){
            return false;
        }
        return this.id.equals(other.id);
    }

    /**
     * Gibt eine String-Raepresentation einer Leistung zurueck.
     * @return string
     */
    @Override
    public String toString() {
        return "Leistungserbringer{" + "mitarbeiterID=" + mitarbeiterID + ", nachname=" + nachname + ", vorname=" + vorname + '}';
    }

    
}
