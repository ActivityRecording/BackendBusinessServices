/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.mle.backend.servicelayer;

import javax.inject.Named;
import ch.bfh.mle.backend.modellayer.Patient;
/**
 *
 * @author Boris Haueter
 * Generic PatienDao implmentation
 */
@Named
public class PatientDao extends GenericJpaDao<Patient> implements IPatientDao {
   
    public PatientDao() {
        super(Patient.class);
    }
    
}
