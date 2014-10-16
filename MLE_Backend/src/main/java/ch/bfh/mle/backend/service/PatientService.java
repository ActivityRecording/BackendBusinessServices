/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.mle.backend.service;

import ch.bfh.mle.backend.model.Patient;
import javax.ejb.Stateless;
import javax.inject.Named;

/**
 *
 * @author Boris
 */
@Stateless
@Named
public class PatientService extends GenericService<Patient>{
   
     public PatientService() {
        super(Patient.class);
    }
}
