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
		@WebInitParam(name = "product_supplier", value = ""),
		@WebInitParam(name = "product_quantity", value = ""),
		@WebInitParam(name = "product_quantity_min", value = ""),
		@WebInitParam(name = "product_location", value = ""),
		@WebInitParam(name = "product_price", value = "")
})
public class ProductEditorServlet extends HttpServlet{
	protected void service (HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        Validator validator = new Validator();
        ProductDAO productDAO = null;
        Product product = null;
        String resultValidation = null;
        
		try {
			productDAO = new ProductDAO();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        String productId = request.getParameter("product_id");
        String productName = request.getParameter("product_name");
        String productDescription = request.getParameter("product_description");
        String productSpupplier = request.getParameter("product_supplier");
        String productQuantity = request.getParameter("product_quantity");
        String productQuantityMin = request.getParameter("product_quantity_min");
        String productLocation = request.getParameter("product_location");
        String productPrice = request.getParameter("product_price");
        
	    if(productName != null){
	        		resultValidation = Validator.validadeIsProductCorrect(productName, productDescription,
	        				productLocation, productQuantity, productQuantityMin, productSpupplier, productPrice);
		    if(resultValidation.length() == 0 ){
		        	product = new Product(productName, productDescription, productLocation, 
		        		Integer.valueOf(productQuantityMin).intValue(), Integer.valueOf(productQuantity).intValue(),
		        		productSpupplier, Float.valueOf(productPrice).floatValue());
		        	product.setId(Integer.parseInt(productId));
		        	try {
						productDAO.updateProduct(product);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        	request.setAttribute("product_id", productId);
		        	request.setAttribute("product_name", productName);
		        	request.setAttribute("product_description", productDescription);
		        	request.setAttribute("product_supplier", productSpupplier);
		        	request.setAttribute("product_quantity", productQuantity);
		        	request.setAttribute("product_quantity_min", productQuantityMin);
		        	request.setAttribute("product_location", productLocation);
		        	request.setAttribute("product_price", productPrice);
		        	RequestDispatcher rd = 
			        request.getRequestDispatcher("/ProductDescription.jsp");
		        	rd.forward(request,response);
		    }
		    else{
		    	request.setAttribute("product_id", productId);
	        	request.setAttribute("product_name", productName);
	        	request.setAttribute("product_description", productDescription);
	        	request.setAttribute("product_supplier", productSpupplier);
	        	request.setAttribute("product_quantity", productQuantity);
	        	request.setAttribute("product_quantity_min", productQuantityMin);
	        	request.setAttribute("product_location", productLocation);
	        	request.setAttribute("product_price", productPrice);
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	request.setAttribute("product_id", product.getId());
        	request.setAttribute("product_name", product.getName());
        	request.setAttribute("product_description", product.getDescription());
        	request.setAttribute("product_supplier", product.getSupplier());
        	request.setAttribute("product_quantity", product.getQuantity());
        	request.setAttribute("product_quantity_min", product.getQuantityMin());
        	request.setAttribute("product_location", product.getLocation());
        	request.setAttribute("product_price", product.getPrice());
        	RequestDispatcher rd = 
	        request.getRequestDispatcher("/ProductEditor.jsp");
        	rd.forward(request,response);
	    }
	}
}

