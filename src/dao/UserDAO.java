package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.User;

public class UserDAO {
	
	final static String NAME_TABLE = "User";
	final static String COLUMN_USERNAME = "username";
	final static String COLUMN_PASSWORD = "password";
	
	public static User getUser(String username, String password) throws SQLException{
		User loggedUser = null;
		ArrayList<User> userList = getUserList();
		
		for(User logged : userList){
			if(logged.getUsername().equals(username) && logged.getPassword().equals(password)){
				loggedUser = logged;
			}
		}
		
		return loggedUser;
	}
	
	public static ArrayList<User> getUserList() throws SQLException{
		
		String sql = "SELECT * FROM " + NAME_TABLE;
		ArrayList<User> userList = new ArrayList<User>();
		Connection connection = FactoryConnection.getInstance().getConnection();
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
		
			while(rs.next()) {
				User user = new User();
				
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				
				userList.add(user);
			}
			
			preparedStatement.close();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
		return userList;
	}
	
}
