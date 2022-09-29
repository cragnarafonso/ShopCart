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
		<div class="card-header my-3">All Orders</div>
		<div class="row">
			<div class="accordion" id="accordionExample">
				<c:forEach items="${listOrders}" var="order" varStatus="status">
					<div class="accordion-item">
						<h2 class="accordion-header" id="headingOne">
							<button class="accordion-button" type="button"
								data-bs-toggle="collapse" data-bs-target="#collapseOne"
								aria-expanded="true" aria-controls="collapseOne">Order
								number: ${status.index + 1}</button>
						</h2>
						<div id="collapseOne" class="accordion-collapse collapse show"
							aria-labelledby="headingOne" data-bs-parent="#accordionExample">
							<code class="prettyprint"> ${order.getOrder_jd()}</code>
						</div>
					</div>
				</c:forEach>
				<span class="badge text-bg-primary">Total Orders: ${numberOrders}</span>
			</div>
		</div>
	</div>
	<%@ include file="/footer.jsp"%>
</body>
</html>