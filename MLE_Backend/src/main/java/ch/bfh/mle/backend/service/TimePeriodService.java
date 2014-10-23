/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.mle.backend.service;

import ch.bfh.mle.backend.model.Supplier;
import ch.bfh.mle.backend.model.TimePeriod;
import ch.bfh.mle.backend.model.TreatmentCase;
import ch.bfh.mle.backend.service.dto.TimePeriodDto;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Stefan Walle
 */
@Stateless
@Named
public class TimePeriodService extends GenericService<TimePeriod>{

    @Inject
    private SupplierService supplierService;

    @Inject
    private TreatmentCaseService treatmentService;

    public TimePeriodService(){
        super(TimePeriod.class);
    }
    
    public void create(TimePeriodDto dto){
        
        // Finde den Leistungserbringer und den Behandlungsfall zum übergebenen Zeitraum
        Long employeeId = dto.getEmployeeId();
        Long treatmentNumber = dto.getTreatmentNumber();
        if (employeeId == null || treatmentNumber == null){
            // Schlüssel für den Leistungserbringer oder Behandlungsfall sind nicht vorhanden
            throw new IllegalArgumentException("EmployeeId and TreatmentNumber must not be null");
        }
        // Finde den Listungserbringer
        List<Supplier> suppliers;
        suppliers = supplierService.readByEmployeeId(employeeId);
        if (suppliers.isEmpty()){
            throw new IllegalArgumentException("No supplier found with employeeId " + employeeId);
        }
        if (suppliers.size() > 1){
            throw new IllegalArgumentException("More than one Supplier found for employeeId " + employeeId);
        }
        Supplier supplier = suppliers.get(0);
        
        // Finde den Behandlungsfall 
        List<TreatmentCase> treatments;
        treatments = treatmentService.readByTreatmentNumber(treatmentNumber);
         if (treatments.isEmpty()){
            throw new IllegalArgumentException("No treatmentcase found with treatmentnumber " + treatmentNumber);
        }
        if (treatments.size() > 1){
            throw new IllegalArgumentException("More than one treatmentcase found for treatmentnumber " + treatmentNumber);
        }
        TreatmentCase treatment = treatments.get(0);
       
        //Erstelle und speichere den Zeitraum
        TimePeriod timePeriod = new TimePeriod(supplier, treatment);
        timePeriod.setStartTime(dto.getStartTime());
        timePeriod.setEndTime(dto.getEndTime());
        timePeriod.setType(dto.getType());
        this.create(timePeriod);
    }
           
    
}
