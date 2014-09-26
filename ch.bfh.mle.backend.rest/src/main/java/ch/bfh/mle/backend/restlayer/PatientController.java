package ch.bfh.mle.backend.restlayer;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ch.bfh.mle.backend.modellayer.Patient;
import ch.bfh.mle.backend.servicelayer.IPatientDao;

@Controller
@RequestMapping("/patients")
public class PatientController extends GenericController<Patient> {

	@Inject
	public PatientController(IPatientDao patientDao) {
		dao = patientDao;
	}
}
