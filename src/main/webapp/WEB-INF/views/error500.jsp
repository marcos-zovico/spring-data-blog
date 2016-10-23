<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Error Page</title>
</head>
<body>
	<h1>Http Status 500</h1>
	<p>Um erro foi encontrado. Tente esta operção mais tarde</p>
	<div>
	   <button onclick="javascript:history.back();">Voltar</button>
	</div>
	
	<!-- 
		Exceção: ${pageContext.exception.message}	
	 -->
	
</body>
</html>