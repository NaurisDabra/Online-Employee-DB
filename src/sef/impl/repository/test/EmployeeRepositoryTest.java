package sef.impl.repository.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import junit.framework.TestCase;
import sef.domain.Employee;
import sef.interfaces.repository.EmployeeRepository;

public class EmployeeRepositoryTest extends TestCase {

	private EmployeeRepository employee;
	
	protected void setUp() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:repository-config.xml");
		employee = (EmployeeRepository)context.getBean("employeeRep");
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testFindEmployeesByName() {
		List<Employee> result=employee.findEmployeesByName("",""); 
		assertNotNull(result);
		assertTrue(result.size() == 10);
		
		result=employee.findEmployeesByName("","-1");
		assertTrue(result.isEmpty());
		
		result=employee.findEmployeesByName("an","");
		assertNotNull(result);
		assertTrue(result.size() == 3);
		
		result=employee.findEmployeesByName("","Darabi");
		assertNotNull(result);
		assertTrue(result.size() == 1);
	
	}

	public void testFindEmployeeByID() {
		Employee result=employee.findEmployeeByID(-1); 
		assertTrue(result.getEnterpriseID()==null&&result.getFirstName()==null&&
				result.getLastName()==null&&result.getMiddleInitial()==null&&
				result.getWorkForce()==null&&result.getLevel()==null);
		
		result=employee.findEmployeeByID(1); 
		System.out.println(result.getFirstName()+result.getMiddleInitial()+result.getLastName());
		assertEquals("Ruzwana",result.getFirstName());
		assertEquals("Bashir",result.getLastName());
		assertEquals("A",result.getMiddleInitial());
	
		

	}


	public void testFindEmployeesByProject(){
		List<Employee> result=employee.findEmployeesByProject(-1); 
		assertTrue(result.isEmpty());
		result=employee.findEmployeesByProject(2); 
		assertNotNull(result);
		assertTrue(result.size()==2);
	
	}

}
