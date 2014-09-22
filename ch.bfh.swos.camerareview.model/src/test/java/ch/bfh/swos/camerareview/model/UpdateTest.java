package ch.bfh.swos.camerareview.model;

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
				"ch.bfh.swos.camerareview.model").createEntityManager();

		Query q = em.createQuery("select a from Author a");
		@SuppressWarnings("unchecked")
		List<Author> foundAuthors = q.getResultList();
		Author firstAuthor = foundAuthors.get(0);

		// Write access needs a transaction
		em.getTransaction().begin();
		firstAuthor.setSurname("OtherName");
		em.getTransaction().commit();
		// Entity is persisted automatically after commit because it is managed
		// by jpa.

		Assert.assertTrue(firstAuthor.getSurname().equals("OtherName"));
	}

}
