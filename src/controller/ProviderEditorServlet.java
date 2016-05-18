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
		@WebInitParam(name = "provider_salesmanPhone", value = ""),
		@WebInitParam(name = "info", value = "")
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
        Provider provider = null;
        String resultValidation = null;
        
        String providerId = request.getParameter("provider_id");
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
        
	    if(providerCompany != null){
	        		resultValidation = Validator.validadeIsProviderCorrect(providerCompany, providerSalesman,
	        				providerSalesmanPhone);
		    if(resultValidation.length() == 0 ){
		        	provider = new Provider(providerCompany, providerSalesman, providerSalesmanPhone);
		        	provider.setId(Integer.parseInt(providerId));
		        	try {
						providerDAO.updateProvider(provider);
					} catch (SQLException e) {
						request.setAttribute("error", "Erro no banco de dados!");
			        	request.setAttribute("provider_company", providerCompany);
			        	request.setAttribute("provider_salesman", providerSalesman);
			        	request.setAttribute("provider_salesmanPhone", providerSalesmanPhone);
			        	RequestDispatcher rd = 
				        request.getRequestDispatcher("/ProviderEditor.jsp");
			        	rd.forward(request,response);
					}
		        	request.setAttribute("info", "Edição feita com sucesso!");

		        	RequestDispatcher rd = 
		        			request.getRequestDispatcher("/ProviderList.jsp");
		        	rd.forward(request,response);
		    }
		    else{
		    	request.setAttribute("provider_id", providerId);
	        	request.setAttribute("provider_company", providerCompany);
	        	request.setAttribute("provider_salesman", providerSalesman);
	        	request.setAttribute("provider_salesmanPhone", providerSalesmanPhone);
        		request.setAttribute("error", resultValidation);
            	RequestDispatcher rd = 
            			request.getRequestDispatcher("/ProviderEditor.jsp");
                rd.forward(request,response);
		    }
	    }
	    else{
	    	try {
				provider = providerDAO.getProvider(Integer.parseInt(providerId));
			} catch (NumberFormatException | SQLException e) {
        		request.setAttribute("error", "Ocorreu um erro no banco de dados.");
            	RequestDispatcher rd = 
            			request.getRequestDispatcher("/ProviderEditor.jsp");
                rd.forward(request,response);
			}
	    	request.setAttribute("provider_id", provider.getId());
        	request.setAttribute("provider_company", provider.getCompany());
        	request.setAttribute("provider_salesman", provider.getSalesman());
        	request.setAttribute("provider_salesmanPhone", provider.getSalesmanPhone());
        	RequestDispatcher rd = 
        			request.getRequestDispatcher("/ProviderEditor.jsp");
        	rd.forward(request,response);
	    }
	}
}
