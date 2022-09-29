<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<title>Login</title>
<%@ include file="/head.jsp"%>
</head>

<body>
	<%@ include file="/nav.jsp"%>

	<div class="container">
		<div class="card w-50 mx-auto my-5">
			<div class="card-header text-center">Hello, please enter your details</div>
			<div class="card-body">
				<form action="<c:url value='/login/login'/>" method="post">
					<div class="form-group">
						<label>E-mail</label> 
						<input type="text" class="form-control" name="client" placeholder="Enter your Username" required> 
						<input type="hidden" name="siteId" value="<c:out value='<%=idSite%>'/>" />
						<input type="submit" class="btn btn-primary" value="Login" />
					</div>
				</form>
			</div>
		</div>
	</div>
	<%@ include file="/footer.jsp"%>
</body>
</html>