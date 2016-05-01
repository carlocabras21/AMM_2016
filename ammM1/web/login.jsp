<%-- 
    Document   : login
    Created on : 23-apr-2016, 17.24.58
    Author     : Carlo

    Descrizione: pagina dove l'utente può loggarsi; compare messaggio d'errore nel caso di dati inseriti errati
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <title>CabrasTennis - login</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="author" content="Carlo Cabras">
        <meta name="keywords" content="tennis, shop">
        <link rel="stylesheet" type="text/css" href="style.css" media="screen">
    </head>
    <body>
        <header>
            <nav class="headerNav">
                <ul>
                    <li><a href="descrizione.jsp">Descrizione</a></li>
                    <li><a href="cliente.html">Per il cliente</a></li>
                    <li><a href="venditore.html">Per il venditore</a></li>
                </ul>
            </nav>
            <h1>Login</h1>
        </header>
        
        <jsp:include page="sezionelaterale.jsp" />
        
        <!--
        passo il parametro "autenticazioneFallita" tramite url e nel caso fosse true allora
        stampo messaggio d'errore
        -->
        <c:if test="${param['autenticazioneFallita'] == true}">
            <div class="error">
                <p>Autenticazione fallita</p>
            </div>
        </c:if>
        
        <div class="content">
            <div class="form">
                <form method="POST" action="login.html">
                    <div class="block">
                        <label for="user">Username</label>
                        <input type="text" name="Username" id="user" value="username" />
                    </div>
                    <div class="block">
                        <label for="psw"> Password </label>
                        <input type="password" name="Password" id="psw" value="password" />
                    </div>
                    <div class="buttons">
                        <input type="reset" value="Reset" />
                        <input type="submit" name="Submit" value="Accedi" />
                    </div>
                </form>
            </div>
        </div>
        
        <jsp:include page="footer.jsp" />
    </body>
</html>
