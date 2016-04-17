<%@page import="model.Product"%>
<%@page import="java.util.ArrayList"%>
<html>
	<head>
		<meta charset="utf-8">
		<title>Cadastro De Estoque</title>
		<link rel="stylesheet" href="/SmartStock/css/bootstrap.min.css" media="screen">
		<script src="/SmartStock/js/jquery-2.2.2.min.js"></script>
		<script src="/SmartStock/js/bootstrap.min.js"></script>
		<script src="/SmartStock/js/smart-stock.js"></script>
		<script>
				function disableError() {
					if("${error}".length == 0)
						document.getElementById("errorInfo").style.display = "none";;
				}
		</script>
	</head>
	<body onload="disableError()">
		<div id="includedContent"></div>
		<div class="container">
			<div class="col-lg-6">
				<form action="/SmartStock/StockRegisterServlet" method="post">
					    <div class="form-group">
					    	<br>
							<br>
							<br>
							<div class="alert alert-dismissible alert-danger" id="errorInfo">
								<strong>Ocorreu um erro!</strong><a href="#" class="alert-link"></a>${error}
							</div>
							Produto:
							<select name="product_id">
								<%
								ArrayList<Product> list = (ArrayList<Product>) request.getAttribute("products");
								if(list != null){
									  
									for(Product product : list) {
										out.println("<option value="+product.getId()+">"+product.getName()+", "+product.getDescription()+"</option>");
									}
								}
								else{
									RequestDispatcher rd = 
									        request.getRequestDispatcher("/StockRegisterServlet");
									    	rd.forward(request,response);
								}
								%>
							</select>
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
