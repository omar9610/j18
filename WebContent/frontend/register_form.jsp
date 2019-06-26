<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html"; charset-ISO-8859-1>
	<title>Registrazione Cliente</title> 
	
    <link rel="stylesheet" href="css/style.css">
    
    <script type="text/javascript" src="js/jquery-3.4.0.min.js"></script>
	<script type="text/javascript" src="js/jquery.validate.min.js"></script>
	

</head>
<body>
	<jsp:directive.include file="header.jsp" />
    <div align="center">
        <h2 class="pageheading">
        	Registrazione Cliente
        </h2>
    </div>

    <div align="center">
    	<form action="register_customer" method="post" id="customerForm">
    		
			<table class="form">
      				<td align="right">E-mail:</td>
      				<td align="left"><input type="text" id="email" name="email" size="45"/></td>
      			</tr>
      			<tr>
      				<td align="right">Nome Completo:</td>
      				<td align="left"><input type="text" id="fullname" name="fullname" size="45"/></td>
      			</tr>
      			<tr>
      				<td align="right">Password</td>
      				<td align="left">
      					<input type="password" id="password" name="password" size="45"/><br/>
      				</td>
      			</tr>
      				<tr>
      				<td align="right">Conferma Password:</td>
      				<td align="left">
      					<input type="password" id="confirmPassword" name="confirmPassword" size="45"/><br/>
      				</td>
      			</tr>
      			<tr>
      				<td align="right">Numero di telefono:</td>
      				<td align="left"><input type="text" id="phone" name="phone" size="45"/> </td>
      			</tr>
      			
      			<tr>
      				<td align="right">Indirizzo:</td>
      				<td align="left"><input type="text" id="address" name="address" size="45"/> </td>
      			</tr>
      			<tr>
      				<td align="right">Città:</td>
      				<td align="left"><input type="text" id="city" name="city" size="45"/> </td>
      			</tr>
      			<tr>
      				<td align="right">Codice Postale</td>
      				<td align="left"><input type="text" id="zipcode" name="zipcode" size="10"/> </td>
      			</tr>
      			<tr>
      				<td align="right">Paese:</td>
      				<td align="left"><input type="text" id="country" name="country" size="45"/> </td>
      			</tr>
      			<tr><td>&nbsp;</td></tr>
      			<tr>
      				<td colspan="2" align="center">
      					<button type="submit">Salva</button>&nbsp;&nbsp;&nbsp;
      					<button id="buttonCancel">Cancella</button>
      				</td>
      			</tr>
      		</table>
		</form>
    </div>
    
    <jsp:directive.include file="footer.jsp" />
</body>

<script type="text/javascript">

	$(document).ready(function () {
	
		$("#customerForm").validate({
			rules: {
				email:{
					required: true,
					email: true
				},
				fullname: "required",
				password: "required",
				confirmPassword: {
					required: true,
					equalTo: "#password"
				},
				phone: "required",
				address: "required",
				city: "required",
				zipcode: "required",
				country: "required",
			},
			
			messages: {		
				email: {
					required: "Inserisci l'email",
					email: "Inserisci un email valido"
				},
				fullname: "Inserisci nome completo",
				password: "Inserisci password",
				confirmPassword: {
					required: "Conferma password",
					equalTo: "Password non corrisponde"
				},
				phone: "Inserisci numero di telefono",
				address: "Inserisci indirizzo",
				city: "Inserisci città",
				zipcode: "Inserisci codice postale",
				country: "Inserisci paese",
			}
		});
		$("#buttonCancel").click(function () {
			history.go(-1);
		});
	});
	
</script>


</html>













