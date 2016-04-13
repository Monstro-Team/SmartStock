package controller;

import services.Validator;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductDAO;
import model.Product;
@WebServlet( urlPatterns = {"/ProductRegisterServlet"},
		initParams = {
				@WebInitParam(name = "product_name", value = ""),
				@WebInitParam(name = "product_description", value = ""),
				@WebInitParam(name = "product_supplier", value = ""),
				@WebInitParam(name = "product_quantity", value = ""),
				@WebInitParam(name = "product_quantity_min", value = ""),
				@WebInitParam(name = "product_location", value = ""),
				@WebInitParam(name = "product_price", value = ""),
				@WebInitParam(name = "error", value = ""),
				@WebInitParam(name = "info", value = "")
		})


public class ProductRegisterServlet extends HttpServlet  {

	private static final long serialVersionUID = -3509054968041169215L;

	protected void service (HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        Validator validator = new Validator();
        
        
        String productName = request.getParameter("product_name");
        String productDescription = request.getParameter("product_description");
        String productSpupplier = request.getParameter("product_supplier");
        String productQuantity = request.getParameter("product_quantity");
        String productQuantityMin = request.getParameter("product_quantity_min");
        String productLocation = request.getParameter("product_location");
        String productPrice = request.getParameter("product_price");
        
        if(productName != null){
	        String resultValidation = Validator.validadeIsProductCorrect(productName, productDescription,
	        		productLocation, productQuantity, productQuantityMin, productSpupplier, productPrice);
	        
	        if(resultValidation.length() == 0){
	        	Product product = new Product(productName, productDescription, productLocation, 
	        			Integer.valueOf(productQuantityMin).intValue(), Integer.valueOf(productQuantity).intValue(),
	        			productSpupplier, Float.valueOf(productPrice).floatValue());
	        	
	        	try {
	        		ProductDAO productDAO = new ProductDAO();
					productDAO.includeProduct(product);
				} catch (SQLException e) {
		        	request.setAttribute("error", "Erro no banco de dados!");
		        	request.setAttribute("product_name", productName);
		        	request.setAttribute("product_description", productDescription);
		        	request.setAttribute("product_supplier", productSpupplier);
		        	request.setAttribute("product_quantity", productQuantity);
		        	request.setAttribute("product_quantity_min", productQuantityMin);
		        	request.setAttribute("product_location", productLocation);
		        	request.setAttribute("product_price", productPrice);
		        	RequestDispatcher rd = 
			        request.getRequestDispatcher("/ProductRegister.jsp");
		        	rd.forward(request,response);
				}
	        	
	        	/*request.setAttribute("product_name", productName);
	        	request.setAttribute("product_description", productDescription);
	        	request.setAttribute("product_supplier", productSpupplier);
	        	request.setAttribute("product_quantity", productQuantity);
	        	request.setAttribute("product_quantity_min", productQuantityMin);
	        	request.setAttribute("product_location", productLocation);
	        	request.setAttribute("product_price", productPrice);*/
	        	request.setAttribute("info", "Produto cadastrado com sucesso!");
	        	RequestDispatcher rd = 
		        request.getRequestDispatcher("/ProductList.jsp");
	        	rd.forward(request,response);
	        }
	        else{
	        	request.setAttribute("error", resultValidation);
	        	request.setAttribute("product_name", productName);
	        	request.setAttribute("product_description", productDescription);
	        	request.setAttribute("product_supplier", productSpupplier);
	        	request.setAttribute("product_quantity", productQuantity);
	        	request.setAttribute("product_quantity_min", productQuantityMin);
	        	request.setAttribute("product_location", productLocation);
	        	request.setAttribute("product_price", productPrice);
	        	RequestDispatcher rd = 
		        request.getRequestDispatcher("/ProductRegister.jsp");
	        	rd.forward(request,response);
	        }
        }
        else{
        	RequestDispatcher rd = 
	        request.getRequestDispatcher("/ProductRegister.jsp");
        	rd.forward(request,response);
        }

    }
}
