/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import ch.bfh.mle.backend.model.TreatmentCase;
import ch.bfh.mle.backend.model.Patient;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Stefan
 */
public class PatientTest {
    
    public PatientTest() {
    }

    protected static EntityManagerFactory emf;

    protected EntityManager em;

    @BeforeClass
    public static void createEntityManagerFactory() {
        emf = Persistence.createEntityManagerFactory("mle_backend_PU");
    }

    @AfterClass
    public static void closeEntityManagerFactory() {
        emf.close();
    }

    @Before
    public void beginTransaction() {
        em = emf.createEntityManager();
        em.getTransaction().begin();
    }

    @After
    public void rollbackTransaction() {   
//        if (em.getTransaction().isActive()) {
//            em.getTransaction().rollback();
//        }
//
//        if (em.isOpen()) {
//            em.close();
//        }
    }
    //@Test
    public void test() {

        Patient patient = new Patient();
        patient.setFirstName("Gandalf");
        patient.setLastName("Der Graue");
        TreatmentCase fall1 = new TreatmentCase(patient);
        fall1.setStartTime(new Date());
        fall1.setEndTime(new Date());
        fall1.setTreatmentId(1234L);
        patient.addTreatmentCase(fall1);
        em.persist(patient);
        em.clear();

        Query q = em.createQuery("select a from Patient a");
        @SuppressWarnings("unchecked")
        List<Patient> foundPatients = q.getResultList();
        Patient firstAuthor = foundPatients.get(0);
        Assert.assertTrue(firstAuthor.getFirstName().equals("Gandalf"));
        Assert.assertTrue(firstAuthor.getLastName().equals("Der Graue"));
}

}
