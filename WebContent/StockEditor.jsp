<%@page import="model.Cabinet"%>
<%@page import="java.util.ArrayList"%>
<html>
	<head>
		<meta charset="utf-8">
		<title>Edição De Estoque</title>
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
	</head>
	<body onload="disableError()">
		<div id="includedContent"></div>
		<div class="container">
			<div class="col-lg-6">
				<form action="/SmartStock/StockEditorServlet?stock_id=${stock_id}" method="post">
					    <div class="form-group">
					    	<br>
							<br>
							<br>
							<div class="alert alert-dismissible alert-danger" id="errorInfo">
							  <strong>Ocorreu um erro!</strong> <a href="#" class="alert-link"></a>${error}
							</div>
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
								request.getRequestDispatcher("/CabinetServlet?stockEditor=true");
								rd.forward(request,response);
							}
						%>
					
							<br>Fornecedor:
							<input class="form-control" id="inputDefault" type="text" value="${stock_supplier}" name="stock_supplier">
							<br>Quantidade:
							<input class="form-control" id="inputDefault" type="text" value="${stock_quantity}" name="stock_quantity">
							<br>Preço:
							<input class="form-control" id="inputDefault" type="text" value="${stock_price}" name="stock_price">
							<br>
							<button class="btn btn-success" type="submit">Gravar</a>
						</div>
	 	 		</form>
	 	 	</div>
	 	 </div>
	</body>
</html>