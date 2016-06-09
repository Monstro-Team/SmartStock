package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Stock;

public class StockDAO {

	private static final String TABLE_NAME = "Stock";
	private static final String COLUMN_PRODUCT_ID = "product_id";
	private static final String COLUMN_ID = "stock_id";
	private static final String COLUMN_PRICE = "stock_price";
	private static final String COLUMN_SUPPLIER = "stock_supplier";
	private static final String COLUMN_QUANTITY = "stock_quantity";
	private static final String COLUMN_MODIFIED = "stock_modified";
	
	private Connection connection;
	
	public StockDAO() throws SQLException {
		this.connection = FactoryConnection.getInstance().getConnection();
	}
	
	public void includeStock(Stock stock) throws SQLException {		
		if(stock != null) {
			int modified = 0;
			if(stock.isModified()){
				modified = 1;
			}
			
			String query =  "INSERT INTO "
					+ TABLE_NAME + " ("
					+ COLUMN_PRODUCT_ID + ", "
					+ COLUMN_PRICE + ", " 
					+ COLUMN_SUPPLIER + ", "
					+ COLUMN_MODIFIED + ", "
					+ COLUMN_QUANTITY + 
					") VALUES (" + "\""
					+ stock.getIdProduct() + "\", \"" 
					+ stock.getPrice() + "\", \""
					+ stock.getSupplier() + "\", " 
					+ modified + ", \"" 
					+ stock.getQuantity() + "\");";
			
			this.updateQuery(query);
		}		
	}
	
	public Stock getStock(int stockId) throws SQLException {
		ArrayList<Stock> stocks = getAllStock();
		
		for(Stock stock: stocks) {
			if(stock.getId() == stockId) { 
				return stock;
			}
		}
		
		return null;
	}
	
	public void updateStock(Stock stock)throws SQLException {
		int modified = 0;
		if(stock.isModified()){
			modified = 1;
		}
		String query =  "UPDATE "
				+ TABLE_NAME
				+ " SET "
				+ COLUMN_PRODUCT_ID + "='" + stock.getIdProduct() + "', "
				+ COLUMN_SUPPLIER + "='" + stock.getSupplier() + "', "
				+ COLUMN_PRICE + "='" + stock.getPrice() + "',"
				+ COLUMN_MODIFIED + "=" + modified + ","
				+ COLUMN_QUANTITY + "=" + stock.getQuantity()
				+ " WHERE "
				+ COLUMN_ID + "=" + stock.getId() + ";";
		
		this.updateQuery(query);
	}	
	
	public ArrayList<Stock> getAllStock() throws SQLException {
		ArrayList<Stock> stocks = new ArrayList<Stock>();
		
		String query = "SELECT * FROM " + TABLE_NAME + ";";
		PreparedStatement preparedStatement = this.connection.prepareStatement(query);
		ResultSet result = preparedStatement.executeQuery();
		
		while(result.next()) {
			Stock stock = new Stock();
			stock.setId(result.getInt(COLUMN_ID));
			stock.setIdProduct(result.getInt(COLUMN_PRODUCT_ID));
			stock.setPrice(result.getFloat(COLUMN_PRICE));
			stock.setQuantity(result.getInt(COLUMN_QUANTITY));
			stock.setSupplier(result.getString(COLUMN_SUPPLIER));
			stock.setModified(result.getBoolean(COLUMN_MODIFIED));
			
			stocks.add(stock);
		}
		
		preparedStatement.close();
		result.close();
		
		return stocks;
	}

	
	public ArrayList<Stock> getAllStockByProductId(int productId)
			throws SQLException {
		ArrayList<Stock> stocks = new ArrayList<Stock>();
		
		String query = "SELECT * FROM "
				+ TABLE_NAME
				+ " WHERE "
				+ COLUMN_PRODUCT_ID + " = " + productId + ";";
		
		PreparedStatement preparedStatement = this.connection.prepareStatement(query);
		ResultSet result = preparedStatement.executeQuery();
		
		while(result.next()) {
			Stock stock = new Stock();
			stock.setId(result.getInt(COLUMN_ID));
			stock.setIdProduct(result.getInt(COLUMN_PRODUCT_ID));
			stock.setPrice(result.getFloat(COLUMN_PRICE));
			stock.setQuantity(result.getInt(COLUMN_QUANTITY));
			stock.setSupplier(result.getString(COLUMN_SUPPLIER));
			stock.setModified(result.getBoolean(COLUMN_MODIFIED));
			
			stocks.add(stock);
		}
		
		preparedStatement.close();
		result.close();
		
		return stocks;
	}
	
	public void deleteStock(int stock_id) throws SQLException{
		String query = "DELETE FROM "
				+ TABLE_NAME
				+ " WHERE "
				+ COLUMN_ID + " = " + stock_id; 
		
		this.updateQuery(query);
	}
	
	private void updateQuery( String query ) throws SQLException {
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		
		preparedStatement.executeUpdate();
		
		preparedStatement.close();
		connection.close();
	}
}
