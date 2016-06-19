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

import dao.UserDAO;
import model.User;

@WebServlet( urlPatterns = {"/LoginServlet"},
initParams = {
		@WebInitParam(name = "error", value = ""),
		@WebInitParam(name = "username", value = "")
})	
		
public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
//	private static final Logger log = Logger.getLogger( LoginServlet.class.getName() );
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		User user = null;
		
		try {
			user = UserDAO.getUser(username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if ( user != null ){
			response.sendRedirect("/SmartStock/index2.html");
		}else{
			request.setAttribute("error", "Login inválido: Usuário e/ou senha incorreta.");
			request.setAttribute("username", username);
			RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
			rd.forward(request,response);
		}
	}
}
