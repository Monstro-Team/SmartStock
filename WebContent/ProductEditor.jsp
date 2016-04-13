<html>
	<head>
		<meta charset="utf-8">
		<title>Edição De Produtos</title>
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
				<form action="/SmartStock/ProductEditorServlet?product_id=${product_id}" method="post">
					    <div class="form-group">
					    	<br>
							<br>
							<br>
							<div class="alert alert-dismissible alert-danger" id="errorInfo">
							  <strong>Ocorreu um erro!</strong> <a href="#" class="alert-link"></a>${error}
							</div>
							Nome do produto:
							<input class="form-control" id="inputDefault" type="text" value="${product_name}" name="product_name">
							<br>Descrição:
							<input class="form-control" id="inputDefault" type="text" value="${product_description}" name="product_description">
							<br>Quantidade mí­nima:
							<input class="form-control" id="inputDefault" type="text" value="${product_quantity_min}" name="product_quantity_min">
							<br>Localização:
							<input class="form-control" id="inputDefault" type="text" value="${product_location}" name="product_location">
							<br>
							<button class="btn btn-success" type="submit">Gravar</a>
						</div>
	 	 		</form>
	 	 	</div>
	 	 </div>
	</body>
</html>
