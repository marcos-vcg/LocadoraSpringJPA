<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Pagina de Login</title>
	</head>
	
	<body>
		<h2>Página de Login da Locadora Digital</h2>
		<form action="efetuaLogin" method="post">
		Login: <input type="text" name="login" /> <br /> 
		Senha: <input type="password" name="senha" /> <br />
		<input type="submit" value="Entrar na Locadora" /> 
		</form>
	</body>
</html>