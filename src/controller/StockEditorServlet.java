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
import services.Validator;

@WebServlet(urlPatterns = {"/StockEditorServlet"},
initParams = {
		@WebInitParam(name = "product_id", value = ""),
		@WebInitParam(name = "stock_id", value = ""),
		@WebInitParam(name = "stock_price", value = ""),
		@WebInitParam(name = "stock_quantity", value = ""),
		@WebInitParam(name = "stock_supplier", value = ""),
		@WebInitParam(name = "error", value = ""),
		@WebInitParam(name = "info", value = "")
})
public class StockEditorServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 192677537272317796L;

	protected void service (HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
		StockDAO stockDAO = null;
		
        String productId = request.getParameter("product_id");
        String stockId = request.getParameter("stock_id");
        String stockPrice = request.getParameter("stock_price");
        String stockQuantity = request.getParameter("stock_quantity");
        String stockSupplier = request.getParameter("stock_supplier");
        String resultValidation = null;
		
		try {
			stockDAO = new StockDAO();
		} catch (SQLException e1) {
			request.setAttribute("error", "Erro no banco de dados!"+e1);

        	RequestDispatcher rd = 
	        request.getRequestDispatcher("/StockEditor.jsp");
        	rd.forward(request,response);
		}
		if(productId != null){
			resultValidation = Validator.validadeIsStockCorrect(stockSupplier,stockQuantity, stockPrice);
			if(resultValidation.length() == 0 ){
				Stock stock = new Stock(Integer.parseInt(stockId),Integer.parseInt(productId),Integer.parseInt(stockQuantity), stockSupplier, Float.parseFloat(stockPrice));
	        	try {
					stockDAO.updateStock(stock);
				} catch (SQLException e) {
					request.setAttribute("error", "Erro no banco de dados!");
		        	request.setAttribute("product_id", productId);
		        	request.setAttribute("stock_id", stockId);
		        	request.setAttribute("stock_quantity",stockQuantity);
		        	request.setAttribute("stock_supplier", stockSupplier);
		        	request.setAttribute("stock_price", stockPrice);
		        	RequestDispatcher rd = 
			        request.getRequestDispatcher("/StockEditor.jsp");
		        	rd.forward(request,response);
				}
	        	request.setAttribute("info", "Edição feita com sucesso!");

	        	RequestDispatcher rd = 
		        request.getRequestDispatcher("/ProductList.jsp");
	        	rd.forward(request,response);
			}
		}
		else{
	    	try {
				Stock stock = stockDAO.getStock(Integer.parseInt(stockId));
	        	request.setAttribute("product_id", stock.getIdProduct());
	        	request.setAttribute("stock_id", stock.getId());
	        	request.setAttribute("stock_quantity",stock.getQuantity());
	        	request.setAttribute("stock_supplier", stock.getSupplier());
	        	request.setAttribute("stock_price", stock.getPrice());
	        	RequestDispatcher rd = 
		        request.getRequestDispatcher("/StockEditor.jsp");
	        	rd.forward(request,response);
			} catch (NumberFormatException | SQLException e) {
        		request.setAttribute("error", "Ocorreu um erro no banco de dados."+ e);
            	RequestDispatcher rd = 
            	        request.getRequestDispatcher("/StockEditor.jsp");
                    	rd.forward(request,response);
			}

			
		}
        
	}
}