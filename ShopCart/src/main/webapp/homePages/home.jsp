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
		<div class="row">

			<c:forEach items="${listProductsView}" var="product">
				<div class="col-6 col-md-4 my-3">
					<div class="card" style="width: 18rem;">
						<img
							src="<c:url value='/images/cyber-monday-shopping-sales.jpg' />"
							class="card-img-top">
						<div class="card-body">
							<h3 class="card-title">${product.getProduct().getProduct_ds()}</h3>
							<p class="card-title">${product.getSiteProducCode().getProduct_code_cd()}</p>
							<p class="card-title">${product.getSiteProducCode().getProduct_code_tp()}</p>
							<p class="card-title">${product.getProduct().getProduct_jd()}</p>
							<h5 class="card-title">${product.getSiteProductPrice().getPrice_vl()}
								${product.getSiteProductPrice().getCurrency_cd()}</h5>
							<div class="col-sm-4">
								<form method="post"
									action="<c:url value='/product/addproducts'/>">
									<div class="container">
										<div class="row">
											<div class="col-auto">
												<input type="hidden" name="siteId"
													value="<c:out value='${product.getSiteproduct().getSite_id()}'/>"/>
												<input type="hidden" name="productId"
													value="<c:out value='${product.getSiteproduct().getProduct_id()}'/>"/>
											</div>
											<div class="col-sm p-2">
												<input type="submit" class="btn btn-primary"
													value="Add to Cart" />
											</div>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
			<span class="badge text-bg-primary">Total Products: ${numberProductsView}</span>
		</div>
	</div>


	<%@ include file="/footer.jsp"%>
</body>
</html>