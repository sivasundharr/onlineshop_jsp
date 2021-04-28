package shop_DB;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import shop_product.Product;

public class ProductModel {
	private String jdbcURL = "jdbc:mysql://localhost:3307/cart";
	private String jdbcUsername = "root";
	private String jdbcPassword = "siva";

	private static final String INSERT_PRODUCTS_SQL = "INSERT INTO product" + " (product_name,category,available,price,image,specifications,seller_address,color,brand)"
			+ " VALUES "
			+ " (?, ?, ?,?,?,?,?,?,?);";

	private static final String SELECT_PRODUCT_BY_ID = "select id,product_name,category,available,price,image,specifications,seller_address,color,brand from product where id =?";
	private static final String SELECT_ALL_PRODUCTS = "select *from product limit 8";
	private static final String SELECT_EACH_PRODUCT ="select *from product where product_name=? or category=?";
	private static final String SELECT_PRODUCT_BY_FILTER = "select *from product where available > 0 and category=?";
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
	
	public void insertProduct(Product prod) throws SQLException {
		System.out.println(INSERT_PRODUCTS_SQL);
		
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCTS_SQL)) {
			preparedStatement.setString(1, prod.getProduct_name());
			preparedStatement.setInt(2, prod.getCategory());
			preparedStatement.setInt(3, prod.getAvailable());
			preparedStatement.setDouble(4, prod.getPrice());
			preparedStatement.setString(5, prod.getImage());
			preparedStatement.setString(6, prod.getSpecifications());
			preparedStatement.setString(7, prod.getSeller());
			preparedStatement.setString(8,prod.getColor());
			preparedStatement.setString(9, prod.getBrand());
			System.out.println(preparedStatement);
			preparedStatement.execute();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}
	
	public Product selectProduct(int id) {
		Product prod = null;
		
		try (Connection connection = getConnection();
				
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			
			ResultSet rs = preparedStatement.executeQuery();

			
			while (rs.next()) {
			
				String name = rs.getString("product_name");
				int category = rs.getInt("category");
				int available = rs.getInt("available");
				double price = rs.getDouble("price");
				String image = rs.getString("image");
				String specifications = rs.getString("specifications");
				String seller = rs.getString("seller_address");
				String color = rs.getString("color");
				String brand = rs.getString("brand");
				Path path = Paths.get(image);
				Path fileName = path.getFileName();
				
				prod = new Product(id, name,category,available,price,fileName.toString(),specifications,seller,color,brand);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return prod;
	}
	public List<Product> selectEachCategory(String productname,int cid){
		List<Product> prods = new ArrayList<>();
		try(Connection connection = getConnection();

				
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EACH_PRODUCT);) {
			preparedStatement.setString(1, productname);
			preparedStatement.setInt(2, cid);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			
			while (rs.next()) {
				int id = rs.getInt("id"); 
				String name = rs.getString("product_name");
				int category = rs.getInt("category");
				int available = rs.getInt("available");
				double price = rs.getDouble("price");
				String image = rs.getString("image");
				Path path = Paths.get(image);
				Path fileName = path.getFileName();
				
				prods.add(new Product(id,name,category,available,price,fileName.toString()));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		
		return prods;
	}
	public List<Product> selectAllProducts() {

		
		List<Product> prods = new ArrayList<>();
	
		try (Connection connection = getConnection();

			
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCTS);) {
			System.out.println(preparedStatement);
			
			ResultSet rs = preparedStatement.executeQuery();

			
			while (rs.next()) {
				int id = rs.getInt("id"); 
				String name = rs.getString("product_name");
				int category = rs.getInt("category");
				int available = rs.getInt("available");
				double price = rs.getDouble("price");
				String image = rs.getString("image");
				Path path = Paths.get(image);
				Path fileName = path.getFileName();
                 
				
				prods.add(new Product(id,name,category,available,price,fileName.toString()));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return prods;
	}
	
	public List<Product> selectProductsByFilter(int cid,String brand,String color) {
	
			System.out.println(brand);
			System.out.println(color);
			List<Product> prods = new ArrayList<>();
			String query=SELECT_PRODUCT_BY_FILTER,f1="";
			PreparedStatement preparedStatement;
			ResultSet rs=null;
			
			if(brand!=null&&brand!="") {
				String[] arrOfStr = brand.split(","); 
		        for (String a : arrOfStr)  
		        	f1+="'"+a+"'"+",";
		        f1 = method(f1);
		        query +=" and brand in("+f1+")";
			}
			
			
			if(color!=null&&color!="") {
				String[] arrOfStr = color.split(","); 
		        for (String a : arrOfStr)  
		        	f1+="'"+a+"'"+",";
		        f1 = method(f1);
		        query +=" and color in("+f1+")";
			}
			
			
			try (Connection connection = getConnection();) {
					
					preparedStatement = connection.prepareStatement(query);
					preparedStatement.setInt(1, cid);
					System.out.println(preparedStatement);
					 rs=preparedStatement.executeQuery();
					
					 while(rs.next()) {
							int id = rs.getInt("id"); 
							String name = rs.getString("product_name");
							int category = rs.getInt("category");
							int available = rs.getInt("available");
							double price = rs.getDouble("price");
							String image = rs.getString("image");
							Path path = Paths.get(image);
							Path fileName = path.getFileName();
							
							prods.add(new Product(id,name,category,available,price,fileName.toString()));
						 }
					 rs.close();
				}
				
			 catch (SQLException e) {
				printSQLException(e);
			}
			
		return prods;
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
	public String method(String str) {
	    if (str != null && str.length() > 0 && str.charAt(str.length() - 1) == ',') {
	        str = str.substring(0, str.length() - 1);
	    }
	    return str;
	}
}
