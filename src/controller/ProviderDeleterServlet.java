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

import dao.ProviderDAO;
import model.Provider;

@WebServlet(urlPatterns = {"/ProviderDeleterServlet"},
initParams = {
		@WebInitParam(name = "provider_id", value = ""),
		@WebInitParam(name = "provider_company", value = ""),
		@WebInitParam(name = "provider_salesman", value = ""),
		@WebInitParam(name = "provider_salesmanPhone", value = "")
})

public class ProviderDeleterServlet  extends HttpServlet {
		
	private static final long serialVersionUID = -4027275717854156243L;
	private int provider_id;
	private Provider provider;
	private String provider_deleter;
	
	protected void service (HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
		provider_id = Integer.parseInt(request.getParameter("provider_id"));
		provider_deleter = request.getParameter("provider_deleter");
		ProviderDAO providerDAO = null;
		
		if (provider_deleter != null)
			if (provider_deleter.length() != 0) {
				if (provider_deleter.equals("true")) {
					try {
						providerDAO = new ProviderDAO();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					try {
						providerDAO.deleteProvider(provider_id);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					RequestDispatcher rd = request.getRequestDispatcher("/ProviderList.jsp");
					rd.forward(request, response);
				}
			}

		try {
			providerDAO = new ProviderDAO();
			provider = providerDAO.getProvider(provider_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	request.setAttribute("provider_id", provider.getId());
		request.setAttribute("provider_company", provider.getCompany());
    	request.setAttribute("provider_salesman", provider.getSalesman());
    	request.setAttribute("provider_salesmanPhone", provider.getSalesmanPhone());
		
		RequestDispatcher rd = request.getRequestDispatcher("/ProviderDeleter.jsp");
		rd.forward(request, response);
	}
}
