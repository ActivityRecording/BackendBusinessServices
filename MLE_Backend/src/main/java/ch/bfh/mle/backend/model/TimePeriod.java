package ch.bfh.mle.backend.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * Die Klasse repraesentiert eine gemessene Zeit in der eine Behandlung erfolgt ist
 * oder in der ein Patient nicht gepflegt wurde. <br>
 Die Art des Zeitraumes wird durch das Merkmal type bestimmt. <br>
 * Das Ende des Zeitraumes kann null sein, solange ein Pflegeunterbruch nicht abgeschlossen ist.
 * @author Stefan Walle
 */
@Entity
@Access(AccessType.FIELD)
@NamedQueries({
    @NamedQuery(
            name="TimePeriod.FindByTreatmentNumber", 
            query="SELECT NEW ch.bfh.mle.backend.service.dto.TimePeriodDto(p.id, p.type, p.startTime, p.endTime, t.treatmentNumber, s.employeeID, s.firstname, s.lastname) FROM TreatmentCase as t JOIN t.timePeriods as p JOIN p.supplier as s WHERE t.treatmentNumber = :treatmentNumber ORDER BY p.startTime"),
    @NamedQuery(
            name="TimePeriod.FindByTreatmentNumberAndEmployee", 
            query="SELECT NEW ch.bfh.mle.backend.service.dto.TimePeriodDto(p.id, p.type, p.startTime, p.endTime, t.treatmentNumber, s.employeeID, s.firstname, s.lastname) FROM TreatmentCase as t JOIN t.timePeriods as p JOIN p.supplier as s WHERE t.treatmentNumber = :treatmentNumber AND s.employeeID = :employeeId ORDER BY p.startTime"),
    @NamedQuery(
            name="TimePeriod.FindTimePeriodById", 
            query="SELECT t FROM TimePeriod as t  WHERE t.id = :id")
})
@NamedNativeQueries({
    @NamedNativeQuery(
            name="TimePeriod.CumulatedTimesByEmployeeAndTreatmentCase", 
            query="SELECT SUM(TIMESTAMPDIFF(SECOND, tp.starttime, tp.endtime)) as time FROM treatmentcase as tc JOIN timeperiod as tp ON tp.TREATMENTCASE_ID = tc.ID JOIN supplier as s ON tp.SUPPLIER_ID = s.ID WHERE tc.treatmentNumber = ? and s.employeeID = ?"),
    @NamedNativeQuery(
            name="TimePeriod.CumulatedTimesByTreatmentCase", 
            query="SELECT SUM(TIMESTAMPDIFF(SECOND, tp.starttime, tp.endtime)) as time FROM treatmentcase as tc JOIN timeperiod as tp ON tp.TREATMENTCASE_ID = tc.ID WHERE tc.treatmentNumber = ?")

})
@XmlAccessorType(XmlAccessType.FIELD)
public class TimePeriod implements Serializable {
    
    /**
     * Default Konstruktor fuer JPA
     */
    protected TimePeriod(){
    }
    
    /**
     * Konstruktor zum Erstellen einer Leistung mit einem Leistungserbringer 
     * und einem Behandlungsfall.
     * @param supplier 
     * @param treatmentCase 
     */
    public TimePeriod(Supplier supplier, TreatmentCase treatmentCase){
        this.supplier = supplier;
        this.treatmentCase = treatmentCase;
    }

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
     * Art des Zeitraumes. 
     */
    @Enumerated(EnumType.STRING)
    private TimePeriodType type;
    
    /**
     * Startzeitpunkt
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;
    
    /**
     * Endzeitpunkt
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;
    
    /**
     * Behandlungsfall zu dem dieser Zeitraum gehoert.
     */
    @ManyToOne
    private TreatmentCase treatmentCase;
    
    /**
     * Leistungserbringer, die diesen Zeitraum gemessen hat.
     */
    @ManyToOne
    private Supplier supplier;

    /**
     * Gibt die technische Datenbank-ID zurueck.
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Gibt die Art des Zeitraumes zurueck.
     * @return type
     */
    public TimePeriodType getType() {
        return type;
    }

    /**
     * Setzt die Zeitraumart.
     * @param type 
     */
    public void setType(TimePeriodType type) {
        this.type = type;
    }

    /**
     * Gibt den Startzeitpunkt des Zeitraumes zurueck.
     * @return start
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * Setzt den Startzeitpunkt des Zeitraumes.
     * @param startTime 
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * Gibt das Ende des Zeitraumes zurueck. <br>
     * Das Ende kann fuer Pflegeunterbrueche null sein, solange der Patient nicht zurueck in der Pflege ist.
     * @return endTime oder null
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * Setzt das Ende des Zeitraumes
     * @param endTime 
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * Gibt den Behandlungsfall zurueck
     * @return treatmentCase
     */
    public TreatmentCase getTreatmentCase() {
        return treatmentCase;
    }

    /**
     * Gibt den Leistungserbringer zurueck
     * @return supplier
     */
    public Supplier getSupplier() {
        return supplier;
    }

    
    /**
     * Ueberschreibt die Standard hashCode Methode.
     * @return hash
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.id);
        return hash;
    }

    /**
     * Vergleicht zwei Zeitraeme, ob sie gleich sind. <br>
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
        if (!(object instanceof TimePeriod)) {
            return false;
        }
        TimePeriod other = (TimePeriod) object;
        if (this.id == null || other.id == null){
            return false;
        }
        return this.id.equals(other.id);
    }
    
    /**
     * Gibt eine String-Repraesentation des Zeitraums zurueck
     * @return 
     */
    @Override
    public String toString() {
        return "TimePeriod{" + "id=" + id + ", type=" + type + '}';
    }
   
}
