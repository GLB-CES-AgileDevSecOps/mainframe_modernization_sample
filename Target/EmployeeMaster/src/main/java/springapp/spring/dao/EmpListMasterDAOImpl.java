package springapp.spring.dao;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import springapp.spring.db.DBConnection;


@Repository
public class EmpListMasterDAOImpl implements EmpListMasterDAO{
	//@Autowired
	private JdbcTemplate jdbcTemplate;
	
	

	
	//@Autowired
	public EmpListMasterDAOImpl(DataSource dataSource) {
		DataSource ds = DBConnection.getDataSource();
		jdbcTemplate = new JdbcTemplate(ds);
	}
	
	public EmpListMasterDAOImpl() {
		DataSource ds = DBConnection.getDataSource();
		jdbcTemplate = new JdbcTemplate(ds);
	}


	
	@Override
	public void setDataSource(DataSource dataSource) {
		DataSource ds = DBConnection.getDataSource();
		jdbcTemplate = new JdbcTemplate(ds);
		
	}
	/**
	*SQL Query for para desgcodeRetrieval 
	**/
	@Override
	public List<Map<String, Object>> desgcodeRetrieval(String sqlQuery,List<Object> parameterList) throws Exception  {
		return jdbcTemplate.queryForList(sqlQuery
		,	parameterList.get(0));
	}
	/**
	*SQL Query for para deptcodeRetrieval 
	**/
	@Override
	public List<Map<String, Object>> deptcodeRetrieval(String sqlQuery,List<Object> parameterList) throws Exception  {
		return jdbcTemplate.queryForList(sqlQuery
		,	parameterList.get(0));
	}

	@Override
	public List<Map<String, Object>> empcodeRetrieval(String sqlQuery, List<Object> parameterList) throws Exception {
		return jdbcTemplate.queryForList(sqlQuery
				,	parameterList.get(0));
		}

	@Override
	public void addDesgnDetails(String sqlQuery, List<Object> parameterList)  throws Exception{
		jdbcTemplate.update(sqlQuery
			,parameterList.get(0)
			,parameterList.get(1)
			,parameterList.get(2)
			,parameterList.get(3)
			,parameterList.get(4));
	}

	@Override
	public void updateDesgnDetails(String sqlQuery, List<Object> parameterList)  throws Exception{
		jdbcTemplate.update(sqlQuery
				,parameterList.get(0)
				,parameterList.get(1)
				,parameterList.get(2)
				,parameterList.get(3)
				,parameterList.get(4));
		
	}

	@Override
	public void deleteDesgnDetails(String sqlQuery, List<Object> parameterList) throws Exception {
		jdbcTemplate.update(sqlQuery
				,parameterList.get(0));
		
	}

	@Override
	public void addDeptDetails(String sqlQuery, List<Object> parameterList) throws Exception {
		jdbcTemplate.update(sqlQuery
				,parameterList.get(0)
				,parameterList.get(1));
		
	}

	@Override
	public void updateDeptDetails(String sqlQuery, List<Object> parameterList)  throws Exception{
		jdbcTemplate.update(sqlQuery
				,parameterList.get(0)
				,parameterList.get(1));
	}

	@Override
	public void deleteDeptDetails(String sqlQuery, List<Object> parameterList) throws Exception {
		jdbcTemplate.update(sqlQuery
				,parameterList.get(0));
		
	}

	@Override
	public List<Map<String, Object>> empDetailRetrieval(String sqlQuery, List<Object> parameterList) throws Exception {
		return jdbcTemplate.queryForList(sqlQuery
				,	parameterList.get(0));
	}

	@Override
	public void addEmployeeDetails(String sqlQuery, List<Object> parameterList) throws Exception{
		jdbcTemplate.update(sqlQuery
				,parameterList.get(0)
				,parameterList.get(1)
				,parameterList.get(2)
				,parameterList.get(3)
				,parameterList.get(4));
		
	}

	@Override
	public void updateEmployeeDetails(String sqlQuery, List<Object> parameterList)  throws Exception{
		jdbcTemplate.update(sqlQuery
				,parameterList.get(0)
				,parameterList.get(1)
				,parameterList.get(2)
				,parameterList.get(3)
				,parameterList.get(4));
		
	}

	@Override
	public void deleteEmpDetails(String sqlQuery, List<Object> parameterList) throws Exception {
		jdbcTemplate.update(sqlQuery
				,parameterList.get(0));
		
	}


	
}
