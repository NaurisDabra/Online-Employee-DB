package sef.impl.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import sef.domain.EmployeeSkill;
import sef.interfaces.repository.SkillRepository;

public class StubSkillRepositoryImpl implements SkillRepository{
	
	private DataSource dataSource;
	

	//DataSource class encapsulates the driver, database url, username and 
	//password information.  The dataSource object is automatically created by 
	//the Spring framework and passed to the constructor therefore there's no need 
	//to instantiate the dataSource variable. A connection can be acquired by 
	//accessing the getConnection method of dataSource. 
	//
	//Tip: create member variables in this class that will contain the objects 
	//passed by the Spring framework so that other methods can access the objects.
		
	private static Logger log = Logger.getLogger(StubSkillRepositoryImpl.class);

	public StubSkillRepositoryImpl(DataSource dataSource){
		
		this.dataSource = dataSource;
				
	}
	
	
	

	@Override
	public List<EmployeeSkill> findEmployeeSkills(long employeeID) {

		List<EmployeeSkill> list = new ArrayList<EmployeeSkill>();
		String statement = "Select a.ID, a.name, a.description, a.rating from employeeskill a,employee b where b.ID=? and b.ID=a.employeeDetail_employee_ID; ";
		Connection connection = null;
		
		try {
			
			connection = dataSource.getConnection();
			PreparedStatement ps = connection.prepareStatement(statement);
			ps.setInt(1, (int) employeeID);			
			ResultSet rs = ps.executeQuery();
			
			/**
			 * 
			 * String statement = "Select a.ID, a.firstName, a.lastName, a.middleInitial, a.level, a.workforce,
			 *  a.enterpriseID from employee a, employeeProjectDetail b where a.ID=b.employeeDetail_employee_ID and b.project_ID=?;";
			 * 
			 */
			
			
			while (rs.next()) {
				
				EmployeeSkill skill = new EmployeeSkill();
				skill.setID(rs.getLong("ID"));
				skill.setName(rs.getString("name"));
				skill.setDescription(rs.getString("description"));
				skill.setRating(rs.getInt("rating"));
				
				list.add(skill);
				
				
			}
			
			rs.close();
			ps.close();
			
			
		}catch(SQLException e) {
			
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException ex) {
					
				}
				}
			
		}
		
		
		return list;
	}



	
	
	


	

}
