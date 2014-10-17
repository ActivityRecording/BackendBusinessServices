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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

/**
 * Repraesentiert einen Erbringer einer Leistung. Dies kann ein Arzt oder eine Plegeperson sein.
 * @author Stefan Walle
 */
@Entity
@Access(AccessType.FIELD)
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"employeeID"})})
public class Supplier implements Serializable{

    /**
     * Default Konstruktor fuer JPA
     */
    public Supplier() {
        this.favorites = new ArrayList<>();
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
     * Mitarbeiter Identifikation
     */
    @NotNull
    @Column(nullable = false)
    private Long employeeID;
    
    /**
     * Nachname des Mitarbeiters
     */
    private String lastname;
    
    /**
     * Vorname des Mitarbeiters
     */
    private String firstname;
    
    /**
     * Rolle des Mitarbeiters.
     */
    @ManyToOne
    private Role role;
    
    /**
     * Liste der persoenlichen Favoriten von Leistungen
     */
    @OneToMany(mappedBy = "supplier", fetch=FetchType.LAZY, cascade = CascadeType.ALL )
    private List<Favorite> favorites;
    
    /**
     * Gibt die technische Datenbank-ID zurueck.
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Gibt die Mitarbeiter-ID des Leistungserbringers zurueck.
     * @return employeeID
     */
    public Long getEmployeeID() {
        return employeeID;
    }

    /**
     * Setzt die Mitarbeiter-ID des Leistungserbringers.
     * @param employeeID 
     */
    public void setEmployeeID(Long employeeID) {
        this.employeeID = employeeID;
    }

    /**
     * Gibt den Namen des Leistungserbringers zurueck.
     * @return lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Setzt den Nachnamen des Mitarbeiters
     * @param lastname 
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * Gibt den Vornamen des Leistungserbringers zurueck.
     * @return firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Setzt den Vornamen des Leistungserbringers
     * @param firstname 
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * Gibt die Rolle des Leistungserbringers zurueck
     * @return role
     */
    public Role getRole() {
        return role;
    }

    /**
     * Setzt die Rolle des Leistungserbringers
     * @param role 
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Gibt die Liste der persoenlichen Favoriten zurueck.
     * @return favorites
     */
    public List<Favorite> getFavorites() {
        return favorites;
    }
    
    /**
     * Setzt die Liste der Favoriten.
     * @param favorites 
     */
    public void setFavorites(List<Favorite> favorites){
        this.favorites = favorites;
    }

    /**
     * Fuegt einen Favoriten zur Liste der Favoriten hinzu. 
     * @param favorit 
     */
    public void addFavorit(Favorite favorit) {
        this.favorites.add(favorit);
    }
    
    /**
     * Ueberschreibt die Standard hashCode Methode.
     * @return hash
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.id);
        return hash;
    }

     /**
     * Vergleicht zwei Leistungserbringer, ob sie gleich sind. <br />
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
        if (!(object instanceof Supplier)) {
            return false;
        }
        final Supplier other = (Supplier) object;
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
        return "Supplier{" + "employeeID=" + employeeID + ", lastname=" + lastname + ", firstname=" + firstname + '}';
    }

    
}
