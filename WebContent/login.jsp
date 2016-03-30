<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Entrar</title>
</head>
<body>

<form action="/SmartStock/LoginServlet" method="POST">
	<input type="text" name="username" placeholder="Nome de usuario ..."><br>
	<input type="password" name="password" placeholder="Senha ..."><br>
	<input type="submit" name="submit" value="Entrar"><br>
</form>

</body>
</html>