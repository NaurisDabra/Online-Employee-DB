package sef.impl.repository.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import junit.framework.TestCase;
import sef.domain.EmployeeSkill;
import sef.interfaces.repository.SkillRepository;
import sef.interfaces.service.SearchService;

public class testStubSkillRepositoryImpl extends TestCase {
	
	private SkillRepository service;
	
	
	protected void setUp() throws Exception {
				
		super.setUp();
		
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:repository-config.xml");
		service = (SkillRepository)context.getBean("skillRep");
		
		
	}
		

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
		
	

	public void testFindEmployeeSkills() {
		
		List<EmployeeSkill> list = service.findEmployeeSkills(9);
		
		assertEquals(list.get(0).getName(), "Java development");
		assertEquals(list.get(0).getDescription(), "Can be included in any Java project");
		
		list = service.findEmployeeSkills(1);
		
		assertEquals(list.get(0).getName(), "Social media management");
		
		
		
	}

}
