<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.io.*,java.sql.*" %>
<%@ page import="java.nio.file.Path"%>
<%@ page import="java.nio.file.Paths"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="headsection.html" %>
<title>Insert title here</title>
</head>
<body>
<% 
Class.forName("com.mysql.jdbc.Driver").newInstance();
Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3307/cart","root","siva");      
Statement st=con.createStatement();
%>
<div class="container">
	<div class="list-group">
				     <h3>Price</h3>
				     <input type="hidden" id="hidden_minimum_price" value="0" />
                    <input type="hidden" id="hidden_maximum_price" value="65000" />
                    <p id="price_show">1000 - 65000</p>
                    <div id="price_range"></div>
        </div>
        <div class="list-group">
     		<h3>Brand</h3>
            <div style="height: 180px; overflow-y: auto; overflow-x: hidden;">
            <%
            	String cate = request.getParameter("cid");
            	int cid = Integer.parseInt(cate);
            	String query="SELECT DISTINCT(brand) FROM product WHERE category='"+cid+"' and available > 0  ORDER BY id DESC";
            	ResultSet rs=st.executeQuery(query);
            	while(rs.next())
            	{
            %>
            
	             <div class="list-group-item checkbox">
	                  <label><input type="checkbox" class="common_selector brand" value="<%=rs.getString("brand") %>"  ><%=rs.getString("brand") %></label>
	             </div>
	        <%
            	}
            	rs.close();
	        %>
             </div>
         </div>
         <div class="list-group">
     		<h3>Color</h3>
            <div style="height: 180px; overflow-y: auto; overflow-x: hidden;">
             <%
            	String query1="SELECT DISTINCT(color) FROM product WHERE category='"+cid+"' and available > 0  ORDER BY id DESC";
            	ResultSet rs1=st.executeQuery(query1);
            	while(rs1.next())
            	{
            	%>
	             <div class="list-group-item checkbox">
	                <label><input type="checkbox" class="common_selector color" value="<%=rs1.getString("color") %>"  ><%=rs1.getString("color") %></label>
	             </div>
	             <%
            		}
            		rs1.close();
	        	%>
             </div>
         </div>    
    <div class="row row-cols-1 row-cols-md-3 filter_data" style="margin:0px auto;" >     
		
	</div>
</div>
<script>
$(document).ready(function(){

  filter_data();
    function filter_data()
    {
        
        var brand = get_filter('brand');
        var color = get_filter('color');
        $.ajax({
            url:"/shop/fetch_data.jsp?cid="+<%=cid%>+"&brand="+brand+"&color="+color+"",
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