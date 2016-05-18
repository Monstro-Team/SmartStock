package dao;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionDAO {
	
	private Connection connection;
	
	public TransactionDAO () throws SQLException {
		this.connection = FactoryConnection.getInstance().getConnection();
	}

}
