package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Product;

public class ProductDAO {
	/*Script for create table:
	 * CREATE TABLE product(product_name VARCHAR(50) NOT NULL, product_description VARCHAR(200), product_price FLOAT(3), product_supplier VARCHAR(50), product_location VARCHAR(50), product_quantity_min INTEGER, product_quantity INTEGER );
	 */
	final String NAME_TABLE = "product";
	final String COLUMN_NAME = "product_name";
	final String COLUMN_DESCRIPTION = "product_description";
	final String COLUMN_PRICE = "product_price";
	final String COLUMN_SUPPLIER = "product_supplier";
	final String COLUMN_LOCATION = "product_location";
	final String COLUMN_QUANTITY_MIN = "product_quantity_min";
	final String COLUMN_QUANTITY = "product_quantity";
	
	public void includeProduct(Product product) throws SQLException{
		
		String query =  "INSERT INTO "
				+ NAME_TABLE + " ( " + COLUMN_NAME+", " + COLUMN_DESCRIPTION+", " + COLUMN_PRICE
				+ ", " + COLUMN_SUPPLIER + ", " + COLUMN_LOCATION + ", " + COLUMN_QUANTITY + ", "
				+ COLUMN_QUANTITY_MIN + ") VALUES ( " + "\"" + product.getName() + "\", \"" 
				+ product.getDescription() + "\", \"" + product.getPrice() + "\", \"" 
				+ product.getSupplier() + "\", \"" + product.getLocation() + "\", \"" 
				+ product.getQuantity() + "\", \"" +product.getQuantityMin() + "\");";
		
		System.out.println(query);
		
		if ( product != null ) {

			this.updateQuery(query);
		}
		
	}
	private void updateQuery( String query ) throws SQLException {
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement( query );
		
		preparedStatement.executeUpdate();
		
		preparedStatement.close();
		connection.close();
	}

}
