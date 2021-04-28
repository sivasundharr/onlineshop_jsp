<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="headsection.html" %>
<title>Login</title>
<style>
label{
color:crimson;
}
span{
margin-top:10px;
color:white;
}
</style>
</head>
<body>
<%@ include file="header.jsp" %>

<div class="container">
	<form name="form" action="<%=request.getContextPath()%>/LoginServlet" method="post">
 		<h1 class="text-primary">
 			Sign In
 		</h1>
 		<div class="form-group">
	    	<label for="inputName">Enter UserName</label>
	    	<input type="text" class="form-control" id="inputName" name="username" autocomplete="off">
  		</div>
  		<div class="form-group">
	    	<label for="inputPassword">Enter Password</label>
	    	<input type="password" class="form-control" id="inputPassword" name="password">
  		</div>
        <span class="text-danger"><%=(request.getAttribute("errMessage") == null) ? "" : request.getAttribute("errMessage")%></span>
       
        <button type="submit" class="btn btn-outline-primary w-100 mt-2">Login</button>
        <button type="reset" class="btn btn-outline-info w-100 mt-2" >Reset</button>
       <span style="font-style:italic;color:maroon;">Already You Not have an account? <a href="Register.jsp">Sign Up</a></span>
        </form>
</div>

<script>
<%@ include file="js/header.js" %>
</script>
</body>
</html>