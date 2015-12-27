<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Categoria</title>
<link type="text/css" rel="stylesheet"
	href="<c:url value="/css/style.css" />">

</head>
<body>
	<c:import url="../menu.jsp" />
	<br>

	<c:url var="save" value="/categoria/save" />
	<form:form modelAttribute="categoria" action="${save}" method="post">

		<form:hidden path="id" />
		<fieldset class="grupo">
			<legend>Cadastro de categoria</legend>
			<div class="campo">
				<form:label path="descricao">Desrição da categoria</form:label>
				<br>
				<form:input path="descricao" type="text" required="true" size="60" />
			</div>
			<div>
				<input type="submit" value="Salvar"> <input type="reset"
					value="Limpar">
			</div>
		</fieldset>
	</form:form>

	<fieldset class="grupo">
		<legend>Lista de categorias</legend>
		<table class="table">
			<tr>
				<th>Código</th>
				<th>Descrição</th>
				<th>Permalink</th>
				<th>Acão</th>
			</tr>
			<c:forEach var="categoria" items="${categorias}" varStatus="i">
				<tr bgcolor='${i.count % 2!= 0 ? '#f1f1f1' : 'white' }'>

					<td>${categoria.id}</td>
					<td>${categoria.descricao}</td>
					<td>${categoria.permaLink}</td>
					<td><c:url var="update"
							value="/categoria/update/${categoria.id}" /> <a href="${update}"
						title="Editar">&#9445</a> <c:url var="delete"
							value="/categoria/delete/${categoria.id}" /> <a href="${delete}"
						title="Excluir">&#9447</a></td>
				</tr>
			</c:forEach>
		</table>
	</fieldset>

</body>
</html>