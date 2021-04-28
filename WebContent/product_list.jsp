<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.*,java.io.*,java.util.*" %>
<%@ page import="java.nio.file.Path"%>
<%@ page import="java.nio.file.Paths"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
	<%@ include file="headsection.html" %>
	<title>Home Page</title>
	<style>
	
	</style>
</head>
<body>
<%@ include file="header.jsp" %>
  
<div class="container" style="height:1000px;" >
	<h1 id="home">Welcome to shopcart</h1>
	<div class="form-search">
		<input type="text" name="search" id="inputSearch" placeholder="Search for category,products..."   
				value="" onkeyup="lookup(this.value);" autocomplete="off"/>
	    
	    
	</div>
		<div class="suggestionsBox" id="suggestions" style="display:none;">
		     <div id="autoSuggestionsList" style="color:red;"></div>
		</div>
		
		
	<div class="row row-cols-1 row-cols-md-4 " style="margin:0px auto;" >
		<c:forEach var="product" items="${listProduct}">
		<div class="col mt-5 mb-5">
			<div class="card h-100" style="width: 16rem;">
			 <a href="detail?id=${product.id}">
			 <img src="<%=request.getContextPath() %>/FileDisplay?filename=${product.image}" 
			 			width="250" height="240" style="padding:5px;"/>
			 </a> 
			  <div class="card-body">
			    <h5 class="card-title">${product.product_name}
			    </h5>
			    <p class="card-text">Price : ${product.price}</p>
			   
			  </div>
			</div>
		</div>
		</c:forEach>
	</div>
	
</div>

<%@ include file="footer.jsp" %>
 
<script>
    <%@ include file="js/header.js" %>  
</script>

</body>
</html>