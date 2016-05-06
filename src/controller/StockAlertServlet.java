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
import model.Product;

@WebServlet(urlPatterns = {"/StockAlertServlet"},
initParams = {
		@WebInitParam(name = "products", value = ""),
})
public class StockAlertServlet extends HttpServlet {

	private static final long serialVersionUID = 6248157882432345603L;
	private ProductDAO productDAO;
	
	protected void service (HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
		ArrayList<Product> products = new ArrayList<Product>();

		try {
			productDAO = new ProductDAO();
			products = productDAO.getAllProductsLowInStock();
		} catch (SQLException e) {
			request.setAttribute("error", "Erro no banco de dados!");
		}
		request.setAttribute("products", products);
		RequestDispatcher rd = 
        request.getRequestDispatcher("/StockAlert.jsp");
    	rd.forward(request,response);
	}
}
