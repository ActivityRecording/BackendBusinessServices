package ch.bfh.mle.backend.service.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


/**
 * Das ActivityDTO dient fuer die Uebergabe einer Leistung ueber eine REST-Schnittstelle.
 * Sie enthaelt im Unterschied zur Entity-Klasse Activity die fachlichen Fremdschluessel des
 * Leistungserbringers (employeeId) und des Behandlungsfalls (treatmentNumber).
 * Die erbrachte Tarmed-Leistung wird ueber die Datenbank-ID referenziert.
 * @author Stefan Walle
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class ActivityDto implements Comparable<ActivityDto>{

    public ActivityDto() {
    }

    public ActivityDto(Long activityId, Integer number, Long employeeId, String supplierFirstname, String supplierLastname, String tarmedActivityId,
                        Long treatmentNumber, String description, Integer duration) {
        this.activityId = activityId;
        this.number = number;
        this.employeeId = employeeId;
        this.supplierFirstname = supplierFirstname;
        this.supplierLastname = supplierLastname;
        this.tarmedActivityId = tarmedActivityId;
        this.treatmentNumber = treatmentNumber;
        this.description = description;
        this.duration = duration;
    }

    public ActivityDto(Long number, Long employeeId, String supplierFirstname, String supplierLastname, String tarmedActivityId,
                        Long treatmentNumber, String description, Integer duration) {
        this(null, number.intValue(), employeeId, supplierFirstname, supplierLastname, tarmedActivityId, treatmentNumber, description, duration);    
    }

    /**
     * Technische Datenbank-ID der Leistung.
     * Diese muss fuer einen POST-Request (neue Leistung) null sein.
     */
    private Long activityId;

    /**
     * Anzahl erbrachte Leistungen
     */
    private Integer number;
    
    /**
     * Fachliche Mitarbeiternummer des Leistungserbrigers
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
     * ID der erbrachten Tarmedleistung
     */
    private String tarmedActivityId;

    /**
     * Beschreibung der Tarmedleistung
     */
    private String description;
    
    /**
     * Dauer der Tarmedleistungseinheit
     */
    private Integer duration;
    
    /**
     * Fachliche Behandlungsfallnummer zu dem die Leistung erbracht wurde
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

    public String getTarmedActivityId() {
        return tarmedActivityId;
    }

    public Long getTreatmentNumber() {
        return treatmentNumber;
    }
    
    public String getDescription(){
        return description;
    }
    
    public Integer getDuration(){
        return duration;
    }

    public String getSupplierFirstname() {
        return supplierFirstname;
    }

    public String getSupplierLastname() {
        return supplierLastname;
    }

    @Override
    public int compareTo(ActivityDto a) {
        
        return tarmedActivityId.compareTo(a.tarmedActivityId);
    }
}
