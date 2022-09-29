<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<title>Home</title>
<%@ include file="/head.jsp"%>
</head>
<body>
	<%@ include file="/nav.jsp"%>

	<div class="container p-4">
		<div class="card-header my-3">All Products</div>
		<div class="row"
			style="height: 50px; padding-top: 15px; align-content: center; color: red; font-weight: bold; border-radius: 10px">
			<p style="align-content: center;">Sorry! There are no products
				available. We apologize for the inconvenience.</p>
			<span class="badge text-bg-primary">${numberProductsView}</span>
		</div>
	</div>
	<%@ include file="/footer.jsp"%>
</body>
</html>