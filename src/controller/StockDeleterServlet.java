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
import dao.ProviderDAO;
import dao.StockDAO;
import model.Product;
import model.Provider;
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
		ProductDAO productDAO = null;
		ProviderDAO providerDAO = null;
		Provider provider = null;
		Product product = null;
		boolean stockModified = true;
		if (stock_deleter != null)
			if (stock_deleter.length() != 0) {
				if (stock_deleter.equals("true")) {
					try {
						stockDAO = new StockDAO();
						stockModified = stockDAO.getStock(stock_id).isModified();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						if(stockModified != true)
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
			providerDAO = new ProviderDAO();
			stock = stockDAO.getStock(stock_id);
			productDAO = new ProductDAO();
			product = productDAO.getProduct(stock.getIdProduct());
			provider = providerDAO.getProvider(Integer.parseInt(stock.getSupplier()));
			stockModified = stock.isModified();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(stockModified){
			request.setAttribute("error", "O estoque já foi movimentado, não pode ser deletado." );
		}
		request.setAttribute("provider", provider.getCompany()+", "+provider.getSalesman() );
		request.setAttribute("product_name", product.getName());
    	request.setAttribute("product_description", product.getDescription());
    	request.setAttribute("product_quantity_min", product.getQuantityMin());
    	request.setAttribute("product_location", product.getLocation());
		request.setAttribute("stock_id", stock.getId());
		request.setAttribute("stock_id_product", stock.getIdProduct());
		request.setAttribute("stock_price", stock.getPrice());
		request.setAttribute("stock_quantity", stock.getQuantity());
		request.setAttribute("stock_supplier", stock.getSupplier());
		
		RequestDispatcher rd = request.getRequestDispatcher("/StockDeleter.jsp");
		rd.forward(request, response);
	}

}