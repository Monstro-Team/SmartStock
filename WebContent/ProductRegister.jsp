<html>
	<head>
		<title>Cadastro De Produtos</title>
	</head>
	<body>
		<h5 style="background-color:red">${error}</h2>
		<form name="input" action="/SmartStock/ProductServlet" method="post">
		Nome do produto: 
		<input type="text" value="${product_name}" name="product_name">
		<br>Descrição: 
		<input type="text" value="${product_description}" name="product_description">
		<br>Fornecedor: 
		<input type="text" value="${product_supplier}" name="product_supplier">
		<br>Quantidade: 
		<input type="text" value="${product_quantity}" name="product_quantity">
		<br>Quantidade mínima: 
		<input type="text" value="${product_quantity_min}" name="product_quantity_min">
		<br>Preço: 
		<input type="text" value="${product_price}" name="product_price">
		<br>Localização: 
		<input type="text" value="${product_location}" name="product_location">
		<br>
		<input type="submit" value="Enviar">
		</form>
	</body>
</html>