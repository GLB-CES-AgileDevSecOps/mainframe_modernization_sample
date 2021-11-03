<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%
	 response.setHeader("Cache-Control","no-cache"); //Forces caches to obtain a new copy of the page from the origin server
     response.setHeader("Cache-Control","no-store"); //Directs caches not to store the page under any circumstance
     response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
     response.setHeader("Pragma","no-cache"); //HTTP 1.0 backward compatibility
 %>
 
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/styles/bootstrap.css" />

<%-- <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/styles/default.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/styles/menu.css" /> --%>
 
<script language="JavaScript" type="text/JavaScript" src="<%=request.getContextPath() %>/resources/scripts/jquery-3.5.1.js"></script>
<script language="JavaScript" type="text/JavaScript" src="<%=request.getContextPath() %>/resources/scripts/bootstrap.js"></script>
<%-- <script language="JavaScript" type="text/JavaScript" src="<%=request.getContextPath() %>/resources/scripts/bootstrap.bundle.js"></script> --%>





