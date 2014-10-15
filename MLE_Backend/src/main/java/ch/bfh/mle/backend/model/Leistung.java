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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Die Klasse repraesentiert eine erbrachte Leistung gemaess Tarmed-Katalog
 * @author Stefan Walle
 */
@Entity
@Access(AccessType.FIELD)
public class Leistung implements Serializable{
    
    /**
     * Default-Konstruktor fuer JPA
     */
    protected Leistung(){
        
    }
    
    /**
     * Konstruktor zum Erstellen einer neuen Leistung <br />
     * Es wird die Leistung aus dem Tarmedkatalog und der Leistungserbringer uebergeben.
     * @param tarmedleistung
     * @param leistungserbringer 
     */
    public Leistung(Tarmedleistung tarmedleistung, Leistungserbringer leistungserbringer){
        this.tarmedleistung = tarmedleistung;
        this.leistungserbringer = leistungserbringer;
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
     * Anzahl erbrachte Leistungen.
     */
    private Integer anzahl;
    /**
     * Erbringer der Leistung.
     */
    @ManyToOne
    private Leistungserbringer leistungserbringer;
    /**
     * Referenzierte Leistung aus dem Tarmedkatalog.
     */
    @ManyToOne
    private Tarmedleistung tarmedleistung;

    /**
     * Behandlungsfall zu dem diese Leistung gehoert.
     */
    @ManyToOne
    private Behandlungsfall behandlungsfall;
    
    /**
     * Git die technische Datenbank-ID zurueck.
     * @return 
     */
    public Long getId() {
        return id;
    }

    /**
     * Gibt die Anzahl zurueck, wie oft diese Leistung erbracht wurde.
     * @return anzahl
     */
    public Integer getAnzahl() {
        return anzahl;
    }

    /**
     * Setzt die Anzahl, die oft diese Leistung erbracht wurde.
     * @param anzahl 
     */
    public void setAnzahl(Integer anzahl) {
        this.anzahl = anzahl;
    }

    /**
     * Gibt den Leistungserbringer zurueck.
     * @return leistungserbringer
     */
    public Leistungserbringer getLeistungserbringer() {
        return leistungserbringer;
    }

    /**
     * Gibt die Tarmedleistung zu dieser erbrachten Leistung zurueck.
     * @return tarmedleistung
     */
    public Tarmedleistung getTarmedleistung() {
        return tarmedleistung;
    }

    /**
     * Gibt den Behandlungsfall zu dieser Leistung zurueck.
     * @return behandlungsfall
     */
    public Behandlungsfall getBehandlungsfall() {
        return behandlungsfall;
    }

    /**
     * Setzt den Behandlungsfall zur Leistung.
     * @param behandlungsfall 
     */
    public void setBehandlungsfall(Behandlungsfall behandlungsfall) {
        this.behandlungsfall = behandlungsfall;
    }
    
    /**
     * Ueberschreibt die Standard hashCode Methode.
     * @return hash
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.id);
        return hash;
    }

    /**
     * Vergleicht zwei Leistungen, ob sie gleich sind. <br />
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
        if (!(object instanceof Leistung)) {
            return false;
        }
        Leistung other = (Leistung) object;
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
        return "Leistung{" + "id=" + id + ", anzahl=" + anzahl + '}';
    }
    
}
