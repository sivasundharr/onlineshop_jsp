<%@ page import="java.sql.*,java.io.*,java.util.*" %> 
<% response.setContentType("text/html");%>
<%
  String num = request.getParameter("q");
	if(num!=null){
    
  try{      
    Class.forName("com.mysql.jdbc.Driver").newInstance();
    Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3307/cart","root","siva");      
    Statement st=con.createStatement();
    String strQuery = "select id,product_name from product where product_name like '"+num+"%' union select id,category from category where category like '"+num+"%'";
    ResultSet rs = st.executeQuery(strQuery);

    while(rs.next()){
      out.println("<a href='product?product="+rs.getString("product_name")+"&cid="+rs.getInt("id")+"' class='suggfield'>"+rs.getString("product_name")+"</a>");
    }
    rs.close();
  }
  catch (Exception e){
    e.printStackTrace();
  }
	}
%>