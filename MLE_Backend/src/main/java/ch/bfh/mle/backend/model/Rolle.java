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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Repraesentiert eine Mitarbeiterrolle <br />
 * Die Rolle kann ein Arzt oder Pflegeperson sein.
 * @author Stefan Walle
 */
@Entity
@Access(AccessType.FIELD)
public class Rolle implements Serializable{

    /**
     * Default Konstruktor fuer JPA
     */
    public Rolle() {
        this.standardkatalog = new ArrayList<>();
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
     * Bezeichnung der Rolle.
     */
    private String bezeichnung;
    
    /**
     * Art der Rolle.
     */
    @Enumerated(EnumType.STRING)
    private Rollenart art;
    
    /**
     * Standardkatalog der Rolle. <br />
     * Jeder Rolle ist ein Katalog mit Standardleistungen zugeteilt.
     */
    @OneToMany(mappedBy = "rolle", fetch=FetchType.LAZY, cascade = CascadeType.ALL )
    private List<Standardleistung> standardkatalog;
    
    /**
     * Gibt die technische Datenbank-ID zurueck.
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Gibt die Bezeichnung der Rolle zurueck.
     * @return bezeichnung
     */
    public String getBezeichnung() {
        return bezeichnung;
    }

    /**
     * Setzt die Bezeichnung der Rolle.
     * @param bezeichnung 
     */
    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    /**
     * Gibt die Art der Rolle zurueck.
     * @return art
     */
    public Rollenart getArt() {
        return art;
    }

    /**
     * Setzt die Art der Rolle.
     * @param art 
     */
    public void setArt(Rollenart art) {
        this.art = art;
    }

    /**
     * Gibt den Standardkatalog der Rolle zurueck.
     * @return standardkatalog
     */
    public List<Standardleistung> getStandardkatalog() {
        return standardkatalog;
    }

    /**
     * Setzt den Standardkatalog der Rolle.
     * @param standardkatalog 
     */
    public void setStandardkatalog(List<Standardleistung> standardkatalog) {
        this.standardkatalog = standardkatalog;
    }

    /**
     * Ueberschreibt die Standard hashCode Methode.
     * @return hash
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.id);
        return hash;
    }

    /**
     * Vergleicht zwei Rollen, ob sie gleich sind. <br />
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
        if (!(object instanceof Rolle)) {
            return false;
        }
        final Rolle other = (Rolle) object;
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
        return "Rolle{" + "id=" + id + ", bezeichnung=" + bezeichnung + '}';
    }

    
}
