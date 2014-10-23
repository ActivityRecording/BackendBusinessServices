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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Repraesentiert einen Katalog von Standardleistungen, die einem Leistungserbringer zur Auswahl stehen.
 * @author Stefan Walle
 */
@Entity
@Access(AccessType.FIELD)
@NamedQueries({
    @NamedQuery(
            name="StandardActivity.FindByEmployeeId", 
            query="SELECT a FROM Supplier AS s JOIN s.role AS r JOIN r.standardActivities a WHERE s.employeeID = :employeeId"),
})
@XmlAccessorType(XmlAccessType.FIELD)
public class StandardActivity implements Serializable{

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
    private String description;
    
    /**
     * Rolle zu der die Standardleistung gehoert.
     */
    @XmlTransient
    @ManyToOne
    private Role role;
    
    /**
     * Zugehoerige Tarmedleistung.
     */
    @ManyToOne
    private TarmedActivity tarmedActivity;

    /**
     * Gibt die technische Datenbank-ID zurück.
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Gibt die Bezeichnung zurueck.
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setzt die Bezeichnung.
     * @param description 
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gibt die Rolle zu der die Standardleistung gehoert zurueck.
     * @return role
     */
    public Role getRole() {
        return role;
    }

    /**
     * Setzt die Rolle zu der die Standardleistung gehoert.
     * @param role 
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Gibt die zugehoerige Tarmedleistung zurueck.
     * @return tarmedActivity
     */
    public TarmedActivity getTarmedActivity() {
        return tarmedActivity;
    }

    /**
     * Setzt die Tarmedleistung.
     * @param tarmedActivity 
     */
    public void setTarmedActivity(TarmedActivity tarmedActivity) {
        this.tarmedActivity = tarmedActivity;
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
        if (!(object instanceof StandardActivity)) {
            return false;
        }
        final StandardActivity other = (StandardActivity) object;
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
        return "StandardActivity{" + "id=" + id + ", description=" + description + '}';
    }

    
}
