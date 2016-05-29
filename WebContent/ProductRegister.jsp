<%@page import="model.Cabinet"%>
<%@page import="java.util.ArrayList"%>
<html>
	<head>
		<meta charset="utf-8">
		<title>Cadastro De Produtos</title>
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
				<form action="/SmartStock/ProductRegisterServlet" method="post">
				    <div class="form-group">
				    	<br>
						<br>
						<br>
						<div class="alert alert-dismissible alert-danger" id="errorInfo">
							<strong>Ocorreu um erro!</strong><a href="#" class="alert-link"></a>${error}
						</div>
						Tipo de Produto:<br>
						<input id="key" type="radio" onclick="javascript:productTypeCheck();" value="key" name="product_type">
						Chave
						<input id="part" type="radio" onclick="javascript:productTypeCheck();" value="part" name="product_type">
						Peça<br><br>
						Nome do produto:
						<input class="form-control" id="product_name" type="text" value="${product_name}" name="product_name">
						<br>Descrição:
						<input class="form-control" id="product_description" type="text" value="${product_description}" name="product_description">
						<br>Quantidade mínima:
						<input class="form-control" id="product_quantity_min" type="text" value="${product_quantity_min}" name="product_quantity_min">
						<div id="divPart">
							<br>Marca:
							<input class="form-control" id="product_brand" type="text" value="${product_brand}" name="product_brand">
							<br>Modelo do Carro:
							<input class="form-control" id="product_modelCar" type="text" value="${product_car_model}" name="product_car_model">
						</div>
						<br>Gaveta:
						<form>
						<%
							ArrayList<Cabinet> list = (ArrayList<Cabinet>) request.getAttribute("cabinets");
							if(list != null) {
								int aux =0;
								
								for(Cabinet cabinet : list) {
									if(aux == 0)
										out.println("<div class=\"btn-group-vertical\">");	
									out.println("<div class=\"btn btn-default\"><input type=\"radio\" name=\"product_location\" value=\""+cabinet.getId()+"\">"+cabinet.getName()+" "+(aux+1)+"<br></div>");							    	
									
									if(aux++ == 6) {
										out.println("</div>");
										aux =0;
									}
								}
							}
							else {
								RequestDispatcher rd = 
								        request.getRequestDispatcher("/CabinetServlet?productRegister=true");
								    	rd.forward(request,response);
							}
							%>
						</form>
						<br><br>
						<button class="btn btn-success" type="submit">Gravar</a>
					</div>
	 	 		</form>
	 	 	</div>
	 	 </div>
	</body>
</html>

<script type="text/javascript">
function productTypeCheck() {
    if (document.getElementById('part').checked) {
    	document.getElementById('divPart').style.display = 'block';
    } else {
    	document.getElementById('divPart').style.display = 'none';
    }
}
</script>
