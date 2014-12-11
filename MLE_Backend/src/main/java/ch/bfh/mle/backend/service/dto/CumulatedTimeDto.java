package ch.bfh.mle.backend.service.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * Das CumulatedTimeDto dient der Uebergabe von gemessenen und verbuchten
 * Zeiten eines Leistungserbringers ueber einen REST-Service.
 * @author Stefan Walle
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class CumulatedTimeDto {

    public CumulatedTimeDto() {
    }

    public CumulatedTimeDto(Long measuredTime, Long allocatedTime){
        Long rest;
        
        this.measuredHours = measuredTime / 3600;
        rest = measuredTime % 3600;
        this.measuredMinutes = rest / 60;
        this.measuredSeconds = rest % 60;
        
        this.allocatedHours = allocatedTime / 60;
        this.allocatedMinutes = allocatedTime % 60;
        this.allocatedSeconds = 0L;
    }
    
    /**
     * Total gemessene Zeit eines Leistungserbringers fuer einen Behandlungsfall
     */
    private Long measuredHours;
    private Long measuredMinutes;
    private Long measuredSeconds;
    /**
     * Total verbuchte Zeit (Tarmed-Leistungen) eines Leistungserbringers 
     * fuer einen Behandlungsfall
     */
    private Long allocatedHours;
    private Long allocatedMinutes;
    private Long allocatedSeconds;

    public Long getMeasuredHours() {
        return measuredHours;
    }

    public Long getMesuredMinutes() {
        return measuredMinutes;
    }

    public Long getMesuredSeconds() {
        return measuredSeconds;
    }

    public Long getAllocatedHours() {
        return allocatedHours;
    }

    public Long getAllocatedMinutes() {
        return allocatedMinutes;
    }

    public Long getAllocatedSeconds() {
        return allocatedSeconds;
    }
    
    

}
