<%@page import="model.Product"%>
<%@page import="java.util.ArrayList"%>
<html>
	<head>
		<meta charset="utf-8">
		<title>Produtos com Baixo Estoque</title>
		<link rel="stylesheet" href="/SmartStock/css/bootstrap.min.css" media="screen">
		<script src="/SmartStock/js/jquery-2.2.2.min.js"></script>
		<script src="/SmartStock/js/bootstrap.min.js"></script>
		<script src="/SmartStock/js/smart-stock.js"></script>
		<script>
			function disableInformation() {
				if("${info}".length == 0)
					document.getElementById("information").style.display = "none";
			}
			function disableError() {
				if("${error}".length == 0)
					document.getElementById("errorInfo").style.display = "none";;
			}
		</script>
	</head>	
		<body onload="disableInformation()">
		<div id="includedContent"></div>
			<br><br><br>
			<div class="container">
				<div class="col-lg-6">
					<div class="alert alert-dismissible alert-success" id="information">
  						<button type="button" class="close" data-dismiss="alert">&times;</button>
  						<strong>Informação:</strong> <a href="#" class="alert-link">${info}</a>
					</div>
					<table class="table table-striped table-hover ">
						<thead>
						    <tr>
						      <th>#</th>
						      <th>Nome</th>
						      <th>Descrição</th>
						      <th>Qnt. Mínima</th>
						      <th>Qnt. em Estoque</th>
						    </tr>
						</thead>
						<tbody>
							<%
							ArrayList<Product> list = (ArrayList<Product>) request.getAttribute("products");
							if(list != null) {							
								for(Product product : list) {
									out.println("<tr>");
									out.println("<td>"+product.getId()+"</td>");
								    out.println("<td>"+"<a href=\"/SmartStock/ProductDescriptionServlet?product_id="+product.getId()+"\">"+product.getName()+"</a>"+"</td>");
								    out.println("<td>"+product.getDescription()+"</td>");
								    out.println("<td><center>"+product.getQuantityMin()+"</center></td>");
								    out.println("<td><center>"+product.getQuantity()+"</center></td>");
								    out.println("</tr>");
								}
							}
							else{
								RequestDispatcher rd = 
								        request.getRequestDispatcher("/StockAlertServlet");
								    	rd.forward(request,response);
							}
							%>
					</tbody>
				</table>
			</div>
		</div> 
	</body>
</html>