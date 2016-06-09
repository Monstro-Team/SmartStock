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
import dao.TransactionDAO;
import model.Product;
import model.Stock;
import model.Transaction;
@WebServlet(urlPatterns = {"/TransactionListServlet"},
initParams = {
		@WebInitParam(name = "list_product", value = "")
})
public class TransactionListServlet extends HttpServlet  {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2784450424999747864L;
	private TransactionDAO transactionDAO;
	private ProductDAO productDAO;
	private StockDAO stockDAO;

	protected void service (HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
		ArrayList<Product> products = new ArrayList<Product>();
		ArrayList<Transaction> transactions = new ArrayList<Transaction>();
		ArrayList<Stock> stocks = new ArrayList<Stock>();
		
		try {
			stockDAO = new StockDAO();
			productDAO = new ProductDAO();
			products = productDAO.getAllProducts();
			transactionDAO = new TransactionDAO();
			transactions = transactionDAO.getAllTransaction();
			stocks = stockDAO.getAllStock();
			
		} catch (SQLException e) {
			request.setAttribute("error", "Erro no banco de dados!");
		}
		request.setAttribute("products", products);
		request.setAttribute("transactions", transactions);
		request.setAttribute("stocks", stocks);
		RequestDispatcher rd = 
        request.getRequestDispatcher("/TransactionList.jsp");
    	rd.forward(request,response);
	}
}
