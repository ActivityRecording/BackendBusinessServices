package ch.bfh.mle.backend.service.dto;

import ch.bfh.mle.backend.model.TimePeriodType;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 *
 * @author Stefan
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class TimePeriodDto {

    public TimePeriodDto() {
        //required for JAXB
    }

    public TimePeriodDto(
        Long timePeriodId,
        TimePeriodType type,
        Date startTime,
        Date endTime,
        Long treatmentNumber,
        Long employeeId
    ){
        this.timePeriodId = timePeriodId;
        this.type = type;
        this.startTime = startTime;
        this.endTime = endTime;
        this.treatmentNumber = treatmentNumber;
        this.employeeId = employeeId;
    }
    
    /**
     * Technische Datenbank-ID
     */
    private Long timePeriodId;
    
    /**
     * Art des Zeitraumes. 
     */
    private TimePeriodType type;
    
    /**
     * Startzeitpunkt
     */
    private Date startTime;
    
    /**
     * Endzeitpunkt
     */
    private Date endTime;
    
    /**
     * Behandlungsfallnummer zu dem dieser Zeitraum gehoert.
     */
    private Long treatmentNumber;
    
    /**
     * Nummer des Mitarbeiters, der diesen Zeitraum gemessen hat.
     */
    private Long employeeId;

    public Long getTimePeriodId() {
        return timePeriodId;
    }

    public TimePeriodType getType() {
        return type;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public Long getTreatmentNumber() {
        return treatmentNumber;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    
}
