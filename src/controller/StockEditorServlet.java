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
import dao.ProviderDAO;
import dao.StockDAO;
import model.Product;
import model.Provider;
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
        boolean stockModfied = true;
    	ArrayList<Provider> providers = new ArrayList<Provider>();
		
		try {
			stockDAO = new StockDAO();
			ProviderDAO providerDAO = new ProviderDAO();
			providers = providerDAO.getAllProviders();
			stockModfied = stockDAO.getStock(Integer.parseInt(stockId)).isModified();
		} catch (SQLException e1) {
			request.setAttribute("error", "Erro no banco de dados!"+e1);

        	RequestDispatcher rd = 
	        request.getRequestDispatcher("/StockEditor.jsp");
        	rd.forward(request,response);
		}
		if(productId != null){
			resultValidation = Validator.validadeIsStockCorrect(stockSupplier,stockQuantity, stockPrice,stockModfied);
			
			if(resultValidation.length() == 0 ){
				System.out.println("entrou"+resultValidation);
				Stock stock = new Stock(Integer.parseInt(stockId),Integer.parseInt(productId),Integer.parseInt(stockQuantity), stockSupplier, Float.parseFloat(stockPrice),false);
	        	try {
					stockDAO.updateStock(stock);
				} catch (SQLException e) {
					request.setAttribute("error", "Erro no banco de dados!");
		        	ProductDAO productDAO = null;
					try {
						productDAO = new ProductDAO();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		        	Product product = new Product();
		        	try {
						productDAO.getProduct(stock.getIdProduct());
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		        	request.setAttribute("providers", providers);
		        	request.setAttribute("product_name", product.getName());
		        	request.setAttribute("product_description", product.getDescription());
		        	request.setAttribute("product_quantity_min", product.getQuantityMin());
		        	request.setAttribute("product_location", product.getLocation());
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
	        	ProductDAO productDAO = new ProductDAO();
	        	Product product = new Product();
	    		if(stock.isModified()){
	    			request.setAttribute("error", "O estoque já foi movimentado, não pode ser editado." );
	    		}
	        	product = productDAO.getProduct(stock.getIdProduct());
	        	request.setAttribute("providers", providers);
	        	request.setAttribute("product_id", product.getId());
	        	request.setAttribute("product_name", product.getName());
	        	request.setAttribute("product_description", product.getDescription());
	        	request.setAttribute("product_quantity_min", product.getQuantityMin());
	        	request.setAttribute("product_location", product.getLocation());
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
