<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.io.*,java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="headsection.html" %>
<title>Add Product</title>
<% 
if((request.getSession(false).getAttribute("Admin")== null) )
{
%>
<jsp:forward page="login.jsp"></jsp:forward>
<%} %>
<style>
label{
color:dodgerblue;

}
</style>
</head>
<body>
<%@ include file="header.jsp" %>
<%! String driverName = "com.mysql.jdbc.Driver";%>
<%!String url = "jdbc:mysql://localhost:3307/cart";%>
<%!String user = "root";%>
<%!String psw = "siva";%>

<div class="container">
<form action="<%=request.getContextPath()%>/insert" method="post" enctype="multipart/form-data" >

<h2 class="text-primary">Add New Product</h2>
  <div class="form-group">
    <label for="inputProductName"></label>
    <input type="text" class="form-control" id="inputProductName" placeholder="Enter product Name" name="product_name">
  </div>
  <%
Connection con = null;
PreparedStatement ps = null;
try
{
Class.forName(driverName);
con = DriverManager.getConnection(url,user,psw);
String sql = "select *from category";
ps = con.prepareStatement(sql);
ResultSet rs = ps.executeQuery(); 
%>
  <div class="form-group">
    <label for="inputCategory">product category</label>
    <select class="form-control" id="inputCategory" name="category">
<%
while(rs.next())
{
String category = rs.getString("category");
int id = rs.getInt("id");
%>
<option value="<%=id%>"><%=category %></option>
<%
}
%>
    </select>
<%
}
catch(SQLException sqe)
{ 
out.println(sqe);
}
%>
  </div>
  <div class="form-group">
    <label for="inputAvailable">Product Available</label>
    <input type="text" class="form-control" id="inputAvailable" placeholder="Enter the total stock in the product" name="avilable">
  </div>
  <div class="form-group">
    <label for="inputPrice">Product price</label>
    <input type="text" class="form-control" id="inputPrice" placeholder="Enter product price" name="price">
  </div>
  
  <div class="form-group">
    <label for="exampleFormControlFile1">Choose Product Image </label>
    <input type="file" class="form-control-file" id="exampleFormControlFile1" name="image">
  </div>
  
  <div class="form-group">
    <label for="exampleFormControlTextarea2">Product Specifications</label>
    <textarea class="form-control" id="exampleFormControlTextarea2" rows="3" name="specifications"></textarea>
  </div>
  <div class="form-group">
    <label for="exampleFormControlTextarea3">Seller Address</label>
    <textarea class="form-control" id="exampleFormControlTextarea3" rows="3" name="seller"></textarea>
  </div>
  <div class="form-group">
    <label for="inputProductColor">Product color</label>
    <input type="text" class="form-control" id="inputProductColor"  name="color">
  </div>
   <div class="form-group">
    <label for="inputProductBrand">Product Brand</label>
    <input type="text" class="form-control" id="inputProductBrand"  name="brand">
  </div>
    <button type="submit" class="btn btn-warning w-100">Submit</button>
</form>
</div>
<%@ include file="footer.jsp" %>
<script>
	<%@ include file="js/header.js" %>
</script>
</body>
</html>
