<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
	<head>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
		<meta charset="utf-8">
		<title>Formulario de Filme</title>
	</head>
	
	<body>
		<c:import url="/WEB-INF/views/menu.jsp" />
		
		<div class="container col-md-8">
			<div class="card card-body">	
				<c:if test="${filme.id == null}"><h2>Novo Filme</h2></c:if>
				<c:if test="${filme.id != null}"><h2>Editar Filme</h2></c:if>
				
				<form action=
							<c:if test="${filme.id == null}">"insertFilme"</c:if>
							<c:if test="${filme.id != null}">"updateFilme"</c:if> 
							method="post" enctype="multipart/form-data">
					
					<c:if test="${filme.id != null}"><input type="hidden" name="id" value='${filme.id}'/></c:if>
			
					<div class="container text-right">
						<img id="mostrarImagem" <c:if test="${filme.id != null}">src="imagemFilme?id=<c:out value='${filme.id}' />"</c:if>  alt="Foto de Capa"  style="width: 100px; height: 130px"  /> 					
						<br>
						<input name="arquivo" id="imagem" type="file" accept="image/*"  onchange="document.getElementById('mostrarImagem').src = window.URL.createObjectURL(this.files[0])">
					</div>						

					<div class="form-group">
						<label>Título*</label> <form:errors path="filme.titulo" cssStyle="color:red" />
						<input name="titulo" type="text" value="<c:out value='${filme.titulo}' />" class="form-control" required="required"><br>
	
						<label>Genero*</label> <form:errors path="filme.genero" cssStyle="color:red" />
						<select name="genero.id" class="form-control" required="required">
								<option value="" >  --  Selecione --  </option>
							<c:forEach var="gen" items="${generos}">
								<option value="<c:out value='${gen.id}'/>"  <c:if test="${filme.genero.id == gen.id}">selected</c:if>  > <c:out value="${gen.nome}" /> </option>
							</c:forEach>
						</select><br>
	
						<label>Cópias*</label> <form:errors path="filme.copias" cssStyle="color:red" />
						<input name="copias" type="number" step="1" value="<c:out value='${filme.copias}' />" class="form-control" required="required"><br>

						<label>Categoria*</label> <form:errors path="filme.categoria" cssStyle="color:red" />
							<select name="categoria.id" class="form-control" required="required">
									<option value="" selected disabled hidden="hidden">  --  Selecione --  </option>
								<c:forEach var="categ" items="${categorias}">
									<option value="<c:out value='${categ.id}' />" <c:if test="${filme.categoria.id == categ.id}">selected</c:if> > <c:out value="${categ.nome}" /></option>
								</c:forEach>
							</select><br>
			
						<label>Lançamento</label> 
						<input type="date" name="lancamento" step="1" value="<c:out value='${filme.lancamento}' />" class="form-control"><br>
	
						<label>Duração</label> 
						<input type="text" name="duracao" value="<c:out value='${filme.duracao}'/>" class="form-control" ><br>
	
						<label>Sinopse</label> 
						<textarea name="sinopse" rows="" cols="" class="form-control"><c:out value='${filme.sinopse}' /></textarea><br><br>		
					</div>
					
					<div class="container text-right">
						<a href="filme" class="btn btn-danger">Cancel</a>
						&nbsp;&nbsp;
						<button type="submit" class="btn btn-success" >Save</button>
					</div>
				</form>
			</div>
		</div>
	</body>
</html>