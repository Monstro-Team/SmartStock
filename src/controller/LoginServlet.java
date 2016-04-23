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

import java.util.HashMap;
import java.util.Map;
import java.util.logging.*;

import dao.UserDAO;
import model.User;

@WebServlet( urlPatterns = {"/LoginServlet"},
initParams = {
		@WebInitParam(name = "error", value = ""),
		@WebInitParam(name = "username", value = "")
})	
		
public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static final Logger log = Logger.getLogger( LoginServlet.class.getName() );
       
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
    }
    
    protected void service (HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
    	System.out.println("dfdfsdvsfsdfsdafsdassfas");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		User user = null;
		
		try {
			user = UserDAO.getUser(username, password);
			
			if ( user != null ){
				request.getSession().setAttribute("user", user);
	            response.sendRedirect(request.getContextPath() + "/index2.html");
	            return;
			}else{
				request.setAttribute("error", "Login inválido: Usuário e/ou senha incorreta.");
				request.setAttribute("username", username);
				request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
