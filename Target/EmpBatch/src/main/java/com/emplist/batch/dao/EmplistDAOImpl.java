package com.emplist.batch.dao;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.emplist.batch.db.DBConnection;

@Repository
public class EmplistDAOImpl implements EmplistDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

//	@Autowired
//	public void setDataSource(DataSource dataSource) {
//        this.jdbcTemplate = new JdbcTemplate(dataSource);
//        System.out.println("4444444444444 ........................................");
//    } 

	@Autowired
	public EmplistDAOImpl(DataSource dataSource) {
		DataSource ds = DBConnection.getDataSource();
		jdbcTemplate = new JdbcTemplate(ds);
	}

	public EmplistDAOImpl() {
		DataSource ds = DBConnection.getDataSource();
		jdbcTemplate = new JdbcTemplate(ds);
	}

	@Override
	@Autowired
	public void setDataSource(@Qualifier("batchDataSource") DataSource batchDataSource) {
		// super.setDataSource(batchDataSource);
		DataSource ds = DBConnection.getDataSource();
		jdbcTemplate = new JdbcTemplate(ds);
	}

	/**
	 * SQL Query for para deptcodeRetrieval
	 **/
	@Override
	public List<Map<String, Object>> deptcodeRetrieval(String sqlQuery, List<Object> parameterList) throws Exception {
		return jdbcTemplate.queryForList(sqlQuery, parameterList.get(0));
	}

	/**
	 * SQL Query for para desgcodeRetrieval
	 **/
	@Override
	public List<Map<String, Object>> desgcodeRetrieval(String sqlQuery, List<Object> parameterList) throws Exception {
		return jdbcTemplate.queryForList(sqlQuery, parameterList.get(0));
	}

	/**
	 * SQL Query for para desgcodeRetrieval
	 **/
	@Override
	public List<Map<String, Object>> prEmployeeCursorDeclare(String sqlQuery, List<Object> parameterList)
			throws Exception {
		return jdbcTemplate.queryForList(sqlQuery);
	}

}