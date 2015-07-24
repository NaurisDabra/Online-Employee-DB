package sef.test.service;

import java.util.List;

import sef.domain.Employee;
import sef.interfaces.service.SearchService;
import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SearchEmployeeTest extends TestCase{
	
	private SearchService service;
	
	public void setUp(){
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:repository-config.xml");
		service = (SearchService)context.getBean("searchService");
	}
	
	public void testListemployees(){
		List result = service.findEmployeesByName("", "");
		assertNotNull(result);
		assertTrue(result.size() > 0);
	} 
	
	
	// pievienoja Andris
	
	public void testfindEmployeesByProject() {
		
		List result = service.findEmployeesByProject(0);		
		
	}
	
	
	public void testListAllProjects() {
		
		
		
	}
	
	
	
	
	
}
