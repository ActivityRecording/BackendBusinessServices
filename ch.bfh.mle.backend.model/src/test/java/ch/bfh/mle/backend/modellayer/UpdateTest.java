package ch.bfh.mle.backend.modellayer;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import junit.framework.Assert;

import org.junit.Test;

public class UpdateTest {

	@Test
	public void test() {
		(new CreateTest()).test();

		EntityManager em = Persistence.createEntityManagerFactory(
				"ch.bfh.mle.backend_Model_PU").createEntityManager();

		Query q = em.createQuery("select a from Patient a");
		@SuppressWarnings("unchecked")
		List<Patient> foundPatients = q.getResultList();
		Patient firstPatient = foundPatients.get(1);

		// Write access needs a transaction
		em.getTransaction().begin();
		firstPatient.setLastName("Der Weisse");
		em.getTransaction().commit();
		// Entity is persisted automatically after commit because it is managed
		// by jpa.
		Assert.assertTrue(firstPatient.getLastName().equals("Der Weisse"));
	}

}
