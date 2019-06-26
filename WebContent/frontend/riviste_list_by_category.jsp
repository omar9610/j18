<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Riviste in ${category.name}</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
   <jsp:directive.include file="header.jsp" />

   <div class="center">
       <br><br>
       <h2>${category.name} </h2>
   </div>
   <div class="rivista_group">
   	<c:forEach items="${listRiviste}" var = "rivista">
   	<div class="rivista" >
   	<div>
   	<a href="view_rivista?id=${rivista.rivistaId }">
   	 <img class="rivista-small" src="data:image/jpg;base64,${rivista.base64Image}" />
   	</a>
   	</div>
   	<div>
   	
   	<a href="view_rivista?id=${rivista.rivistaId }">
   	<b>${rivista.title }</b>
   	</a></div>
   	<div>Rating *****</div>
				
				<div>
					<b>$ ${rivista.price}</b>
				</div>
   	</div>
   
   	</c:forEach>
   
   </div>

    <jsp:directive.include file="footer.jsp" />
</body>
</html>

