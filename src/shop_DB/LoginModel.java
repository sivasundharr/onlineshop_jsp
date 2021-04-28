package shop_DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import shop_product.Login;

public class LoginModel {
	private String jdbcURL = "jdbc:mysql://localhost:3307/cart";
	private String jdbcUsername = "root";
	private String jdbcPassword = "siva";
	
	String userNameDB = "";
	String passwordDB = "";
    String roleDB = "";
	
	private static final String SELECT_USER = "select username,password,role from users";
	
		protected Connection getConnection() {
			Connection connection = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
			} catch (SQLException e) {
				
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				
				e.printStackTrace();
			}
			return connection;
		}
		
public String authenticateUser(Login loginBean)
{
	 String userName = loginBean.getUsername();
	 String password = loginBean.getPassword();
	
	 try(Connection connection = getConnection();
	PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER);)
	    {
		 ResultSet rs = preparedStatement.executeQuery();
			 while (rs.next()) {
				 	userNameDB = rs.getString("username");
		            passwordDB = rs.getString("password");
		            roleDB = rs.getString("role");
		            
		            if(userName.equals(userNameDB) && password.equals(passwordDB) && roleDB.equals("Admin"))
		            return "Admin_Role";
		            else if(userName.equals(userNameDB) && password.equals(passwordDB) && roleDB.equals("User"))
		            return "User_Role";
			 }
		 }
	 	catch(SQLException e)
	    {
	        e.printStackTrace();
	    }
	 return "Invalid user credentials";
}
}
