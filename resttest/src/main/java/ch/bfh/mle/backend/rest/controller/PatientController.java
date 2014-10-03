/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.mle.backend.rest.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ch.bfh.mle.backend.modellayer.Patient;
import ch.bfh.mle.backend.servicelayer.IPatientDao;

/**
 *
 * @author Your Name <boris.haueter students.bfh.ch>
 */

@Controller
@RequestMapping("/patients")
public class PatientController extends GenericController<Patient> {

    @Inject
    public PatientController(IPatientDao patientDao) {
        dao = patientDao;
    }
}
