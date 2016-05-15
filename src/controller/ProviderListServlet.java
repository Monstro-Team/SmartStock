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

import model.Provider;
import dao.ProviderDAO;

@WebServlet(urlPatterns = {"/ProviderListServlet"},
initParams = {
		@WebInitParam(name = "provider", value = ""),
})
public class ProviderListServlet extends HttpServlet  {

	private static final long serialVersionUID = -4429371055509587937L;
	private ProviderDAO providerDAO;

	protected void service (HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
		ArrayList<Provider> providers = new ArrayList<Provider>();

		try {
			providerDAO = new ProviderDAO();
			providers = providerDAO.getAllProvider();
		} catch (SQLException e) {
			request.setAttribute("error", "Erro no banco de dados!");
		}
		request.setAttribute("providers", providers);
		RequestDispatcher rd = 
        request.getRequestDispatcher("/ProviderList.jsp");
    	rd.forward(request,response);
	}
}
