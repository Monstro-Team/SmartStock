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
import dao.ProviderDAO;
import model.Provider;

public class ProviderDAOTest {

	private Connection connection;
	private ProviderDAO providerDAO;

	@Before
	public void setUp() throws Exception {
		this.connection = FactoryConnection.getInstance().getConnection();
		this.providerDAO = new ProviderDAO();
	}

	@After
	public void tearDown() throws Exception {
		String query = "DELETE FROM " + ProviderDAO.TABLE_NAME + " WHERE " + ProviderDAO.COLUMN_SALESMAN + " = 'test';";
		
		PreparedStatement preparedStatement = this.connection.prepareStatement(query);
		preparedStatement.execute();		
		preparedStatement.close();
	}

	@Test
	public void testIncludeProvider() throws SQLException {
		Provider provider = new Provider("FornecedorIncludeTest", "test", "(61)1234-5678");
		providerDAO.includeProvider(provider);
		
		String query = "SELECT * FROM " + ProviderDAO.TABLE_NAME + " WHERE " + ProviderDAO.COLUMN_COMPANY + " = 'FornecedorIncludeTest';";
		
		PreparedStatement preparedStatement = this.connection.prepareStatement(query);
		ResultSet result = preparedStatement.executeQuery();
		
		boolean providerRetrieved = result.first();
		
		preparedStatement.close();
		result.close();
		
		assertTrue(providerRetrieved);
	}

	@Test
	public void testIncludeNullProvider() throws SQLException {
		ArrayList<Provider> providersBefore = providerDAO.getAllProviders();
		Provider provider = null;
		providerDAO.includeProvider(provider);
		ArrayList<Provider> providersAfter = providerDAO.getAllProviders();
		
		assertEquals(providersBefore.size(), providersAfter.size());
	}

	@Test
	public void testGetAllProviders() throws SQLException {
		Provider provider = new Provider("TestGetAllProvider1", "test", "(61)1234-5678");
		providerDAO.includeProvider(provider);
		Provider provider2 = new Provider("TestGetAllProvider2", "test", "(61)1234-5678");
		providerDAO.includeProvider(provider2);
		
		ArrayList<Provider> products = providerDAO.getAllProviders();
		
		assertTrue(products.size() > 0);
	}

	@Test
	public void testUpdateProvider() throws SQLException {
		Provider providerToUpdate = new Provider("3M", "test", "(61)1234-5678");
		providerDAO.includeProvider(providerToUpdate);
		
		String query = "SELECT * FROM " + ProviderDAO.TABLE_NAME + " WHERE " + ProviderDAO.COLUMN_COMPANY + " = '3M';";
		
		PreparedStatement preparedStatement = this.connection.prepareStatement(query);
		ResultSet result = preparedStatement.executeQuery();
		
		int id = 0;		
		Provider providerResult = null;
		
		if(result.first()) {
			id = result.getInt(ProviderDAO.COLUMN_ID);
			
			providerResult = new Provider();
			providerResult.setId(id);
			providerResult.setCompany("FornecedorTesteAtualizado");
			providerResult.setSalesman(result.getString(ProviderDAO.COLUMN_SALESMAN));
			providerResult.setSalesmanPhone(result.getString(ProviderDAO.COLUMN_SALESMANPHONE));
			
			providerDAO.updateProvider(providerResult);
		}
		
		query = "SELECT * FROM " + ProviderDAO.TABLE_NAME + " WHERE " + ProviderDAO.COLUMN_ID + " = " + id + ";";
		
		preparedStatement = this.connection.prepareStatement(query);
		result = preparedStatement.executeQuery();
		
		String updatedName = "";
		
		if(result.first()) {
			updatedName = result.getString(ProviderDAO.COLUMN_COMPANY);
		}
		
		preparedStatement.close();
		result.close();
		
		assertEquals("FornecedorTesteAtualizado", updatedName);
	}

	@Test
	public void testGetProvider() throws SQLException {
		Provider provider = new Provider("TestGetProvider", "test", "(61)1234-5678");
		providerDAO.includeProvider(provider);
		
		String query = "SELECT * FROM " + ProviderDAO.TABLE_NAME + " LIMIT 1;";
		
		PreparedStatement preparedStatement = this.connection.prepareStatement(query);
		ResultSet result = preparedStatement.executeQuery();
		
		int idToRetrieve = -1;
		
		if(result.first()) {
			idToRetrieve = result.getInt(ProviderDAO.COLUMN_ID);
		}
		
		preparedStatement.close();
		result.close();
		
		assertNotNull(providerDAO.getProvider(idToRetrieve));
	}

	@Test
	public void testGetProviderInvalidId() throws SQLException {
		assertNull(providerDAO.getProvider(-1));
	}

	@Test
	public void testDeleteProvider() throws SQLException {
		Provider provider = new Provider("FornecedorDeleteTest", "test", "(61)1234-5678");
		providerDAO.includeProvider(provider);
		
		ArrayList<Provider> providers = providerDAO.getAllProviders();
		
		int countBefore = providers.size();
		
		String query = "SELECT * FROM " + ProviderDAO.TABLE_NAME + " WHERE " + ProviderDAO.COLUMN_COMPANY + " = 'FornecedorDeleteTest';";
		
		PreparedStatement preparedStatement = this.connection.prepareStatement(query);
		ResultSet result = preparedStatement.executeQuery();
		
		Provider providerToDelete = null;
		
		if(result.first()) {
			providerToDelete = new Provider();
			providerToDelete.setId(result.getInt(ProviderDAO.COLUMN_ID));
			providerToDelete.setCompany(result.getString(ProviderDAO.COLUMN_COMPANY));
			providerToDelete.setSalesman(result.getString(ProviderDAO.COLUMN_SALESMAN));
			providerToDelete.setSalesmanPhone(result.getString(ProviderDAO.COLUMN_SALESMANPHONE));
			
			preparedStatement.close();
			result.close();
		}
			
		providerDAO.deleteProvider(providerToDelete.getId());
		
		providers = providerDAO.getAllProviders();
		int countAfter = providers.size();
		
		assertTrue(countBefore > countAfter);
	}
}
