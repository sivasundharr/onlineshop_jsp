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
  String query = "select *from users where username='"+request.getSession(false).getAttribute("username")+"'";
  ResultSet rs = st.executeQuery(query);
 %>
 
<!DOCTYPE html>
<html>
<head>
<%@ include file="headsection.html" %>
<title>Profile-<%=request.getSession(false).getAttribute("username")%></title>
<style>
*{
color:teal;
text-align:center;
}
</style>
</head>
<body>
<%@ include file="header.jsp" %>
<div class="containerex" >
<h2 style="color:dodgerblue;">Your Profile</h2>
	<%
	while(rs.next()){
		
	String name = rs.getString("username");
	String image = rs.getString("image");
	String email = rs.getString("email");
	String mobile = rs.getString("mobile_number");
	%>
	<img src="<%=request.getContextPath() %>/FileDisplay?filename=profile.png" width="250"  height="260" />
	<h3>Name : <%=name %></h3>
	<h3>Email Address : <%=email %></h3>
	<h3>Mobile Number : <%=mobile %></h3>
	<%
	}
	rs.close();
	con.close();
	%>
</div>

<script>
        <%@ include file="js/header.js" %>  
</script>
</body>
</html>