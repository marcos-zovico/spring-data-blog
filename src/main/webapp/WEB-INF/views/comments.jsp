<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div>
	<c:url var="save" value="/comentario/save" />
	<form:form modelAttribute="comentario" action="${save}" method="post">
		<input type="hidden" value="${postagem.permaLink}" name="permaLink">

		<div>
			<form:label path="texo">Digite seu comentário</form:label>
			<form:textarea path="texto" rows="5" cols="80" required="true" />
		</div>
		<br>

		<div>
			<input type="submit" value="Salval"> 
			<input type="reset"  value="Limpar">
		</div>
	</form:form>
</div>