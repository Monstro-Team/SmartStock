<%@page import="model.Cabinet"%>
<%@page import="model.Provider"%>
<%@page import="java.util.ArrayList"%>
<html>
	<head>
		<meta charset="utf-8">
		<title>Movimentar Mercadoria</title>
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
				<form action="/SmartStock/TransactionRegisterServlet?stock_id=${stock_id}&product_id=${product_id}" method="post">
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
						int location = 1;	
						/*try{
								location = Integer.parseInt((String)request.getAttribute("product_location"));
							}catch(java.lang.NumberFormatException ex){
								location = (int)request.getAttribute("product_location");
							}*/
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
								request.getRequestDispatcher("/CabinetServlet?transactionRegister=true");
								rd.forward(request,response);
							}
						%>
							<br>
							<br>Fornecedor: ${provider}
							<br>Quantidade:${stock_quantity}
							<br>Preço:${stock_price}
							<INPUT TYPE="hidden" NAME="product_id" VALUE="${product_id}">
							<INPUT TYPE="hidden" NAME="stock_id" VALUE="${stock_id}">
							<INPUT TYPE="hidden" NAME="product_name" VALUE="${product_name}">
							<br>Quantidade movida:
							<input class="form-control" id="inputDefault" type="text" value="${quantity_moved}" name="quantity_moved">
							<input type="radio" name="transaction_type" value="1"> Quadro<br>
						    <input type="radio" name="transaction_type" value="2"> Venda<br>
						    <input type="radio" name="transaction_type" value="3"> Transferencia de loja<br>
							<button class="btn btn-success" type="submit">Gravar</a>
						</div>
	 	 		</form>
	 	 	</div>
	 	 </div>
	</body>
</html>