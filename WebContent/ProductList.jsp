<html>
	<head>
		<meta charset="utf-8">
		<title>Cadastro De Produtos</title>
		<link rel="stylesheet" href="/SmartStock/css/bootstrap.min.css" media="screen">
		<script src="/SmartStock/js/jquery-2.2.2.min.js"></script>
		<script src="/SmartStock/js/bootstrap.min.js"></script>
		<script src="/SmartStock/js/smart-stock.js"></script>
		<script>
				function disableError() {
					if("${error}".length == 0)
						document.getElementById("errorInfo").style.visibility = "hidden";
				}
		</script>
	</head>
	
	<body>
		<form action="/SmartStock/ProductDescriptionServlet" method="post">
					    <div class="form-group">
							Nome do produto:
							<input class="form-control" id="inputDefault" type="text" name="product_id">
							<br>
							<button class="btn btn-success" type="submit">Gravar</a>
						</div>
	 	 		</form>
	</body>
</html>