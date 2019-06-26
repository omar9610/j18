<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<meta http-equiv="Content-Typer" content="text/html; charset=ISO-8859-1">
<title>${rivista.title}- Riviste Online</title>
<link rel="stylesheet" href="css/style.css">
<script type="text/javascript" src="js/jquery-3.4.0.min.js"></script>
</head>
<body>
	<jsp:directive.include file="header.jsp" />

	<div class="center">
		<table class="rivista">
			<tr>
				<td colspan="3" align="left" >
					<p id="rivista-title">${rivista.title}</p>
				</td>
			</tr>
			<tr>
				<td rowspan="2">
				<img class="rivista-large" src="data:image/jpg;base64,${rivista.base64Image}"
					/></td>
				<td valign ="top" align="left">Rating ***** </td>
				<td valign="top" rowspan="2" width="20%"><h2>$ ${rivista.price}</h2> <br/><br/>
					<button id="buttonAddToCart">Add to Cart</button>
				</td>
			</tr>
			<tr>
				<td id="description">
				${rivista.description}
				</td>
			</tr>
			<tr><td> &nbsp; </td></tr>
		
			
			
		</table>
	</div>

	<jsp:directive.include file="footer.jsp" />
	<script type="text/javascript">
	$(document).ready(function() {
	
	$("#buttonAddToCart").click(function() {
		window.location ='add_to_cart?id='+ ${rivista.rivistaId};
	});		
	});

	</script>
</body>
</html>

