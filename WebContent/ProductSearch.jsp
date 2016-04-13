<%@page import="model.Product"%>
<%@page import="java.util.ArrayList"%>
<html>
	<head>
		<meta charset="utf-8">
		<title>Pesquisa De Produtos</title>
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
			<br><br><br>
			<div class="container">
				<div class="col-lg-6">
					<div class="alert alert-dismissible alert-danger" id="errorInfo">
						<strong>Ocorreu um erro!</strong><a href="#" class="alert-link"></a>${error}
					</div>
					<form action="/SmartStock/ProductSearchServlet" method="post">
					    <div class="form-group">
					    	Pesquisar:
							<input class="form-control" id="inputDefault" type="text" value="${product_search}" name="product_search">
					    	<button class="btn btn-primary" type="submit">Pesquisar</a>
					    </div>
					</form>
					<table class="table table-striped table-hover ">
						<thead>
						    <tr>
						      <th>#</th>
						      <th>Nome</th>
						      <th>Descrição</th>
						      <th>Quantidade</th>
						      <th>Fornecedor</th>
						      <th>Ação</th>
						    </tr>
						</thead>
						<tbody>
								<%
								ArrayList<Product> list = (ArrayList<Product>) request.getAttribute("products");
								if(list != null){
								
									for(Product product : list) {
										out.println("<tr>");
										out.println("<td>"+product.getId()+"</td>");
									    out.println("<td>"+"<a href=\"/SmartStock/ProductDescriptionServlet?product_id="+product.getId()+"\">"+product.getName()+"</a>"+"</td>");
									    out.println("<td>"+product.getDescription()+"</td>");
									    out.println("<td>"+product.getQuantity()+"</td>");
									    out.println("<td>"+product.getSupplier()+"</td>");
									    out.println("<td>"+"<a href=\"/SmartStock/ProductDeleterServlet?product_id="+product.getId()+"\">Deletar </a><a href=\"/SmartStock/ProductEditorServlet?product_id="+product.getId()+"\">Editar</a>"+"</td>");
									    out.println("</tr>");
									}
								}
								%>
						</tbody>
				</table>
			</div>
		</div> 
	</body>
</html>