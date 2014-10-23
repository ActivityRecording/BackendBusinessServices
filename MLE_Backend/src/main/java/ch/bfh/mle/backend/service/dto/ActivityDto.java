package ch.bfh.mle.backend.service.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 *
 * @author Stefan Walle
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class ActivityDto {

    public ActivityDto() {
    }

    public ActivityDto(Long activityId, Integer number, Long employeeId, Long tarmedActivityId, Long treatmentNumber) {
        this.activityId = activityId;
        this.number = number;
        this.employeeId = employeeId;
        this.tarmedActivityId = tarmedActivityId;
        this.treatmentNumber = treatmentNumber;
    }
    
    /**
     * Technische Datenbank-ID.
     */
    private Long activityId;

    /**
     * Anzahl erbrachte Leistungen.
     */
    private Integer number;
    
    /**
     * Erbringer der Leistung.
     */
    private Long employeeId;
    
    /**
     * Referenzierte Leistung aus dem Tarmedkatalog.
     */
    private Long tarmedActivityId;

    /**
     * Behandlungsfall zu dem diese Leistung gehoert.
     */
    private Long treatmentNumber;

    public Long getActivityId() {
        return activityId;
    }

    public Integer getNumber() {
        return number;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public Long getTarmedActivityId() {
        return tarmedActivityId;
    }

    public long getTreatmentNumber() {
        return treatmentNumber;
    }
    
    
}
