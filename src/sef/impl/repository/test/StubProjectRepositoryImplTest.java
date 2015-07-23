package sef.impl.repository.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import junit.framework.TestCase;
import sef.interfaces.repository.ProjectRepository;
import sef.interfaces.service.SearchService;

public class StubProjectRepositoryImplTest extends TestCase {
	private ProjectRepository projectRep;
	public StubProjectRepositoryImplTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:repository-config.xml");
		projectRep = (ProjectRepository)context.getBean("projectRep");
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testStubProjectRepositoryImpl() {
		assertEquals(1,1);
	}

	public void testListAllProjects() {
		assertEquals(8, projectRep.listAllProjects().size());
	}

	public void testGetEmployeeProjects() {
		assertEquals(0, projectRep.getEmployeeProjects(-1).size());
		assertEquals(1, projectRep.getEmployeeProjects(2).size());
	}

	public void testGetEmployeeProjectRoles() {
		assertEquals(1,1);
	}

	public void testGetEmployeeProjectHistory() {
		assertEquals(0, projectRep.getEmployeeProjectHistory(-1).size());
		assertEquals(2, projectRep.getEmployeeProjectHistory(9).size());
	}

}
