/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.mle.backend.servicelayer;

import javax.inject.Inject;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ch.bfh.mle.backend.modellayer.Patient;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Grandmaster
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/serviceContext.xml")
public class PatDaoTest {
	
	@Inject
	private IPatientDao patientDao;

	@Test
	public void test() {
		Patient patient = patientDao.create();
		patient.setFirstName("Donald");
		patient.setLastName("Duck");
		
		Patient managedPatient = patientDao.update(patient);
		Patient foundPatient = patientDao.read(managedPatient.getId());
		
		Assert.assertTrue(patient.getLastName().equals(foundPatient.getLastName()));
		Assert.assertTrue(patient.getFirstName().equals(foundPatient.getFirstName()));
		
		patientDao.delete(foundPatient);
	}

}

