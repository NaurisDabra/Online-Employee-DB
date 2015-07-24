package sef.impl.service.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import junit.framework.TestCase;
import sef.domain.Employee;
import sef.domain.Project;
import sef.interfaces.service.SearchService;

public class testSearchService extends TestCase {

	private SearchService service;

	public void setUp() {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:repository-config.xml");
		service = (SearchService) context.getBean("searchService");
	}
	
	public void testListAllProjects() {
		List<Project> result = service.listAllProjects();
		assertNotNull(result);
		assertEquals(result.size(), 8);
	}
	
	public void testFindEmployeesByProject() {
		List<Employee> result;
		
		result = service.findEmployeesByProject(100);
		assertNotNull(result);
		assertEquals(result.size(), 0);
		
		result = service.findEmployeesByProject(1);
		assertNotNull(result);
		assertEquals(result.size(), 0);
		
		result = service.findEmployeesByProject(4);
		assertNotNull(result);
		assertEquals(result.size(), 1);

		result = service.findEmployeesByProject(2);
		assertNotNull(result);
		assertEquals(result.size(), 2);
		
	}

	public void testFindEmployeesByName() {
        List<Employee> result;

        result = service.findEmployeesByName("asdasd", "iririr");
        assertNotNull(result);
        assertEquals(result.size(), 0);

        result = service.findEmployeesByName("Ruzwana", "iririr");
        assertNotNull(result);
        assertEquals(result.size(), 0);

        result = service.findEmployeesByName("asdasd", "Bashir");
        assertNotNull(result);
        assertEquals(result.size(), 0);
        
        result = service.findEmployeesByName("Ruzwana", "Bashir");
        assertNotNull(result);
        assertEquals(result.size(), 1);
        assertTrue(result.get(0).getFirstName().equals("Ruzwana"));
        assertTrue(result.get(0).getFirstName().equals("Bashir"));

	}
	
}
