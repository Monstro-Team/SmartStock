<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="model.Cabinet"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="/SmartStock/css/bootstrap.min.css" media="screen">
		<script src="/SmartStock/js/jquery-2.2.2.min.js"></script>
		<script src="/SmartStock/js/bootstrap.min.js"></script>
		<script src="/SmartStock/js/smart-stock.js"></script>
		<script>
				function disableError() {
					if("${error}".length == 0)
						document.getElementById("errorInfo").style.display = "none";
				}
		</script>
		<title>Deletar um Estoque</title>
	</head>
	<body>
		<div id="includedContent"></div>
		<br><br><br>
		<div class="container">
			<div class="col-lg-6">
				<form action="/SmartStock/StockDeleterServlet?stock_id=${stock_id}&stock_deleter=true" method="post">
					<div class="form-group">
						Nome do produto: ${product_name}
						<br>Descrição: ${product_description}
						<br>Quantidade mínima: ${product_quantity_min}
						<br>Localização: 
						<%
							ArrayList<Cabinet> cabinets = (ArrayList<Cabinet>) request.getAttribute("cabinets");
							int location = Integer.parseInt((String)request.getAttribute("product_location"));
							if(cabinets != null){
								int aux =0;
								
								
								for(Cabinet cabinet : cabinets) {
									if(aux == 0)
										out.println("<div class=\"btn-group-vertical\">");	
									if(cabinet.getId() != location)
										out.println("<div class=\"btn btn-default\">"+cabinet.getName()+" "+(aux+1)+"</div>");							    	
									else
										out.println("<div class=\"btn btn-info\">"+cabinet.getName()+" "+(aux+1)+"</div>");	
									if(aux++ == 6){
										out.println("</div>");
										aux =0;
									}
								}
							}
							else{
								RequestDispatcher rd = 
								request.getRequestDispatcher("/CabinetServlet?stockDeleter=true");
								rd.forward(request,response);
							}
						%>
						<br>Preço: ${stock_price}
						<br>Quantidade do estoque: ${stock_quantity}
						<br>Fornecedor: ${provider}
						<input type="hidden" name="product_deleter" value="true">
						<button class="btn btn-danger" type="submit">Deletar</a>				
					</div>
				</form>
			</div>
		</div>
	</body>
</html>