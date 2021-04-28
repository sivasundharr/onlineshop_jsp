<nav>
            <ul>
                <li class="right">
                    <i class="fa fa-bars" style="font-size:24px;color:white;" id="bar"></i>
                </li>
               
                <li class="right">
                    <a href="<%=request.getContextPath()%>/" >Home</a>
                </li>
                <%
					if((request.getSession(false).getAttribute("Admin")!= null) )
					{
				%>
                <li class="right">
                	<a href="<%=request.getContextPath()%>/new">New</a>
                </li>
               <% } %>
                
                <% 
				if((request.getSession(false).getAttribute("username")== null) )
				{
				%>
                 <li>
                    <a href="<%=request.getContextPath()%>/login.jsp">Login</a>
                </li>
                <% }else{ %>
                <li>
                    <a href="<%=request.getContextPath()%>/LogoutServlet">Logout</a>
                </li>
                <% } %>
                
            </ul>
        </nav>
        
   <div class="sider">
       <i id="closeicon" class="fa fa-close" style="font-size:24px"></i>
		<% 
				if((request.getSession(false).getAttribute("username")!= null) )
				{
					
				
		%>
       		<a href="<%=request.getContextPath()%>/profile.jsp">
       			<img src="<%=request.getContextPath() %>/FileDisplay?filename=profile.png" width="35" height="35"/>
       			<%=request.getSession(false).getAttribute("username")%>
       		</a>
       	<%} %>
       <a href="#">About</a>
       
       <%
		if((request.getSession(false).getAttribute("Admin")== null) )
		{
		%>
       <a href="#">Your Cart</a>
       <a href="#">Your Orders</a>
       <%} %>
       
   </div>
   
