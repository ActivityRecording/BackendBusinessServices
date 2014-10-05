/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.mle.backend.modellayer;

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
import javax.persistence.OneToOne;

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
    private Leistung(){
        
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
    @OneToOne(fetch = FetchType.LAZY)
    private Leistungserbringer leistungserbringer;
    /**
     * Referenzierte Leistung aus dem Tarmedkatalog.
     */
    @OneToOne(fetch = FetchType.LAZY)
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
     * Setzt den Leistungserbringer.
     * @param leistungserbringer 
     */
    public void setLeistungserbringer(Leistungserbringer leistungserbringer) {
        this.leistungserbringer = leistungserbringer;
    }

    /**
     * Gibt die Tarmedleistung zu dieser erbrachten Leistung zurueck.
     * @return tarmedleistung
     */
    public Tarmedleistung getTarmedleistung() {
        return tarmedleistung;
    }

    /**
     * Setzt die Tarmedleistung aus dem Tarmedkatalog.
     * @param tarmedleistung 
     */
    public void setTarmedleistung(Tarmedleistung tarmedleistung) {
        this.tarmedleistung = tarmedleistung;
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
        return this.id == other.id;
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
