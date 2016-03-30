package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/SmartStock/ProductServlet")
public class ProductServlet extends HttpServlet  {
    protected void service (HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        // escreve o texto
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Cadastro de Produto</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("Cadastro de Produto");
        out.println("</body>");
        out.println("</html>");
    }
}
