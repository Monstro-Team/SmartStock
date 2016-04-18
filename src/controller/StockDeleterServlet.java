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

import dao.StockDAO;
import model.Stock;

@WebServlet(urlPatterns = { "/StockDeleterServlet" }, initParams = { @WebInitParam(name = "stock_id", value = ""),
		@WebInitParam(name = "stock_deleter", value = "") })
public class StockDeleterServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6515063608857757209L;
	private int stock_id;
	private Stock stock;
	private String stock_deleter;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		stock_id = Integer.parseInt(request.getParameter("stock_id"));
		stock_deleter = request.getParameter("stock_deleter");
		StockDAO stockDAO = null;
		if (stock_deleter != null)
			if (stock_deleter.length() != 0) {
				if (stock_deleter.equals("true")) {
					try {
						stockDAO = new StockDAO();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
							stockDAO.deleteStock(stock_id);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					RequestDispatcher rd = request.getRequestDispatcher("/StockList.jsp");
					rd.forward(request, response);
				}
			}

		try {
			stockDAO = new StockDAO();
			stock = stockDAO.getStock(stock_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("stock_id", stock.getId());
		request.setAttribute("stock_id_product", stock.getIdProduct());
		request.setAttribute("stock_price", stock.getPrice());
		request.setAttribute("stock_quantity", stock.getQuantity());
		request.setAttribute("stock_supplier", stock.getSupplier());
		
		RequestDispatcher rd = request.getRequestDispatcher("/StockDeleter.jsp");
		rd.forward(request, response);
	}

}