<!doctype html>
<html lang="en">
<head>
<%@ include file="/common/common.jsp"%>
<title>: : Employee Master Maintenance : :</title>

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

.body {
  height: 100%;
}

.body {
  display: -ms-flexbox;
  display: flex;
  -ms-flex-align: center;
  align-items: center;
  padding-top: 1px;
  padding-bottom: 1px;
  background-color: #f5f5f5;
}
</style>


<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/styles/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/styles/default.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/styles/menu.css" />
</head>

<body>

<%-- <%@ include file="/common/menu.jsp"%> --%>
	
	<div class="container">
	
	<header class="blog-header py-3">
		<div class="row flex-nowrap justify-content-between align-items-center">
			<%---- <div class="col-4 pt-1">
				<a class="text-muted" href="#">About</a>
			</div> --%>
			<div class="col-12 text-center">
				<%-- <img class="mb-4" src="<%=request.getContextPath() %>/resources/images/OPERS-logo_es.png" alt="" width="30" height="20">&nbsp;&nbsp; --%>
				<a class="blog-header-logo text-dark" href="#">Employee Master Maintenance</a>
			</div>
			
			<%-- <div class="col-4 d-flex justify-content-end align-items-center">
			
				<a class="text-muted" href="#" aria-label="Search"> 
					<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="none" stroke="currentColor" stroke-linecap="round"
						stroke-linejoin="round" stroke-width="2" class="mx-3" role="img"
						viewBox="0 0 24 24" focusable="false">
						<title>Search</title>
						<circle cx="10.5" cy="10.5" r="7.5" />
						<path d="M21 21l-5.2-5.2" />
					</svg>
				</a> 
				<a class="btn btn-sm btn-outline-secondary" href="#">News</a>
			</div> --%>
			
		</div>
	</header>
	
	<div class="nav-scroller py-1 mb-2">
	    <nav class="nav d-flex justify-content-between">
	      <a class="p-2 text-muted" href="<%=request.getContextPath() %>/">Home</a>
	      <a class="p-2 text-muted" href="#">Globe</a>
	      <a class="p-2 text-muted" href="#">Technology</a>
	      <a class="p-2 text-muted" href="#">Design</a>
	      <a class="p-2 text-muted" href="#">Culture</a>
	      <a class="p-2 text-muted" href="#">Business</a>
	      <a class="p-2 text-muted" href="#">Products</a>
	      <a class="p-2 text-muted" href="#">Opinion</a>
	      <a class="p-2 text-muted" href="#">Administrator</a>
	      <a class="p-2 text-muted" href="#">Contact Us</a>
	      <a class="p-2 text-muted" href="#">Help</a>
	      <a class="p-2 text-muted" href="#">Feedback</a>
	    </nav>
	  </div>
	
	
	
	<form class="form-signin" action="empmenu">

		<%-- <img class="mb-4" src="<%=request.getContextPath() %>/resources/images/OPERS-logo_m.png" alt="" width="108" height="72"> --%>
		<h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
		<%-- <label for="inputEmail" class="sr-only">Email address</label> 
		<input type="email" id="inputEmail" class="form-control" placeholder="Email address" required autofocus> --%>
		<label for="inputUserId" class="sr-only">User Id</label> 
		<input type="text" id="inputUserId" class="form-control" placeholder="User Id" required autofocus>
		<label for="inputPassword" class="sr-only">Password</label> 
		<input type="password" id="inputPassword" class="form-control" placeholder="Password" required>
		<div class="checkbox mb-3"></div>
		<button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
		<%-- <p class="mt-5 mb-3 text-muted">&copy; 2020s-2020</p> --%>
	</form>

		
<%-- <%@ include file="/common/footer.jsp"%> --%>

  <div class="my-5 pt-5 text-muted text-center text-small" style="padding-top: 1px; padding-bottom: 1px; ">
    <p class="mb-1">&copy; 2020-2021                  Atos|Syntel</p>
    <ul class="list-inline">
      <li class="list-inline-item"><a href="#">Privacy</a></li>
      <li class="list-inline-item"><a href="#">Terms</a></li>
      <li class="list-inline-item"><a href="#">Support</a></li>
    </ul>
  </div>
  
</div>



</body>
</html>
 