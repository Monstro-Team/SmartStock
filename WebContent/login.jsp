<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/SmartStock/css/bootstrap.min.css">
<script src="/SmartStock/js/jquery-2.2.2.min.js"></script>
<script src="/SmartStock/js/bootstrap.min.js"></script>
<script type="text/javascript">
	$(function(){
    	$("#includedContent").load("http://localhost:8080/SmartStock/menu_login.html");
	});
</script>
<script>
	function disableError() {
		if("${error}".length == 0)
			document.getElementById("errorLogin").style.display = "none";
	}
</script>
<title>Login</title>
</head>
	<body onload="disableError()">
		<div id="includedContent"></div>
			<div class="container">
				<div class="col-lg-6">
				 <fieldset>
					<legend>Login</legend>
						<div class="form-group">
							<form action="/SmartStock/LoginServlet" method="POST">
								<div class="alert alert-dismissible alert-danger" id="errorLogin">
									${error}
								</div>
								<br>
								Nome de Usuário:
								<input class="form-control" value = "${username}" type="text" name="username"><br>
								Senha:
								<input class="form-control" type="password" name="password"><br>
								<input class="btn btn-success" type="submit" name="submit" value="Entrar"><br>
							</form>
						</div>
					</fieldset>
				</div>
			</div>
	</body>
</html>