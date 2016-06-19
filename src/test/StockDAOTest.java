package test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.FactoryConnection;
import dao.StockDAO;
import model.Stock;

public class StockDAOTest {

	private Connection connection;
	private StockDAO stockDAO;

	@Before
	public void setUp() throws Exception {
		this.connection = FactoryConnection.getInstance().getConnection();
		this.stockDAO = new StockDAO();
	}

	@After
	public void tearDown() throws Exception {
//		String query = "DELETE FROM " + StockDAO.TABLE_NAME + " WHERE " + StockDAO.COLUMN_SUPPLIER + " = 'test';";
//		
//		PreparedStatement preparedStatement = this.connection.prepareStatement(query);
//		preparedStatement.execute();		
//		preparedStatement.close();
	}

	@Test
	public void testIncludeStock() throws SQLException {
		Stock stock = new Stock(1, 10, "test", 5, false);
		stockDAO.includeStock(stock);
		
		String query = "SELECT * FROM " + StockDAO.TABLE_NAME + " WHERE " + StockDAO.COLUMN_PRICE + " = 5;";
		
		PreparedStatement preparedStatement = this.connection.prepareStatement(query);
		ResultSet result = preparedStatement.executeQuery();
		
		boolean stockRetrieved = result.first();
		
		preparedStatement.close();
		result.close();
		
		assertTrue(stockRetrieved);
	}

	@Test
	public void testIncludeNullStock() throws SQLException {
		ArrayList<Stock> stocksBefore = stockDAO.getAllStock();
		Stock stock = null;
		stockDAO.includeStock(stock);
		ArrayList<Stock> stocksAfter = stockDAO.getAllStock();
		
		assertEquals(stocksBefore.size(), stocksAfter.size());
	}

	@Test
	public void testGetStock() throws SQLException {
		Stock stock = new Stock(1, 10, "test", 3, false);
		stockDAO.includeStock(stock);
		
		String query = "SELECT * FROM " + StockDAO.TABLE_NAME + " LIMIT 1;";
		
		PreparedStatement preparedStatement = this.connection.prepareStatement(query);
		ResultSet result = preparedStatement.executeQuery();
		
		int idToRetrieve = -1;
		
		if(result.first()) {
			idToRetrieve = result.getInt(StockDAO.COLUMN_ID);
		}
		
		preparedStatement.close();
		result.close();
		
		assertNotNull(stockDAO.getStock(idToRetrieve));
	}

	@Test
	public void testGetStockInvalidId() throws SQLException {
		assertNull(stockDAO.getStock(-1));
	}

	@Test
	public void testUpdateStock() throws SQLException {
		Stock stockToUpdate = new Stock(1, 10, "test", 7, true);
		stockDAO.includeStock(stockToUpdate);
		
		String query = "SELECT * FROM " + StockDAO.TABLE_NAME + " WHERE " + StockDAO.COLUMN_PRICE + " = 7;";
		
		PreparedStatement preparedStatement = this.connection.prepareStatement(query);
		ResultSet result = preparedStatement.executeQuery();
		
		int id = 0;		
		Stock stockResult = null;
		
		if(result.first()) {
			id = result.getInt(StockDAO.COLUMN_ID);
			
			stockResult = new Stock();
			stockResult.setId(id);
			stockResult.setIdProduct(result.getInt(StockDAO.COLUMN_PRODUCT_ID));
			stockResult.setPrice(32);
			stockResult.setQuantity(result.getInt(StockDAO.COLUMN_QUANTITY));
			stockResult.setSupplier(result.getString(StockDAO.COLUMN_SUPPLIER));
			
			stockDAO.updateStock(stockResult);
		}
		
		query = "SELECT * FROM " + StockDAO.TABLE_NAME + " WHERE " + StockDAO.COLUMN_ID + " = " + id + ";";
		
		preparedStatement = this.connection.prepareStatement(query);
		result = preparedStatement.executeQuery();
		
		float updatedPrice = 0;
		
		if(result.first()) {
			updatedPrice = result.getFloat(StockDAO.COLUMN_PRICE);
		}
		
		preparedStatement.close();
		result.close();
		
		assertTrue(updatedPrice != 7);
	}

	@Test
	public void testGetAllStock() throws SQLException {
		Stock stock = new Stock(1, 10, "test", 30, false);
		stockDAO.includeStock(stock);
		Stock stock2 = new Stock(1, 10, "test", 40, false);
		stockDAO.includeStock(stock2);
		
		ArrayList<Stock> stocks = stockDAO.getAllStock();
		
		assertTrue(stocks.size() > 0);
	}

	@Test
	public void testGetAllStockByProductId() throws SQLException {
		Stock stock = new Stock(1, 10, "test", 70, false);
		stockDAO.includeStock(stock);
		
		String query = "SELECT * FROM " + StockDAO.TABLE_NAME + " WHERE " + StockDAO.COLUMN_PRICE + " = 70;";
		
		PreparedStatement preparedStatement = this.connection.prepareStatement(query);
		ResultSet result = preparedStatement.executeQuery();
		
		Stock stockRetrieved = null;
		
		if(result.first()) {
			stockRetrieved = new Stock();
			stockRetrieved.setId(result.getInt(StockDAO.COLUMN_ID));
			stockRetrieved.setIdProduct(result.getInt(StockDAO.COLUMN_PRODUCT_ID));
			stockRetrieved.setPrice(result.getFloat(StockDAO.COLUMN_PRICE));
			stockRetrieved.setQuantity(result.getInt(StockDAO.COLUMN_QUANTITY));
			stockRetrieved.setSupplier(result.getString(StockDAO.COLUMN_SUPPLIER));
			
			preparedStatement.close();
			result.close();
		}
		
		ArrayList<Stock> allStock = stockDAO.getAllStockByProductId(stockRetrieved.getIdProduct());
		
		assertTrue(allStock.size() > 0);
	}

	@Test
	public void testDeleteStock() throws SQLException {
		Stock stock = new Stock(1, 10, "test", 50, false);
		stockDAO.includeStock(stock);
		
		ArrayList<Stock> stocks = stockDAO.getAllStock();
		
		int countBefore = stocks.size();
		
		String query = "SELECT * FROM " + StockDAO.TABLE_NAME + " WHERE " + StockDAO.COLUMN_PRICE + " = 50;";
		
		PreparedStatement preparedStatement = this.connection.prepareStatement(query);
		ResultSet result = preparedStatement.executeQuery();
		
		Stock stockToDelete = null;
		
		if(result.first()) {
			stockToDelete = new Stock();
			stockToDelete.setId(result.getInt(StockDAO.COLUMN_ID));
			stockToDelete.setIdProduct(result.getInt(StockDAO.COLUMN_PRODUCT_ID));
			stockToDelete.setPrice(result.getFloat(StockDAO.COLUMN_PRICE));
			stockToDelete.setQuantity(result.getInt(StockDAO.COLUMN_QUANTITY));
			stockToDelete.setSupplier(result.getString(StockDAO.COLUMN_SUPPLIER));
			
			preparedStatement.close();
			result.close();
		}
			
		stockDAO.deleteStock(stockToDelete.getId());
		
		stocks = stockDAO.getAllStock();
		int countAfter = stocks.size();
		
		assertTrue(countBefore > countAfter);
	}
}
