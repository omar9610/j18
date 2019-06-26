<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Register as a Customer</title>
	
	<link rel="stylesheet" href="css/style.css" >
	<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
	<script type="text/javascript" src="js/jquery.validate.min.js"></script>
	
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	
	<div align="center">
		<h2 class="pageheading">
			Edit My Profile
		</h2>
	</div>
	
	<div align="center">
		<form action="update_profile" method="post" id="customerForm">
		
		<table class="form">
			<tr>
				<td align="right">E-mail:</td>
				<td align="left"><b>${loggedCustomer.email}</b> (Cannot be changed)</td>
			</tr>
			<tr>
				<td align="right">Nome completo:</td>
				<td align="left"><input type="text" id="fullName" name="fullName" size="45" value="${loggedCustomer.fullname}" /></td>
			</tr>			
			<tr>
				<td align="right">Numero di telefono:</td>
				<td align="left"><input type="text" id="phone" name="phone" size="45" value="${loggedCustomer.phone}" /></td>
			</tr>
			<tr>
				<td align="right">Indirizzo:</td>
				<td align="left"><input type="text" id="address" name="address" size="45" value="${loggedCustomer.address}" /></td>
			</tr>
			<tr>
				<td align="right">Citta:</td>
				<td align="left"><input type="text" id="city" name="city" size="45" value="${loggedCustomer.city}" /></td>
			</tr>
			<tr>
				<td align="right">Codice postale:</td>
				<td align="left"><input type="text" id="zipCode" name="zipCode" size="45" value="${loggedCustomer.zipcode}" /></td>
			</tr>
			<tr>
				<td align="right">Continente:</td>
				<td align="left"><input type="text" id="country" name="country" size="45" value="${loggedCustomer.country}" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<i>(Leave password fields blank if you don't want to change password)</i>
				</td>
			</tr>
			<tr>
				<td align="right">Password:</td>
				<td align="left"><input type="password" id="password" name="password" size="45" /></td>
			</tr>
			<tr>
				<td align="right">Confirm Password:</td>
				<td align="left"><input type="password" id="confirmPassword" name="confirmPassword" size="45" /></td>
			</tr>																			
			<tr><td>&nbsp;</td></tr>
			<tr>
				<td colspan="2" align="center">
					<button type="submit">Save</button>&nbsp;&nbsp;&nbsp;
					<button id="buttonCancel">Cancel</button>
				</td>
			</tr>				
		</table>
		</form>
	</div>

	<jsp:directive.include file="footer.jsp" />
</body>
<script type="text/javascript">

	$(document).ready(function() {
		$("#customerForm").validate({
			rules: {
				email: {
					required: true,
					email: true
				},
				fullName: "required",
				
				confirmPassword: {
					equalTo: "#password"
				},
				
				phone: "required",								
				address: "required",
				city: "required",
				zipCode: "required",
				country: "required",
			},
			
			messages: {
				email: {
					required: "Please enter e-mail address",
					email: "Please enter a valid e-mail address"
				},
				
				fullName: "Please enter full name",
				
				confirmPassword: {
					equalTo: "Confirm password does not match password"
				},
				
				phone: "Please enter phone number",								
				address: "Please enter address",
				city: "Please enter city",
				zipCode: "Please enter zip code",
				country: "Please enter country",
			}
		});
		
		$("#buttonCancel").click(function() {
			history.go(-1);
		});
	});	
</script>
</html>








