<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
<!-- <script
	src="scripts/validation.js"></script> -->

<script type="text/javascript">
	window.onload = function() {
		//document.getElementById("actiono").value = "";
		document.getElementById("dvPassport").style.display = "none";
		var value = document.getElementById("messageo").value;

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
		<form:form id="myForm" method="POST" action="empmap1"
			commandName="empmap1">

			<div style="padding: 2px;">
				<br>
			</div>
			<div style="padding: 2px;">
				<form:label path="" id="TXT00003" name="TXT00003"
					style="font-weight: bold; position: relative;  margin-left:1px; color:BLACK  ">
SELECT ACTION AND TYPE A EMPLOYEE CODE, THEN PRESS ENTER. </form:label>
				<br>
			</div>
			<div style="padding: 2px;">
				<br>
			</div>

			<div style="padding: 2px;">
				<br>
			</div>
	</div>
	<div style="padding: 2px;">
		<form:label path="" id="TXT00006" name="TXT00006"
			style="font-weight: bold; position: relative;  margin-left:1px; color:GREEN  ">
ACTION : </form:label>
		<form:input path="actiono"
			style="margin-left:1px; position: relative; color:TURQUOISE "
			maxlength="1" size="1" required="required" />
		<form:label path="" id="TXT00007" name="TXT00007"
			style="font-weight: bold; position: relative;  margin-left:50px; color:BLACK  ">
 (A)DD EMPLOYEE DETAILS </form:label>
		<br>
	</div>
	<div style="padding: 2px;">
		<form:label path="" id="TXT00008" name="TXT00008"
			style="font-weight: bold; position: relative;  margin-left:180px; color:BLACK  ">
 (C)HANGE EMPLOYEE DETAILS </form:label>
		<br>
	</div>
	<div style="padding: 2px;">
		<form:label path="" id="TXT00009" name="TXT00009"
			style="font-weight: bold; position: relative;  margin-left:180px; color:BLACK  ">
 (D)ELETE EMPLOYEE DETAILS</form:label>
		<br>
	</div>

	<div style="padding: 2px;">
		<form:label path="" id="TXT00004" name="TXT00004"
			style="font-weight: bold; position: relative;  margin-left:1px; color:GREEN  ">
EMPLOYEE CODE : </form:label>
		<form:input style="margin-left:85px; position: relative;"
			path="wsEmpId" maxlength="8" size="15" required="required" />
		<br>
	</div>
	<div style="padding: 2px;">
		<form:label path="" id="TXT00004" name="TXT00004"
			style="font-weight: bold; position: relative;  margin-left:1px; color:GREEN  ">
EMPLOYEE NAME : </form:label>
		<form:input style="margin-left:80px; position: relative;"
			path="wsEmpName" maxlength="50" size="50" />
		<br>
	</div>
	<div style="padding: 2px;">
		<form:label path="" id="TXT00004" name="TXT00004"
			style="font-weight: bold; position: relative;  margin-left:1px; color:GREEN  ">
DEPARTMENT CODE : </form:label>
		<form:input style="margin-left:60px; position: relative;"
			path="wsDeptcode" maxlength="5" size="10" />
		<br>
	</div>
	<div style="padding: 2px;">
		<form:label path="" id="TXT00004" name="TXT00004"
			style="font-weight: bold; position: relative;  margin-left:1px; color:GREEN  ">
DEPARTMENT DESCRIPTION : </form:label>
		<form:input style="margin-left:1px; position: relative;"
			path="wsDeptDesc" maxlength="50" size="50" readonly="true" />
		<br>
	</div>
	<div style="padding: 2px;">
		<form:label path="" id="TXT00004" name="TXT00004"
			style="font-weight: bold; position: relative;  margin-left:1px; color:GREEN  ">
DESIGNATION CODE : </form:label>
		<form:input style="margin-left:60px; position: relative;"
			path="wsDesgcd" maxlength="5" size="10" />
		<br>
	</div>
	<div style="padding: 2px;">
		<form:label path="" id="TXT00004" name="TXT00004"
			style="font-weight: bold; position: relative;  margin-left:1px; color:GREEN  ">
DESIGNATION DESCRIPTION : </form:label>
		<form:input style="margin-left:1px; position: relative;"
			path="wsDesgDesc" maxlength="50" size="50" readonly="true" />
		<br>
	</div>
	<div style="padding: 2px;">
		<label id="TXT00006" name="TXT00006"
			style="font-weight: bold; position: relative; margin-left: 1px; color: GREEN">
			BASIC PAY : </label>
		<form:input path="wsEmpBasic"
			style="margin-left: 138px; position: relative; color: TURQUOISE"
			maxlength="11" size="50" readonly="true" />
		<br>
	</div>
	<div style="padding: 2px;">
		<label id="TXT00006" name="TXT00006"
			style="font-weight: bold; position: relative; margin-left: 1px; color: GREEN">
			HRA PAY : </label>
		<form:input path="wsEmpHra"
			style="margin-left: 147px; position: relative; color: TURQUOISE"
			maxlength="11" size="50" readonly="true" />
		<br>
	</div>
	<div style="padding: 2px;">
		<label id="TXT00006" name="TXT00006"
			style="font-weight: bold; position: relative; margin-left: 1px; color: GREEN">
			GROSS PAY : </label>
		<form:input path="wsEmpGrosspay"
			style="margin-left: 130px; position: relative; color: TURQUOISE"
			maxlength="11" size="50" readonly="true" />
		<br>
	</div>
	<div style="padding: 2px;">
		<label id="TXT00006" name="TXT00006"
			style="font-weight: bold; position: relative; margin-left: 1px; color: GREEN">
			LOCATION : </label>
		<form:input path="wsEmpLoc"
			style="margin-left: 135px; position: relative; color: TURQUOISE"
			maxlength="50" size="50" />
		<br>
	</div>
	<div style="padding: 2px;">
		<br>
	</div>



	<div style="padding: 2px;">

		<div style="padding: 2px;">
			<form:input path="message"
				style="margin-left: 1px; position: relative; color:RED; font-weight:bold;"
				maxlength="79" size="79" />
		</div>

		<input onclick="resetFields()" type="button"
			style="margin-left: 30px; position: relative;" value="Cancel"
			name="Cancel" /> <input id="btnPassport" type="Submit"
			style="margin-left: 50px; position: relative;" value="Submit"
			name="Submit" onSubmit="ShowHideDiv(this)" /> <input
			id="btnPassport" type="Submit"
			style="margin-left: 50px; position: relative;" value="Proceed"
			name="Proceed" onSubmit="ShowHideDiv(this)" /> <input type="button"
			style="margin-left: 50px; position: relative;" value="Exit"
			name="Exit" onclick="location.href='/empmenu'" />

	</div>

	</form:form>

	</div>


	<%@ include file="/common/footer.jsp"%>

</body>
</html>