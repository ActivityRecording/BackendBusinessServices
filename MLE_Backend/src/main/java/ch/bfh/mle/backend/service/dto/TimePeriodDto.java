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
public class TimePeriodDto implements Comparable<TimePeriodDto>{

    public TimePeriodDto() {
        //required for JAXB
    }

    public TimePeriodDto(
        Long timePeriodId,
        TimePeriodType type,
        Date startTime,
        Date endTime,
        Long treatmentNumber,
        Long employeeId,
        String supplierFirstname,
        String supplierLastname
    ){
        this.timePeriodId = timePeriodId;
        this.type = type;
        this.startTime = startTime;
        this.endTime = endTime;
        this.treatmentNumber = treatmentNumber;
        this.employeeId = employeeId;
        this.supplierFirstname = supplierFirstname;
        this.supplierLastname = supplierLastname;
        if (endTime != null && startTime != null){
            Long duration;
            duration = (endTime.getTime() - startTime.getTime()) / 1000;
            this.durationHours = duration / 3600;
            Long rest = duration % 3600;
            this.durationMinutes = rest / 60;
            this.durationSeconds = rest % 60;
        } else {
            this.durationHours = 0L;
            this.durationMinutes = 0L;
            this.durationSeconds = 0L;
        }
            
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

    /**
     * Vorname des Leistungserbringers
     */
    private String supplierFirstname;
    
    /**
     * Nachname des Leistungserbringers
     */
    private String supplierLastname;
    
    /**
     * Stunden der Zeitraumdauer
     */
    private Long durationHours;
    
    /**
     * Minuten der Zeitraumdauer
     */
    private Long durationMinutes;
    
    /**
     * Sekunden der Zeitraumdauer
     */
    private Long durationSeconds;
    
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

    public String getSupplierFirstname() {
        return supplierFirstname;
    }

    public String getSupplierLastname() {
        return supplierLastname;
    }

    public Long getDurationHours() {
        return durationHours;
    }

    public Long getDurationMinutes() {
        return durationMinutes;
    }

    public Long getDurationSeconds() {
        return durationSeconds;
    }

    @Override
    public int compareTo(TimePeriodDto t) {
        return startTime.compareTo(t.startTime);
    }
}
