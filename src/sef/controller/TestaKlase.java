package sef.controller;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import sef.domain.Employee;
import sef.interfaces.repository.EmployeeRepository;

public class TestaKlase {

	public TestaKlase() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:repository-config.xml");
		EmployeeRepository customerDAO = (EmployeeRepository) context.getBean("employeeRep");

		List<Employee> customer1 = customerDAO.findEmployeesByName("N", "");
		System.out.println(customer1.toString());
		for (int i = 0; i < customer1.size(); i++)
			System.out.println(customer1.get(i).getFirstName());
		System.out.println("done");
		Employee employee = customerDAO.findEmployeeByID(1);
		System.out.println(employee.getLastName());
		customer1 = customerDAO.findEmployeesByProject(2);
		for (int i = 0; i < customer1.size(); i++)
			System.out.println(customer1.get(i).getFirstName());
	}

}
