package ch.bfh.mle.backend.service.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * Das StandardActivitiyListItemDto dient fuer die Uebergabe des Standardleistungskatalogs
 * ueber eine REST-Schnittstelle.
 * @author Stefan Walle
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class StandardActivitiyListItemDto {
    
    /**
     * Konstruktor fuer JAXB
     */
    public StandardActivitiyListItemDto(){
    }

    /**
     * Konstruktor zum Erzeugen einer StandardActivitiyListItemDto
     * @param standardActivityId
     * @param description
     * @param category
     * @param sortKey
     * @param tarmedId
     * @param duration
     * @param groupId
     * @param cardinality
     * @param requiresTime
     * @param notSelectable
     * @param noPeriodAllocation 
     */
    public StandardActivitiyListItemDto(Long standardActivityId, String description, Integer category, Integer sortKey, String tarmedId, Integer duration, Integer groupId, Integer cardinality, Boolean requiresTime, Boolean notSelectable, Boolean noPeriodAllocation) {
        this.standardActivityId = standardActivityId;
        this.description = description;
        this.category = category;
        this.sortKey = sortKey;
        this.tarmedId = tarmedId;
        this.duration = duration;
        this.groupId = groupId;
        this.cardinality = cardinality;
        this.requiresTime = requiresTime;
        this.notSelectable = notSelectable;
        this.noPeriodAllocation = noPeriodAllocation;
    }

    /**
     * Technische Datenbank-ID der StandardActivitiy
     */
    private Long standardActivityId;
    
    /**
     * Bezeichnung
     */
    private String description;
    
    /**
     * Leistungskategorie
     */
    private Integer category;
    
    /**
     * Sortierungsschluessel der Leistungen innerhaln einer Rolle
     */
    private Integer sortKey;
    
    /**
     * Fachliche Tarmed-ID.
     */
    private String tarmedId;
    
    /**
     * Vorgegebene Dauer der Leistung in Minuten.
     */
    private Integer duration;

    /**
     * ID mit der Tarmedleistungen gruppiert werden koennen. <br />
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
     * Waehlbarkeit der Leistung. Wenn ja, kann die Leistung nicht manuell ausgewaehlt werden. <br />
     * Die Leistung wird automatisch hinzugefuegt.
     */
    private Boolean notSelectable;
    
    /**
     * Gibt an, ob die Leistung mit den Zeitperioden verrechnet wird.
     */
    private Boolean noPeriodAllocation;

    public Long getStandardActivityId() {
        return standardActivityId;
    }

    public String getDescription() {
        return description;
    }

    public Integer getCategory() {
        return category;
    }

    public Integer getSortKey() {
        return sortKey;
    }

    public String getTarmedId() {
        return tarmedId;
    }

    public Integer getDuration() {
        return duration;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public Integer getCardinality() {
        return cardinality;
    }

    public Boolean getRequiresTime() {
        return requiresTime;
    }

    public Boolean getNotSelectable() {
        return notSelectable;
    }

    public Boolean getNoPeriodAllocation() {
        return noPeriodAllocation;
    }
    
    
}
