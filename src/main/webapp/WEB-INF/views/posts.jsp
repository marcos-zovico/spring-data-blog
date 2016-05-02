<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>
<link type="text/css" rel="stylesheet"
	href="<c:url value="/css/style.css" />">

</head>
<body>
	<fieldset class="header">
		<h1>Blog do Curso de SpringData JPA</h1>
	</fieldset>

	<c:import url="menu.jsp" />
	<br>
	<fieldset>
		<c:forEach var="p" items="${page.content}">
			<div>
				<div>
					<h2>
						<a href="<c:url value="/${p.permaLink}" />" title="${p.titulo}">
							${p.titulo}</a>
					</h2>
					<p>
						Autor: <a href="<c:url value="/autor/${p.autor.id}/page/1"/>">
							${p.autor.nome}</a> |
						<fmt:parseDate var="date" value="${p.dataPostagem}"
							pattern="yyyy-MM-dd'T'HH:mm:ss" />
						Data:
						<fmt:formatDate value="${date}" type="both" />
						| # ${fn:length(p.comentarios)} Comentario(s)
					</p>
				</div>
				<div>
					<p class="post-texto">
						<c:forTokens var="resumo" items="${p.texto}" delims=" " begin="0"
							end="60">
							${resumo}
						</c:forTokens>
						<a href="<c:url value="/${p.permaLink}" />"> [Leia mais]</a>
					</p>
				</div>
				<div>
					<p class="post-categ">
						<c:forEach var="c" items="${p.categorias}">
							<a href="<c:url value="/categoria/${c.permaLink}/page/1"/>"
								title="${c.descricao}"> ${c.descricao} </a>
						</c:forEach>
					</p>
				</div>
			</div>
		</c:forEach>
		<div align="center">
			[
			<c:forEach var="p" begin="1" end="${page.totalPages}">
				<c:choose>
					<c:when test="${ (p-1) eq page.number }">
						<label style="font-size: 18pt;">${p}</label>
					</c:when>
					<c:otherwise>
						<a href="<c:url value="${urlPagination}/${p}"/>">${p}</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			]
		</div>
	</fieldset>
</body>
</html>