<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
String idSite = request.getParameter("siteId"); //$NON-NLS-1$
String sessionToken = (String) request.getAttribute("sessionToken"); //$NON-NLS-1$
%>

<!DOCTYPE html>
<html>
<head>
<title>WebSites</title>
<%@ include file="/head.jsp"%>
</head>
<body>
	<div class="container">
		<nav class="navbar navbar-dark bg-dark">
			<div class="container-fluid">
				<form method="post" action="<c:url value='/site/listsites'/>">
					<input type="hidden" name="sessionToken"
						value="<c:out value='<%=sessionToken%>'/>" /> <img
						src="<c:url value='/images/6011.jpg' />" alt="" width="30"
						height="24" class="d-inline-block align-text-top"> 
					<input type="submit" class="btn btn-dark" value="Store" />
				</form>
			</div>
		</nav>
	</div>
	<div class="container p-4">
		<div class="card text-center">
			<div class="card-body">
				<table class="table table-hover caption-top">
					<caption>Choose a Site</caption>
					<thead>
						<tr>
							<th scope="col">#</th>
							<th scope="col">Sites</th>
							<th scope="col">Action</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${listSitesACT}" var="site" varStatus="status">
							<tr>
								<td>${status.index + 1}</td>
								<td>${site.getSite_ds()}</td>
								<td><form method="post"
										action="<c:url value='/product/listproducts'/>">
										<div class="container">
											<div class="row">
												<div class="col-sm">
													<input type="hidden" name="siteId"
														value="<c:out value='${site.getSite_id()}'/>" /> <input
														type="hidden" name="sessionToken"
														value="<c:out value='<%=sessionToken%>'/>" />
												</div>
												<div class="col-sm">
													<input type="submit" class="btn btn-primary" value="Open" />
												</div>
											</div>
										</div>
									</form></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<span class="badge text-bg-primary">Total Sites:
					${numberSitesACT}</span>
			</div>
		</div>
	</div>
	<%@ include file="/footer.jsp"%>
</body>
</html>