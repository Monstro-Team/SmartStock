package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Cabinet;
import model.Product;


public class CabinetDAO {
	/*Script for create table:
	 * CREATE TABLE cabinet(cabinet_id INTEGER PRIMARY KEY AUTO_INCREMENT, cabinet_name VARCHAR(50) NOT NULL, cabinet_drawer INTEGER );
	 */
	
	final String TABLE_NAME = "cabinet";
	final String COLUMN_ID = "cabinet_id";
	final String COLUMN_NAME = "cabinet_name";
	final String COLUMN_DRAWER = "cabinet_drawer";
	
	private Connection connection;
	
	public CabinetDAO() throws SQLException {
		this.connection = FactoryConnection.getInstance().getConnection();
	}
	
	public void includeCabinet(Cabinet cabinet) throws SQLException {
		
		String query =  "INSERT INTO "
				+ TABLE_NAME + " (" + COLUMN_NAME+", " + COLUMN_DRAWER+
				") VALUES (" + "\"" + cabinet.getName() + "\", \"" 
				+ cabinet.getDrawer() +"\");";
		
		System.out.println(query);
		
		if ( cabinet != null ) {

			this.updateQuery(query);
		}
	}
	
	public ArrayList<Cabinet> getAllCabinet() throws SQLException {
		ArrayList<Cabinet> cabinets = new ArrayList<Cabinet>();
		
		String query = "SELECT * FROM " + TABLE_NAME + ";";
		PreparedStatement preparedStatement = this.connection.prepareStatement(query);
		ResultSet result = preparedStatement.executeQuery();
		
		while(result.next()) {
			Cabinet cabinet = new Cabinet();
			cabinet.setId(result.getInt(COLUMN_ID));
			cabinet.setName(result.getString(COLUMN_NAME));
			cabinet.setDrawer(result.getInt(COLUMN_DRAWER));
			cabinets.add(cabinet);
		}
		
		preparedStatement.close();
		result.close();
		
		return cabinets;
	}

	
	private void updateQuery( String query ) throws SQLException {
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement( query );
		
		preparedStatement.executeUpdate();
		
		preparedStatement.close();
		connection.close();
	}
	
}
