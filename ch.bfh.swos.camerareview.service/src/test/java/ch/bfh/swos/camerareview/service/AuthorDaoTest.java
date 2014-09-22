package ch.bfh.swos.camerareview.service;

import javax.inject.Inject;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ch.bfh.swos.camerareview.model.Author;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/serviceContext.xml")
public class AuthorDaoTest {
	
	@Inject
	private IAuthorDao authorDao;

	@Test
	public void test() {
		Author author = authorDao.create();
		author.setSurname("Donald");
		author.setForename("Duck");
		
		Author managedAuthor = authorDao.update(author);
		Author foundAuthor = authorDao.read(managedAuthor.getId());
		
		Assert.assertTrue(author.getSurname().equals(foundAuthor.getSurname()));
		Assert.assertTrue(author.getForename().equals(foundAuthor.getForename()));
		
		authorDao.delete(foundAuthor);
	}

}
