<%@page import="model.Product"%>
<%@page import="java.util.ArrayList"%>
<html>
	<head>
		<meta charset="utf-8">
		<title>Cadastro De Estoque</title>
		<link rel="stylesheet" href="/SmartStock/css/bootstrap.min.css" media="screen">
		<script src="/SmartStock/js/jquery-2.2.2.min.js"></script>
		<script src="/SmartStock/js/bootstrap.min.js"></script>
		<script src="/SmartStock/js/smart-stock.js"></script>
		<script src="/SmartStock/js/angular.min.js"></script>
		<script>
				function disableError() {
					if("${error}".length == 0)
						document.getElementById("errorInfo").style.display = "none";;
				}
				angular.module('myApp', []).controller('namesCtrl', function($scope) {
				    $scope.products = [
						<%
						ArrayList<Product> list = (ArrayList<Product>) request.getAttribute("products");
						if(list != null){
							int aux = list.size();
							for(Product product : list) {
								out.println(" { id: "+product.getId()+", name: '"+product.getName()+" "+product.getDescription()+"' }");
								if(--aux != 0)
									out.println(",");
							}
						}
						else{
							RequestDispatcher rd = 
							        request.getRequestDispatcher("/StockRegisterServlet");
							    	rd.forward(request,response);
						}
						%>
				    ];

				});
		</script>
	</head>
	<body onload="disableError()">
		<div id="includedContent"></div>
		<div class="container">
			<div class="col-lg-6">
				<form action="/SmartStock/StockRegisterServlet" method="post">
					    <div class="form-group">
					    	<br>
							<br>
							<br>
							<div class="alert alert-dismissible alert-danger" id="errorInfo">
								<strong>Ocorreu um erro!</strong><a href="#" class="alert-link"></a>${error}
							</div>
							Produto:
							
							<div ng-app="myApp" ng-controller="namesCtrl">
							
							<p>Pesquisar no campo:</p>
							<input type="text" ng-model="search">							
		                    <select name="product_id" multiple="" class="form-control">							  
				  		      <option ng-repeat="x in products | filter:search" value="{{x.id}}">  
		                      	{{ x.name }}
		                      </option>
		                   	 </select>
							<br>Fornecedor:
							<input class="form-control" id="inputDefault" type="text" value="${stock_supplier}" name="stock_supplier">
							<br>Quantidade:
							<input class="form-control" id="inputDefault" type="text" value="${stock_quantity}" name="stock_quantity">
							<br>Preço:
							<input class="form-control" id="inputDefault" type="text" value="${stock_price}" name="stock_price">
							<br>
							<button class="btn btn-success" type="submit">Gravar</a>
						</div>
	 	 		</form>
	 	 	</div>
	 	 </div>
	</body>
</html>
