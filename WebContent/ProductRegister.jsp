<html>
	<head>
		<title>Cadastro De Produtos</title>
		<link rel="stylesheet" href="/SmartStock/css/bootstrap.min.css">
		<script>
		function disableError() {
			if("${error}".length == 0)
				document.getElementById("errorInfo").style.visibility = "hidden";
			}
		</script>
	</head>
	<body onload="disableError()">
		<div class="container">
			<div class="col-lg-6">
				<form action="/SmartStock/ProductServlet" method="post">
				  <fieldset>
				    <legend>Registrar Produto</legend>
					    <div class="form-group">
							<div class="alert alert-dismissible alert-danger" id="errorInfo">
							  <strong>Ocorreu um erro!</strong> <a href="#" class="alert-link"></a>${error}
							</div>
							Nome do produto: 
							<input class="form-control" id="inputDefault" type="text" value="${product_name}" name="product_name">
							<br>Descrição: 
							<input class="form-control" id="inputDefault" type="text" value="${product_description}" name="product_description">
							<br>Fornecedor: 
							<input class="form-control" id="inputDefault" type="text" value="${product_supplier}" name="product_supplier">
							<br>Quantidade: 
							<input class="form-control" id="inputDefault" type="text" value="${product_quantity}" name="product_quantity">
							<br>Quantidade mínima: 
							<input class="form-control" id="inputDefault" type="text" value="${product_quantity_min}" name="product_quantity_min">
							<br>Preço: 
							<input class="form-control" id="inputDefault" type="text" value="${product_price}" name="product_price">
							<br>Localização: 
							<input class="form-control" id="inputDefault" type="text" value="${product_location}" name="product_location">
							<br>
							<button class="btn btn-success" type="submit">Gravar</a>
						</div>
			 	 	</fieldset>
	 	 		</form>
	 	 	</div>
	 	 </div>
	</body>
</html>