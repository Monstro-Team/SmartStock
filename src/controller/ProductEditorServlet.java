package controller;

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
import services.Validator;

@WebServlet(urlPatterns = {"/ProductEditorServlet"},
initParams = {
		@WebInitParam(name = "product_id", value = ""),
		@WebInitParam(name = "product_name", value = ""),
		@WebInitParam(name = "product_description", value = ""),
		@WebInitParam(name = "product_quantity_min", value = ""),
		@WebInitParam(name = "product_location", value = ""),
		@WebInitParam(name = "info", value = "")
})
public class ProductEditorServlet extends HttpServlet{
	protected void service (HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        Validator validator = new Validator();
        ProductDAO productDAO = null;
        Product product = null;
        String resultValidation = null;
        
        String productId = request.getParameter("product_id");
        String productName = request.getParameter("product_name");
        String productDescription = request.getParameter("product_description");
        String productQuantityMin = request.getParameter("product_quantity_min");
        String productLocation = request.getParameter("product_location");
        
		try {
			productDAO = new ProductDAO();
		} catch (SQLException e1) {
			request.setAttribute("error", "Erro no banco de dados!");
        	request.setAttribute("product_name", productName);
        	request.setAttribute("product_description", productDescription);
        	request.setAttribute("product_quantity_min", productQuantityMin);
        	request.setAttribute("product_location", productLocation);
        	RequestDispatcher rd = 
	        request.getRequestDispatcher("/ProductEditor.jsp");
        	rd.forward(request,response);
		}
        
	    if(productName != null){
	        		resultValidation = Validator.validadeIsProductCorrect(productName, productDescription,
	        				productLocation, productQuantityMin);
		    if(resultValidation.length() == 0 ){
		        	product = new Product(productName, productDescription, productLocation, 
		        		Integer.valueOf(productQuantityMin).intValue());
		        	product.setId(Integer.parseInt(productId));
		        	try {
						productDAO.updateProduct(product);
					} catch (SQLException e) {
						request.setAttribute("error", "Erro no b50anco de dados!");
			        	request.setAttribute("product_name", productName);
			        	request.setAttribute("product_description", productDescription);
			        	request.setAttribute("product_quantity_min", productQuantityMin);
			        	request.setAttribute("product_location", productLocation);
			        	RequestDispatcher rd = 
				        request.getRequestDispatcher("/ProductEditor.jsp");
			        	rd.forward(request,response);
					}
		        	request.setAttribute("info", "Edição feita com sucesso!");

		        	RequestDispatcher rd = 
			        request.getRequestDispatcher("/ProductList.jsp");
		        	rd.forward(request,response);
		    }
		    else{
		    	request.setAttribute("product_id", productId);
	        	request.setAttribute("product_name", productName);
	        	request.setAttribute("product_description", productDescription);
	        	request.setAttribute("product_quantity_min", productQuantityMin);
	        	request.setAttribute("product_location", productLocation);
        		request.setAttribute("error", resultValidation);
            	RequestDispatcher rd = 
            	        request.getRequestDispatcher("/ProductEditor.jsp");
                    	rd.forward(request,response);
		    }
	    }
	    else{
	    	try {
				product = productDAO.getProduct(Integer.parseInt(productId));
			} catch (NumberFormatException | SQLException e) {
        		request.setAttribute("error", "Ocorreu um erro no banco de dados.");
            	RequestDispatcher rd = 
            	        request.getRequestDispatcher("/ProductEditor.jsp");
                    	rd.forward(request,response);
			}
	    	request.setAttribute("product_id", product.getId());
        	request.setAttribute("product_name", product.getName());
        	request.setAttribute("product_description", product.getDescription());
        	request.setAttribute("product_quantity_min", product.getQuantityMin());
        	request.setAttribute("product_location", product.getLocation());
        	RequestDispatcher rd = 
	        request.getRequestDispatcher("/ProductEditor.jsp");
        	rd.forward(request,response);
	    }
	}
}

