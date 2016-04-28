<%-- 
    Document   : compra
    Created on : 25-apr-2016, 16.10.31
    Author     : Carlo

    Descrizione: questa pagina mostra il risultato dell'acquisto, cioè se esso è andato bene allora mostra il riepilogo,
                 altrimenti mostra un messaggio di errore
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
                    <li><a href="cliente.jsp">Per il cliente</a></li>
                </ul>
            </nav>
            <h1>Risultato acquisto</h1>
        </header>
        
        <jsp:include page="sezionelaterale.jsp" />
        
        <!--
            Uso il parametro "confermato" passato via URL per ocmunicare al cliente l'esito dell'acquisto
        -->
        <div class="content">
            <c:if test="${param['confermato'] == true}"> <!-- se è true, stampo il riepilogo dell'oggetto -->
                    <p>Acquisto CONFERMATO!</p>
                    <h3>Riepilogo oggetto:</h3>
                            <table>
                                <tr>
                                    <th>Nome</th>
                                    <th>Foto</th>
                                    <th>Prezzo</th>
                                </tr>
                                <tr>
                                    <td>${param['nome']}</td>
                                    <td>
                                        <img title="${param['nome']}" 
                                             alt="foto di ${param['nome']}" 
                                             src="${param['img']}" 
                                             width="100" height="100">
                                    </td>
                                    <td>${param['prezzo']}</td>
                                </tr>
                            </table>
            </c:if>
            <c:if test="${param['confermato'] == false}"> <!-- se è false, stampo un messaggio d'errore -->
                <p>Acquisto non completato, il tuo saldo non &egrave; sufficiente.</p>
            </c:if>
       
        </div>
        
        <jsp:include page="footer.jsp" />
    </body>
</html>