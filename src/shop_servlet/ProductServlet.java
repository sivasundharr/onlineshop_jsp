package shop_servlet;


import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import shop_DB.ProductModel;
import shop_product.Product;

@WebServlet("/")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, 
maxFileSize = 1024 * 1024 * 10, 
maxRequestSize = 1024 * 1024 * 50)
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductModel productModel;
	public String newFile;
	public static final String UPLOAD_DIR = "images";
    public String dbFileName = "";
    
    public ProductServlet() {
    	productModel = new ProductModel();
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    		doGet(request,response);
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/new":
				showNewForm(request,response);
				break;
				
			case "/detail":
				productDetail(request,response);
				break;
				
			case "/product":
				listIndividual(request,response);
				break;
				
			case "/insert":
				createProduct(request,response);
				break;
				
			case "/edit":
				
				break;
			case "/update":
				
				break;
			default:
				listProduct(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	
	
	
	private void listProduct(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Product> listProduct = productModel.selectAllProducts();
		
		 
		request.setAttribute("listProduct", listProduct);
		RequestDispatcher dispatcher = request.getRequestDispatcher("product_list.jsp");
		dispatcher.forward(request, response);
	}
	
	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("product_form.jsp");
		dispatcher.forward(request, response);
	}
	
	private void createProduct(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException,ServletException {
		int available=1,category=0;
		float price=1;
		Part part = request.getPart("image");
		
		String fileName = extractFileName(part);
        
        String applicationPath = getServletContext().getRealPath("");
        String uploadPath = applicationPath + File.separator + UPLOAD_DIR;
        System.out.println("applicationPath:" + applicationPath);
        File fileUploadDirectory = new File(uploadPath);
        if (!fileUploadDirectory.exists()) {
            fileUploadDirectory.mkdirs();
        }
        String savePath = uploadPath + File.separator + fileName;
        System.out.println("savePath: " + savePath);
        String sRootPath = new File(savePath).getAbsolutePath();
        System.out.println("sRootPath: " + sRootPath);
        part.write(savePath + File.separator);
        File fileSaveDir1 = new File(savePath);
        
        dbFileName = UPLOAD_DIR + File.separator + fileName;
        part.write(savePath + File.separator);

		System.out.println(fileSaveDir1);
		String name = request.getParameter("product_name");
		String cate = request.getParameter("category");
		category = Integer.parseInt(cate);
		
		String avil1 = request.getParameter("avilable");
		if(avil1!=null) {
		available =Integer.parseInt(avil1);
		}
		String pri1 = request.getParameter("price");
		if(pri1!=null) {
		price =Integer.parseInt(pri1);
		}
		String specifications = request.getParameter("specifications");
		String seller = request.getParameter("seller");
		String color = request.getParameter("color");
		String brand = request.getParameter("brand");
		
		Product newproduct = new Product(name, category,available,price,sRootPath,specifications,seller,color,brand);
		productModel.insertProduct(newproduct);
		response.sendRedirect("list");
	}
	
	private void productDetail(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, ServletException
	{
		int id = Integer.parseInt(request.getParameter("id"));
		Product existingProduct = productModel.selectProduct(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("details.jsp");
		request.setAttribute("product", existingProduct);
		dispatcher.forward(request, response);
	}
	
	private void listIndividual(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, ServletException
	{
		String product = request.getParameter("product");
		String cate = request.getParameter("cid");
		int cid = Integer.parseInt(cate);
		List<Product> listIndividual = productModel.selectEachCategory(product,cid);
		request.setAttribute("listProduct", listIndividual);
		RequestDispatcher dispatcher = request.getRequestDispatcher("product_category.jsp");
		dispatcher.forward(request, response);
	}
	
	
	
	private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }
	
}
