<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
<title>Result for ${keyword} Riviste Online</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<jsp:directive.include file="header.jsp" />

	<div class="center">
		
		<c:if test="${fn:length(result)==0 }">
			<h2>Nesusn risultato per "${keyword}"</h2>

		</c:if>
		<c:if test="${fn:length(result)>0 }">
			<div class="rivista_group">
				<center><h2>Result for "${keyword}"</h2></center>
				<c:forEach items="${result}" var="rivista">
				<div>
					<div id="search-img">
						<div >
							<a href="view_rivista?id=${rivista.rivistaId }">
							 <img class="rivista-small"
								src="data:image/jpg;base64,${rivista.base64Image}"/>
							</a>
						</div>
					</div>
					<div id="search-description">
						<div>

						<h2>	<a href="view_rivista?id=${rivista.rivistaId }"> <b>${rivista.title }</b>
							</a></h2>
						</div>
						<div>Rating *****</div>

						<div>
						<p>${fn:substring(rivista.description,0,100)}...</p>
						</div>
					</div>
					<div id="search-price">
						<h3><b>$ ${rivista.price}</b></h3>
						<h3><a href="">Aggiungi al Carrello</a></h3>
					</div>
					</div>
				</c:forEach>

			</div>
		</c:if>

	</div>

	<jsp:directive.include file="footer.jsp" />
</body>
</html>

