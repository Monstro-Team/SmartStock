<%@page import="model.Product"%>
<%@page import="model.Transaction"%>
<%@page import="model.Stock"%>
<%@page import="java.util.ArrayList"%>
<html>
	<head>
		<meta charset="utf-8">
		<title>Listagem De Movimentação</title>
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
				<div class="col-lg-16">
					<div class="alert alert-dismissible alert-success" id="information">
  						<button type="button" class="close" data-dismiss="alert">&times;</button>
  						<strong>Informação:</strong> <a href="#" class="alert-link">${info}</a>
					</div>
					<table class="table table-striped table-hover ">
						<thead>
						    <tr>
						      <th>#</th>
						      <th>Produto</th>
						      <th>Quantidade movimentada</th>
						      <th>Tipo da movimentação</th>
						      <th>Data</th>
						    </tr>
						</thead>
						<tbody>
								<%
								ArrayList<Product> list = (ArrayList<Product>) request.getAttribute("products");
								ArrayList<Transaction> transactionsList = (ArrayList<Transaction>) request.getAttribute("transactions");
								ArrayList<Stock> stocksList = (ArrayList<Stock>) request.getAttribute("stocks");
								int aux =1;
								if(list != null){
									for(Transaction transaction : transactionsList){
										for(Stock stock : stocksList){
											if(stock.getId() == transaction.getStockId())
											for(Product product : list) {
												if(product.getId() == stock.getIdProduct())
												out.println("<tr>");
												out.println("<td>"+aux+++"</td>");
											    out.println("<td>"+"<a href=\"/SmartStock/ProductDescriptionServlet?product_id="+product.getId()+"\">"+product.getName()+"</a>"+"</td>");
											    out.println("<td>"+transaction.getQuantityMoved()+"</td>");
											    switch(transaction.getTransactionType()){
												    case 1:{
													    out.println("<td>Quadro</td>");
												    	break;
												    }
													case 2:{
													    out.println("<td>Venda</td>");
												    	break;
												    }
													case 3:{
														out.println("<td>Outra Loja</td>");
													}
											    }
											    out.println("</tr>");
											    out.println("<td>"+transaction.getDate()+"</td>");
											}
										}
									}
								}
								else{
									RequestDispatcher rd = 
									        request.getRequestDispatcher("/TransactionListServlet");
									    	rd.forward(request,response);
								}
								%>
						</tbody>
				</table>
			</div>
		</div> 
	</body>
</html>