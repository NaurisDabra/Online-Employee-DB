package sef.controller;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import sef.domain.Employee;
import sef.domain.EmployeeSkill;
import sef.interfaces.repository.EmployeeRepository;
import sef.interfaces.repository.SkillRepository;

public class TestaKlase {

	public TestaKlase() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:repository-config.xml");
		SkillRepository customerDAO = (SkillRepository) context.getBean("skillRep");

		List<EmployeeSkill> customer1 = customerDAO.findEmployeeSkills(1);
		System.out.println(customer1.toString());
		for (int i = 0; i < customer1.size(); i++)
			System.out.println(customer1.get(i).getName());
		System.out.println("done");
		
	}

}
