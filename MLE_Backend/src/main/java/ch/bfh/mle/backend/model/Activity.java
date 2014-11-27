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
 * Die Klasse repraesentiert eine erbrachte Leistung gemaess Tarmed-Katalog
 * @author Stefan Walle
 */
@Entity
@Access(AccessType.FIELD)
@NamedQueries({
    @NamedQuery(
            name="Activity.ActivitiesWithCountForTratmendCaseAndEmployee", 
            query="SELECT NEW ch.bfh.mle.backend.service.dto.ActivityCountDto(m.id, SUM(a.number)) FROM TreatmentCase as t JOIN t.activities AS a JOIN a.tarmedActivity as m JOIN a.supplier as s WHERE t.treatmentNumber = :treatmentNumber AND s.employeeID = :employeeId GROUP BY m.id"),
    @NamedQuery(
            name="Activity.FindAllActivitiesByTreatmentNumber", 
            query="SELECT NEW ch.bfh.mle.backend.service.dto.ActivityDto(a.id, a.number, s.employeeID, a.tarmedActivity.id, t.treatmentNumber, ta.description, ta.duration) FROM TreatmentCase AS t JOIN t.activities AS a JOIN a.tarmedActivity AS ta JOIN a.supplier AS s WHERE t.treatmentNumber = :treatmentNumber" ),
    @NamedQuery(
            name="Activity.FindActivitiyById", 
            query="SELECT a FROM Activity AS a WHERE a.id = :id" ),
    @NamedQuery(
            name="Activity.CumulatedTimesByEmployeeAndTreatmentCase", 
            query="SELECT SUM(m.duration) FROM TreatmentCase as t JOIN t.activities AS a JOIN a.supplier AS s JOIN a.tarmedActivity AS m WHERE t.treatmentNumber = :treatmentNumber AND s.employeeID = :employeeId")
})
@XmlAccessorType(XmlAccessType.FIELD)
public class Activity implements Serializable{
    
    /**
     * Default-Konstruktor fuer JPA
     */
    protected Activity(){
    }
    
    /**
     * Konstruktor zum Erstellen einer neuen Leistung <br />
     * Für die Erstellung wird die Tarmedleistung, der Behandlungsfall und der Leistungserbringer benötigt.
     * @param tarmedActivity
     * @param supplier 
     * @param treatmentCase 
     */
    public Activity(TarmedActivity tarmedActivity, Supplier supplier, TreatmentCase treatmentCase){
        this.tarmedActivity = tarmedActivity;
        this.supplier = supplier;
        this.treatmentCase = treatmentCase;
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
    @XmlTransient
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
