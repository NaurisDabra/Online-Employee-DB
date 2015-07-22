package sef.impl.service;

import sef.domain.EmployeeDetail;
import sef.interfaces.repository.EmployeeRepository;
import sef.interfaces.repository.ProjectRepository;
import sef.interfaces.repository.SkillRepository;
import sef.interfaces.service.EmployeeDetailsService;

import org.apache.log4j.Logger;

public class StubEmployeeDetailsServiceImpl implements EmployeeDetailsService{
	
	//Tip: create member variables in this class that will contain the objects 
	//passed by the Spring framework so that other methods can access the objects.

	private static Logger log = Logger.getLogger(StubEmployeeDetailsServiceImpl.class);
	private EmployeeRepository empDAO;
	private SkillRepository skillDAO;
	ProjectRepository projectDAO;
	public StubEmployeeDetailsServiceImpl(EmployeeRepository empDAO, ProjectRepository projectDAO, SkillRepository skillDAO){
	this.empDAO=empDAO;
	this.skillDAO=skillDAO;
	this.projectDAO = projectDAO;
	}

	@Override
	public EmployeeDetail getEmployeeDetails(long employeeID) {
		
		EmployeeDetail detail = new EmployeeDetail();	
		detail.setEmployee(empDAO.findEmployeeByID(employeeID));
		detail.setProjectList(projectDAO.getEmployeeProjectHistory(employeeID));
		detail.setSkillList(skillDAO.findEmployeeSkills(employeeID));
		return detail;
	}
}
