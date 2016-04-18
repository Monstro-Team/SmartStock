package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Stock;

public class StockDAO {
	/*Script for create table:
	 * CREATE TABLE stock(stock_id INTEGER PRIMARY KEY AUTO_INCREMENT, product_id INTEGER NOT NULL, stock_price INTEGER, stock_supplier VARCHAR(50), stock_quantity INTEGER );
	 */
	final String TABLE_NAME = "stock";
	final String COLUMN_PRODUCT_ID = "product_id";
	final String COLUMN_ID = "stock_id";
	final String COLUMN_PRICE = "stock_price";
	final String COLUMN_SUPPLIER = "stock_supplier";
	final String COLUMN_QUANTITY = "stock_quantity";
	
	private Connection connection;
	
	public StockDAO()  throws SQLException {
		this.connection = FactoryConnection.getInstance().getConnection();
	}
	
	public void includeStock(Stock stock) throws SQLException {
		
		String query =  "INSERT INTO "
				+ TABLE_NAME + " (" + COLUMN_PRODUCT_ID+", " + COLUMN_PRICE+", " 
				+ COLUMN_SUPPLIER + ", " + COLUMN_QUANTITY + 
				") VALUES (" + "\"" + stock.getIdProduct() + "\", \"" 
				+ stock.getPrice() + "\", \"" + stock.getSupplier() + "\", \"" 
				+ stock.getQuantity() + "\");";
		
		System.out.println(query);
		
		if ( stock != null ) {

			this.updateQuery(query);
		}
		
	}
	
	public ArrayList<Stock> getAllStockByProductId(int productId) throws SQLException {
		ArrayList<Stock> stocks = new ArrayList<Stock>();
		
		String query = "SELECT * FROM " + TABLE_NAME + " WHERE "+COLUMN_PRODUCT_ID+" = "+productId+";";
		PreparedStatement preparedStatement = this.connection.prepareStatement(query);
		ResultSet result = preparedStatement.executeQuery();
		
		while(result.next()) {
			Stock stock = new Stock();
			stock.setId(result.getInt(COLUMN_ID));
			stock.setIdProduct(result.getInt(COLUMN_PRODUCT_ID));
			stock.setPrice(result.getFloat(COLUMN_PRICE));
			stock.setQuantity(result.getInt(COLUMN_QUANTITY));
			stock.setSupplier(result.getString(COLUMN_SUPPLIER));
			
			stocks.add(stock);
		}
		
		preparedStatement.close();
		result.close();
		
		return stocks;
	}

	
	private void updateQuery( String query ) throws SQLException {
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement( query );
		
		preparedStatement.executeUpdate();
		
		preparedStatement.close();
		connection.close();
	}


}
