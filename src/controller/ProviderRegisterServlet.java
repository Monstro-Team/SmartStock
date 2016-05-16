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
@WebServlet( urlPatterns = {"/ProviderRegisterServlet"},
initParams = {
		@WebInitParam(name = "company_name", value = ""),
		@WebInitParam(name = "salesman_name", value = ""),
		@WebInitParam(name = "salesman_phone", value = "")
})
public class ProviderRegisterServlet extends HttpServlet {
	protected void service (HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {    
        
        String companyName = request.getParameter("company_name");
        String salesmanName = request.getParameter("salesman_name");
        String salesmanPhone = request.getParameter("salesman_phone");
        
        if(companyName != null){
	        String resultValidation = Validator.validadeIsProviderCorrect(companyName,salesmanName,salesmanPhone);
	        
	        if(resultValidation.length() == 0){
	        	Provider provider = new Provider(companyName,salesmanName,salesmanPhone);
	        	
	        	try {
	        		ProviderDAO providerDAO = new ProviderDAO();
					providerDAO.includeProvider(provider);
				} catch (SQLException e) {
		        	request.setAttribute("error", "Erro no banco de dados!");
		        	request.setAttribute("company_name", companyName);
		        	request.setAttribute("salesman_name", salesmanName);
		        	request.setAttribute("salesman_phone", salesmanPhone);
		        	RequestDispatcher rd = 
			        request.getRequestDispatcher("/ProviderRegister.jsp");
		        	rd.forward(request,response);
				}
	        	
	        	request.setAttribute("info", "Provider cadastrado com sucesso!");
	        	RequestDispatcher rd = 
		        request.getRequestDispatcher("/ProviderList.jsp");
	        	rd.forward(request,response);
	        }
	        else{
	        	request.setAttribute("error", resultValidation);
	        	request.setAttribute("company_name", companyName);
	        	request.setAttribute("salesman_name", salesmanName);
	        	request.setAttribute("salesman_phone", salesmanPhone);
	        	RequestDispatcher rd = 
		        request.getRequestDispatcher("/ProviderRegister.jsp");
	        	rd.forward(request,response);
	        }
        }
        else{
        	RequestDispatcher rd = 
	        request.getRequestDispatcher("/ProviderRegister.jsp");
        	rd.forward(request,response);
        }
    }
}
