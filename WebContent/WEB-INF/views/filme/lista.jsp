<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
	<head>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
		<meta charset="utf-8">
		<title>Lista de Filmes</title>
	</head>
	
	<body>
		<c:import url="/WEB-INF/views/menu.jsp" />
		
		<div class="row">
			<div class="container">
				<h3 class="text-center">Lista de Filmes</h3>
				<hr>
				
				<div class="navbar">	
					<a href="<%=request.getContextPath()%>/formularioFilme" class="btn btn-success">Novo Filme</a>
					<form class="form-inline" action="buscaFilmes">
						<input class="form-control" type="text" name="busca">
						<input type="submit" value="search" class="btn btn-outline-success">
					</form>
				</div>
				
				<br>
				
				<table class="table table-bordered" style="text-align:center;">
					<thead>
						<tr>
							<th>Id</th>
							<th>Título</th>
							<th>Genero</th>
							<th>Duração</th>
							<th>Lançamento</th>
							<th>Actions</th>
						</tr>
					</thead>
					
					<tbody>
						<c:forEach var="filme" items="${filmes}">
							<tr>
								<td><c:out value="${filme.id}" /></td>
					            <td><c:out value="${filme.titulo}"/></td>
					            <td><c:out value="${filme.genero.nome}"/></td>
					            <td><c:out value="${filme.duracao}"/></td>
					            <td><c:out value="${filme.lancamento}"/></td>
								<td>
									<a href="editFilme?id=<c:out value='${filme.id}' />" class="editar" class="btn btn-info" >
										<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-pencil-square" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
	  									<path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456l-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
	  									<path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/></svg>
									</a>
									&nbsp;&nbsp;
									<a href="deleteFilme?id=<c:out value='${filme.id}'/>" class="botao-exclui" class="btn btn-warning" type="" onclick="return confirm('Excluir Filme?')" >
										<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-trash" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
	  									<path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"/>
	  									<path fill-rule="evenodd" d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4L4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"/></svg>
									</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>	
	</body>
</html>