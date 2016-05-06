package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Product;

public class ProductDAO {
	
	private static final String TABLE_NAME = "product";
	private static final String COLUMN_ID = "product_id";
	private static final String COLUMN_NAME = "product_name";
	private static final String COLUMN_DESCRIPTION = "product_description";
	private static final String COLUMN_PRICE = "product_price";
	private static final String COLUMN_SUPPLIER = "product_supplier";
	private static final String COLUMN_LOCATION = "product_location";
	private static final String COLUMN_QUANTITY_MIN = "product_quantity_min";
	private static final String COLUMN_QUANTITY = "product_quantity";
	
	private Connection connection;
	
	public ProductDAO() throws SQLException {
		this.connection = FactoryConnection.getInstance().getConnection();
	}
	
	public void updateProduct(Product product)throws SQLException {
		String query =  "UPDATE "
				+ TABLE_NAME
				+ " SET "
				+ COLUMN_NAME + "='" + product.getName() + "', "
				+ COLUMN_DESCRIPTION + "='" + product.getDescription() + "', "
				+ COLUMN_LOCATION + "='" + product.getLocation() + "',"
				+ COLUMN_QUANTITY_MIN + "=" + product.getQuantityMin()
				+ " WHERE "
				+ COLUMN_ID + "=" + product.getId() + ";";
		
		this.updateQuery(query);
	}
	
	public void deleteProduct(int product_id) throws SQLException{
		String query = "DELETE FROM "
				+ TABLE_NAME
				+ " WHERE "
				+ COLUMN_ID + " = " +product_id; 

		this.updateQuery(query);
	}
	
	public void includeProduct(Product product) throws SQLException {
		if(product != null) {
			String query =  "INSERT INTO "
					+ TABLE_NAME + " ("
					+ COLUMN_NAME + ", "
					+ COLUMN_DESCRIPTION + ", " 
					+ COLUMN_LOCATION + ", "
					+ COLUMN_QUANTITY_MIN
					+ ") VALUES (" + "\""
					+ product.getName() + "\", \"" 
					+ product.getDescription() + "\", \""
					+ product.getLocation() + "\", \"" 
					+ product.getQuantityMin() + "\");";

			this.updateQuery(query);
		}
	}
	
	public Product getProduct(int productId) throws SQLException {
		ArrayList<Product> products = getAllProducts();
		
		for(Product product: products){
			if(product.getId() == productId) {
				return product;
			}
		}
		
		return null;
	}

	public ArrayList<Product> getAllProducts() throws SQLException {
		ArrayList<Product> products = new ArrayList<Product>();
		
		String query = "SELECT * FROM " + TABLE_NAME + ";";
		PreparedStatement preparedStatement = this.connection.prepareStatement(query);
		ResultSet result = preparedStatement.executeQuery();
		
		while(result.next()) {
			Product product = new Product();
			product.setId(result.getInt(COLUMN_ID));
			product.setName(result.getString(COLUMN_NAME));
			product.setDescription(result.getString(COLUMN_DESCRIPTION));
			product.setLocation(result.getString(COLUMN_LOCATION));
			product.setQuantityMin(result.getInt(COLUMN_QUANTITY_MIN));
			
			products.add(product);
		}
		
		preparedStatement.close();
		result.close();
		
		return products;
	}

	private void updateQuery(String query) throws SQLException {
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		
		preparedStatement.executeUpdate();
		
		preparedStatement.close();
		connection.close();
	}
}
