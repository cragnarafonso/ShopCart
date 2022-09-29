<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


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
				<a class="navbar-brand"  href="<c:url value='/site/listsites'/>"> <img src="<c:url value='/images/6011.jpg' />"
					alt="" width="30" height="24" class="d-inline-block align-text-top">
					Online Store
				</a>
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
							<th scope="col">Information</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td
								style="height: 50px; padding-top: 15px; align-content: center; color: red; font-weight: bold; border-radius: 10px">Sorry!
								There are no websites available. We apologize for the
								inconvenience.</td>
						</tr>
					</tbody>
				</table>
				<span class="badge text-bg-primary">${numberSitesACT}</span>
			</div>

		</div>
	</div>
	<%@ include file="/footer.jsp"%>
</body>
</html>