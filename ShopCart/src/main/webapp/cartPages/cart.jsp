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
					src="<c:url value='/images/6011.jpg' />" alt="" width="30"
					height="24" class="d-inline-block align-text-top"> All
					Products
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
							<th scope="col">Code</th>
							<th scope="col">Price</th>
							<th scope="col">Image</th>
							<th scope="col">Quantity</th>
							<th scope="col">Cancel</th>
						</tr>
					</thead>
					<c:forEach items="${listProductsView}" var="product"
						varStatus="status">
						<tbody class="table align-middle">
							<tr>
								<td>${status.index + 1}</td>
								<td>${product.getProduct().getProduct_ds()}</td>
								<td>${product.getSiteProducCode().getProduct_code_cd()}</td>
								<td>${product.getSiteProductPrice().getPrice_vl()}</td>
								<td><img
									src="<c:url value='/images/cyber-monday-shopping-sales.jpg' />"
									class="rounded mx-auto d-block" height="60" width="60"></td>
								<td>
									<div class="form-group d-flex justify-content-betwwen">

										<form method="post"
											action="<c:url value='/cart/lessproducts'/>">
											<input type="hidden" name="productId"
												value="<c:out value='${product.getSiteproduct().getProduct_id()}'/>" />
											<input type="hidden" name="siteId"
												value="<c:out value='${product.getSiteproduct().getSite_id()}'/>" />
											 <input type="submit" class="btn btn-primary" value="-" />
										</form>

										<input type="text" name="quantity"
											value="${product.getSiteCartProduct().getOrder_qt()}"
											class="form-control" readonly>

										<form method="post"
											action="<c:url value='/cart/moreproducts'/>">
											<input type="hidden" name="productId"
												value="<c:out value='${product.getSiteproduct().getProduct_id()}'/>" />
											<input type="hidden" name="siteId"
												value="<c:out value='${product.getSiteproduct().getSite_id()}'/>" />
											<input type="submit" class="btn btn-primary" value="+" />
										</form>

									</div>
								</td>
								<td>

									<form method="post"
										action="<c:url value='/cart/removeproducts'/>">
										<input type="hidden" name="productId"
											value="<c:out value='${product.getSiteproduct().getProduct_id()}'/>" />
										<input type="hidden" name="siteId"
											value="<c:out value='${product.getSiteproduct().getSite_id()}'/>" />
										<input type="submit" class="btn btn-dangery" value="Remove" />
									</form>
								</td>
							</tr>
						</tbody>
					</c:forEach>
				</table>
				<div class="alert alert-info" role="alert">
					<form method="post" action="<c:url value='/login/checklogin'/>">
						<h3>
							Total:
							<c:out value='${totalPrice}' />
							<input type="hidden" name="siteId"
								value="<c:out value='<%=idSite%>'/>" />
						</h3>
						<input type="submit" class="btn btn-success" value="Checkout" />
					</form>
				</div>
				<span class="badge text-bg-primary">Total Products:
					${numberProductsCart}</span>
			</div>
		</div>
	</div>
	<%@ include file="/footer.jsp"%>
</body>