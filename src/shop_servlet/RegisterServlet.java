package shop_servlet;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import shop_DB.RegisterModel;
import shop_product.Register;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RegisterModel registerModel;
    
    public RegisterServlet() {
        super();
        
    }
    
    public void init() {
      registerModel =new RegisterModel();
    }

    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("emailId");
		String mobile = request.getParameter("mobile");
		
		String address1 = request.getParameter("address1");
		String address2 = request.getParameter("address2");
		
		
			Register reg = new Register();
			reg.setUsername(username);
			reg.setPassword(password);
			reg.setEmail(email);
			reg.setMobile(mobile);
			reg.setAddress1(address1);
			reg.setAddress2(address2);
			
			try {
				registerModel.CreateNewUser(reg);
				
	        } catch (Exception e) {
	            
	            e.printStackTrace();
	        }
			
			response.sendRedirect("login.jsp");
	}

}
