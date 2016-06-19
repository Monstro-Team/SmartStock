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

import dao.StockDAO;
import model.Stock;

@WebServlet(urlPatterns = {"/StockListServlet"},
initParams = {
		@WebInitParam(name = "products", value = ""),
})
public class StockListServlet extends HttpServlet {
	private StockDAO stockDAO;
	private static final long serialVersionUID = -4429371055509587937L;

	protected void service (HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
		ArrayList<Stock> stocks = new ArrayList<Stock>();
		try {
			stockDAO = new StockDAO();
			stocks = stockDAO.getAllStock();
		} catch (SQLException e) {
			request.setAttribute("error", "Erro no banco de dados!");
		}
		request.setAttribute("stocks", stocks);
		RequestDispatcher rd = 
        request.getRequestDispatcher("/StockList.jsp");
    	rd.forward(request,response);
	}
	
}
