<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isErrorPage="true"%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="/head.jsp"%>
<meta charset="UTF-8">
<title>Error</title>
</head>
<body>
<div class="container" >
	<div class="card text-center">
		<div class="card-header">
			<h4 class="alert-heading">Watch out! An error was found!</h4>
		</div>
		<div class="card-body">
			<div class="alert alert-danger" role="alert">
				<p class="card-text">
					Sorry for bothering! Please click on the link to access the <a class="alert-link" href="<c:url value='/index.jsp'/>">HOMEPAGE</a>
				</p>
				<hr>
				<p class="mb-0">
					Exception is: <%=exception%></p>
			</div>
		</div>

	</div>
</div>
	<%@ include file="/footer.jsp"%>
</body>
</html>