package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Product;
import model.Provider;

public class ProviderDAO {

	private static final String TABLE_NAME = "Provider";
	private static final String COLUMN_ID = "provider_id";
	private static final String COLUMN_COMPANY = "provider_company";
	private static final String COLUMN_SALESMAN = "provider_salesman";
	private static final String COLUMN_SALESMANPHONE = "provider_salesmanPhone";
	
	private Connection connection;
	
	public ProviderDAO() throws SQLException {
		this.connection = FactoryConnection.getInstance().getConnection();
	}
	
	public void includeProvider(Provider provider) throws SQLException {
		if(provider != null) {
			String query =  "INSERT INTO "
					+ TABLE_NAME + " ("
					+ COLUMN_COMPANY + ", "
					+ COLUMN_SALESMAN + ", "
					+ COLUMN_SALESMANPHONE 
					+ ") VALUES (" + "\""
					+ provider.getCompany() + "\", \"" 
					+ provider.getSalesman() + "\", \"" 
					+ provider.getSalesmanPhone() + "\");";
			
			this.updateQuery(query);
		}
	}
	
	public ArrayList<Provider> getAllProviders() throws SQLException {
		ArrayList<Provider> providers = new ArrayList<Provider>();
		
		String query = "SELECT * FROM " + TABLE_NAME + ";";
		PreparedStatement preparedStatement = this.connection.prepareStatement(query);
		ResultSet result = preparedStatement.executeQuery();
		
		while(result.next()) {
			Provider provider = new Provider();
			provider.setId(result.getInt(COLUMN_ID));
			provider.setCompany(result.getString(COLUMN_COMPANY));
			provider.setSalesman(result.getString(COLUMN_SALESMAN));
			provider.setSalesmanPhone(result.getString(COLUMN_SALESMANPHONE));
			providers.add(provider);
		}
		
		preparedStatement.close();
		result.close();
		
		return providers;
	}
	
	public void updateProvider(Provider provider)throws SQLException {
		String query =  "UPDATE "
				+ TABLE_NAME
				+ " SET "
				+ COLUMN_COMPANY + "= '" + provider.getCompany() + "', "
				+ COLUMN_SALESMAN + "= '" + provider.getSalesman() + "', "
				+ COLUMN_SALESMANPHONE + "= '" + provider.getSalesmanPhone()
				+ " WHERE "
				+ COLUMN_ID + " = " + provider.getId() + ";";
		
		this.updateQuery(query);
	}
	
	public Provider getProvider(int provider_id) throws SQLException {
		ArrayList<Provider> providers = getAllProviders();
		
		for(Provider provider : providers){
			if(provider.getId() == provider_id) {
				return provider;
			}
		}
		
		return null;
	}
	
	public void deleteProvider(int provider_id) throws SQLException{
		String query = "DELETE FROM "
				+ TABLE_NAME
				+ " WHERE "
				+ COLUMN_ID + " = " + provider_id; 
		
		this.updateQuery(query);
	}
	
	private void updateQuery(String query) throws SQLException {
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		
		preparedStatement.executeUpdate();
		
		preparedStatement.close();
		connection.close();
	}	
}
