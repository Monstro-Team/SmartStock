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
import model.Key;
import model.Part;
import model.Product;

@WebServlet( urlPatterns = {"/ProductRegisterServlet"},
		initParams = {
			@WebInitParam(name = "product_name", value = ""),
			@WebInitParam(name = "product_description", value = ""),
			@WebInitParam(name = "product_quantity_min", value = ""),
			@WebInitParam(name = "product_location", value = ""),
			@WebInitParam(name = "product_type", value = ""),
			@WebInitParam(name = "product_brand", value = ""),
			@WebInitParam(name = "product_car_model", value = ""),
			@WebInitParam(name = "error", value = ""),
			@WebInitParam(name = "info", value = "")
		})
public class ProductRegisterServlet extends HttpServlet  {

	private static final long serialVersionUID = -3509054968041169215L;

	protected void service (HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        String productName = request.getParameter("product_name");
        String productDescription = request.getParameter("product_description");
        String productQuantityMin = request.getParameter("product_quantity_min");
        String productLocation = request.getParameter("product_location");
        String productType = request.getParameter("product_type");
        String productBrand = request.getParameter("product_brand");
        String productCarModel = request.getParameter("product_car_model");
        
        if(productName != null) {
	        String resultValidation = Validator.validadeIsProductCorrect(productName, productDescription,
	        		productLocation, productQuantityMin);
	        
	        if(resultValidation.length() == 0) {
	        	try {
	        		Product product;
	        		ProductDAO productDAO = new ProductDAO();
	        		
	        		if(productType.equals("key")) {
	        			product = new Key(productName, productDescription, productLocation, 
	    	        			Integer.valueOf(productQuantityMin).intValue());

	        			productDAO.includeProduct((Key) product);
	        		}
	        		else {
	        			product = new Part(productName, productDescription, productLocation, 
	    	        			Integer.valueOf(productQuantityMin).intValue(), productBrand, productCarModel);

	        			productDAO.includeProduct((Part) product);
	        		}
				} catch (SQLException e) {
		        	request.setAttribute("error", "Erro no banco de dados!");
		        	request.setAttribute("product_name", productName);
		        	request.setAttribute("product_description", productDescription);
		        	request.setAttribute("product_quantity_min", productQuantityMin);
		        	request.setAttribute("product_location", productLocation);
		        	
		        	RequestDispatcher rd = request.getRequestDispatcher("/ProductRegister.jsp");
		        	rd.forward(request,response);
				}
	        	
	        	request.setAttribute("info", "Produto cadastrado com sucesso!");
	        	RequestDispatcher rd = request.getRequestDispatcher("/ProductList.jsp");
	        	rd.forward(request, response);
	        }
	        else{
	        	request.setAttribute("error", resultValidation);
	        	request.setAttribute("product_name", productName);
	        	request.setAttribute("product_description", productDescription);
	        	request.setAttribute("product_quantity_min", productQuantityMin);
	        	request.setAttribute("product_location", productLocation);
	        	request.setAttribute("product_type", productType);
	        	
	        	RequestDispatcher rd = request.getRequestDispatcher("/ProductRegister.jsp");
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
