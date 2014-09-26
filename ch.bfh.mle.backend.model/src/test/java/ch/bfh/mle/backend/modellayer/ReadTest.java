package ch.bfh.mle.backend.modellayer;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import junit.framework.Assert;

import org.junit.Test;

public class ReadTest {

	@Test
	public void test() {
		(new CreateTest()).test();
		
		EntityManager em = Persistence.createEntityManagerFactory(
				"ch.bfh.mle.backend_Model_PU").createEntityManager();

		Query q = em.createQuery("select a from Patient a");
		@SuppressWarnings("unchecked")
		List<Patient> foundPatients = q.getResultList();
		Patient firstAuthor = foundPatients.get(0);
		Assert.assertTrue(firstAuthor.getFirstName().equals("Gandalf"));
                Assert.assertTrue(firstAuthor.getLastName().equals("Der Graue"));
	}

}
