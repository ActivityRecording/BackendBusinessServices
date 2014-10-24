package ch.bfh.mle.backend.service.dto;

import ch.bfh.mle.backend.model.TimePeriodType;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * Das TimePeriodDto dient der Uebergabe von Zeitraeumen ueber eine REST-Schnittstelle.
 * Als Fremdschluessel auf den Behandlungsfall und den Leistungserbringer werden die fachliche
 * Behandlungsfallnummer (treatmentNumber) und die Mitarbeiternummer (employeeId) verwendet.
 * Beim POST-Request muss die technische ID (timePeriodId) null sein.
 * @author Stefan Walle
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
     * Muss im Fall eines POST-Requests null sein.
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
