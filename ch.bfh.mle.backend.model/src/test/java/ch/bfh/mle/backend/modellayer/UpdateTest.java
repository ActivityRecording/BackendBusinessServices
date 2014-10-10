package ch.bfh.mle.backend.modellayer;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import junit.framework.Assert;

import org.junit.Test;

public class UpdateTest {

    @Test
    public void test() {

	EntityManager em = Persistence.createEntityManagerFactory(
            "ch.bfh.mle.backend_Model_PU").createEntityManager();

        Patient patient = new Patient();
        patient.setFirstName("Gandalf");
        patient.setLastName("Der Graue");
        Behandlungsfall fall1 = new Behandlungsfall(patient);
        fall1.setBeginn(new Date());
        fall1.setEnde(new Date());
        fall1.setFallId(1003L);
        patient.addBehandlungsfall(fall1);
 
        em.getTransaction().begin();
        em.persist(patient);
        em.getTransaction().commit();
        
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
