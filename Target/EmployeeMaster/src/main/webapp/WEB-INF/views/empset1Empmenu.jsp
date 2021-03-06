<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/common.jsp"%>
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


<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript">
	window.onload = function() {
		document.getElementById("actiono").value = "";

		var value = document.getElementById("message").value;

		if (value != '' || value !== "") {
			document.getElementById("dvPassport").style.display = "block";
		} else {

			document.getElementById("dvPassport").style.display = "none";
		}

	}
</script>
<script type="text/javascript">
	function ShowHideDiv(btnPassport) {
		var dvPassport = document.getElementById("dvPassport");

		dvPassport.style.display = "block";
		btnPassport.value = "Submit";
		document.getElementById("myForm").submit();

	}
</script>
<script>
	function resetFields() {
		document.getElementById("myForm").reset();

	}
</script>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/styles/default.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/styles/menu.css" />
</head>
<body class="text-left">

	<%@ include file="/common/menu.jsp"%>

	<div>

		<form:form id="myForm" action="empmenu" method="post"
			commandName="empmenu">

			<div style="padding: 2px;">
				<br>
			</div>
			<div style="padding: 2px;">
				<form:label path="" id="TXT00003" name="TXT00003"
					style="font-weight: bold; position: relative;  margin-left:1px; color:NEUTRAL  ">
SELECT AN ACTION.  THEN CLICK ON SUBMIT. </form:label>
				<br>
			</div>
			<div style="padding: 2px;">
				<br>
			</div>
			<div style="padding: 2px;">

				<form:label path="" id="TXT00005" name="TXT00005"
					style="font-weight: bold; position: relative;  margin-left:168px; color:NEUTRAL  ">
1.  Department Master Maintenance	 </form:label>
				<br>
			</div>

			<div style="padding: 2px;">
				<form:label path="" id="TXT00006" name="TXT00006"
					style="font-weight: bold; position: relative;  margin-left:168px; color:NEUTRAL  ">
2.  Designation Master Maintenance </form:label>
				<br>
			</div>
			<div style="padding: 2px;">
				<form:label path="" id="TXT00007" name="TXT00007"
					style="font-weight: bold; position: relative;  margin-left:168px; color:NEUTRAL  ">
3.  Employee Master Maintenance </form:label>
				<br>
			</div>
			<div style="padding: 2px;">
				<br>
			</div>
			<div style="padding: 2px;">

				<br>
				<form:label path="" id="TXT00004" name="TXT00004"
					style="font-weight: bold; position: relative;  margin-left:12px; color:GREEN  ">
ACTION . . . . </form:label>
				<form:input id="actiono"
					style="margin-left:140px; position: relative; " path="actiono"
					maxlength="1" size="1" required="required" />
			</div>
			<div style="padding: 2px;">
				<br>
			</div>
			<div style="padding: 2px;">

				<br>
			</div>
			<div id="dvPassport" style="padding: 2px;">
				<form:input id="message" path="message"
					style="margin-left:30px; position: relative;font-weight: bold; color:RED "
					maxlength="79" size="79" />
				<br>
			</div>
			<%-- <form:label path=""  id="TXT00008" name="TXT00008" style="font-weight: bold; position: relative;  margin-left:1px; color:BLUE  ">
F3=EXIT   F12=CANCEL </form:label> --%>
			<%-- <form:input  path="dummyo"  style="margin-left:58px; position: relative; " maxlength="1" size="1" /> --%>
			<div>
				<input type="hidden" id="menmap1Id"> <input type="hidden"
					id="menmap1Name"> <input type="hidden" id="menmap1Param1">
				<input type="hidden" id="menmap1Param2">
			</div>
			<div style="padding: 2px;">
				<input onclick="resetFields()" type="button"
					style="margin-left: 30px; position: relative;" value="Cancel"
					name="Cancel" /> <input id="btnPassport" type="Submit"
					style="margin-left: 50px; position: relative;" value="Submit"
					name="Submit" onsubmit="ShowHideDiv(this)" /> <input
					type="Submit" style="margin-left: 50px; position: relative;"
					value="Exit" name="Exit" />

			</div>
		</form:form>

	</div>




	<%@ include file="/common/footer.jsp"%>


</body>
</html>