package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductDAO;
import dao.StockDAO;
import model.Product;
import model.Stock;

@WebServlet(urlPatterns = {"/ProductDescriptionServlet"},
	initParams = {
			@WebInitParam(name = "product_id", value = ""),
			@WebInitParam(name = "error", value = ""),
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
		StockDAO stockDAO;
		ArrayList<Stock> stocks = null;
		try {
			productDAO = new ProductDAO();
			stockDAO = new StockDAO();
			product = productDAO.getProduct(product_id);
			stocks = stockDAO.getAllStockByProductId(product_id);
		} catch (SQLException e) {
			request.setAttribute("error", "Erro no banco de dados!");
		}
		
    	request.setAttribute("product_name", product.getName());
    	request.setAttribute("product_description", product.getDescription());
    	request.setAttribute("product_quantity_min", product.getQuantityMin());
    	request.setAttribute("product_location", product.getLocation());
    	request.setAttribute("stocks", stocks);
    	RequestDispatcher rd = 
        request.getRequestDispatcher("/ProductDescription.jsp");
    	rd.forward(request,response);
	}
}
