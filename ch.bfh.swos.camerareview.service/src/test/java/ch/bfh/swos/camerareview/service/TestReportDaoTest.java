package ch.bfh.swos.camerareview.service;

import static org.junit.Assert.fail;

import java.util.Date;

import javax.inject.Inject;
import javax.print.attribute.standard.DateTimeAtCompleted;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ch.bfh.swos.camerareview.model.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/serviceContext.xml")
public class TestReportDaoTest {

	@Inject
	private ICameraDao cameraDao;
	@Inject
	private IAuthorDao authorDao;
	@Inject
	private ICommentDao commentDao;
	@Inject
	private ITestReportDao testReportDao;
	
	private Camera _camera;
	private Author _author;
	private Comment  _comment;
	
	@Before
	public void setUp() throws Exception {
		_camera = cameraDao.create();
		_camera.setDescription("Beginner model of Nikon.");
		_camera.setModel("Nikon D40");
		_camera.setProducer("Nikon");
		_camera = cameraDao.update(_camera);
		
		_author = authorDao.create();
		_author.setForename("Best");
		_author.setSurname("Seller");
		_author = authorDao.update(_author);
		
		_comment = commentDao.create();
		_comment.setAuthor(_author);
		_comment.setDate(new Date(2012,12,21));
		_comment.setTitle("Review Nikon D40");
		_comment.setText("My Test Review of Nikon D40.");
	}

	@After
	public void tearDown() throws Exception {
		cameraDao.delete(_camera);
		authorDao.delete(_author);
		if(_comment.getId()>0)
		{
			commentDao.delete(_comment);
		}
	}

	@Test
	public void test() {
		TestReport t = testReportDao.create();
		
		t.setAuthor(_author);
		//t.setCamera(_camera); // TODO
		t.setDate(new Date(2012,12,21));
		t.setStatus(1);
		t.setText("My Text is brigther.");
		t.setTitle("Test");
		try{
			
			
			t = testReportDao.update(t);
			
			TestReport t2 = testReportDao.read(t.getId());
			
			Assert.assertEquals(t.getId(), t2.getId());
		}finally{
			testReportDao.delete(t);
		}
	}

}
