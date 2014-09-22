package ch.bfh.swos.camerareview.service;

import java.util.Vector;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ch.bfh.swos.camerareview.model.TestReport;
import ch.bfh.swos.camerareview.service.ITestReportDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/serviceContext.xml")
public class PeristenceServiceTest {
	
	@Inject
	private ITestReportDao testReportDao;
	
	@Test
	public void test() {
		
		TestReport testReport = testReportDao.create();
		testReport.setTitle("Test");
		
		TestReport managedTestReport = testReportDao.update(testReport);
		TestReport foundTestReport = testReportDao.read(managedTestReport.getId());
		
		Vector<TestReport> foundTestReports = (Vector<TestReport>) testReportDao.read();
		
		Assert.assertTrue(testReport.getTitle().equals(foundTestReport.getTitle()));
		Assert.assertTrue(testReport.getTitle().equals(foundTestReports.get(0).getTitle()));
		
		testReportDao.delete(foundTestReport);
	}

}
