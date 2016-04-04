package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FactoryConnection {
	
	static String statusConnection = "";
	
	private final String local = "jdbc:mysql://localhost/smart_stock";
	private final String user = "root";
<<<<<<< HEAD
	private final String password = "12345";
=======
	private final String password = "Mudar123";
>>>>>>> d52ac42... Creating validators to username and password
	
	private static FactoryConnection instance;

	private FactoryConnection() {
		
	}
			
	public static FactoryConnection getInstance() {
		if ( instance == null ) {
			instance = new FactoryConnection();
		}else{
			//do nothing
		}
		return instance;
	}

	public Connection getConnection() throws SQLException {
		DriverManager.registerDriver(new com.mysql.jdbc.Driver()); 
		Connection connection = DriverManager.getConnection( local, user, password );
		return connection;
	}

}
