package controller;

import services.Validator;
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

@WebServlet( urlPatterns = {"/StockRegisterServlet"},
initParams = {
		@WebInitParam(name = "product_id", value = ""),
		@WebInitParam(name = "stock_price", value = ""),
		@WebInitParam(name = "stock_quantity", value = ""),
		@WebInitParam(name = "stock_supplier", value = ""),
		@WebInitParam(name = "error", value = ""),
		@WebInitParam(name = "info", value = "")
})
public class StockRegisterServlet extends HttpServlet  {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9092441279867110502L;

	protected void service (HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        String productId = request.getParameter("product_id");
        String stockPrice = request.getParameter("stock_price");
        String stockSupplier = request.getParameter("stock_supplier");
        String stockQuantity = request.getParameter("stock_quantity");
        
        if(productId != null){
	        String resultValidation = Validator.validadeIsStockCorrect(stockSupplier,stockQuantity,stockPrice);
	        
	        if(resultValidation.length() == 0){
	        	Stock stock = new Stock(Integer.parseInt(productId),Integer.parseInt(stockQuantity), stockSupplier,Float.parseFloat(stockPrice));
	        	
	        	try {
	        		StockDAO stockDAO = new StockDAO();
	        		stockDAO.includeStock(stock);
				} catch (SQLException e) {
		        	request.setAttribute("error", "Erro no banco de dados!");
		        	request.setAttribute("product_id", productId);
		        	request.setAttribute("product_price", stockPrice);
		        	request.setAttribute("product_quantity", stockQuantity);
		        	request.setAttribute("product_supplier", stockSupplier);
		        	RequestDispatcher rd = 
			        request.getRequestDispatcher("/StockRegister.jsp");
		        	rd.forward(request,response);
				}
	        	
	        	request.setAttribute("info", "Estoque cadastrado com sucesso!");
	        	RequestDispatcher rd = 
		        request.getRequestDispatcher("/ProductList.jsp");
	        	rd.forward(request,response);
	        }
	        else{
	        	request.setAttribute("error", resultValidation);
	        	request.setAttribute("product_id", productId);
	        	request.setAttribute("product_price", stockPrice);
	        	request.setAttribute("product_quantity", stockQuantity);
	        	request.setAttribute("product_supplier", stockSupplier);
	        	RequestDispatcher rd = 
		        request.getRequestDispatcher("/StockRegister.jsp");
	        	rd.forward(request,response);
	        }
        }
        else{
        	ArrayList<Product> products = new ArrayList<Product>();
        	try {
				ProductDAO productDAO = new ProductDAO();
				products = productDAO.getAllProducts();
			} catch (SQLException e) {
				request.setAttribute("error", "Erro no banco de dados!");
			}
        	request.setAttribute("products", products);
        	RequestDispatcher rd = 
	        request.getRequestDispatcher("/StockRegister.jsp");
        	rd.forward(request,response);
        }
    }
}