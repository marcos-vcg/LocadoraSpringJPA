<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html lang="pt-br">
	<head>
		<meta charset="utf-8">
		<title>Locadora Digital</title>		
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
		<link rel="stylesheet" href="resources/css/style.css">
		<meta charset="utf-8">
		<link rel="icon" href="resources/images/favicon.png">
	</head>
	
	<body>
	
		<c:import url="/WEB-INF/views/menu.jsp" />
		
		<br>
		
		<div class="row">
			<div class="container">	
				<h1>Locadora Digital</h1>
				
				<h3 class="text-center">Seja Bem Vindo</h3>
				
				<hr>
				
				<div class="container text-center">
					<img class="text-center" alt="" src="resources/images/locadora.png" >
				</div>		
			</div>
		</div>
	</body>
</html>