package springapp.spring.dao;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

@Repository
public interface EmpListMasterDAO {
	public void setDataSource(DataSource dataSource);
	/**
	*SQL Query for para deptcodeRetrieval 
	**/
	public List<Map<String, Object>> desgcodeRetrieval(String sqlQuery,List<Object> parameterList) throws Exception ;
	public List<Map<String, Object>> deptcodeRetrieval(String sqlQuery, List<Object> parameterList) throws Exception;
	public List<Map<String, Object>> empcodeRetrieval(String sqlQuery, List<Object> parameterList) throws Exception;
	public void addDesgnDetails(String sqlQuery, List<Object> parameterList) throws Exception;
	public void updateDesgnDetails(String sqlQuery, List<Object> parameterList) throws Exception;
	public void deleteDesgnDetails(String sqlQuery, List<Object> parameterList) throws Exception;
	public void addDeptDetails(String sqlQuery, List<Object> parameterList) throws Exception;
	public void updateDeptDetails(String sqlQuery, List<Object> parameterList) throws Exception;
	public void deleteDeptDetails(String sqlQuery, List<Object> parameterList) throws Exception;
	public List<Map<String, Object>> empDetailRetrieval(String sqlQuery, List<Object> parameterList) throws Exception;
	public void addEmployeeDetails(String sqlQuery, List<Object> parameterList) throws Exception;
	public void updateEmployeeDetails(String sqlQuery, List<Object> parameterList) throws Exception;
	public void deleteEmpDetails(String sqlQuery, List<Object> parameterList) throws Exception;
	
	
	
	

}
