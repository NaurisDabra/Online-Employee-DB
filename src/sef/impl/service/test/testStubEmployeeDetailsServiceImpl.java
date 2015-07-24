package sef.impl.service.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import junit.framework.TestCase;
import sef.domain.EmployeeDetail;
import sef.impl.service.StubEmployeeDetailsServiceImpl;
import sef.interfaces.repository.EmployeeRepository;
import sef.interfaces.repository.ProjectRepository;
import sef.interfaces.repository.SkillRepository;

public class testStubEmployeeDetailsServiceImpl extends TestCase {
	
	
	private EmployeeRepository employeeR;
	private SkillRepository skillR;
	private ProjectRepository projectR;
	private StubEmployeeDetailsServiceImpl service;
	protected void setUp() throws Exception {
		
		super.setUp();
		
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:repository-config.xml");
		
		employeeR = (EmployeeRepository)context.getBean("employeeRep");
		skillR = (SkillRepository)context.getBean("skillRep");
		projectR = (ProjectRepository)context.getBean("projectRep");
		service = new StubEmployeeDetailsServiceImpl(employeeR, projectR, skillR);
		
		
	}
	
		

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	

	

	public void testGetEmployeeDetails() {
		
		
		EmployeeDetail detail = service.getEmployeeDetails(7);
		assertFalse(detail.getEmployee().getFirstName().isEmpty());
		assertTrue(detail.getSkillList().isEmpty());
		assertEquals(2,detail.getProjectList().size());
		
	
		
	}

}
