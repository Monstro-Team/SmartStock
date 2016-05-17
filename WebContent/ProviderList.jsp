<%@page import="model.Provider"%>
<%@page import="java.util.ArrayList"%>
<html>
	<head>
		<meta charset="utf-8">
		<title>Listagem De Fornecedores</title>
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
						      <th>Empresa</th>
						      <th>Nome do vendedor</th>
						      <th>Telefone do vendedor</th>
						       <th>Ação</th>
						    </tr>
						</thead>
						<tbody>
								<%
								ArrayList<Provider> list = (ArrayList<Provider>) request.getAttribute("providers");
								if(list != null){
								
									for(Provider provider : list) {
										out.println("<tr>");
										out.println("<td>"+provider.getId()+"</td>");
									    out.println("<td>"+provider.getCompany()+"</td>");
									    out.println("<td>"+provider.getSalesman()+"</td>");
									    out.println("<td>"+provider.getSalesmanPhone()+"</td>");
									    out.println("<td> </a><a href=\"/SmartStock/ProviderEditorServlet?provider_id="+provider.getId()+"\">Editar</a>"+"</td>");
									    out.println("</tr>");
									}
								}
								else{
									RequestDispatcher rd = 
									        request.getRequestDispatcher("/ProviderListServlet");
									    	rd.forward(request,response);
								}
								%>
						</tbody>
				</table>
			</div>
		</div> 
	</body>
</html>