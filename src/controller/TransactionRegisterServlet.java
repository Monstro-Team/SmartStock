package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import dao.TransactionDAO;
import model.Product;
import model.Stock;
import model.Transaction;
import services.Validator;

@WebServlet( urlPatterns = {"/TransactionRegisterServlet"},
initParams = {
		@WebInitParam(name = "list_product", value = ""),
		@WebInitParam(name = "stock_id", value = ""),
		@WebInitParam(name = "quantity_moved", value = ""),
		@WebInitParam(name = "transaction_type", value = ""),
		@WebInitParam(name = "error", value = ""),
		@WebInitParam(name = "info", value = "")
})

public class TransactionRegisterServlet extends HttpServlet{

	private static final long serialVersionUID = -6933661885239384226L;

	protected void service (HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
		StockDAO stockDAO = null;
		Stock stock = null;
        String productId = request.getParameter("product_id");
        String stockId = request.getParameter("stock_id");
        String productName = request.getParameter("product_name");
        String quantityMoved = request.getParameter("quantity_moved");
        String transactionType = request.getParameter("transaction_type");
        String productLocation = request.getParameter("product_location");
        int stockQuantity = 0;
        
        if(productName != null){
        	try {
        		stockDAO = new StockDAO();
				
				stock = stockDAO.getStock(Integer.parseInt(stockId));

				stockQuantity = stock.getQuantity();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
	        String resultValidation = Validator.validadeIsTransactionCorrect(stockId, quantityMoved,
	        		transactionType,stockQuantity);

	        if(resultValidation.length() == 0){
	        	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	        	Date date = new Date();

	        	Transaction transaction = new Transaction(Integer.valueOf(quantityMoved).intValue(),Integer.valueOf(stockId).intValue(),
	        			Integer.valueOf(transactionType).intValue(), dateFormat.format(date));
	        	
	        	try {
	        		TransactionDAO transactionDAO = new TransactionDAO();
					transactionDAO.includeTransaction(transaction);
					stock.setQuantity(stockQuantity-transaction.getQuantityMoved());
					stock.setModified(true);
					stockDAO.updateStock(stock);
					
				} catch (SQLException e) {
		        	request.setAttribute("error", "Erro no banco de dados!" +e);
		        	request.setAttribute("stock_id", stockId);
		        	request.setAttribute("product_id", productId);
		        	request.setAttribute("quantity_moved", quantityMoved);
		        	request.setAttribute("transaction_type", transactionType);
		        	request.setAttribute("product_location", productLocation);
		        	RequestDispatcher rd = 
			        request.getRequestDispatcher("/TransactionRegister.jsp");
		        	rd.forward(request,response);
				}
	        	
	        	request.setAttribute("info", "Produto cadastrado com sucesso!");
	        	RequestDispatcher rd = 
		        request.getRequestDispatcher("/ProductList.jsp");
	        	rd.forward(request,response);
	        }
	        else{
	        	request.setAttribute("error", resultValidation);
	        	request.setAttribute("product_id", productId);
	        	request.setAttribute("stock_id", stockId);
	        	request.setAttribute("quantity_moved", quantityMoved);
	        	request.setAttribute("transaction_type", transactionType);
	        	request.setAttribute("product_location", productLocation);
	        	RequestDispatcher rd = 
		        request.getRequestDispatcher("/TransactionRegister.jsp");
	        	rd.forward(request,response);
	        }
        }
        else{
        	try {
				ProductDAO productDAO = new ProductDAO();
				stockDAO = new StockDAO();
				ProviderDAO providerDAO = new ProviderDAO();
				
				Product product = productDAO.getProduct(Integer.parseInt(productId));
				stock = stockDAO.getStock(Integer.parseInt(stockId));
				model.Provider provider = providerDAO.getProvider(Integer.parseInt(stock.getSupplier()));
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
	        	request.setAttribute("product_id", productId);
		    			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        	RequestDispatcher rd = 
	        request.getRequestDispatcher("/TransactionRegister.jsp");
        	rd.forward(request,response);
        }
    }

}
