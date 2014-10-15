/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.mle.backup.files;

import ch.bfh.mle.backend.model.Patient;
import javax.inject.Named;
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
