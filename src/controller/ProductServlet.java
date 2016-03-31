package controller;

import services.Validator;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Product;
@WebServlet( urlPatterns = {"/ProductServlet"},
		initParams = {
				@WebInitParam(name = "product_name", value = ""),
				@WebInitParam(name = "product_description", value = ""),
				@WebInitParam(name = "product_supplier", value = ""),
				@WebInitParam(name = "product_quantity", value = ""),
				@WebInitParam(name = "product_quantity_min", value = ""),
				@WebInitParam(name = "product_location", value = ""),
				@WebInitParam(name = "product_price", value = "")
		})


public class ProductServlet extends HttpServlet  {
    protected void service (HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Validator validator = new Validator();
        
        
        String productName = request.getParameter("product_name");
        String productDescription = request.getParameter("product_description");
        String productSpupplier = request.getParameter("product_supplier");
        String productQuantity = request.getParameter("product_quantity");
        String productQuantityMin = request.getParameter("product_quantity_min");
        String productLocation = request.getParameter("product_location");
        String productPrice = request.getParameter("product_price");
        
        if(productName != null){
	        String resultValidation = Validator.validadeIsProductCorrect(productName, productDescription,
	        		productLocation, productQuantity, productQuantityMin, productSpupplier, productPrice);
	        
	        if(resultValidation.length() == 0){
	        	Product product = new Product(productName, productDescription, productLocation, 
	        			Integer.valueOf(productQuantityMin).intValue(), Integer.valueOf(productQuantity).intValue(),
	        			productSpupplier, Float.valueOf(productPrice).floatValue());
	            out.println("<html>");
	            out.println("<head>");
	            out.println("<title>Cadastro de Produto</title>");
	            out.println("</head>");
	            out.println("<body>");
	            out.println(product.getName()+"<br>");
	            out.println(product.getDescription()+"<br>");
	            out.println(product.getSupplier()+"<br>");
	            out.println(product.getQuantity()+"<br>");
	            out.println(product.getQuantityMin()+"<br>");
	            out.println(product.getLocation()+"<br>");
	            out.println(product.getPrice()+"<br>");
	            out.println("</body>");
	            out.println("</html>");
	        }
	        else{
	            out.println("<html>");
	            out.println("<head>");
	            out.println("<title>Cadastro de Produto</title>");
	            out.println("</head>");
	            out.println("<body>");
	            out.println("ERRO: <br>"+ resultValidation);
	            out.println("</body>");
	            out.println("</html>");
	            return ;
	        }
        }
        else{
        	out.println("formulario");
        }

    }
}
