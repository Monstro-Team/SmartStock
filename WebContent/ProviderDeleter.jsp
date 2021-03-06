<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="model.Provider"%>
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
		<title>Deletar um Fornecedor</title>
	</head>
	<body>
		<div id="includedContent"></div>
		<br><br><br>
		<div class="container">
			<div class="col-lg-6">
				<form action="/SmartStock/ProviderDeleterServlet?provider_id=${provider_id}&provider_deleter=true" method="post">
					<div class="form-group">
						Empresa: ${provider_company}
						<br>Nome do Vendedor: ${provider_salesman}
						<br>Telefone do Vendedor: ${provider_salesmanPhone}
						
						<button class="btn btn-danger" type="submit">Deletar</a>				
					</div>
				</form>
			</div>
		</div>
	</body>
</html>