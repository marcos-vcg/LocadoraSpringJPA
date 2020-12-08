<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Página Inicial</title>
	</head>
	
	<body>
		<h2>Página inicial da Locadora Digital</h2>
    	<p>Bem vindo, ${usuarioLogado.login}</p> 

		<a href="hello">Olá!</a>
		<a href="student">Add Estudante</a>
		<a href="novaTarefa">Adicionar Tarefa!</a>
		<a href="filmes">Lista de Filmes!</a>
		
	</body>
</html>