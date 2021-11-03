package com.emplist.batch.dao;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

@Repository
public interface EmplistDAO {

public void setDataSource(DataSource dataSource);
	
	/**
	*SQL Query for para deptcodeRetrieval 
	**/
	public List<Map<String, Object>> deptcodeRetrieval(String sqlQuery,List<Object> parameterList) throws Exception ;
	
	/**
	*SQL Query for para desgcodeRetrieval 
	**/
	public List<Map<String, Object>> desgcodeRetrieval(String sqlQuery,List<Object> parameterList) throws Exception ;

	public List<Map<String, Object>> prEmployeeCursorDeclare(String sqlQuery, List<Object> parameterList)throws Exception ;
	
}