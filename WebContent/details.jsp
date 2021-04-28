<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="headsection.html" %>
<title>Details</title>
</head>
<body>
<%@ include file="header.jsp" %>
<div class="container">
	<img src="<%=request.getContextPath() %>/FileDisplay?filename=${product.image}" class="mt-5" height="500" width="60%" />
	<div class="content-detail">
	<h2>Product Name : ${product.product_name}</h2>
	<h2>Price : ${product.price}</h2>
	<h3>Brand : ${product.brand}</h3><br>
	<h3>color: ${product.color}</h3>
	<h3>Specifications : ${product.specifications}</h3>
	<h3>Seller : ${product.seller}</h3>
	</div>
	<a class="btn btn-warning w-50%" href="mycart.jsp?key=${product.id}">Add To Cart</a>
	<a class="btn btn-warning w-50%" href="#">Buy Now</a>
</div>
<%@ include file="footer.jsp" %>
<script>
<%@ include file="js/header.js" %>
</script>
</body>
</html>