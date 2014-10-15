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
public class Activity implements Serializable{
    
    /**
     * Default-Konstruktor fuer JPA
     */
    protected Activity(){
        
    }
    
    /**
     * Konstruktor zum Erstellen einer neuen Leistung <br />
     * Es wird die Leistung aus dem Tarmedkatalog und der Leistungserbringer uebergeben.
     * @param tarmedActivity
     * @param supplier 
     */
    public Activity(TarmedActivity tarmedActivity, Supplier supplier){
        this.tarmedActivity = tarmedActivity;
        this.supplier = supplier;
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
    private Integer number;
    /**
     * Erbringer der Leistung.
     */
    @ManyToOne
    private Supplier supplier;
    /**
     * Referenzierte Leistung aus dem Tarmedkatalog.
     */
    @ManyToOne
    private TarmedActivity tarmedActivity;

    /**
     * Behandlungsfall zu dem diese Leistung gehoert.
     */
    @ManyToOne
    private TreatmentCase treatmentCase;
    
    /**
     * Git die technische Datenbank-ID zurueck.
     * @return 
     */
    public Long getId() {
        return id;
    }

    /**
     * Gibt die Anzahl zurueck, wie oft diese Leistung erbracht wurde.
     * @return number
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * Setzt die Anzahl, die oft diese Leistung erbracht wurde.
     * @param number 
     */
    public void setNumber(Integer number) {
        this.number = number;
    }

    /**
     * Gibt den Leistungserbringer zurueck.
     * @return supplier
     */
    public Supplier getSupplier() {
        return supplier;
    }

    /**
     * Gibt die Tarmedleistung zu dieser erbrachten Leistung zurueck.
     * @return tarmedActivity
     */
    public TarmedActivity getTarmedActivity() {
        return tarmedActivity;
    }

    /**
     * Gibt den Behandlungsfall zu dieser Leistung zurueck.
     * @return treatmentCase
     */
    public TreatmentCase getTreatmentCase() {
        return treatmentCase;
    }

    /**
     * Setzt den Behandlungsfall zur Leistung.
     * @param treatmentCase 
     */
    public void setTreatmentCase(TreatmentCase treatmentCase) {
        this.treatmentCase = treatmentCase;
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
        if (!(object instanceof Activity)) {
            return false;
        }
        Activity other = (Activity) object;
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
        return "Activity{" + "id=" + id + ", number=" + number + '}';
    }
    
}
