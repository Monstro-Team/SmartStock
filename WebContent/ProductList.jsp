<%@page import="model.Product"%>
<%@page import="java.util.ArrayList"%>
<html>
	<head>
		<meta charset="utf-8">
		<title>Listagem De Produtos</title>
		<link rel="stylesheet" href="/SmartStock/css/bootstrap.min.css" media="screen">
		<script src="/SmartStock/js/jquery-2.2.2.min.js"></script>
		<script src="/SmartStock/js/bootstrap.min.js"></script>
		<script src="/SmartStock/js/smart-stock.js"></script>
	</head>	
	<body>
		<div id="includedContent"></div>
			<br><br><br>
			<div class="container">
				<div class="col-lg-6">
					<table class="table table-striped table-hover ">
						<thead>
						    <tr>
						      <th>#</th>
						      <th>Nome</th>
						      <th>Descrição</th>
						      <th>Quantidade</th>
						      <th>Fornecedor</th>
						    </tr>
						</thead>
						<tbody>
							<tr>
								<%
								ArrayList<Product> list = (ArrayList<Product>) request.getAttribute("products");
								if(list != null){
									for(Product product : list) {
										out.println("<td>"+product.getId()+"</td>");
									    out.println("<td>"+"<a href=\"/SmartStock/ProductDescriptionServlet?product_id="+product.getId()+"\">"+product.getName()+"</a>"+"</td>");
									    out.println("<td>"+product.getDescription()+"</td>");
									    out.println("<td>"+product.getQuantity()+"</td>");
									    out.println("<td>"+product.getSupplier()+"</td>");
									}
								}
								else{
									RequestDispatcher rd = 
									        request.getRequestDispatcher("/ProductListServlet");
									    	rd.forward(request,response);
								}
								%>
							</tr>
						</tbody>
				</table>
			</div>
		</div> 
	</body>
</html>