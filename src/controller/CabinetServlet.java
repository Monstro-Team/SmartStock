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
		@WebInitParam(name = "deleter", value = "")
})
public class CabinetServlet extends HttpServlet  {

	protected void service (HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
		CabinetDAO cabinetDAO = null;
		String description =  request.getParameter("description");
		String deleter =  request.getParameter("deleter");
		String editor =  request.getParameter("editor");
		String stockDeleter =  request.getParameter("stockDeleter");
		String stockEditor =  request.getParameter("stockEditor");
		String productRegister =  request.getParameter("productRegister");
		ArrayList<Cabinet> cabinets = new ArrayList<Cabinet>();
		try {
			cabinetDAO = new CabinetDAO();
			cabinets = cabinetDAO.getAllCabinet();
		} catch (SQLException e) {
			request.setAttribute("error", "Erro no banco de dados!");
		}
		request.setAttribute("cabinets", cabinets);
		
		if(description != null && description.equals("true")){
			RequestDispatcher rd = 
			        request.getRequestDispatcher("/ProductDescription.jsp");
			    	rd.forward(request,response);
		}
		if(deleter != null && deleter.equals("true")){
			RequestDispatcher rd = 
			        request.getRequestDispatcher("/ProductDeleter.jsp");
			    	rd.forward(request,response);
		}
		if(editor != null && editor.equals("true")){
			RequestDispatcher rd = 
			        request.getRequestDispatcher("/ProductEditor.jsp");
			    	rd.forward(request,response);
		}
		if(stockDeleter != null && stockDeleter.equals("true")){
			RequestDispatcher rd = 
			        request.getRequestDispatcher("/StockDeleter.jsp");
			    	rd.forward(request,response);
		}
		if(stockEditor != null && stockEditor.equals("true")){
			RequestDispatcher rd = 
			        request.getRequestDispatcher("/StockEditor.jsp");
			    	rd.forward(request,response);
		}
		if(productRegister != null && productRegister.equals("true")){
			RequestDispatcher rd = 
	        request.getRequestDispatcher("/ProductRegister.jsp");
	    	rd.forward(request,response);
		}
	}
}
