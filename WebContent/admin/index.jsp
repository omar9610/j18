<%--
  Created by IntelliJ IDEA.
  User: alael
  Date: 25/04/2019
  Time: 18:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MagazineStore Administration</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
    <jsp:directive.include file="header.jsp" />
    <div align="center">
        <h2 class="pageheading">Admin Dashboard</h2>
    </div>

    <div align="center">
        <hr width="60%"/>
        <h2 class="pageheading">Quick Actions:</h2>
        <b>
        <a href="">Nuova Rivista</a> &nbsp;
        <a href="create_user">Nuovo Utente</a> &nbsp;
        <a href="create_category">Nuova Categoria</a> &nbsp;
        <a href="create_customer">Nuovo Cliente</a> &nbsp;
        </b>
    </div>

    <div align="center">
        <hr width="60%"/>
        <h2 class="pageheading">Vendite recenti:</h2>
    </div>

    <div align="center">
        <hr width="60%"/>
        <h2 class="pageheading">Recensioni recenti:</h2>
    </div>

    <div align="center">
        <hr width="60%"/>
        <h2 class="pageheading">Statistiche:</h2>
    </div>


    <jsp:directive.include file="footer.jsp" />
</body>
</html>
