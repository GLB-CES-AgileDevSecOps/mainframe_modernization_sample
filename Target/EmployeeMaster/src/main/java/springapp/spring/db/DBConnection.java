package springapp.spring.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;

public class DBConnection {
	private static Connection conn = null;
	public static String DRIVER_CLASS = "org.mariadb.jdbc.Driver";
	//public static String DRIVER_CLASS = "com.mysql.jdbc.Driver";

	private static DBConnection instance = new DBConnection();

	private DBConnection(String DRIVER_CLASS) {
		try {
			Class.forName(DRIVER_CLASS);
		} catch (ClassNotFoundException e) {
			System.out.println("Exception"+ e.getMessage());
		}
	}


	public DBConnection() {

		try {
			Class.forName(DRIVER_CLASS);
		} catch (ClassNotFoundException e) {
			System.out.println("Exception"+ e.getMessage());
		}
	}

	private Connection createConnection() {

		Connection newConnection = null;
		String URL = "";
		String USER = "";
		String PASSWORD = "";

		URL = "jdbc:mysql://localhost:3306/empbatch";
		USER = "root";
		PASSWORD = "Syntel123";
		try {
			newConnection = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			System.out.println("Exception"+ e.getMessage());
		}
		return newConnection;
	}

	public static Connection getConnection() {
		return instance.createConnection();
	}


	public static DriverManagerDataSource getDataSource() {

		String URL = "";
		String USER = "";
		String PASSWORD = "";

		URL = "jdbc:mysql://localhost:3306/empbatch";
		USER = "root";
		PASSWORD = "Syntel123";
		DriverManagerDataSource dataSource = null;

		try {
			dataSource = new DriverManagerDataSource();
			dataSource.setDriverClassName(DRIVER_CLASS);
			dataSource.setUrl(URL);
			dataSource.setUsername(USER);
			dataSource.setPassword(PASSWORD);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataSource;
	}}
