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
			<div class="alert alert-danger d-flex align-items-center"
				role="alert">
				<div>Sorry but your Username does not exist. Enter a valid
					Username!!!</div>
			</div>
			<div class="card-body">
				<form action="<c:url value='/login/login'/>" method="post">
					<div class="form-group">
						<label>E-mail</label> 
						<input type="text" class="form-control" name="client" placeholder="Enter your Username" required> 
						<input type="hidden" name="siteId" value="<c:out value='<%=idSite%>'/>" />
						<input type="submit" class="btn btn-primary" value="<c:out value='Login'/>" />
					</div>
				</form>
			</div>
		</div>
	</div>
	<%@ include file="/footer.jsp"%>
</body>
</html>