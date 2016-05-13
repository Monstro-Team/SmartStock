package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.User;

public class UserDAO {
	
	private static final String TABLE_NAME = "User";
	private static final String COLUMN_USER_ID = "user_id";
	private static final String COLUMN_USERNAME = "username";
	private static final String COLUMN_PASSWORD = "password";
	
	public static User getUser(String username, String password)
			throws SQLException {
		User loggedUser = null;
		ArrayList<User> userList = getUserList();
		
		for(User logged : userList){
			if(logged.getUsername().equals(username)
					&& logged.getPassword().equals(password)){
				loggedUser = logged;
			}
		}
		
		return loggedUser;
	}
	
	public static ArrayList<User> getUserList() throws SQLException {		
		String sql = "SELECT * FROM " + TABLE_NAME;
		ArrayList<User> userList = new ArrayList<User>();
		Connection connection = FactoryConnection.getInstance().getConnection();
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
		
			while(rs.next()) {
				User user = new User();		
				user.setIdUser(rs.getInt(COLUMN_USER_ID));
				user.setUsername(rs.getString(COLUMN_USERNAME));
				user.setPassword(rs.getString(COLUMN_PASSWORD));
				
				userList.add(user);
			}
			
			preparedStatement.close();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
		return userList;
	}	
}
