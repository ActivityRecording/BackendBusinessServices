package ch.bfh.swos.camerareview.model;

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
				"ch.bfh.swos.camerareview.model").createEntityManager();

		Query q = em.createQuery("select a from Author a");
		@SuppressWarnings("unchecked")
		List<Author> foundAuthors = q.getResultList();
		Author firstAuthor = foundAuthors.get(0);
		Assert.assertTrue(firstAuthor.getSurname().equals("Tolkien"));

	//	List<TestReport> foundTestReports = firstAuthor.getTestReports();
		//TestReport firstTestReport = foundTestReports.get(0);
	//	Assert.assertTrue(firstTestReport.getTitle().startsWith("Testbericht Canon EOS 600D"));
	}

}
