<%@page import="model.Cabinet"%>
<%@page import="java.util.ArrayList"%>
<html>
	<head>
		<meta charset="utf-8">
		<title>Cadastro De Fornecedor</title>
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
				<form action="/SmartStock/ProviderRegisterServlet" method="post">
				    <div class="form-group">
				    	<br>
						<br>
						<br>
						<div class="alert alert-dismissible alert-danger" id="errorInfo">
							<strong>Ocorreu um erro!</strong><a href="#" class="alert-link"></a>${error}
						</div>
						Empresa:
						<input class="form-control" id="inputDefault" type="text" value="${company_name}" name="company_name">
						<br>Nome do Vendedor:
						<input class="form-control" id="inputDefault" type="text" value="${salesman_name}" name="salesman_name">
						<br>Telefone do Vendedor:
						<input class="form-control" id="inputDefault" type="text" value="${salesman_phone}" name="salesman_phone">
						<button class="btn btn-success" type="submit">Gravar</a>
					</div>
	 	 		</form>
	 	 	</div>
	 	 </div>
	</body>
</html>
