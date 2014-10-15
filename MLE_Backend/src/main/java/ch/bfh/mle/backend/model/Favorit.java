/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.mle.backend.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Repraesentiert eine Liste von Tarmedleistungen, die dem Anwender in der Liste seiner Favoriten angezeigt werden.
 * @author Stefan Walle
 */
@Entity
@Access(AccessType.FIELD)
public class Favorit implements Serializable {

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
     * Leistungserbringer, zu dem der Favorit gehoert.
     */
    @ManyToOne
    private Leistungserbringer leistungserbringer;
    
    /**
     * Zugehoerige Tarmedleistung.
     */
    @ManyToOne
    private Tarmedleistung tarmedLeistung;

    /**
     * Gibt die technische Datenbank-ID zurueck.
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Gibt den Leistungserbringer zurueck.
     * @return leistungserbringer
     */
    public Leistungserbringer getLeistungserbringer() {
        return leistungserbringer;
    }

    /**
     * Setzt den Leistungserbringer.
     * @param leistungserbringer 
     */
    public void setLeistungserbringer(Leistungserbringer leistungserbringer) {
        this.leistungserbringer = leistungserbringer;
    }
    
    /**
     * Gibt die Tarmedleistung zurueck.
     * @return tarmedLeistung
     */
    public Tarmedleistung getTarmedLeistung() {
        return tarmedLeistung;
    }
    
    /**
     * Setzt die Tarmedleisung.
     * @param tarmedLeistung 
     */
    public void setTarmedLeistung(Tarmedleistung tarmedLeistung) {
        this.tarmedLeistung = tarmedLeistung;
    }

    /**
     * Ueberschreibt die Standard hashCode Methode.
     * @return hash
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        return hash;
    }

    /**
     * Vergleicht zwei Favoriten, ob sie gleich sind. <br />
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
        if (!(object instanceof Favorit)) {
            return false;
        }
        Favorit other = (Favorit) object;
        if (this.id == null || other.id == null){
            return false;
        }
        return this.id.equals(other.id);
    }

    /**
     * Gibt eine String-Raepresentation des Favoriten zurueck.
     * @return string
     */
    @Override
    public String toString() {
        return "Favorit{" + "id=" + id + '}';
    }
}
