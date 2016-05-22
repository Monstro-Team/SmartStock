package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FactoryConnection {
	
	private static final String DATABASE = "jdbc:mysql://localhost/smart_stock";
	private static final String USER = "root";
	private static final String PASSWORD = "";

	private static FactoryConnection instance;

	private FactoryConnection() {
		
	}
			
	public static FactoryConnection getInstance() {
		if(instance == null) {
			instance = new FactoryConnection();
		}

		return instance;
	}

	public Connection getConnection() throws SQLException {
		DriverManager.registerDriver(new com.mysql.jdbc.Driver()); 
		Connection connection = DriverManager
				.getConnection(DATABASE, USER, PASSWORD);
		
		return connection;
	}
}
