package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        
        String productName = request.getParameter("product_name");
        String productDescription = request.getParameter("product_description");
        String productSpupplier = request.getParameter("product_supplier");
        String productQuantity = request.getParameter("product_quantity");
        String productQuantityMin = request.getParameter("product_quantity_min");
        String productLocation = request.getParameter("product_location");
        String productPrice = request.getParameter("product_price");
        
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Cadastro de Produto</title>");
        out.println("</head>");
        out.println("<body>");
        out.println(productName+"<br>");
        out.println(productDescription+"<br>");
        out.println(productSpupplier+"<br>");
        out.println(productQuantity+"<br>");
        out.println(productQuantityMin+"<br>");
        out.println(productLocation+"<br>");
        out.println(productPrice+"<br>");
        out.println("</body>");
        out.println("</html>");
    }
}
