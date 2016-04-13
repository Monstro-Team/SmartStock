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

@WebServlet(urlPatterns = {"/ProductDescriptionServlet"},
	initParams = {
			@WebInitParam(name = "product_id", value = ""),
			@WebInitParam(name = "product_name", value = ""),
			@WebInitParam(name = "product_description", value = ""),
			@WebInitParam(name = "product_quantity_min", value = ""),
			@WebInitParam(name = "product_location", value = ""),
	})
public class ProductDescriptionServlet extends HttpServlet {
	
	private int product_id;
	private Product product;
	
	protected void service (HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
		product_id = Integer.parseInt(request.getParameter("product_id"));
		
		ProductDAO productDAO;
		try {
			productDAO = new ProductDAO();
			product = productDAO.getProduct(product_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}

    	request.setAttribute("product_name", product.getName());
    	request.setAttribute("product_description", product.getDescription());
    	request.setAttribute("product_quantity_min", product.getQuantityMin());
    	request.setAttribute("product_location", product.getLocation());

    	RequestDispatcher rd = 
        request.getRequestDispatcher("/ProductDescription.jsp");
    	rd.forward(request,response);
	}
}
