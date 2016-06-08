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
import dao.ProductDAO;
import model.Product;

public class ProductDAOTest {

	private Connection connection;
	private ProductDAO productDAO;

	@Before
	public void setUp() throws Exception {
		this.connection = FactoryConnection.getInstance().getConnection();
		this.productDAO = new ProductDAO();
	}

	@After
	public void tearDown() throws Exception {
		String query = "DELETE FROM " + ProductDAO.TABLE_NAME + " WHERE " + ProductDAO.COLUMN_DESCRIPTION + " = 'test';";
		
		PreparedStatement preparedStatement = this.connection.prepareStatement(query);
		preparedStatement.execute();		
		preparedStatement.close();
	}

	@Test
	public void testUpdateProduct() throws SQLException {
		Product productToUpdate = new Product("ProdutoTeste", "test", "X1", 10);
		productDAO.includeProduct(productToUpdate);
		
		String query = "SELECT * FROM " + ProductDAO.TABLE_NAME + " WHERE " + ProductDAO.COLUMN_NAME + " = 'ProdutoTeste';";
		
		PreparedStatement preparedStatement = this.connection.prepareStatement(query);
		ResultSet result = preparedStatement.executeQuery();
		
		int id = 0;
		
		Product productResult = null;
		
		if(result.first()) {
			id = result.getInt(ProductDAO.COLUMN_ID);
			
			productResult = new Product();
			productResult.setId(id);
			productResult.setName("ProdutoTesteAtualizado");
			productResult.setDescription(result.getString(ProductDAO.COLUMN_DESCRIPTION));
			productResult.setQuantityMin(result.getInt(ProductDAO.COLUMN_QUANTITY_MIN));
			
			productDAO.updateProduct(productResult);
		}
		
		query = "SELECT * FROM " + ProductDAO.TABLE_NAME + " WHERE " + ProductDAO.COLUMN_ID + " = " + id + ";";
		
		preparedStatement = this.connection.prepareStatement(query);
		result = preparedStatement.executeQuery();
		
		String updatedName = "";
		
		if(result.first()) {
			updatedName = result.getString(ProductDAO.COLUMN_NAME);
		}
		
		preparedStatement.close();
		result.close();
		
		assertEquals("ProdutoTesteAtualizado", updatedName);
	}

	@Test
	public void testDeleteProduct() throws SQLException {
		Product product = new Product("ProdutoDeleteTest", "test", "X2", 10);
		productDAO.includeProduct(product);
		
		ArrayList<Product> products = productDAO.getAllProducts();
		
		int countBefore = products.size();
		
		String query = "SELECT * FROM " + ProductDAO.TABLE_NAME + " WHERE " + ProductDAO.COLUMN_NAME + " = 'ProdutoDeleteTest';";
		
		PreparedStatement preparedStatement = this.connection.prepareStatement(query);
		ResultSet result = preparedStatement.executeQuery();
		
		Product productToDelete = null;
		
		if(result.first()) {
			productToDelete = new Product();
			productToDelete.setId(result.getInt(ProductDAO.COLUMN_ID));
			productToDelete.setName(result.getString(ProductDAO.COLUMN_NAME));
			productToDelete.setDescription(result.getString(ProductDAO.COLUMN_DESCRIPTION));
			productToDelete.setLocation(result.getString(ProductDAO.COLUMN_LOCATION));
			productToDelete.setQuantityMin(result.getInt(ProductDAO.COLUMN_QUANTITY_MIN));
			
			preparedStatement.close();
			result.close();
		}
			
		productDAO.deleteProduct(productToDelete.getId());
		
		products = productDAO.getAllProducts();
		int countAfter = products.size();
		
		assertTrue(countBefore > countAfter);
	}

	@Test
	public void testIncludeProduct() throws SQLException {
		Product product = new Product("ProdutoIncludeTest", "test", "X2", 10);
		productDAO.includeProduct(product);
		
		String query = "SELECT * FROM " + ProductDAO.TABLE_NAME + " WHERE " + ProductDAO.COLUMN_NAME + " = 'ProdutoIncludeTest';";
		
		PreparedStatement preparedStatement = this.connection.prepareStatement(query);
		ResultSet result = preparedStatement.executeQuery();
		
		boolean productRetrieved = result.first();
		
		preparedStatement.close();
		result.close();
		
		assertTrue(productRetrieved);
	}
	
	@Test
	public void testIncludeNullProduct() throws SQLException {
		ArrayList<Product> productsBefore = productDAO.getAllProducts();
		Product product = null;
		productDAO.includeProduct(product);
		ArrayList<Product> productsAfter = productDAO.getAllProducts();
		
		assertEquals(productsBefore.size(), productsAfter.size());
	}

	@Test
	public void testGetProduct() throws SQLException {
		Product product = new Product("TestGetProduct", "test", "X3", 200);
		productDAO.includeProduct(product);
		
		String query = "SELECT * FROM " + ProductDAO.TABLE_NAME + " LIMIT 1;";
		
		PreparedStatement preparedStatement = this.connection.prepareStatement(query);
		ResultSet result = preparedStatement.executeQuery();
		
		int idToRetrieve = -1;
		
		if(result.first()) {
			idToRetrieve = result.getInt(ProductDAO.COLUMN_ID);
		}
		
		preparedStatement.close();
		result.close();
		
		assertNotNull(productDAO.getProduct(idToRetrieve));
	}
	
	@Test
	public void testGetProductNullId() throws SQLException {		
		assertNull(productDAO.getProduct(-1));
	}

	@Test
	public void testGetAllProducts() throws SQLException {
		Product product = new Product("TestGetAllProduct1", "test", "X3", 200);
		productDAO.includeProduct(product);
		Product product2 = new Product("TestGetAllProduct2", "test", "X3", 200);
		productDAO.includeProduct(product2);
		
		ArrayList<Product> products = productDAO.getAllProducts();
		
		assertTrue(products.size() > 0);
	}

	@Test
	public void testGetAllProductsLowInStock() throws SQLException {
		Product product = new Product("TestGetAllLowInStock", "test", "X4", 5, 10);
		String query =  "INSERT INTO "
				+ ProductDAO.TABLE_NAME + " ("
				+ ProductDAO.COLUMN_NAME + ", "
				+ ProductDAO.COLUMN_DESCRIPTION + ", " 
				+ ProductDAO.COLUMN_LOCATION + ", "
				+ ProductDAO.COLUMN_QUANTITY + ", "
				+ ProductDAO.COLUMN_QUANTITY_MIN
				+ ") VALUES (" + "\""
				+ product.getName() + "\", \"" 
				+ product.getDescription() + "\", \""
				+ product.getLocation() + "\", \"" 
				+ product.getQuantity() + "\", \"" 
				+ product.getQuantityMin() + "\");";
		
		PreparedStatement preparedStatement = this.connection.prepareStatement(query);
		preparedStatement.execute();
		preparedStatement.close();
		
		ArrayList<Product> productsLowInStock = productDAO.getAllProductsLowInStock();
		
		assertTrue(productsLowInStock.size() > 0);
	}
}
