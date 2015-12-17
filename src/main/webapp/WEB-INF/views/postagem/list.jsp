<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Postagens</title>
<link type="text/css" rel="stylesheet" href="<c:url value="/css/style.css" />">
</head>
<body>
<c:import url="../menu.jsp"/>
	<br>
	<fieldset>
		<legend>Lista de postagens</legend>
		<table class="table">
			<tr>
				<th>Código</th>
				<th>Título do post</th>
				<th>Permalink</th>
				<th>Data de postagem</th>
				<th>Autor</th>
				<th>Categorias</th>
				<th>Acão</th>
			</tr>
			<c:forEach var="postagem" items="${postagens}" varStatus="i">
			<tr bgcolor='${i.count % 2!= 0 ? '#f1f1f1' : 'white' }'>
								
				<td>${postagem.id}</td>
				<td>${postagem.titulo}</td>
				<td>${postagem.permaLink}</td>
				<td>${postagem.dataPostagem}</td>
				<td>${postagem.autor.nome}</td>
				<td>
					<c:forEach var="c" items="${postagem.categorias}">
						[ ${c.descricao} ]
					
					</c:forEach>
				</td>
				<td>
					<c:url var="update" value="/postagem/update/${postagem.id}" />
					<a href="${update}" title="Editar">&#9445</a>
					<c:url var="delete" value="/postagem/delete/${postagem.id}" />
					<a href="${delete}" title="Excluir">&#9447</a>
				</td>
			</tr>
			</c:forEach>
		</table>
	</fieldset>
</body>
</html>