package ch.bfh.mle.backend.service.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * Das ActivityCountDto ist ein temporaeres Objekt, das verwendet wird um die Anzahl
 * erfasster Leistungen pro Tarmed-Leistung eines Behandlungsfalles zu ermitteln.
 * @author Stefan Walle
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class ActivityCountDto {

    /**
     * Konstruktor fuer JAXB
     */
    public ActivityCountDto() {
    }

    public ActivityCountDto(String tarmedId, Long capturedCount) {
        this.tarmedId = tarmedId;
        this.capturedCount = capturedCount;
    }
    
    private String tarmedId;
    
    private Long capturedCount;

    public String getTarmedId() {
        return tarmedId;
    }

    public Long getCapturedCount() {
        return capturedCount;
    }
    
    
}
