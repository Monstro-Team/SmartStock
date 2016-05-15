package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Provider;

public class ProviderDAO {

	private static final String TABLE_NAME = "Provider";
	private static final String COLUMN_ID = "provider_id";
	private static final String COLUMN_CONPANY = "provider_conpany";
	private static final String COLUMN_SALESMAN = "provider_salesman";
	private static final String COLUMN_SALESMANPHONE = "provider_salesmanPhone";
	
	private Connection connection;
	
	public ProviderDAO() throws SQLException {
		this.connection = FactoryConnection.getInstance().getConnection();
	}
	
	public void includeCabinet(Provider provider) throws SQLException {
		if(provider != null) {
			String query =  "INSERT INTO "
					+ TABLE_NAME + " ("
					+ COLUMN_CONPANY + ", "
					+ COLUMN_SALESMAN + ", "
					+ COLUMN_SALESMANPHONE 
					+ ") VALUES (" + "\""
					+ provider.getConpany() + "\", \"" 
					+ provider.getSalesman() + "\", \"" 
					+ provider.getSalesmanPhone() + "\");";
			
			this.updateQuery(query);
		}
	}
	
	public ArrayList<Provider> getAllCabinet() throws SQLException {
		ArrayList<Provider> providers = new ArrayList<Provider>();
		
		String query = "SELECT * FROM " + TABLE_NAME + ";";
		PreparedStatement preparedStatement = this.connection.prepareStatement(query);
		ResultSet result = preparedStatement.executeQuery();
		
		while(result.next()) {
			Provider provider = new Provider();
			provider.setId(result.getInt(COLUMN_ID));
			provider.setConpany(result.getString(COLUMN_CONPANY));
			provider.setSalesman(result.getString(COLUMN_SALESMAN));
			provider.setSalesmanPhone(result.getString(COLUMN_SALESMANPHONE));
			providers.add(provider);
		}
		
		preparedStatement.close();
		result.close();
		
		return providers;
	}
	
	private void updateQuery(String query) throws SQLException {
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		
		preparedStatement.executeUpdate();
		
		preparedStatement.close();
		connection.close();
	}	
}