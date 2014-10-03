package ch.bfh.mle.backend.modellayer;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import org.junit.BeforeClass;

import org.junit.Test;

public class CreateTest {

//    @PersistenceContext(unitName = "ch.bfh.mle.backend_Model_PU")
//    EntityManager em;

    
    
//    @BeforeClass
//    public static void setUpClass() throws Exception {
//        
//
////        if (em == null) {
//          EntityManager  em = (EntityManager) Persistence.createEntityManagerFactory("ch.bfh.mle.backend_Model_PU").createEntityManager();
////        }
//    }

    @Test
    public void test() {

        EntityManager  em = (EntityManager) Persistence.createEntityManagerFactory("ch.bfh.mle.backend_Model_PU").createEntityManager();
        
        Patient patient = new Patient();
        patient.setFirstName("Gandalf");
        patient.setLastName("Der Graue");
        ArrayList<Behandlungsfall> behandlungsfaelle = new ArrayList<Behandlungsfall>();
        Behandlungsfall fall1 = new Behandlungsfall(patient);
        fall1.setBeginn(new Date());
        fall1.setEnde(new Date());
        fall1.setFallId(1234L);
        behandlungsfaelle.add(fall1);
        patient.setBehandlungsfaelle(behandlungsfaelle);

//	Author author2 = new Author();
//      author2.setForename("Zweiter");
//      author2.setSurname("Author");
//		
//      Camera camera = new Camera();
//	camera.setDescription("Spiegelreflex Kamera");
//	camera.setModel("EOS 600D");
//	camera.setProducer("Canon");
//	camera.setProductionYear(2012);
//		
//	TestReport testReport = new TestReport();
//	testReport.setAuthor(author);
//	testReport.setCamera(camera); // TODO
//	testReport.setDate(new Date());
//	testReport.setStatus(0);
//	testReport.setText("Die Kamera hat gut abgeschnitten.");
//	testReport.setTitle("Testbericht Canon EOS 600D");
//		
//	ArrayList<TestReport> reports = new ArrayList<TestReport>();
//	reports.add(testReport);
//	author.setTestReports(reports);
//	Comment comment = new Comment();
//      comment.setAuthor(author2);
//      comment.setDate(new Date());
//      comment.setTestReport(testReport);
//      comment.setText("Sehr guter Testbericht");
//      comment.setTitle("Mein Kommentar");

        em.getTransaction().begin();
        em.persist(patient);
        em.getTransaction().commit();
    }

}
