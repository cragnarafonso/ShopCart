<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="/head.jsp"%>
<title>Welcome</title>
</head>
<body>

	<div class="container">
		<nav class="navbar navbar-dark bg-dark">
			<div class="container-fluid">
				<a class="navbar-brand" href="<c:url value='/index.jsp' />"> <img
					src="<c:url value='/images/6011.jpg' />" alt="" width="30"
					height="24" class="d-inline-block align-text-top"> Online Store</a> 
			</div>
		</nav>
	</div>

	<div class="container p-4">
		<div class="card text-center">
			<img
				style="-webkit-user-select: none; display: block; margin: auto; padding: env(safe-area-inset-top) env(safe-area-inset-right) env(safe-area-inset-bottom) env(safe-area-inset-left)"
				src="<c:url value='/images/black-friday-elements-assortment.jpg' />"
				width="583" height="389">
			<div class="card-body">
				<p class="card-text">Come in and buy our products.</p>
				<form method="post" action=" <c:url value='/site/listsites'/>">
					<div class="container">
						<div class="row">
							<div class="col-sm p-2">
								<input type="submit" class="btn btn-primary"
									value="Open Online Store" />
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<%@ include file="/footer.jsp"%>
</body>
</html>