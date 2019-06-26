<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
  
<!DOCTYPE html>
<html>
<head>
	<meta http- charset="ISO-8859-1">
	<title>Nuova Rivista</title> 
	
    <link rel="stylesheet" href="../css/style.css">
    <link rel="stylesheet" href="../css/jquery-ui.min.css">
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"> 
    <link rel="stylesheet" href="..//css/richtext.min.css">
    
    
    <script type="text/javascript" src="../js/jquery-3.4.0.min.js"></script>
	<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
	
	<script type="text/javascript" src="../js/jquery-ui.min.js"></script>
 	<script type="text/javascript" src="../js/jquery.richtext.min.js"></script>


</head>
<body>
	<jsp:directive.include file="header.jsp" />
    <div align="center">
        <h2 class="pageheading">
        	<c:if test="${rivista != null}">
        		Modifica Rivista
        	</c:if>
        	<c:if test="${rivista == null}">
        		Crea Nuova Rivista
        	</c:if>
        	
        </h2>
    </div>

    <div align="center">
    	<c:if test="${rivista != null}">
    		<form action="update_rivista" method="post" id="rivistaForm"  enctype="multipart/form-data">
    		<input type="hidden" name="rivistaId" value="${rivista.rivistaId}">
    		
    	</c:if>
    	
    	<c:if test="${rivista == null}">
    		<form action="create_rivista" method="post" id="rivistaForm" enctype="multipart/form-data">
    	</c:if>
    	
			<table class="form">
			  	<tr>
			  		<td>Categoria:</td>
			  	<td>
			  	
			  	 <select name="category">
			  		 <c:forEach items="${listCategory}" var="category">
			  		 <c:if test="${category.categoriaId eq rivista.categoria.categoriaId}">
			  		 	 <option value="${category.categoriaId}"selected>
			  		 </c:if>
			  		 <c:if test="${category.categoriaId ne rivista.categoria.categoriaId}">
			  		  <option value="${category.categoriaId}">
			  		 </c:if>
			  	 			${category.name}
			  	 		</option>
			  	 	</c:forEach>
			  	 </select>
			  	 </td>
			    </tr>
      			<tr>
      				<td align="right">Titolo:</td>
      				<td align="left"><input type="text" id="title" name="title" size="20" value="${rivista.title}"/></td>
      			</tr>
      			<tr>
      				<td align="right">Data di publicazione:</td>
      				<td align="left"><input type="text" id="publishDate" name="publishDate" size="20" 
      				    value="<fmt:formatDate  pattern ='dd/mm/yyyy' value='${Rivista.publishDate}'/> "/></td>
      			</tr>
      			<tr>
      				<td align="right">Immaggine:</td>
      				<td align="left">
      					<input type="file" id="RivistaImage" name="RivistaImage" size="20"/><br/>
      				<img id="thumbnail" alt="Image Preview" style="width:15%; margin-top:0px"
      				src="data:image/jpg;base64,${rivista.base64Image}"
      				/>
      				</td>
      			</tr>
      				<tr>
      				<td align="right">Prezzo:</td>
      				<td align="left"><input type="text" id="price" name="price" size="20" value="${rivista.price}"/></td>
      			</tr>
      			<tr>
      				<td align="right">Descrizione:</td>
      				<td align="left">
      				<textarea rows="5" cols="50"name="description" id="description">${rivista.description}</textarea>
      				</td>
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
		$('#publishDate').datepicker({dateFormat: 'dd-mm-yy'});
		$('#description').richText();

		$('#RivistaImage').change(function(){
			showImageThumbnail(this);
		});
		$("#rivistaForm").validate({
			rules: {
				category: "required",
				title: "required",
				publishDate: "required",
				
				<c:if test="${rivista == null}">
				RivistaImage: "required",
				</c:if>
				price: "required",
				description: "required",
			},
			
			messages: {		
				category: " Selezione la Categoria",
				title: " Inserisci il titolo",
				publishDate: " Inserisci la data di pubblicazione",
				RivistaImage: " Inserisci Immagine",
				price: " Inserisci Prezzo",
				description: " Inserisci Descrizione"
			}
		});
		$("#buttonCancel").click(function () {
			history.go(-1);
		});
	});
	function showImageThumbnail(fileInput){
		var file = fileInput.files[0];
		var reader= new FileReader();
		reader.onload=function(e){
			$('#thumbnail').attr('src',e.target.result);
		};
		reader.readAsDataURL(file);
	}
</script>


</html>













