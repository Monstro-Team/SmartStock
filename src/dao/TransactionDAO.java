package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Transaction;

public class TransactionDAO {
	private static final String TABLE_NAME = "Transaction";
	private static final String COLUMN_STOCK = "stock_id";
	private static final String COLUMN_TYPE = "transaction_type";
	private static final String COLUMN_QUANTITY = "quantity_moved";
	private static final String COLUMN_DATE = "transaction_date";
	

	private Connection connection;
	
	public TransactionDAO() throws SQLException {
		this.connection = FactoryConnection.getInstance().getConnection();
	}
	public void includeTransaction(Transaction transaction) throws SQLException {		
		if(transaction != null) {
			String query =  "INSERT INTO "
					+ TABLE_NAME + " ("
					+ COLUMN_STOCK + ", "
					+ COLUMN_TYPE + ", "
					+ COLUMN_QUANTITY + ", " 
					+ COLUMN_DATE +
					") VALUES (" + "\""
					+ transaction.getStockId() + "\", \"" 
					+ transaction.getTransactionType() + "\", \""
					+ transaction.getQuantityMoved() + "\", \""
					+ transaction.getDate()  + "\");";
			
			this.updateQuery(query);
		}		
	}
	
	private void updateQuery( String query ) throws SQLException {
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		
		preparedStatement.executeUpdate();
		
		preparedStatement.close();
		connection.close();
	}
}
