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
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * Repraesentiert eine Liste von Tarmedleistungen, die dem Anwender in der Liste seiner Favoriten angezeigt werden.
 * @author Stefan Walle
 */
@Entity
@Access(AccessType.FIELD)
@XmlAccessorType(XmlAccessType.FIELD)
public class Favorite implements Serializable {

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
     * Leistungserbringer, zu dem der Favorit gehoert.
     */
    @ManyToOne
    private Supplier supplier;
    
    /**
     * Zugehoerige Tarmedleistung.
     */
    @ManyToOne
    private TarmedActivity tarmedActivity;

    /**
     * Gibt die technische Datenbank-ID zurueck.
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Gibt den Leistungserbringer zurueck.
     * @return supplier
     */
    public Supplier getSupplier() {
        return supplier;
    }

    /**
     * Setzt den Leistungserbringer.
     * @param supplier 
     */
    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
    
    /**
     * Gibt die Tarmedleistung zurueck.
     * @return tarmedActivity
     */
    public TarmedActivity getTarmedActivity() {
        return tarmedActivity;
    }
    
    /**
     * Setzt die Tarmedleisung.
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
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        return hash;
    }

    /**
     * Vergleicht zwei Favoriten, ob sie gleich sind. <br>
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
        if (!(object instanceof Favorite)) {
            return false;
        }
        Favorite other = (Favorite) object;
        if (this.id == null || other.id == null){
            return false;
        }
        return this.id.equals(other.id);
    }

    /**
     * Gibt eine String-Raepresentation des Favoriten zurueck.
     * @return string
     */
    @Override
    public String toString() {
        return "Favorite{" + "id=" + id + '}';
    }
}
