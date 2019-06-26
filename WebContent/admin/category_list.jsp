
<%@ page language="java" contentType="text/html;charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  <!-- Presa dal sito di oracle(JSTL Core taglib)
                                                                        Che mi serve per iterare una collezione, infatti
                                                                        uso il forEach in questo file-->

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html" charset="ISO-8859-1">
    <title>Gestire le categorie - MagazineStore Administration</title>
    <link rel="stylesheet" href="../css/style.css">
    <script type="text/javascript" src="../js/jquery-3.4.0.min.js"></script>
	<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
    
</head>
<body>
<jsp:directive.include file="/admin/header.jsp" />
    <div align="center">
        <h2 class="pageheading">Gestione Categorie</h2>
        <h3><a href="category_form.jsp">Crea una nuova Categoria</a></h3>
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
                <th>Nome</th>
                <th>Azioni</th>
            </tr>
            <%--Per ogni oggetto utente nella collezione creamo una nuova riga di tabella--%>
            <%--var="user" è il nome della variable usata nel ciclo for
                items: punta al nome della variabile "listUser" nel request(presente nella classe ListUserServlet)
                che deve corrispondere al nome che abbiamo specificato come attributo--%>
            <c:forEach var="cat" items="${listCategory}" varStatus="status">
                <tr>
                    <%--&lt;%&ndash;prima colonna: index&ndash;%&gt;--%>
                    <td>${status.index + 1}</td>
                    <td>${cat.categoriaId}</td>
                    <td>${cat.name}</td>
                    <td>
                    	<a href="edit_category?id=${cat.categoriaId}">Modifica</a>
                    	<a href="javascript:void(0)" class="deleteLink" id="${cat.categoriaId}">Elimina</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        
    </div>



	<jsp:directive.include file="footer.jsp" />	
	
	<script type="text/javascript">
	$(document).ready(function () {
		$(".deleteLink").each(function () {
			$(this).on("click", function () {
				categoriaId= $(this).attr("id");
				if(confirm("Sei sicuro di eliminare la categoria con ID: " + categoriaId + "?"))	{
					window.location = 'delete_category?id=' + categoriaId;
				}	
			});		
		});
	});
	</script>
</body>
</html>
