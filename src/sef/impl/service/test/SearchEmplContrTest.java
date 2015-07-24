package sef.impl.service.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import junit.framework.TestCase;
import sef.controller.SearchEmployeeController;
import sef.domain.Employee;
import sef.domain.EmployeeProjectDetail;
import sef.domain.EmployeeSkill;
import sef.domain.Project;
import sef.impl.service.StubEmployeeDetailsServiceImpl;
import sef.interfaces.repository.EmployeeRepository;
import sef.interfaces.repository.ProjectRepository;
import sef.interfaces.repository.SkillRepository;
import sef.interfaces.service.EmployeeDetailsService;
import sef.interfaces.service.SearchService;

public class SearchEmplContrTest extends TestCase {
	private SearchEmployeeController search;
	private SearchService searchService;
	private EmployeeDetailsService detailsService;
	public SearchEmplContrTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:repository-config.xml");
		searchService = (SearchService) context.getBean("searchService");		
		EmployeeRepository employeeR = (EmployeeRepository)context.getBean("employeeRep");
		SkillRepository skillR = (SkillRepository)context.getBean("skillRep");
		ProjectRepository projectR = (ProjectRepository)context.getBean("projectRep");
		detailsService = new StubEmployeeDetailsServiceImpl(employeeR, projectR, skillR);
		super.setUp();
		search = new SearchEmployeeController(searchService, detailsService);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testSearchEmployeeController() {
		
	}

	public void testOnViewEmployeeDetails() throws Exception {
		ModelAndView test =search.onViewEmployeeDetails(7);
		Employee emp =(Employee) test.getModel().get("employee");
		List<EmployeeProjectDetail> empDet =(List<EmployeeProjectDetail>)  test.getModel().get("projectList");
		List<EmployeeSkill> empSkill = (List<EmployeeSkill>) test.getModel().get("skillList");
		assertTrue(empSkill.isEmpty());
		assertEquals(7, emp.getID());
		assertEquals(test.getViewName(),"find/employeeDetails");
	}

	public void testOnInitialSearchFormState() {
		ModelAndView test =search.onInitialSearchFormState();
		List<Project> projectList = (List<Project>) test.getModel().get("projectList");
		
		assertEquals(test.getViewName(),"find/employeeSearchForm");
	
		
	}

	public void testOnSubmitSearchByName() {
		ModelAndView test =search.onSubmitSearchByName("", "", 1);
		
		List<Employee> employeeList  = (List<Employee>)  test.getModel().get("employeeList");
		List<Project> projectList = (List<Project>)  test.getModel().get("projectList");
		assertEquals(employeeList.size(), 10);
		assertEquals(projectList.size(), 8);
		assertEquals(test.getViewName(),"find/employeeSearchForm");
	
		
	}

	public void testOnSubmitSearchByProject() {
		ModelAndView test =search.onSubmitSearchByProject(2);
		List<Employee> employeeList  = (List<Employee>)  test.getModel().get("employeeList");
		List<Project> projectList = (List<Project>)  test.getModel().get("projectList");
		assertEquals(employeeList.size(), 2);
		assertEquals(projectList.size(), 8);
		assertEquals(test.getViewName(),"find/employeeSearchForm");
	
	}

}
