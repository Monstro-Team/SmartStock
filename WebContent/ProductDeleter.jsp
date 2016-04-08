<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
						document.getElementById("errorInfo").style.visibility = "hidden";
				}
		</script>
		<title>Deletar um Produto</title>
	</head>
	<body>
		<div id="includedContent"></div>
		<br><br><br>
		<div class="container">
			<div class="col-lg-6">
				<form action="/SmartStock/ProductDeleterServlet?product_id=${product_id}&product_deleter=true" method="post">
					<div class="form-group">
						Nome do produto: ${product_name}
						<br>Descrição: ${product_description}
						<br>Fornecedor: ${product_supplier}
						<br>Quantidade: ${product_quantity}
						<br>Quantidade mínima: ${product_quantity_min}
						<br>Preço: ${product_price}
						<br>Localização: ${product_location}
						<input type="hidden" name="product_deleter" value="true">
						<button class="btn btn-danger" type="submit">Deletar</a>				
					</div>
				</form>
			</div>
		</div>
	</body>
</html>