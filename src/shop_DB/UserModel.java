package shop_DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import shop_product.User;

public class UserModel {
	private String jdbcURL = "jdbc:mysql://localhost:3307/cart";
	private String jdbcUsername = "root";
	private String jdbcPassword = "siva";

	private static final String INSERT_USERS_SQL = "INSERT INTO users" + "  (username,email,mobile_number,address1,address2,image,password)"
			+ " VALUES "
			+ " (?, ?, ?,?,?,?,?);";
	
	private static final String SELECT_USER_BY_ID = "select username,email,mobile_number,address1,address2,image from users where id =?";
	
	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	public void createUser(User user) throws SQLException {
		System.out.println(INSERT_USERS_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getEmail());
			preparedStatement.setLong(3, user.getMobile_number());
			preparedStatement.setString(4, user.getAddress1());
			preparedStatement.setString(5, user.getAddress2());
			preparedStatement.setString(6, user.getImage());
			preparedStatement.setString(7, user.getPassword());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}
	
	public User selectUser(int id) {
		User user = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String name = rs.getString("username");
				String email = rs.getString("email");
				long mobile = rs.getLong("mobile_number");
				String address1 = rs.getString("address1");
				String address2 = rs.getString("address2");
				String image = rs.getString("image");
				
				user = new User(name,email,mobile,address1,address2,image);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return user;
	}
	
	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
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
