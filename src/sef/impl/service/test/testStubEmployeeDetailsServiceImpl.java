package sef.impl.service.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import junit.framework.TestCase;
import sef.domain.EmployeeDetail;
import sef.interfaces.repository.EmployeeRepository;
import sef.interfaces.repository.ProjectRepository;
import sef.interfaces.repository.SkillRepository;

public class testStubEmployeeDetailsServiceImpl extends TestCase {
	
	private EmployeeDetail service;
	private EmployeeRepository employeeR;
	private SkillRepository skillR;
	private ProjectRepository projectR;
	
	protected void setUp() throws Exception {
		
		super.setUp();
		
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:repository-config.xml");
		
		employeeR = (EmployeeRepository)context.getBean("employeeRep");
		skillR = (SkillRepository)context.getBean("skillRep");
		projectR = (ProjectRepository)context.getBean("projectRep");
		
		
		
	}
	
		

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	

	

	public void testGetEmployeeDetails() {
		
		EmployeeDetail detail = new EmployeeDetail();	
		detail.setEmployee(employeeR.findEmployeeByID(1));
		detail.setProjectList(projectR.getEmployeeProjectHistory(8));
		detail.setSkillList(skillR.findEmployeeSkills(1));
		assertFalse(detail.getEmployee().getFirstName().isEmpty());
		assertFalse(detail.getSkillList().isEmpty());
		assertFalse(detail.getProjectList().isEmpty());
		
	
		
	}

}
