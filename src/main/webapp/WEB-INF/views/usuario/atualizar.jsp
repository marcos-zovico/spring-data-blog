<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Editar Usuário</title>
<link type="text/css" rel="stylesheet"
	href="<c:url value="/css/style.css" />">

</head>
<body>
	<c:import url="../menu.jsp" />
	<br>

		<c:url var="save" value="/usuario/update/senha" />
		<form:form modelAttribute="usuario" action="${save}" method="post">
			<form:hidden path="id" />
			<fieldset class="grupo">
				<legend>Editar senha</legend>
				<div class="campo">
					<form:label path="senha">Senha</form:label>
					<form:password path="senha" required="true" />
				</div>
				<div>
					<input type="submit" value="Salvar"> <input type="reset"
						value="Limpar">
				</div>
			</fieldset>
		</form:form>

		<c:url var="save" value="/usuario/update" />
		<form:form modelAttribute="usuario" action="${save}" method="post">

			<form:hidden path="id" />
			<fieldset class="grupo">
				<legend>Editar Nome e E-mail</legend>
				<div class="campo">
					<form:label path="nome">Nome do Usuário</form:label>
					<br>
					<form:input path="nome" type="text" title="Inserir Nome" />
				</div>
				<div class="campo">
					<form:label path="email">E-mail</form:label>
					<br>
					<form:input path="email" type="email" required="true" />
				</div>
				<div>
					<input type="submit" value="Salvar"> <input type="reset"
						value="Limpar">
				</div>
			</fieldset>
		</form:form>

		<security:authorize access="hasAuthority('ADMIN')">
		<c:url var="save" value="/usuario/update/perfil" />
		<form:form modelAttribute="usuario" action="${save}" method="post">
			<form:hidden path="id" />
			<fieldset class="grupo">
				<legend>Editar Perfil</legend>
				<div class="campo">
					<form:label path="perfil">Perfil</form:label>
					<br>
					<form:select path="perfil">
						<form:option value="ADMIN" label="ADMIN" />
						<form:option value="AUTOR" label="AUTOR" />
						<form:option value="LEITOR" label="LEITOR" />
					</form:select>
				</div>
				<div>
					<input type="submit" value="Salvar"> 
					<input type="reset"	value="Limpar">
				</div>
			</fieldset>
		</form:form>
		</security:authorize>


</body>
</html>