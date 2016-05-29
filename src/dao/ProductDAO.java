package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Key;
import model.Part;
import model.Product;

public class ProductDAO {
	
	private static final String TABLE_KEY = "Keyy";
	private static final String TABLE_PART = "Part";
	private static final String COLUMN_ID = "product_id";
	private static final String COLUMN_NAME = "product_name";
	private static final String COLUMN_DESCRIPTION = "product_description";
//	private static final String COLUMN_PRICE = "product_price";
//	private static final String COLUMN_SUPPLIER = "product_supplier";
	private static final String COLUMN_LOCATION = "product_location";
	private static final String COLUMN_QUANTITY_MIN = "product_quantity_min";
	private static final String COLUMN_QUANTITY = "product_quantity";
	private static final String COLUMN_BRAND = "product_brand";
	private static final String COLUMN_CAR_MODEL = "product_car_type";
	
	private Connection connection;
	
	public ProductDAO() throws SQLException {
		this.connection = FactoryConnection.getInstance().getConnection();
	}
	
	public void updateProduct(Key product) throws SQLException {
		String query =  "UPDATE "
				+ TABLE_KEY
				+ " SET "
				+ COLUMN_NAME + "='" + product.getName() + "', "
				+ COLUMN_DESCRIPTION + "='" + product.getDescription() + "', "
				+ COLUMN_LOCATION + "='" + product.getLocation() + "',"
				+ COLUMN_QUANTITY_MIN + "=" + product.getQuantityMin()
				+ " WHERE "
				+ COLUMN_ID + "=" + product.getId() + ";";
		
		this.updateQuery(query);
	}
	
	public void updateProduct(Part product) throws SQLException {
		String query =  "UPDATE "
				+ TABLE_PART
				+ " SET "
				+ COLUMN_NAME + "='" + product.getName() + "', "
				+ COLUMN_DESCRIPTION + "='" + product.getDescription() + "', "
				+ COLUMN_LOCATION + "='" + product.getLocation() + "',"
				+ COLUMN_QUANTITY_MIN + "=" + product.getQuantityMin() + ", "
				+ COLUMN_BRAND + "='" + product.getBrand() + "', "
				+ COLUMN_CAR_MODEL + "='" + product.getModelCar() + "' "
				+ " WHERE "
				+ COLUMN_ID + "=" + product.getId() + ";";
		
		this.updateQuery(query);
	}
	
	public void deleteProduct(int product_id) throws SQLException{
		String query = "DELETE FROM "
				+ TABLE_KEY
				+ " WHERE "
				+ COLUMN_ID + " = " + product_id; 

		this.updateQuery(query);
	}
	
	public void includeProduct(Key product) throws SQLException {
		if(product != null) {
			String query =  "INSERT INTO "
					+ TABLE_KEY + " ("
					+ COLUMN_NAME + ", "
					+ COLUMN_DESCRIPTION + ", " 
					+ COLUMN_LOCATION + ", "
					+ COLUMN_QUANTITY_MIN
					+ ") VALUES (" + "\""
					+ product.getName() + "\", \"" 
					+ product.getDescription() + "\", \""
					+ product.getLocation() + "\", " 
					+ product.getQuantityMin() + ");";

			this.updateQuery(query);
		}
	}
	
	public void includeProduct(Part product) throws SQLException {
		if(product != null) {
			String query =  "INSERT INTO "
					+ TABLE_PART + " ("
					+ COLUMN_NAME + ", "
					+ COLUMN_DESCRIPTION + ", " 
					+ COLUMN_LOCATION + ", "
					+ COLUMN_QUANTITY_MIN + ", "
					+ COLUMN_BRAND + ", "
					+ COLUMN_CAR_MODEL
					+ ") VALUES (" + "\""
					+ product.getName() + "\", \"" 
					+ product.getDescription() + "\", \""
					+ product.getLocation() + "\", \"" 
					+ product.getQuantityMin() + "\", \""
					+ product.getBrand() + "\", \""
					+ product.getModelCar() + "\");";

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
		
		String queryKey = "SELECT * FROM " + TABLE_KEY + ";";
		PreparedStatement preparedStatement = this.connection.prepareStatement(queryKey);
		ResultSet result = preparedStatement.executeQuery();
		
		while(result.next()) {
			Product product = new Key();
			product.setId(result.getInt(COLUMN_ID));
			product.setName(result.getString(COLUMN_NAME));
			product.setDescription(result.getString(COLUMN_DESCRIPTION));
			product.setLocation(result.getString(COLUMN_LOCATION));
			product.setQuantityMin(result.getInt(COLUMN_QUANTITY_MIN));
			
			products.add(product);
		}
		

		
		String queryPart = "SELECT * FROM " + TABLE_PART + ";";
		preparedStatement = this.connection.prepareStatement(queryPart);
		result = preparedStatement.executeQuery();
		
		while(result.next()) {
			Part productPart = new Part();
			productPart.setId(result.getInt(COLUMN_ID));
			productPart.setName(result.getString(COLUMN_NAME));
			productPart.setDescription(result.getString(COLUMN_DESCRIPTION));
			productPart.setLocation(result.getString(COLUMN_LOCATION));
			productPart.setQuantityMin(result.getInt(COLUMN_QUANTITY_MIN));
			productPart.setBrand(result.getString(COLUMN_BRAND));
			productPart.setModelCar(COLUMN_CAR_MODEL);
			
			products.add(productPart);
		}
		
		preparedStatement.close();
		result.close();
		
		return products;
	}
	
	public ArrayList<Product> getAllProductsLowInStock() throws SQLException {
		ArrayList<Product> products = new ArrayList<Product>();
		
		String query = "SELECT * FROM "
				+ TABLE_KEY
				+ " WHERE "
				+ COLUMN_QUANTITY + " <= " + COLUMN_QUANTITY_MIN + ";";

		PreparedStatement preparedStatement = this.connection.prepareStatement(query);
		ResultSet result = preparedStatement.executeQuery();
		
		while(result.next()) {
			Product product = new Key();
			product.setId(result.getInt(COLUMN_ID));
			product.setName(result.getString(COLUMN_NAME));
			product.setDescription(result.getString(COLUMN_DESCRIPTION));
			product.setLocation(result.getString(COLUMN_LOCATION));
			product.setQuantity(result.getInt(COLUMN_QUANTITY));
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
