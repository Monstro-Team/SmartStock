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

@WebServlet(urlPatterns = {"/ProductDeleterServlet"},
initParams = {
		@WebInitParam(name = "product_id", value = ""),
		@WebInitParam(name = "product_name", value = ""),
		@WebInitParam(name = "product_description", value = ""),
		@WebInitParam(name = "product_quantity_min", value = ""),
		@WebInitParam(name = "product_location", value = ""),
		@WebInitParam(name = "product_deleter", value = "")
})

public class ProductDeleterServlet  extends HttpServlet {
		

	private static final long serialVersionUID = -4027275717854156243L;
	private int product_id;
	private Product product;
	private String product_deleter;
	
	protected void service (HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
		product_id = Integer.parseInt(request.getParameter("product_id"));
		product_deleter = request.getParameter("product_deleter");
		ProductDAO productDAO = null;
		if(product_deleter != null)	
		if(product_deleter.length() != 0){
			if(product_deleter.equals("true")){
				try {
					productDAO = new ProductDAO();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				try {
					productDAO.deleteProduct(product_id);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		    	RequestDispatcher rd = 
		    	        request.getRequestDispatcher("/ProductList.jsp");
		    	    	rd.forward(request,response);
			}
		}

		try {
			productDAO = new ProductDAO();
			product = productDAO.getProduct(product_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("product_id", product.getId());
    	request.setAttribute("product_name", product.getName());
    	request.setAttribute("product_description", product.getDescription());
    	request.setAttribute("product_quantity_min", product.getQuantityMin());
    	request.setAttribute("product_location", product.getLocation());

    	RequestDispatcher rd = 
        request.getRequestDispatcher("/ProductDeleter.jsp");
    	rd.forward(request,response);
	}
}
