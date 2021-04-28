package shop_servlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




@WebServlet("/product_create")
public class product_create extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public product_create() {
    	super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();
		out.println("hello this is get");
		out.println(request.getServletPath());
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("this is post");
	    String name = request.getParameter("product_name");
	    out.println(request.getParameter("product_name"));
	    String category = request.getParameter("category");
	    String available = request.getParameter("avilable");
	    String image = request.getParameter("file");
	    String price = request.getParameter("price");
	    String details = request.getParameter("details");
	    String specifications = request.getParameter("specifications");
	    String seller = request.getParameter("seller");

	    if(name!=null && category!=null && available!=null && price !=null && image!=null){
	    int avilable = Integer.parseInt(available);
	    float price_new = Float.parseFloat("price");
	      try{
	          Class.forName("com.mysql.jdbc.Driver");
	          Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3307/cart","root","siva");
	          Statement stmt=con.createStatement();
	          String query = " insert into product(name,category,available,price,image,details,specifications,seller_address)"
	          + " values (?, ?, ?,?,?,?,?,?)";
			  
	          	
	          PreparedStatement preparedStmt = con.prepareStatement(query);
	          preparedStmt.setString (1, name);
	          preparedStmt.setString (2, category);
	          preparedStmt.setInt  (3,avilable);
	          preparedStmt.setFloat(4,price_new);
	          preparedStmt.setString(5,image);
	          preparedStmt.setString(6,details);
	          preparedStmt.setString(7,specifications);
	          preparedStmt.setString(8,seller);
	          out.println(preparedStmt);
	          preparedStmt.execute();

	          con.close();
	         
	         }
	        catch(Exception e){
	        out.println(e);
	      }
	    }
	    out.close();
	   }
	

}