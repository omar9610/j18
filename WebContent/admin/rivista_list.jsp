
<%@ page language="java" contentType="text/html;charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  <!-- Presa dal sito di oracle(JSTL Core taglib)
                                                                        Che mi serve per iterare una collezione, infatti
                                                                        uso il forEach in questo file-->

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html" charset="ISO-8859-1">
    <title>Gestione Riviste - MagazineStore Administration</title>
    <link rel="stylesheet" href="../css/style.css">
    <script type="text/javascript" src="../js/jquery-3.4.0.min.js"></script>
	<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
</head>
<body>
<jsp:directive.include file="/admin/header.jsp" />
    <div align="center">
        <h2 class="pageheading">Gestione Riviste</h2>
        <h3><a href="new_rivista">Crea nuova Rivista</a></h3>
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
                <th>Immaggine</th>
                <th>Titolo</th>
                <th>Categoria</th>
                <th>Prezzo</th>
                <th>Data di Publicazione</th>
                <th>Azioni</th>
            </tr>
                
            <c:forEach var="rivista" items="${listRivista}" varStatus="status">
                <tr>
                    <%--&lt;%&ndash;prima colonna: index&ndash;%&gt;--%>
                    <td>${status.index + 1}</td>
                    <td>${rivista.rivistaId}</td>
                    
                    <td>
                   	<img src="data:image/jpg;base64,${rivista.base64Image}" width="84" height="110" /> 
                    </td>
                    <td>${rivista.title}</td>
                    <td>${rivista.categoria.name}</td>
                    <td>&euro;${rivista.price}</td>
                    <td>${rivista.publishDate}</td>
                    <td>
                    	<a href="edit_rivista?id=${rivista.rivistaId}">Modifica</a>
                    	<a href="javascript:void(0)" class="deleteLink" id="${rivista.rivistaId}">Elimina</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        
    </div>



	<jsp:directive.include file="footer.jsp" />	
	
	<script>
		$(document).ready(function () {
			$('#publishDate').datepicker({dateFormat: 'dd-mm-yy'});
			$(".deleteLink").each(function () {
				$(this).on("click", function () {
					rivistaId= $(this).attr("id");
					if(confirm("Sei sicuro di eliminare qusta rivista: " + rivistaId + "?"))	{
						window.location = 'delete_rivista?id=' + rivistaId;
					}	
				});		
			});
		});
		
	</script>
</body>
</html>
