package sef.impl.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import sef.domain.Employee;
import sef.interfaces.repository.EmployeeRepository;

import org.apache.log4j.Logger;

public class StubEmployeeRepositoryImpl implements EmployeeRepository {
	// Also called DAO

	// DataSource class encapsulates the driver, database url, username and
	// password information. The dataSource object is automatically created by
	// the Spring framework and passed to the constructor therefore there's no
	// need
	// to instantiate the dataSource variable. A connection can be acquired by
	// accessing the getConnection method of dataSource.
	//
	// Tip: create member variables in this class that will contain the objects
	// passed by the Spring framework so that other methods can access the
	// objects.
//TODO delete all System.out.println()
	private static Logger log = Logger.getLogger(StubEmployeeRepositoryImpl.class);
	private DataSource dataSource;

	public StubEmployeeRepositoryImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public List<Employee> findEmployeesByName(String firstName, String lastName) {
		String statement = "Select * from employee where firstName like ? and lastName like ?;";
		System.out.println("statement");
		List<Employee> list = new ArrayList<Employee>();
		Connection conn = null;

		if (lastName == null)
			lastName = "";
		try {
			System.out.println("conn start");
			conn = dataSource.getConnection();
			System.out.println("conn got");
			PreparedStatement ps = conn.prepareStatement(statement);
			ps.setString(1, "%" + firstName + "%");
			if (lastName.isEmpty())
				ps.setString(2, "%" + lastName + "%");
			else
				ps.setString(2, lastName);
			
			ResultSet rs = ps.executeQuery();
			System.out.println("query executed");
			while (rs.next()) {
				Employee employee = new Employee();
				System.out.println("result got");
				employee.setID(rs.getInt("ID"));
				employee.setFirstName(rs.getString("firstName"));
				employee.setLastName(rs.getString("lastName"));
				employee.setMiddleInitial(rs.getString("middleInit"));
				employee.setLevel(rs.getString("level"));
				employee.setWorkForce(rs.getString("workforce"));
				employee.setEnterpriseID(rs.getString("enterpriseID"));
				list.add(employee);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}

		return list;
	}

	@Override
	public Employee findEmployeeByID(long employeeID) {
		Employee employee = new Employee();
		String statement = "Select * from employee where ID = ?;";
		System.out.println("statement");
		Connection conn = null;
		try {
			System.out.println("conn start");
			conn = dataSource.getConnection();
			System.out.println("conn got");
			PreparedStatement ps = conn.prepareStatement(statement);
			ps.setLong(1, employeeID);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				employee.setID(rs.getInt("ID"));
				employee.setFirstName(rs.getString("firstName"));
				employee.setLastName(rs.getString("lastName"));
				employee.setMiddleInitial(rs.getString("middleInit"));
				employee.setLevel(rs.getString("level"));
				employee.setWorkForce(rs.getString("workforce"));
				employee.setEnterpriseID(rs.getString("enterpriseID"));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		System.out.println("query executed");
		return employee;
	}

	@Override
	public List<Employee> findEmployeesByProject(long projectID) {

		List<Employee> list = new ArrayList<Employee>();
		String statement = "Select a.ID, a.firstName, a.lastName, a.middleInit, a.level, a.workforce, a.enterpriseID from employee a, employeeProjectDetail b where a.ID=b.employeeDetail_employee_ID and b.project_ID=?;";
		System.out.println("statement");
		Connection conn = null;
		try {
			System.out.println("conn start");
			conn = dataSource.getConnection();
			System.out.println("conn got");
			PreparedStatement ps = conn.prepareStatement(statement);
			
			ps.setLong(1, projectID);
			ResultSet rs = ps.executeQuery();
			System.out.println("query executed");
			while (rs.next()) {
				Employee employee = new Employee();
				System.out.println("result got");
				employee.setID(rs.getInt("ID"));
				employee.setFirstName(rs.getString("firstName"));
				employee.setLastName(rs.getString("lastName"));
				employee.setMiddleInitial(rs.getString("middleInit"));
				employee.setLevel(rs.getString("level"));
				employee.setWorkForce(rs.getString("workforce"));
				employee.setEnterpriseID(rs.getString("enterpriseID"));
				list.add(employee);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		
		return list;
	}

}
