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
 * Repraesentiert eine Regel fur die Aufteilung der Leistungen auf die gemessene Zeit <br>
 * Je Tarmedleistung kann eine Regel zugeordnet werden, die bei der Auswahl und Verrechnung
 * der Leistungen beruecksichtigt werden muss.
 * @author Stefan Walle
 */
@Entity
@Access(AccessType.FIELD)
@XmlAccessorType(XmlAccessType.FIELD)
public class AllocationRule implements Serializable{

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
    private TarmedActivity tarmedActivity;
    
    /**
     * ID mit der Tarmedleistungen gruppiert werden koennen. <br>
     * Aus einer Gruppe kann nur eine Leistung ausgewaehlt werden.
     */
    private Integer groupId;
    
    /**
     * Haeufigkeit, wie oft die Leistung pro Behandlungsfall angegeben werden kann.
     */
    private Integer cardinality;
    
    /**
     * Zeitangabe erforderlich. Wenn ja, muss bei der Erfassung eine Behandlungszeit eingegeben werden.
     */
    private Boolean requiresTime;
    
    /**
     * Waehlbarkeit der Leistung. Wenn ja, kann die Leistung nicht manuell ausgewaehlt werden. <br>
     * Die Leistung wird automatisch hinzugefuegt.
     */
    private Boolean notSelectable;
    
    /**
     * Gibt an, ob die Leistung mit den Zeitperioden verrechnet wird.
     */
    private Boolean noPeriodAllocation;

    /**
     * Gibt die technische Datenbank-ID zurueck.
     * @return id
     */
    public Long getId() {
        return id;
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
     * Gibt die GruppierungsID zurueck.
     * @return groupId
     */
    public Integer getGroupId() {
        return groupId;
    }

    /**
     * Setzt die GruppierungsID.
     * @param groupId 
     */
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    /**
     * Gibt die Kardinalitaet zurueck.
     * @return cardinality
     */
    public Integer getCardinality() {
        return cardinality;
    }

    /**
     * Setzt die Kardinalitaet.
     * @param cardinality 
     */
    public void setCardinality(Integer cardinality) {
        this.cardinality = cardinality;
    }

    /**
     * Gibt eine Boolean zurueck, ob eine Zeitangabe erforderlich ist.
     * @return Boolean
     */
    public Boolean getRequiresTime() {
        return requiresTime;
    }

    /**
     * Setzt die Boolean, ob eine Zeitangabe erforderlich ist. 
     * @param requiresTime 
     */
    public void setRequiresTime(Boolean requiresTime) {
        this.requiresTime = requiresTime;
    }

    /**
     * Gibt eine Boolean zurueck, ob die Leistung manuell auswaehlbar ist.
     * @return Boolean
     */
    public Boolean getNotSelectable() {
        return notSelectable;
    }

    /**
     * Setzt die Boolean, ob die Leistung manuell auswaehlbar ist.
     * @param notSelectable 
     */
    public void setNotSelectable(Boolean notSelectable) {
        this.notSelectable = notSelectable;
    }

    /**
     * Gibt eine Boolean zurueck, die aussagt, ob die Leistung mit der Zeitmessung verrechnet wird
     * @return Boolean
     */
    public Boolean getNoPeriodAllocation() {
        return noPeriodAllocation;
    }

    /**
     * Setzt die Boolean noPeriodAllocation.
     * @param noPeriodAllocation 
     */
    public void setNoPeriodAllocation(Boolean noPeriodAllocation) {
        this.noPeriodAllocation = noPeriodAllocation;
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
     * Vergleicht zwei Zuteilungsregeln, ob sie gleich sind. <br>
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
        if (!(object instanceof AllocationRule)) {
            return false;
        }
        AllocationRule other = (AllocationRule) object;
        if (this.id == null || other.id == null){
            return false;
        }
        return this.id.equals(other.id);
    }
    
    /**
     * Gibt eine String-Repraesentation der Zuteilungsregel zurueck
     * @return String
     */
   @Override
    public String toString() {
        return "AllocationRule{" + "id=" + id + '}';
    }

}
