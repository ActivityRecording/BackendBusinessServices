package ch.bfh.mle.backend.service.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 *
 * @author Stefan
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class SimpleActivityDto {

    public SimpleActivityDto() {
    }

    public SimpleActivityDto(String tarmedActivityId, Integer number) {
        this.tarmedActivityId = tarmedActivityId;
        this.number = number;
    }
    
    /**
     * ID der erbrachten Tarmedleistung
     */
    private String tarmedActivityId;
    
    /**
     * Anzahl erbrachte Leistungen
     */
    private Integer number;

    public String getTarmedActivityId() {
        return tarmedActivityId;
    }

    public Integer getNumber() {
        return number;
    }
    
    
}
