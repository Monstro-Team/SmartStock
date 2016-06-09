package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Product;
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
	public ArrayList<Transaction> getAllTransaction() throws SQLException {
		ArrayList<Transaction> transactions = new ArrayList<Transaction>();
		
		String query = "SELECT * FROM " + TABLE_NAME + ";";
		PreparedStatement preparedStatement = this.connection.prepareStatement(query);
		ResultSet result = preparedStatement.executeQuery();
		
		while(result.next()) {
			Transaction transaction  = new Transaction();
			transaction.setStockId(result.getInt(COLUMN_STOCK));
			transaction.setTransactionType(result.getInt(COLUMN_TYPE));
			transaction.setDate(result.getString(COLUMN_DATE));
			transaction.setQuantityMoved(result.getInt(COLUMN_QUANTITY));
			
			transactions.add(transaction);
		}
		
		preparedStatement.close();
		result.close();
		
		return transactions;
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
