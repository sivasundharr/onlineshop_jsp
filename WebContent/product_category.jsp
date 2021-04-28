<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.io.*,java.sql.*" %>
<%@ page import="java.nio.file.Path"%>
<%@ page import="java.nio.file.Paths"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="headsection.html" %>
<title>Category</title>
<style>
h3{
color:white;
}
</style>

</head>

<body>

<%@ include file="header.jsp" %>
<% 
Class.forName("com.mysql.jdbc.Driver").newInstance();
Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3307/cart","root","siva");      
Statement st=con.createStatement();
%>
<div class="containerex" >
		<div class="form-search mb-3">
			<input type="text" name="search" id="inputSearch" placeholder="Search for category,products..."   value="" 
					onkeyup="lookup(this.value);" autocomplete="off" />
					
		    <button type="button" id="Searchbtn">Search</button>
	    
		</div>
		<div class="suggestionsBox" id="suggestions" style="display:none;">
	     	<div id="autoSuggestionsList" style="color:red;"></div>
		</div>	
		<div class="rower">
		
		<div>
			<div class="list-group" id="l1">
				    <h3>Price</h3>
			<%
            	String cate = request.getParameter("cid");
				String product = request.getParameter("product");
            	int cid = Integer.parseInt(cate);
            	String query="select min(price) as minimum,max(price) as maximum from product where available>0 and category='"+cid+"'";
            	ResultSet rs=st.executeQuery(query);
          
          
            	while(rs.next())
            	{
            %>
				     <input type="hidden" id="hidden_minimum_price" value="<%=rs.getString("minimum") %>" />
                    <input type="hidden" id="hidden_maximum_price" value="<%=rs.getString("maximum") %>" />
                    <% if(rs.getString("minimum")!=null){ %>
                    <p id="price_show" style="color:white;"><%=rs.getString("minimum") %>-<%=rs.getString("maximum") %></p>
                    <div id="price_range"></div>
                 	 <% }else{ %>
                 	 
			                 	 
						<script>
						 var l1 = document.getElementById("l1");
						 l1.style.display = "none";
						</script>
			 		<%
			 		}
                } 
            	 rs.close();
            %>
       		</div>
        
            <%
            	String query1="SELECT DISTINCT(brand) FROM product WHERE category='"+cid+"' and available > 0  ORDER BY id DESC";
            	ResultSet rs1=st.executeQuery(query1);
            	if (!rs1.isBeforeFirst() ) {    
            	}
            	else{
            %>
            <div class="list-group">
     		<h3>Brand</h3>
            <div style="height: 180px; overflow-y: auto; overflow-x: hidden;">
           <% 
            	while(rs1.next())
            	{
           
            %>
	             <div class="list-group-item checkbox">
	                  <label><input type="checkbox" class="common_selector brand" value="<%=rs1.getString("brand") %>"  ><%=rs1.getString("brand") %></label>
	             </div>
	        <%
            	}
           	%>
           		</div>
         		</div>
         	<%
            	}
            	rs1.close();
	        %>
             
         
             <%
            	String query2="SELECT DISTINCT(color) FROM product WHERE category='"+cid+"' and available > 0  ORDER BY id DESC";
            	ResultSet rs2=st.executeQuery(query2);
            	if (!rs2.isBeforeFirst() ) {    
            	}
            	else{
             %>
             	<div class="list-group">
     				<h3>Color</h3>
            		<div style="height: 180px; overflow-y: auto; overflow-x: hidden;">
              <%
            	while(rs2.next())
            	{
            	%>
	             <div class="list-group-item checkbox">
	                <label><input type="checkbox" class="common_selector color" value="<%=rs2.getString("color") %>"  ><%=rs2.getString("color") %></label>
	             </div>
	             <%
            		}
           		%>
           			</div>
         		</div>
	            <%
            	}
            	rs2.close();
	        	%>
	        	
	       <%
	       String query4="select min(price) as minimum,max(price) as maximum from product where available>0 and category='"+cid+"'";
            	ResultSet rs4=st.executeQuery(query4);
          
          
            	while(rs4.next())
            	{
            		String name=rs4.getString("minimum");
            		%>
            		
           <%if(rs4.getString("minimum")!=null){ %>
	       <div class="list-group">
     		<h3>Price</h3>
            <div style="height: 180px; overflow-y: auto; overflow-x: hidden;">
	             <div class="list-group-item checkbox">
	                <label><input type="radio" class="common_selector price" value="lh" name="pricef" >low to high</label>
	             </div>
	             <div class="list-group-item checkbox">
	                  <label><input type="radio" class="common_selector price" value="hl" name="pricef" >high to low</label>
	             </div>
	        
           		</div>
         		</div>
         	
               
               </div>
        
        <%}} rs4.close();%>
           
             
         
         
		
		
		<div class="row row-cols-1 row-cols-md-3 filter_data" style="margin:0px auto;" >
			<c:if test="${fn:contains(listProduct,id)}">
		
			 <c:forEach var ="product"  items="${listProduct}">
			 	<div class='col mt-5 mb-5'>
			 		<div class='card h-100' style="width: 16rem;">
			 			<a href="detail?id=${product.id}">
			 				<img src="<%=request.getContextPath() %>/FileDisplay?filename=${product.image}" width="250" height="300" />
			 			</a>
			 			<div class="card-body">
			 				<h5 class="card-title">${product.product_name}</h5>
			 				<p class="card-text">Price :${product.price}</p>
			 			</div>
			 		</div>
			 	</div>
			 </c:forEach>
		</c:if>
			
		</div>
</div>

</div>
<%@ include file="footer.jsp" %> 

<script>
	<%@ include file="js/header.js" %>
</script>
<script>
$(document).ready(function(){

    
    function filter_data()
    {
    	
        var brand = get_filter('brand');
        var color = get_filter('color');
        var price = get_filter('price');
        $.ajax({
        	url:"/shop/fetch_data.jsp?cid="+<%=cid%>+"&brand="+brand+"&color="+color+"&price="+price+"",
            method:"GET",
            success:function(data){
                $(".filter_data").html(data);
            }
        });
    }

    function get_filter(class_name)
    {
        var filter = [];
        $('.'+class_name+':checked').each(function(){
            filter.push($(this).val());
        });
        return filter;
    }

    $('.common_selector').click(function(){
        filter_data();
    });

   

});
</script>

</body>
</html>