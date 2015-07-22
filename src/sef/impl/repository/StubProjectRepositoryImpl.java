package sef.impl.repository;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import sef.domain.Project;
import sef.domain.EmployeeProjectDetail;
import sef.domain.ProjectRole;
import sef.interfaces.repository.ProjectRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;


import org.apache.log4j.Logger;

public class StubProjectRepositoryImpl implements ProjectRepository {

	//DataSource class encapsulates the driver, database url, username and 
	//password information.  The dataSource object is automatically created by 
	//the Spring framework and passed to the constructor therefore there's no need 
	//to instantiate the dataSource variable. A connection can be acquired by 
	//accessing the getConnection method of dataSource. 
	//
	//Tip: create member variables in this class that will contain the objects 
	//passed by the Spring framework so that other methods can access the objects.

	private static Logger log = Logger.getLogger(StubProjectRepositoryImpl.class);
	
	private DataSource dataSource;
	 
		
	
	public StubProjectRepositoryImpl(DataSource dataSource) {
		
			this.dataSource = dataSource;
	}

	@Override
	public List<Project> listAllProjects() {
		List<Project> list = new ArrayList<Project>();
		String sql = "SELECT * FROM PROJECT";
		Connection conn = null;
 
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Project project=new Project();
				project.setID(rs.getLong("ID"));
				project.setName(rs.getString("Name"));
				project.setDescription(rs.getString("Description"));
				project.setClient(rs.getString("Client"));
				list.add(project);								
			}
			rs.close();
			ps.close();
 
		} catch (SQLException e) {
			throw new RuntimeException(e);
 
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}

		
		return list;
	}
	
	@Override
	public List<Project> getEmployeeProjects(long employeeID) {
		
		List<Project> list = new ArrayList<Project>();
		
		String sql = "Select a.ID, a.name, a.description, a.client from project a, employeeProjectDetail b where a.ID=b.project_ID and b.employeeDetail_employee_ID=?;";
		Connection conn = null;
 
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setLong(1, employeeID);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Project project=new Project();
				project.setID(rs.getLong("ID"));
				project.setName(rs.getString("name"));
				project.setDescription(rs.getString("description"));
				project.setClient(rs.getString("client"));
				list.add(project);								
			}
			rs.close();
			ps.close();
 
		} catch (SQLException e) {
			throw new RuntimeException(e);
 
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
		
		
		return list;
	}
	
	
	@Override
	public List<ProjectRole> getEmployeeProjectRoles(long employeeID,
			long projectID) {
		
		List<ProjectRole> list = new ArrayList<ProjectRole>();
		
		String sql = "Select a.ID, a.role, a.startDate, a.endDate from projectrole a, employeeProjectDetail b where a.ID=b.employeeDetail_ID and b.employeeDetail_employee_ID=? and b.project_ID=?;";
		Connection conn = null;
 
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setLong(1, employeeID);
			ps.setLong(2, projectID);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				ProjectRole project=new ProjectRole();
				project.setID(rs.getLong("ID"));
				project.setRole(rs.getString("role"));
				project.setStartDate(rs.getDate("startDate"));
				project.setEndDate(rs.getDate("endDate"));
				list.add(project);								
			}
			rs.close();
			ps.close();
 
		} catch (SQLException e) {
			throw new RuntimeException(e);
 
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
		
		
		return list;
	}
		
	
	
	

	@Override
	public List<EmployeeProjectDetail> getEmployeeProjectHistory(long employeeID) {
		List<EmployeeProjectDetail> detailList = new ArrayList<EmployeeProjectDetail>();
		
		String sql = "Select a.ID, a.name, a.description, a.client from project a,employeeprojectdetail b where a.ID=b.project_ID and b.employeeDetail_employee_ID=?;";
		Connection conn = null;
 
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setLong(1, employeeID);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				EmployeeProjectDetail project=new EmployeeProjectDetail();
				List<ProjectRole> rolelist = new ArrayList<ProjectRole>();
				
					Project newproject=new Project();
					newproject.setID(rs.getLong("ID"));
					newproject.setName(rs.getString("name"));
					newproject.setDescription(rs.getString("description"));
					newproject.setClient(rs.getString("client"));			
					
				String statement="Select a.ID,a.role,a.startDate,a.endDate from projectrole a,employeeprojectdetail b where a.employeeprojectdetail_ID=b.ID and b.project_ID=? and b.employeeDetail_employee_ID=?;";
				PreparedStatement ps2 = conn.prepareStatement(statement);
				ps2.setLong(2, employeeID);
				ps2.setLong(1, newproject.getID());
				ResultSet rs2 = ps2.executeQuery();
				System.out.println(3);
				while(rs2.next()){
					ProjectRole newrole=new ProjectRole();
					newrole.setID(rs2.getLong("ID"));
					newrole.setRole(rs2.getString("role"));
					newrole.setStartDate(rs2.getDate("startDate"));
					newrole.setEndDate(rs2.getDate("endDate"));
					rolelist.add(newrole);		
					System.out.println(rs2.getLong("ID"));
				}
				project.setProject(newproject);
				project.setProjectRoles(rolelist);

				detailList.add(project);								
			}
			
			rs.close();
			ps.close();
 
		} catch (SQLException e) {
			throw new RuntimeException(e);
 
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
		
		
		return detailList;
	}



	
}
