package ch.bfh.mle.backend.service.dto;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * Das ActivityContainerDto dient der Uebergabe von Erbrachten Leistungen
 * fuer die Speicherung auf der Datenbank. 
 * @author Stefan Walle
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class ActivityContainerDto {

    public ActivityContainerDto() {
    }

    public ActivityContainerDto(Long employeeId, Long treatmentNumber, List<SimpleActivityDto> activities) {
        this.employeeId = employeeId;
        this.treatmentNumber = treatmentNumber;
        this.activities = activities;
    }
    
    /**
     * Fachliche Mitarbeiternummer des Leistungserbrigers
     */
    private Long employeeId;
    
    /**
     * Fachliche Behandlungsfallnummer zu dem die Leistung erbracht wurde
     */
    private Long treatmentNumber;

    /**
     * Erbrachte Tarmedleistungen
     */
    private List<SimpleActivityDto> activities;

    public Long getEmployeeId() {
        return employeeId;
    }

    public Long getTreatmentNumber() {
        return treatmentNumber;
    }

    public List<SimpleActivityDto> getActivities() {
        return activities;
    }

}
