<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="headsection.html" %>
<title>Register</title>
<style>
	label{
	color:crimson;
	font-size:1em;
	}
</style>
</head>
<body>
<%@ include file="header.jsp" %>

<div class="container">
	<form name="form" action="<%=request.getContextPath()%>/RegisterServlet" method="post">
		<h1 class="text-primary">
 			Sign Up
 		</h1>
 		<div class="form-group">
	    	<label for="inputName">Enter Your Name</label>
	    	<input type="text" class="form-control" id="inputName" name="username" autocomplete="off">
  		</div>
  		<div class="form-group">
	    	<label for="inputPassword">Enter Password</label>
	    	<input type="password" class="form-control" id="inputPassword" name="password">
  		</div>
  		
  		<div class="form-group">
		    <label for="exampleInputEmail1">Email address</label>
		    <input type="email" class="form-control" id="exampleInputEmail1" name="emailId">
  		</div>
  		
  		<div class="form-group">
	    	<label for="inputMobile">Enter Mobile Number</label>
	    	<input type="text" class="form-control" id="inputMobile" name="mobile">
  		</div>
  		
  		<div class="form-group">
    		<label for="inputAddress1">Address 1</label>
    		<textarea class="form-control" id="inputAddress1" rows="3" name="address1"></textarea>
  		</div>
  		
  		<div class="form-group">
    		<label for="inputAddress2">Address 2</label>
    		<textarea class="form-control" id="inputAddress2" rows="3" name="address2"></textarea>
  		</div>
  		
        <span class="text-danger"><%=(request.getAttribute("errMessage") == null) ? "" : request.getAttribute("errMessage")%></span>
       
        <button type="submit" class="btn btn-outline-primary w-100 mt-2">Register</button>
        <button type="reset" class="btn btn-outline-info w-100 mt-2" >Reset</button>
	</form>
</div>
	
</body>
</html>