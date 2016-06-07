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

import dao.CabinetDAO;
import dao.FactoryConnection;
import model.Cabinet;

public class CabinetDAOTest {
	
	private Connection connection;
	private CabinetDAO cabinetDAO;
	private Cabinet cabinet;

	@Before
	public void setUp() throws Exception {
		this.connection = FactoryConnection.getInstance().getConnection();
		this.cabinetDAO = new CabinetDAO();
	}

	@After
	public void tearDown() throws Exception {
		String query = "DELETE FROM " + CabinetDAO.TABLE_NAME + " WHERE " + CabinetDAO.COLUMN_NAME + " = 'Z';";
		
		PreparedStatement preparedStatement = this.connection.prepareStatement(query);
		preparedStatement.execute();		
		preparedStatement.close();
	}

	@Test
	public void testIncludeCabinet() throws SQLException {
		Cabinet cabinetToInsert = new Cabinet("Z", 200);
		cabinetDAO.includeCabinet(cabinetToInsert);
		
		String query = "SELECT * FROM " + CabinetDAO.TABLE_NAME + " WHERE " + CabinetDAO.COLUMN_NAME + " = 'Z' AND " + CabinetDAO.COLUMN_DRAWER + " = 200;";
		
		PreparedStatement preparedStatement = this.connection.prepareStatement(query);
		ResultSet result = preparedStatement.executeQuery();
		
		if(result.first()) {
			cabinet = new Cabinet();
			cabinet.setId(result.getInt(CabinetDAO.COLUMN_ID));
			cabinet.setName(result.getString(CabinetDAO.COLUMN_NAME));
			cabinet.setDrawer(result.getInt(CabinetDAO.COLUMN_DRAWER));
			
			preparedStatement.close();
			result.close();
		}
		
		assertEquals(cabinetToInsert.getName(), cabinet.getName());
		assertEquals(cabinetToInsert.getDrawer(), cabinet.getDrawer());
	}

	@Test
	public void testGetAllCabinet() throws SQLException {
		ArrayList<Cabinet> cabinets;
		int cabinetsAdded = 0;
		cabinets = cabinetDAO.getAllCabinet();
		int cabinetCountBefore = cabinets.size();
		
		for(int i = 1; i < 4; i++) {
			cabinet = new Cabinet("Z", i);
			cabinetDAO.includeCabinet(cabinet);
			cabinetsAdded++;
		}
		
		cabinets = cabinetDAO.getAllCabinet();
		int cabinetCountAfter = cabinets.size();
		
		assertEquals(cabinetCountBefore + cabinetsAdded, cabinetCountAfter);
	}
}
