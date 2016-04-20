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

import dao.CabinetDAO;
import dao.StockDAO;
import model.Cabinet;
import model.Stock;

@WebServlet(urlPatterns = {"/CabinetServlet"},
initParams = {
		@WebInitParam(name = "cabinets", value = ""),
		@WebInitParam(name = "description", value = ""),
})
public class CabinetServlet extends HttpServlet  {

	protected void service (HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
		CabinetDAO cabinetDAO = null;
		String description =  request.getParameter("description");
		ArrayList<Cabinet> cabinets = new ArrayList<Cabinet>();
		try {
			cabinetDAO = new CabinetDAO();
			cabinets = cabinetDAO.getAllCabinet();
		} catch (SQLException e) {
			request.setAttribute("error", "Erro no banco de dados!");
		}
		request.setAttribute("cabinets", cabinets);
		
		if(description.equals("true")){
			RequestDispatcher rd = 
			        request.getRequestDispatcher("/ProductDescription.jsp");
			    	rd.forward(request,response);
		}else{
			RequestDispatcher rd = 
	        request.getRequestDispatcher("/ProductRegister.jsp");
	    	rd.forward(request,response);
		}
	}
}
