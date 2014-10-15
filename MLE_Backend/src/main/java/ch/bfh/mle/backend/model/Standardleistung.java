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
 * Repraesentiert einen Katalog von Standardleistungen, die einem Leistungserbringer zur Auswahl stehen.
 * @author Stefan Walle
 */
@Entity
@Access(AccessType.FIELD)
public class Standardleistung implements Serializable{

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
     * Bezeichnung
     */
    private String bezeichnung;
    
    /**
     * Rolle zu der die Standardleistung gehoert.
     */
    @ManyToOne
    private Rolle rolle;
    
    /**
     * Zugehoerige Tarmedleistung.
     */
    @ManyToOne
    private Tarmedleistung tarmedLeistung;

    /**
     * Gibt die technische Datenbank-ID zurück.
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Gibt die Bezeichnung zurueck.
     * @return bezeichnung
     */
    public String getBezeichnung() {
        return bezeichnung;
    }

    /**
     * Setzt die Bezeichnung.
     * @param bezeichnung 
     */
    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    /**
     * Gibt die Rolle zu der die Standardleistung gehoert zurueck.
     * @return rolle
     */
    public Rolle getRolle() {
        return rolle;
    }

    /**
     * Setzt die Rolle zu der die Standardleistung gehoert.
     * @param rolle 
     */
    public void setRolle(Rolle rolle) {
        this.rolle = rolle;
    }

    /**
     * Gibt die zugehoerige Tarmedleistung zurueck.
     * @return tarmedLeistung
     */
    public Tarmedleistung getTarmedLeistung() {
        return tarmedLeistung;
    }

    /**
     * Setzt die Tarmedleistung.
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
        int hash = 5;
        hash = 31 * hash + Objects.hashCode(this.id);
        return hash;
    }

    /**
     * Vergleicht zwei Standardleistungen, ob sie gleich sind. <br />
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
        if (!(object instanceof Standardleistung)) {
            return false;
        }
        final Standardleistung other = (Standardleistung) object;
        if (this.id == null || other.id == null){
            return false;
        }
        return this.id.equals(other.id);
    }

    /**
     * Gibt eine String-Raepresentation einer Standardleistung zurueck.
     * @return string
     */
    @Override
    public String toString() {
        return "Standardleistung{" + "id=" + id + ", bezeichnung=" + bezeichnung + '}';
    }

    
}
