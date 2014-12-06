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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Repraesentiert einen Erbringer einer Leistung. Dies kann ein Arzt oder eine Plegeperson sein.
 * @author Stefan Walle
 */
@Entity
@Access(AccessType.FIELD)
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"employeeID"})})
@NamedQueries({
    @NamedQuery(
            name="Supplier.FindByEmployeeId", 
            query="SELECT s FROM Supplier AS s WHERE s.employeeID = :employeeId"),
    @NamedQuery(
            name="Supplier.FindByRoletype", 
            query="SELECT s FROM Supplier AS s WHERE s.role.type = :roleType")
})
@XmlAccessorType(XmlAccessType.FIELD)
public class Supplier implements Serializable, Comparable<Supplier>{

    /**
     * Default Konstruktor fuer JPA
     */
    public Supplier() {
        this.favorites = new ArrayList<>();
        this.approvals = new ArrayList<>();
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
    @XmlTransient
    @OneToMany(mappedBy = "supplier", fetch=FetchType.LAZY, cascade = CascadeType.ALL )
    private List<Favorite> favorites;
    
    /**
     * Liste der Freigaben durch den Leistungserbringer
     */
    @XmlTransient
    @OneToMany(mappedBy = "supplier", fetch=FetchType.LAZY, cascade = CascadeType.ALL )
    private List<Approval> approvals;
    
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
     * Gibt die Freigaben eines Leistungserbringers zurueck
     * @return approvals
     */
    public List<Approval> getApprovals() {
        return approvals;
    }

    /**
     * Setzt die Freigaben eines Leistungserbringers.
     * @param approvals 
     */
    public void setApprovals(List<Approval> approvals) {
        this.approvals = approvals;
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

    @Override
    public int compareTo(Supplier s) {
        int result;
        result = lastname.toUpperCase().compareTo(s.lastname.toUpperCase());
        result = (result != 0 ? result : firstname.toUpperCase().compareTo(s.firstname.toUpperCase()));
        return result;
    }

    
}
