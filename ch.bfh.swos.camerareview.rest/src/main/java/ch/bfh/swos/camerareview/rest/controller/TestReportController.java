package ch.bfh.swos.camerareview.rest.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ch.bfh.swos.camerareview.model.TestReport;
import ch.bfh.swos.camerareview.service.ITestReportDao;

@Controller
@RequestMapping("/testreports")
public class TestReportController extends GenericController<TestReport> {

	@Inject
	public TestReportController(ITestReportDao testReportDao) {
		dao = testReportDao;
	}
	
}
