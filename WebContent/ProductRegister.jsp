<html>
	<head>
		<meta charset="utf-8">
		<title>Cadastro De Produtos</title>
		<link rel="stylesheet" href="/SmartStock/css/bootstrap.min.css" media="screen">
		<script src="/SmartStock/js/jquery-2.2.2.min.js"></script>
		<script src="/SmartStock/js/bootstrap.min.js"></script>
		<script src="/SmartStock/js/smart-stock.js"></script>
	</head>
	<body>
		<div id="includedContent"></div>
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
							<br>Descri��o:
							<input class="form-control" id="inputDefault" type="text" value="${product_description}" name="product_description">
							<br>Fornecedor:
							<input class="form-control" id="inputDefault" type="text" value="${product_supplier}" name="product_supplier">
							<br>Quantidade:
							<input class="form-control" id="inputDefault" type="text" value="${product_quantity}" name="product_quantity">
							<br>Quantidade m�nima:
							<input class="form-control" id="inputDefault" type="text" value="${product_quantity_min}" name="product_quantity_min">
							<br>Pre�o:
							<input class="form-control" id="inputDefault" type="text" value="${product_price}" name="product_price">
							<br>Localiza��o:
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
