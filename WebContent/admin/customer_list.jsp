

<%@ page language="java" contentType="text/html;charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  <!-- Presa dal sito di oracle(JSTL Core taglib)
                                                                        Che mi serve per iterare una collezione, infatti
                                                                        uso il forEach in questo file-->

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html" charset="ISO-8859-1">
    <title>Gestione Clienti - MagazineStore Administration</title>
    <link rel="stylesheet" href="../css/style.css">
     <script type="text/javascript" src="../js/jquery-3.4.0.min.js"></script>
</head>
<body>
<jsp:directive.include file="/admin/header.jsp" />
    <div align="center">
        <h2 class="pageheading">Gestione Cliente</h2>
        <h3><a href="customer_form.jsp">Crea un nuovo Cliente</a></h3>
    </div>
    
    <c:if test="${message != null}">
    <div align="center">
    	<h4 class="message">${message}</h4>
    </div>
	</c:if>
    <div align="center">
        <table border="1" cellpadding="5">
            <tr>
                <th>Index</th>
                <th>ID</th>
                <th>E-mail</th>
                <th>Nome Completo</th>
                <th>Città</th>
                <th>Paese</th>
                <th>Data Registrazione</th>
                <th>Azioni</th>
            </tr>
           
            <c:forEach var="customer" items="${listCustomer}" varStatus="status">
                <tr>
                    <%--&lt;%&ndash;prima colonna: index&ndash;%&gt;--%>
                    <td>${status.index + 1}</td>
                    <td>${customer.clienteId}</td>
                    
                  	<td>${customer.email}</td>
                    <td>${customer.fullname}</td>
                    <td>${customer.city}</td>
                    <td>${customer.country}</td>
                    <td>${customer.registerDate}</td>
                    <td>
                    	<a href="edit_customer?id=${customer.clienteId}">Modifica</a>
                    	<a href="javascript:void(0)" class="deleteLink" id="${customer.clienteId}">Elimina</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        
    </div>



	<jsp:directive.include file="footer.jsp" />	
	
	<script>
		$(document).ready(function () {
			$(".deleteLink").each(function () {
				$(this).on("click", function () {
					clienteId= $(this).attr("id");
					if(confirm("Sei sicuro di eliminare cliente con ID: " + clienteId + "?"))	{
						window.location = 'delete_customer?id=' + clienteId;
					}	
				});		
			});
		});
		
	</script>
</body>
</html>

