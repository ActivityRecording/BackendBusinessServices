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
 * Repraesentiert eine Regel fur die Aufteilung der Leistungen auf die gemessene Zeit <br />
 * Je Tarmedleistung kann eine Regel zugeordnet werden, die bei der Auswahl und Verrechnung
 * der Leistungen beruecksichtigt werden muss.
 * @author Stefan Walle
 */
@Entity
@Access(AccessType.FIELD)
public class Zuteilungsregel implements Serializable{

    /**
     * Serial-ID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Technische Datenbank-ID der Entitaet
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * Referenziert die zugehoerige Tarmedleistung.
     */
    @ManyToOne
    private Tarmedleistung tarmedLeistung;
    
    /**
     * ID mit der Tarmedleistungen gruppiert werden koennen. <br />
     * Aus einer Gruppe kann nur eine Leistung ausgewaehlt werden.
     */
    private Integer gruppierungsID;
    
    /**
     * Haeufigkeit, wie oft die Leistung pro Behandlungsfall angegeben werden kann.
     */
    private Integer kardinalitaet;
    
    /**
     * Zeitangabe erforderlich. Wenn ja, muss bei der Erfassung eine Behandlungszeit eingegeben werden.
     */
    private Boolean erfordertZeitangabe;
    
    /**
     * Waehlbarkeit der Leistung. Wenn ja, kann die Leistung nicht manuell ausgewaehlt werden. <br />
     * Die Leistung wird automatisch hinzugefuegt.
     */
    private Boolean nichtauswaehlbar;
    
    /**
     * Gibt an, ob die Leistung mit den Zeitperioden verrechnet wird.
     */
    private Boolean ohneZeitmessung;

    /**
     * Gibt die technische Datenbank-ID zurueck.
     * @return id
     */
    public Long getId() {
        return id;
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
     * Gibt die GruppierungsID zurueck.
     * @return gruppierungsID
     */
    public Integer getGruppierungsID() {
        return gruppierungsID;
    }

    /**
     * Setzt die GruppierungsID.
     * @param gruppierungsID 
     */
    public void setGruppierungsID(Integer gruppierungsID) {
        this.gruppierungsID = gruppierungsID;
    }

    /**
     * Gibt die Kardinalitaet zurueck.
     * @return kardinalitaet
     */
    public Integer getKardinalitaet() {
        return kardinalitaet;
    }

    /**
     * Setzt die Kardinalitaet.
     * @param kardinalitaet 
     */
    public void setKardinalitaet(Integer kardinalitaet) {
        this.kardinalitaet = kardinalitaet;
    }

    /**
     * Gibt eine Boolean zurueck, ob eine Zeitangabe erforderlich ist.
     * @return Boolean
     */
    public Boolean getErfordertZeitangabe() {
        return erfordertZeitangabe;
    }

    /**
     * Setzt die Boolean, ob eine Zeitangabe erforderlich ist. 
     * @param erfordertZeitangabe 
     */
    public void setErfordertZeitangabe(Boolean erfordertZeitangabe) {
        this.erfordertZeitangabe = erfordertZeitangabe;
    }

    /**
     * Gibt eine Boolean zurueck, ob die Leistung manuell auswaehlbar ist.
     * @return Boolean
     */
    public Boolean getNichtauswaehlbar() {
        return nichtauswaehlbar;
    }

    /**
     * Setzt die Boolean, ob die Leistung manuell auswaehlbar ist.
     * @param nichtauswaehlbar 
     */
    public void setNichtauswaehlbar(Boolean nichtauswaehlbar) {
        this.nichtauswaehlbar = nichtauswaehlbar;
    }

    /**
     * Gibt eine Boolean zurueck, die aussagt, ob die Leistung mit der Zeitmessung verrechnet wird
     * @return Boolean
     */
    public Boolean getOhneZeitmessung() {
        return ohneZeitmessung;
    }

    /**
     * Setzt die Boolean ohneZeitmessung.
     * @param ohneZeitmessung 
     */
    public void setOhneZeitmessung(Boolean ohneZeitmessung) {
        this.ohneZeitmessung = ohneZeitmessung;
    }

    /**
     * Ueberschreibt die Standard hashCode Methode.
     * @return hash
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.id);
        return hash;
    }

    /**
     * Vergleicht zwei Zuteilungsregeln, ob sie gleich sind. <br />
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
        if (!(object instanceof Zuteilungsregel)) {
            return false;
        }
        Zuteilungsregel other = (Zuteilungsregel) object;
        if (this.id == null || other.id == null){
            return false;
        }
        return this.id.equals(other.id);
    }
    
    /**
     * Gibt eine String-Repraesentation der Zuteilungsregel zurueck
     * @return 
     */
   @Override
    public String toString() {
        return "Zuteilungsregel{" + "id=" + id + '}';
    }

}
