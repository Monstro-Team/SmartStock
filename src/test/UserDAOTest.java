package test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.FactoryConnection;
import dao.UserDAO;
import model.User;

public class UserDAOTest {

	private Connection connection;

	@Before
	public void setUp() throws Exception {
		this.connection = FactoryConnection.getInstance().getConnection();
	}

	@After
	public void tearDown() throws Exception {
		String query = "DELETE FROM " + UserDAO.TABLE_NAME + " WHERE " + UserDAO.COLUMN_PASSWORD + " = 'test';";
		
		PreparedStatement preparedStatement = this.connection.prepareStatement(query);
		preparedStatement.execute();		
		preparedStatement.close();
	}

	@Test
	public void testGetUser() throws SQLException {
		assertNotNull(UserDAO.getUser("admin", "12345"));
	}

	@Test
	public void testInvalidUser() throws SQLException {
		assertNull(UserDAO.getUser("joao", "54321"));
	}

	@Test
	public void testInvalidUsername() throws SQLException {
		assertNull(UserDAO.getUser("joao", "12345"));
	}

	@Test
	public void testInvalidPassword() throws SQLException {
		assertNull(UserDAO.getUser("admin", "54321"));
	}

	@Test
	public void testGetUserList() throws SQLException {
		ArrayList<User> users = UserDAO.getUserList();
		
		assertTrue(users.size() > 0);
	}
}
