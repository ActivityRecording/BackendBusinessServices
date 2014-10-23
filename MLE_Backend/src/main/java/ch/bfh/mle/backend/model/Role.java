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
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Repraesentiert eine Mitarbeiterrolle <br />
 * Die Rolle kann ein Arzt oder Pflegeperson sein.
 * @author Stefan Walle
 */
@Entity
@Access(AccessType.FIELD)
@XmlAccessorType(XmlAccessType.FIELD)
public class Role implements Serializable{

    /**
     * Default Konstruktor fuer JPA
     */
    public Role() {
        this.standardActivities = new ArrayList<>();
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
    private String description;
    
    /**
     * Art der Rolle.
     */
    @Enumerated(EnumType.STRING)
    private RoleType type;
    
    /**
     * Standardkatalog der Rolle. <br />
     * Jeder Rolle ist ein Katalog mit Standardleistungen zugeteilt.
     */
    @XmlTransient
    @OneToMany(mappedBy = "role", fetch=FetchType.LAZY, cascade = CascadeType.ALL )
    private List<StandardActivity> standardActivities;
    
    /**
     * Gibt die technische Datenbank-ID zurueck.
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Gibt die Bezeichnung der Rolle zurueck.
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setzt die Bezeichnung der Rolle.
     * @param description 
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gibt die Art der Rolle zurueck.
     * @return type
     */
    public RoleType getType() {
        return type;
    }

    /**
     * Setzt die Art der Rolle.
     * @param type 
     */
    public void setType(RoleType type) {
        this.type = type;
    }

    /**
     * Gibt den Standardkatalog der Rolle zurueck.
     * @return standardActivities
     */
    public List<StandardActivity> getStandardActivities() {
        return standardActivities;
    }

    /**
     * Setzt den Standardkatalog der Rolle.
     * @param standardActivities 
     */
    public void setStandardActivities(List<StandardActivity> standardActivities) {
        this.standardActivities = standardActivities;
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
        if (!(object instanceof Role)) {
            return false;
        }
        final Role other = (Role) object;
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
        return "Role{" + "id=" + id + ", description=" + description + '}';
    }

    
}
