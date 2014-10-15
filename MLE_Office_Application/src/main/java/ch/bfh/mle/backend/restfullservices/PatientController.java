/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.mle.backend.restfullservices;

import javax.inject.Inject;

import ch.bfh.mle.backend.modellayer.Patient;
import ch.bfh.mle.backend.servicelayer.IPatientDao;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 * @author Your Name <boris.haueter students.bfh.ch>
 */

@RestController
@RequestMapping("/patients")
public class PatientController extends GenericController<Patient> {

    @Inject
    public PatientController(IPatientDao patientDao) {
        dao = patientDao;
    }
}
