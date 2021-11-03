package com.emplist.batch.db;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class DbUtil {
	private static final Logger logger = LogManager.getLogger(DbUtil.class);
	 public static void close(Connection connection) {
	        if (connection != null) {
	            try {
	                connection.close();
	            } catch (SQLException e) {
	            	logger.error("SQLException",e);
	            	
	            }
	        }
	    }
	 
	    public static void close(Statement statement) {
	        if (statement != null) {
	            try {
	                statement.close();
	            } catch (SQLException e) {
	            	logger.error("SQLException",e);
	            	
	            }
	        }
	    }
	    
	    public static void close(PreparedStatement preparedStatement) {
	        if (preparedStatement != null) {
	            try {
	            	preparedStatement.close();
	            } catch (SQLException e) {
	            	logger.error("SQLException",e);
	            
	            }
	        }
	    }
	    
	    public static void close(CallableStatement callableStatement) {
	        if (callableStatement != null) {
	            try {
	            	callableStatement.close();
	            } catch (SQLException e) {
	            	logger.error("SQLException",e);

	            }
	        }
	    }
	 
	    public static void close(ResultSet resultSet) {
	        if (resultSet != null) {
	            try {
	                resultSet.close();
	            } catch (SQLException e) {
	            	logger.error("SQLException",e);
	           
	            }
	        }
	    }
}
