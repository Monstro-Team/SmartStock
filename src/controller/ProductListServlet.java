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

import model.Product;
import dao.ProductDAO;

@WebServlet(urlPatterns = {"/ProductListServlet"},
initParams = {
		@WebInitParam(name = "products", value = ""),
})
public class ProductListServlet extends HttpServlet  {

	private static final long serialVersionUID = -4429371055509587937L;
	private ProductDAO productDAO;

	protected void service (HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
		ArrayList<Product> products = new ArrayList<Product>();

		try {
			productDAO = new ProductDAO();
			products = productDAO.getAllProducts();
		} catch (SQLException e) {
			request.setAttribute("error", "Erro no banco de dados!");
		}
		
		request.setAttribute("products", products);
		RequestDispatcher rd = request.getRequestDispatcher("/ProductList.jsp");
    	rd.forward(request,response);
	}
}
