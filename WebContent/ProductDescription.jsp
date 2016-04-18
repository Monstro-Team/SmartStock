<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.Stock"%>
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
		<title>Descrição do Produto</title>
	</head>
	<body>
		<div id="includedContent"></div>
		<br><br><br>
		<div class="container">
			<div class="col-lg-6">
					<div class="form-group">
						Nome do produto: ${product_name}
						<br>Descrição: ${product_description}
						<br>Quantidade mínima: ${product_quantity_min}
						<br>Localização: ${product_location}
						<br>
						<table class="table table-striped table-hover ">
							<thead>
							    <tr>
							      <th>#</th>
							      <th>Quantidade</th>
							      <th>Fornecedor</th>
							      <th>Ação</th>
							    </tr>
							</thead>
							<tbody>
									<%
									ArrayList<Stock> list = (ArrayList<Stock>) request.getAttribute("stocks");
									if(list != null){
									
										for(Stock stock : list) {
											out.println("<tr>");
											out.println("<td>"+stock.getId()+"</td>");
										    out.println("<td>"+stock.getQuantity()+"</td>");
										    out.println("<td>"+stock.getSupplier()+"</td>");
										    out.println("<td>"+"<a href=\"/SmartStock/StockDeleterServlet?stock_id="+stock.getId()+"\">"+"Deletar"+"</a>"+"<a href=\"/SmartStock/StockEditorServlet?stock_id="+stock.getId()+"\">"+"Editar"+"</a> "+"</td>");
										    out.println("</tr>");
										}
									}
									else{
										out.println("Erro ao construir a lista.");
									}
									%>
							</tbody>
						</table>
					</div>
				</div>
			</div>
	</body>
</html>