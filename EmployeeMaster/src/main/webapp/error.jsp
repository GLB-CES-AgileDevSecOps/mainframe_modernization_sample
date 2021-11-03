<!doctype html>
<html lang="en">
<head>
<%@ include file="/common/common.jsp"%>
<title>: : Employee Master Maintenance  Application : :</title>

<style>
.bd-placeholder-img {
	font-size: 1.125rem;
	text-anchor: middle;
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
}

@media ( min-width : 768px) {
	.bd-placeholder-img-lg {
		font-size: 3.5rem;
	}
}
</style>

<%-- <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/styles/default.css" /> --%>
<%-- <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/styles/menu.css" /> --%>
</head>
<body class="text-center">

<%@ include file="/common/menu.jsp"%>
	
	<form class="form-signin" action="empmenu">

		<img class="mb-4" src="<%=request.getContextPath() %>/resources/images/atos-syntel-6.png" alt="" width="130" height="72">
		
	</form>

<%@ include file="/common/footer.jsp"%>
</body>
</html>
 