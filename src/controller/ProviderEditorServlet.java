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
import services.Validator;

@WebServlet(urlPatterns = {"/ProviderEditorServlet"},
initParams = {
		@WebInitParam(name = "provider_id", value = ""),
		@WebInitParam(name = "provider_company", value = ""),
		@WebInitParam(name = "provider_salesman", value = ""),
		@WebInitParam(name = "provider_salesmanPhone", value = "")
})
public class ProviderEditorServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1339041936997740338L;

	protected void service (HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        ProviderDAO providerDAO = null;
        Provider product = null;
        String resultValidation = null;
        
        String providerId = request.getParameter("product_id");
        String providerCompany = request.getParameter("provider_company");
        String providerSalesman = request.getParameter("provider_salesman");
        String providerSalesmanPhone = request.getParameter("provider_salesmanPhone");
        
		try {
			providerDAO = new ProviderDAO();
		} catch (SQLException e1) {
			request.setAttribute("error", "Erro no banco de dados!");
        	request.setAttribute("provider_company", providerCompany);
        	request.setAttribute("provider_salesman", providerSalesman);
        	request.setAttribute("provider_salesmanPhone", providerSalesmanPhone);
        	RequestDispatcher rd = 
        			request.getRequestDispatcher("/ProviderEditor.jsp");
        	rd.forward(request,response);
		}
	}
}

