<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Order Details - Evergreen Magazinestore Administration</title>
	<link rel="stylesheet" href="../css/style.css" >
	<script type="text/javascript" src="../js/jquery-3.3.1.min.js"></script>	
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	
	<div align="center">
		<h2 class="pageheading">Details of Order ID: ${order.ordineId}</h2>		
	</div>
	
	<c:if test="${message != null}">
	<div align="center">
		<h4 class="message">${message}</h4>
	</div>
	</c:if>
	
	<div align="center">
		<h2>Order Overview:</h2>	
		<table>
			<tr>
				<td><b>Ordered By: </b></td>
				<td>${order.cliente.fullname}</td>
			</tr>
			<tr>
				<td><b>Rivisita Copies: </b></td>
				<td>${order.rivistaCopies}</td>
			</tr>
			<tr>
				<td><b>Total Amount: </b></td>
				<td><fmt:formatNumber value="${order.total}" type="currency" /></td>
			</tr>			
			<tr>
				<td><b>Recipient Name: </b></td>
				<td>${order.recipientName}</td>
			</tr>
			<tr>
				<td><b>Recipient Phone: </b></td>
				<td>${order.recipientPhone}</td>
			</tr>
			<tr>
				<td><b>Payment Method: </b></td>
				<td>${order.paymentMethod}</td>
			</tr>
			<tr>
				<td><b>Shipping Address: </b></td>
				<td>${order.shippingAddress}</td>
			</tr>
			<tr>
				<td><b>Order Status: </b></td>
				<td>${order.status}</td>
			</tr>
			<tr>
				<td><b>Order Date: </b></td>
				<td>${order.orderDate}</td>
			</tr>												
		</table>
	</div>
	<div align="center">
		<h2>Ordered Magazine</h2>
		<table border="1">
			<tr>
				<th>Index</th>
				<th>Book Title</th>
				<th>Price</th>
				<th>Quantity</th>
				<th>Subtotal</thd>
			</tr>
			<c:forEach items="${order.dettagliOrdines}" var="dettagliOrdine" varStatus="status">
			<tr>
				<td>${status.index + 1}</td>
				<td>
				<img src="data:image/jpg;base64,${dettagliOrdine.rivista.base64Image}" width="48" height="64" /> 
					${dettagliOrdine.rivista.title}
				</td>
				<td><fmt:formatNumber value="${dettagliOrdine.rivista.price}" type="currency"/></td>
				<td>${dettagliOrdine.quantity}</td>
				<td><fmt:formatNumber value="${dettagliOrdine.subtotal}" type="currency"/></td>
			</tr>
			</c:forEach>
			<tr>
				<td colspan="4" align="right">
					<b><i>TOTAL:</i></b>
				</td>
				<td>
					<b>${order.rivistaCopies}</b>
				</td>
				<td>
					<b><fmt:formatNumber value="${order.total}" type="currency" /></b>
				</td>
			</tr>
		</table>
	</div>
	<div align="center">
		<br/>
		<a href="edit_order?id=${order.ordineId}">Edit this Order</a>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="">Delete this Order</a>
	</div>
	
	<jsp:directive.include file="footer.jsp" />
	
	
</body>
</html>