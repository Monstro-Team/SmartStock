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

@WebServlet(urlPatterns = {"/StockAlertServlet"},
initParams = {
		@WebInitParam(name = "products", value = ""),
})
public class StockAlertServlet extends HttpServlet {

	private static final long serialVersionUID = 6248157882432345603L;
	private ProductDAO productDAO;
	private StockDAO stockDAO;
	
	protected void service (HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
		ArrayList<Product> products = new ArrayList<Product>();
		int total;
		try {
			productDAO = new ProductDAO();
			stockDAO = new StockDAO();
			
			for(Product product: productDAO.getAllProducts()){
				total =0;
				for(Stock stock: stockDAO.getAllStock()){
					if(stock.getIdProduct() == product.getId()){
						total = total + stock.getQuantity();
					}
				}
				if(total < product.getQuantityMin()){
					product.setQuantity(total);
					products.add(product);
				}
			}
		} catch (SQLException e) {
			request.setAttribute("error", "Erro no banco de dados!");
		}
		request.setAttribute("products", products);
		RequestDispatcher rd = 
        request.getRequestDispatcher("/StockAlert.jsp");
    	rd.forward(request,response);
	}
}
