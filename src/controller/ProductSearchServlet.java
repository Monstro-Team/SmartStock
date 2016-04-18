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
	import model.Product;
	import dao.ProductDAO;


@WebServlet(urlPatterns = {"/ProductSearchServlet"},
initParams = {
		@WebInitParam(name = "products", value = ""),
		@WebInitParam(name = "product_search", value = "")
})
public class ProductSearchServlet extends HttpServlet  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8400241187685298364L;
	private ProductDAO productDAO;

	protected void service (HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
		ArrayList<Product> products = new ArrayList<Product>();
        String productSearch = request.getParameter("product_search");
		try {
			productDAO = new ProductDAO();
			products = productDAO.getAllProducts();
		} catch (SQLException e) {
    		request.setAttribute("error", "Ocorreu um erro no banco de dados!");
        	RequestDispatcher rd = 
        	        request.getRequestDispatcher("/ProductSearch.jsp");
                	rd.forward(request,response);
		}
		if(productSearch != null & productSearch.length() != 0){
			request.setAttribute("products", search(products,productSearch));
		if(search(products, productSearch).size() == 0)
    		request.setAttribute("error", "<br>Não existem produtos com o nome ou a descrição pesquisada!");
		}
		else
    		request.setAttribute("error", "<br>Digite algo para ser pesquisado!");
		RequestDispatcher rd = 
        request.getRequestDispatcher("/ProductSearch.jsp");
    	rd.forward(request,response);
	}
	
	private ArrayList<Product> search(final ArrayList<Product> allProducts,final String search){
		ArrayList<Product> resultSearch = new ArrayList<Product>();
		for(Product product:allProducts){
			if(product.getName().toLowerCase().contains(search.toLowerCase())){
				resultSearch.add(product);
			}
			else{
				if(product.getDescription().toLowerCase().contains(search.toLowerCase())){
					resultSearch.add(product);
				}
			}
		}
		
		return resultSearch;
	}
}
