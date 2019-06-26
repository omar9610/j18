    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div align="center">  
    <div>
         <a href="${pageContext.request.contextPath}/admin/">
        <img src="../images/logo.png" />
        </a>
    </div>
    <div>
        Welcome, <c:out value="${sessionScope.useremail}" /> | <a href="logout">Logout</a>
        <br/><br/>
    </div>
    <div id="headermenu">
   		<div>
    		<a href="list_users">
    			<img src="../images/user.png" height="66" width="66"/><br>Utenti
    		</a>
   		</div>
   		<div>
    		<a href="list_category">
    			<img src="../images/category.png" height="66" width="66"/><br>Categorie
    		</a>
   		</div>
		<div>
    		<a href="list_magazines">
    			<img src="../images/magazine.png" height="55" width="66"/><br>Riviste
    		</a>
   		</div>
   		<div>
    		<a href="list_customer">
    			<img src="../images/customer.png" height="60" width="66"/><br>Clienti
    		</a>
   		</div>
   		<div>
    		<a href="list_order">
    			<img src="../images/order.png" height="66" width="66"/><br>Ordini
    		</a>
   		</div>
       
    </div>
</div>
