package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.*;

import dao.UserDAO;
import model.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static final Logger log = Logger.getLogger( LoginServlet.class.getName() );
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		log.log(Level.FINE, "Username = ", username);
		
		User user = null;
		
		try {
			user = UserDAO.getUser(username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if ( user != null ){
			
			response.sendRedirect("index.html");
		}else{
			response.sendRedirect("error.jsp");
		}
		
	}

}
