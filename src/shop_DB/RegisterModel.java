package shop_DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import shop_product.Register;

public class RegisterModel {
	private String jdbcURL = "jdbc:mysql://localhost:3307/cart";
	private String jdbcUsername = "root";
	private String jdbcPassword = "siva";
	
	private static final String INSERT_USER = "insert into users(username,password,email,mobile_number,address1,address2)values(?,?,?,?,?,?)";
	
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
	
	public void CreateNewUser(Register reg) {
		int result = 0;
		System.out.println(INSERT_USER);
		 try(Connection connection = getConnection();
					PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER);)
		 {
			 preparedStatement.setString(1,reg.getUsername());
			 preparedStatement.setString(2,reg.getPassword());
			 preparedStatement.setString(3,reg.getEmail());
			 preparedStatement.setString(4,reg.getMobile());
			 preparedStatement.setString(5,reg.getAddress1());
			 preparedStatement.setString(6,reg.getAddress2());
			 System.out.println(preparedStatement);
			result = preparedStatement.executeUpdate();
		 }
		 catch(SQLException e)
		    {
			 printSQLException(e);
		    }
		
	}
	 private void printSQLException(SQLException ex) {
	        for (Throwable e: ex) {
	            if (e instanceof SQLException) {
	                e.printStackTrace(System.err);
	                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
	                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
	                System.err.println("Message: " + e.getMessage());
	                Throwable t = ex.getCause();
	                while (t != null) {
	                    System.out.println("Cause: " + t);
	                    t = t.getCause();
	                }
	            }
	        }
	    }
	
	
}
