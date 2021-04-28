<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*,java.io.*,java.util.*" %>
<%@ page import="java.nio.file.Path"%>
<%@ page import="java.nio.file.Paths"%> 
<% response.setContentType("text/html");%>

<%
     
  Class.forName("com.mysql.jdbc.Driver").newInstance();
  Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3307/cart","root","siva");      
  Statement st=con.createStatement();
 %>
 
 <%
 String cate = request.getParameter("cid");
 String product = request.getParameter("product");
 int cid = Integer.parseInt(cate);
 String brand = request.getParameter("brand");
 String color = request.getParameter("color");
 String price = request.getParameter("price");
 
 
 	PreparedStatement preparedStatement;
	ResultSet rs=null;
 	String query,f1="",output="";
 	
 	query = "select *from product where available > 0 and category=?";
 	
 	
 	
 	if(brand!=null&&brand!="") {
		String[] arrOfStr = brand.split(","); 
        for (String a : arrOfStr)  
        	f1+="'"+a+"'"+",";
        f1 = method(f1);
        query +=" and brand in("+f1+")";
        f1="";
	}
	
	
	if(color!=null&&color!="") {
		String[] arrOfStr = color.split(","); 
        for (String a : arrOfStr)  
        	f1+="'"+a+"'"+",";
        f1 = method(f1);
        query +=" and color in("+f1+")";
        f1="";
	}
	
	if(price!=null &&price!=""){
		if(price.equals("lh")){
			query +=" order by price asc";
		}
		else{
			query +=" order by price desc";
		}
	}
	
try{
 	preparedStatement = con.prepareStatement(query);
	preparedStatement.setInt(1, cid);
	System.out.println(preparedStatement);
	 rs=preparedStatement.executeQuery();
	 while(rs.next()){
		 String image =rs.getString("image");
		 	Path path = Paths.get(image);
			Path fileName = path.getFileName();
		 output+="<div class='col mt-5 mb-5'><div class='card h-70' style='width: 16rem;''>";
		 output+="<a href='/shop/detail?id="+rs.getInt("id")+"'>";
		 output+="<img src='/shop/FileDisplay?filename="+fileName.toString()+"' width='250' height='300' />";
		 output+="</a>";
		 output+="<div class='card-body'><h5 class='card-title'>"+rs.getString("product_name")+"</h5><p class='card-text'>Price :"+rs.getDouble("price")+"</p></div></div></div>";
	 }
 
	 out.write(output);
	
 rs.close();
 con.close();
}
catch (Exception e){
    e.printStackTrace();
  }
%>
<%!
 public String method(String str) {
	    if (str != null && str.length() > 0 && str.charAt(str.length() - 1) == ',') {
	        str = str.substring(0, str.length() - 1);
	    }
	    return str;
	}
 %>
