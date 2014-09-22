package ch.bfh.swos.camerareview.model;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.junit.Test;

public class CreateTest {

	@Test
	public void test() {
		
		Author author = new Author();
		author.setForename("J.R.R");
		author.setSurname("Tolkien");

		//Author author2 = new Author();
		//author2.setForename("Zweiter");
		//author2.setSurname("Author");
		
		Camera camera = new Camera();
		camera.setDescription("Spiegelreflex Kamera");
		camera.setModel("EOS 600D");
		camera.setProducer("Canon");
		camera.setProductionYear(2012);
		
		TestReport testReport = new TestReport();
		testReport.setAuthor(author);
		//testReport.setCamera(camera); // TODO
		testReport.setDate(new Date());
		testReport.setStatus(0);
		testReport.setText("Die Kamera hat gut abgeschnitten.");
		testReport.setTitle("Testbericht Canon EOS 600D");
		
		ArrayList<TestReport> reports = new ArrayList<TestReport>();
		reports.add(testReport);
	//	author.setTestReports(reports);
		
		//Comment comment = new Comment();
		//comment.setAuthor(author2);
		//comment.setDate(new Date());
		//comment.setTestReport(testReport);
		//comment.setText("Sehr guter Testbericht");
		//comment.setTitle("Mein Kommentar");
		
		EntityManager em = Persistence.createEntityManagerFactory(
				"ch.bfh.swos.camerareview.model").createEntityManager();

		em.getTransaction().begin();
		em.persist(testReport);
		//em.persist(comment);
		em.getTransaction().commit();
	}

}
