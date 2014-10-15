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

/**
 * Repraesentiert eine Leistung aus dem Tarmedkatalog
 * @author Stefan Walle
 */
@Entity
@Access(AccessType.FIELD)
public class Tarmedleistung implements Serializable{

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
     * Fachliche Tarmed-ID.
     */
    private Long tarmedId;
    
    /**
     * Bezeichung der Tarmedleistung.
     */
    private String Bezeichnung;
    
    /**
     * Vorgegebene Dauer der Leistung in Minuten.
     */
    private Integer dauer;

    /**
     * Gibt die technische Datenbank-ID zurueck.
     * @return id 
     */
    public Long getId() {
        return id;
    }

    /**
     * Gibt die fachliche Tarmed-ID zurueck.
     * @return tarmedId
     */
    public Long getTarmedId() {
        return tarmedId;
    }

    /**
     * Setzt die fachliche Tarmed-ID.
     * @param tarmedId 
     */
    public void setTarmedId(Long tarmedId) {
        this.tarmedId = tarmedId;
    }

    /**
     * Gibt die Bezeichung zurueck.
     * @return bezeichnung
     */
    public String getBezeichnung() {
        return Bezeichnung;
    }

    /**
     * Setzt die Bezeichnung der Tarmedleistung.
     * @param Bezeichnung 
     */
    public void setBezeichnung(String Bezeichnung) {
        this.Bezeichnung = Bezeichnung;
    }

    /**
     * Gibt die Dauer der Tamedleistung in Minuten zurueck.
     * @return dauer
     */
    public Integer getDauer() {
        return dauer;
    }

    /**
     * Setzt die Dauer der Tarmedleistung in Minuten.
     * @param dauer 
     */
    public void setDauer(Integer dauer) {
        this.dauer = dauer;
    }

    /**
     * Ueberschreibt die Standard hashCode Methode.
     * @return hash
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.id);
        return hash;
    }

    /**
     * Ueberschreibt die Standard equals Methode und vergeicht zwei Tarmedleistungen.
     * @param obj
     * @return boolean
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        final Tarmedleistung other = (Tarmedleistung) obj;
        if (this.id == null || other.id == null){
            return false;
        }
        return this.id.equals(other.id);
    }

    /**
     * Gibt eine String-Raepresentation einer Rolle zurueck.
     * @return string
     */
    @Override
    public String toString() {
        return "Tarmedleistung{" + "tarmedId=" + tarmedId + ", Bezeichnung=" + Bezeichnung + ", dauer=" + dauer + '}';
    }

    
}
