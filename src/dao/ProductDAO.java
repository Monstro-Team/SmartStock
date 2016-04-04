package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Product;

public class ProductDAO {
	/*Script for create table:
	 * CREATE TABLE product(product_id INTEGER PRIMARY KEY AUTO_INCREMENT, product_name VARCHAR(50) NOT NULL, product_description VARCHAR(200), product_price FLOAT(3), product_supplier VARCHAR(50), product_location VARCHAR(50), product_quantity_min INTEGER, product_quantity INTEGER );
	 */
	final String TABLE_NAME = "product";
	final String COLUMN_ID = "product_id";
	final String COLUMN_NAME = "product_name";
	final String COLUMN_DESCRIPTION = "product_description";
	final String COLUMN_PRICE = "product_price";
	final String COLUMN_SUPPLIER = "product_supplier";
	final String COLUMN_LOCATION = "product_location";
	final String COLUMN_QUANTITY_MIN = "product_quantity_min";
	final String COLUMN_QUANTITY = "product_quantity";
	
	private Connection connection;
	
	public ProductDAO() throws SQLException {
		this.connection = FactoryConnection.getInstance().getConnection();
	}
	
	public int includeProduct(Product product) throws SQLException {
		
		String query =  "INSERT INTO "
				+ TABLE_NAME + " (" + COLUMN_NAME+", " + COLUMN_DESCRIPTION+", " + COLUMN_PRICE
				+ ", " + COLUMN_SUPPLIER + ", " + COLUMN_LOCATION + ", " + COLUMN_QUANTITY + ", "
				+ COLUMN_QUANTITY_MIN + ") VALUES (" + "\"" + product.getName() + "\", \"" 
				+ product.getDescription() + "\", \"" + product.getPrice() + "\", \"" 
				+ product.getSupplier() + "\", \"" + product.getLocation() + "\", \"" 
				+ product.getQuantity() + "\", \"" +product.getQuantityMin() + "\");";
		
		System.out.println(query);
		
		if ( product != null ) {

			this.updateQuery(query);
		}
		
		return product.getId();
	}
	
	public Product getProduct(int productId) throws SQLException {
		ArrayList<Product> products = getAllProducts();
		
		for(Product product: products){
			if(product.getId() == productId)
				return product;
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
			product.setQuantity(result.getInt(COLUMN_QUANTITY));
			product.setSupplier(result.getString(COLUMN_SUPPLIER));
			product.setPrice(result.getFloat(COLUMN_PRICE));
			
			products.add(product);
		}
		for(int i =0; i< products.size(); i++){
			System.out.println(products.get(i).getName());
			
		}
		
		preparedStatement.close();
		result.close();
		
		return products;
	}

	private void updateQuery( String query ) throws SQLException {
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement( query );
		
		preparedStatement.executeUpdate();
		
		preparedStatement.close();
		connection.close();
	}

}
