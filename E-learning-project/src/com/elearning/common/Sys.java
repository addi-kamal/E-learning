/**
 * 
 */
package com.elearning.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.elearning.db.Connexion;
import com.elearning.users.User;

/**
 * @author rahima
 *
 */
public class Sys {
	
	/**
	 * @return a user if the login is successful and null if not
	 *
	 */
	public User checkLogin(String email, String password) throws SQLException {
		Connection connection = new Connexion().ConnectToDB();
		
		String sql = "SELECT * FROM users WHERE email = ? AND password= ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, email);
		statement.setString(2, password);

		ResultSet result = statement.executeQuery();

		User user = new User();
		if (result.next()) {
			user.setCurrentid(result.getInt("id"));
			user.setEmail(email);
			user.setPassword(password);
			user.setNom(result.getString("nom"));
			user.setPrenom(result.getString("prenom"));
			user.setType(result.getString("user_type"));
			user.setUrlavatar(result.getString("url_avatar"));
			user.setIsVerified(result.getString("isVerified"));
		} else {
			user = null;
		}

		connection.close();

		return user;
}
	
}
