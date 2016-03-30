package dao;

import model.User;

public class UserDAO {
	
	/**
	 * Searches the database for a instance of User.
	 * @param username -> username to be found.
	 * @return -> return the user found, or null.
	 */
	public static User getUser(String username){
		
		//TODO: Change the user after database is implemented.
		User userTest = new User("test", "1234");
		
		if ( userTest.getUsername().equals(username) ){
			
			return userTest;
		}else{
			
			return null;
		}
		
	}
}
