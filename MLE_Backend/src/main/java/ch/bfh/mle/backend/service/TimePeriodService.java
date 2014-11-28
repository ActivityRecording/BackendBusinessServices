package ch.bfh.mle.backend.service;

import ch.bfh.mle.backend.model.Supplier;
import ch.bfh.mle.backend.model.TimePeriod;
import ch.bfh.mle.backend.model.TreatmentCase;
import ch.bfh.mle.backend.service.dto.TimePeriodDto;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

/**
 * Die Klasse TimePeriodService stellt Applikationsfunktionalitaeten 
 * fuer die Zeitraeme zur Verfuegung,
 * @author Stefan Walle
 */
@Stateless
@Named
public class TimePeriodService extends GenericService<TimePeriod>{

    /**
     * Kontruktor zum Erstellen eines TimePeriodService
     */
    public TimePeriodService(){
        super(TimePeriod.class);
    }

    @Inject
    private SupplierService supplierService;

    @Inject
    private TreatmentCaseService treatmentService;
    
    /**
     * Speichert einen Zeitraum auf der Datenbank.
     * Folgende Voraussetzungen muessen gegeben sein:<br>
     * - Der Leistungserbringer mit der Mitarbeiternummer employeeId muss auf der
     *   Datenbank vorhanden sein. <br>
     * - Der Behandlungsfall mit der Behandlungsfallnummer treatmentNumber muss auf
     *   der Datenbank vorhanden sein.
     *
     * @param dto TimePeriodDto
     */
    public void create(TimePeriodDto dto){
        
        // Ueberpruefen des Inputs
        Long employeeId = dto.getEmployeeId();
        Long treatmentNumber = dto.getTreatmentNumber();
        if (employeeId == null || treatmentNumber == null){
            // Schlüssel für den Leistungserbringer oder Behandlungsfall ist nicht vorhanden
            throw new IllegalArgumentException("EmployeeId and TreatmentNumber must not be null");
        }
        if (dto.getType() == null){
            throw new IllegalArgumentException("Timeperiod type must be set");
        }
        // Finde den Listungserbringer
        Supplier supplier;
        supplier = supplierService.readByEmployeeId(employeeId);
        if (supplier == null){
            throw new IllegalArgumentException("No Supplier found with employeeId " + employeeId);
        }
        
        // Finde den Behandlungsfall 
        TreatmentCase treatment;
        treatment = treatmentService.readByTreatmentNumber(treatmentNumber);
        if (treatment == null){
            throw new IllegalArgumentException("No Treatmentcase found with treatmentNumber " + treatmentNumber);
        }
       
        //Erstelle und speichere den Zeitraum
        TimePeriod timePeriod = new TimePeriod(supplier, treatment);
        timePeriod.setStartTime(dto.getStartTime());
        timePeriod.setEndTime(dto.getEndTime());
        timePeriod.setType(dto.getType());
        this.create(timePeriod);
    }
    
    /**
     * Gibt eine Liste von Zeitraum-DTO zurueck fuer den Behandlungsfall treatmentNumber.
     * @param treatmentNumber - darf nicht null sein.
     * @return List<TimePeriodDto>
     */
    public List<TimePeriodDto> readByTreatmentNumber(@NotNull Long treatmentNumber){
        TypedQuery<TimePeriodDto> query = entityManager.createNamedQuery("TimePeriod.FindByTreatmentNumber", TimePeriodDto.class);
        query.setParameter("treatmentNumber", treatmentNumber);
	return query.getResultList();
    }
    
    /**
     * Findet eine TimePeriod Entity anhand der ID und löscht diese anschliessend
     * @param id
     */
     public void deleteTimePeriodeByID(@NotNull Long id){
         TypedQuery<TimePeriod> query = entityManager.createNamedQuery("TimePeriod.FindTimePeriodById", TimePeriod.class);
         query.setParameter("id", id);
         TimePeriod result;
         result = query.getSingleResult();
         if(result != null){
             entityManager.remove(result);
         }
     }
     
     public Long getCumulatedTime(@NotNull Long treatmentNumber, @NotNull Long employeeId){
         TypedQuery<BigDecimal> query = entityManager.createNamedQuery("TimePeriod.CumulatedTimesByEmployeeAndTreatmentCase", BigDecimal.class);
         query.setParameter(1, treatmentNumber);
         query.setParameter(2, employeeId);
         Long measuredTime;
         BigDecimal result;
         result = query.getSingleResult();
         if (result == null){
             result = BigDecimal.ZERO;
         }
         measuredTime = result.longValue();
         return measuredTime;
     }
}
