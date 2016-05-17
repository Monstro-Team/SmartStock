<%@page import="model.Cabinet"%>
<%@page import="java.util.ArrayList"%>
<html>
	<head>
		<meta charset="utf-8">
		<title>Edição De Fornecedores</title>
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
				<form action="/SmartStock/ProviderEditorServlet?product_id=${provider_id}" method="post">
					    <div class="form-group">
					    	<br>
							<br>
							<br>
							<div class="alert alert-dismissible alert-danger" id="errorInfo">
							  <strong>Ocorreu um erro!</strong> <a href="#" class="alert-link"></a>${error}
							</div>
							Empresa:
							<input class="form-control" id="inputDefault" type="text" value="${provider_company}" name="product_name">
							<br>Nome do Vendedor:
							<input class="form-control" id="inputDefault" type="text" value="${provider_salesman}" name="product_description">
							<br>Telefone do Vendedor:
							<input class="form-control" id="inputDefault" type="text" value="${provider_salesmanPhone}" name="product_quantity_min">
							<br>
							<button class="btn btn-success" type="submit">Gravar</a>
						</div>
	 	 		</form>
	 	 	</div>
	 	 </div>
	</body>
</html>
