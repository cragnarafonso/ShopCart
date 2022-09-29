
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page import="com.database.models.*"%>
<%@ page errorPage="error.jsp" %>  

<%

String idSite = request.getParameter("siteId"); //$NON-NLS-1$
String identiSessionTk = "sessionTk" + idSite; //$NON-NLS-1$

SiteCartModel siteCartSession = new SiteCartModel();

siteCartSession = (SiteCartModel) session.getAttribute(identiSessionTk);

%>

<div class="container">
	<nav class="navbar navbar-dark bg-dark">
		<div class="container-fluid">
			<form method="post" action="<c:url value='/login/logout'/>">
				<input type="hidden" name="siteId"
					value="<c:out value='<%=idSite%>'/>" /> <img
					src="<c:url value='/images/6011.jpg' />" alt="" width="30"
					height="24" class="d-inline-block align-text-top"> <input
					type="submit" class="btn btn-dark" value="Store" />
			</form>
		</div>
	</nav>
	<ul class="nav nav-tabs  nav-fill justify-content-center">
		<li class="nav-item">
			<form method="post" action="<c:url value='/product/listproducts'/>">
				<input type="hidden" name="siteId"
					value="<c:out value='<%=idSite%>'/>" />
				<input type="submit" class="nav-link" value="Home" />
			</form>
		</li>

		<li class="nav-item">
			<form method="post" action="<c:url value='/cart/cartproducts'/>">
				<input type="hidden" name="siteId"
					value="<c:out value='<%=idSite%>'/>" /> 
				<input type="submit" class="nav-link" value="Cart" /> <span
					class="badge rounded-pill text-bg-warning">${numberProductsCart}</span>
			</form>
		</li>

		<c:if test="<%=siteCartSession != null%>">

			<li class="nav-item">
				<form method="post" action="<c:url value='/order/listorder'/>">
					<input type="hidden" name="siteId"
						value="<c:out value='<%=idSite%>'/>" /> 
					<input type="submit" class="nav-link" value="Orders" />
				</form>
			</li>

			<li class="nav-item">
				<form method="post" action="<c:url value='/login/logout'/>">
					<input type="hidden" name="siteId"
						value="<c:out value='<%=idSite%>'/>" /> 
					<input type="submit" class="nav-link" value="Logout" />
				</form>
			</li>

		</c:if>

		<c:if test="<%=siteCartSession == null%>">
			<li class="nav-item">
				<form method="post" action="<c:url value='/login/checklogin'/>">
					<input type="hidden" name="siteId"
						value="<c:out value='<%=idSite%>'/>" /> 
					<input type="submit" class="nav-link" value="Login" />
				</form>
			</li>
		</c:if>
	</ul>
</div>