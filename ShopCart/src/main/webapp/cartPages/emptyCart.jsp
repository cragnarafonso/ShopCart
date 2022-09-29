<!DOCTYPE html>
<html>
<head>
<title>Cart</title>
<%@ include file="/head.jsp"%>
</head>
<body>
	<%@ include file="/nav.jsp"%>

	<div class="container p-4">
		<nav class="navbar bg-light">
			<div class="container-fluid">
				<a class="navbar-brand" href="###"> <img
					src="<c:url value='/images/6011.jpg' />" alt="" width="30" height="24"
					class="d-inline-block align-text-top"> All Products
				</a>
			</div>
		</nav>
		<div class="card text-center">
			<div class="card-body">
				<table class="table table-hover">
					<thead class="table-dark">
						<tr>
							<th scope="col">#</th>
							<th scope="col">Name</th>
							<th scope="col">Price</th>
							<th scope="col">Image</th>
							<th scope="col">Quantity</th>
							<th scope="col">Cancel</th>
						</tr>
					</thead>
				</table>
				<p style="align-content: center;">Sorry! There are no products
					in the cart!</p>
			</div>
			<span class="badge text-bg-primary">#</span>
		</div>
	</div>
	<%@ include file="/footer.jsp"%>
</body>