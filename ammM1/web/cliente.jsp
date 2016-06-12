<%-- 
    Document   : cliente
    Created on : 23-apr-2016, 16.51.41
    Author     : Carlo

--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>CabrasTennis - cliente</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="author" content="Carlo Cabras">
        <meta name="keywords" content="tennis, shop">
        <link rel="stylesheet" type="text/css" href="style.css" media="screen">
        <script src="js/jquery-2.2.4.min.js"></script>
        <script src="js/javascript.js"></script>
    </head>
    
        <header>
            <nav  class="headerNav">
                <ul>
                    <li><a href="descrizione.jsp">Descrizione</a></li>
                    <li><a href="login.html">Login</a></li>
                </ul>
            </nav>
            <h1>Cliente</h1>
        </header>
        
        <jsp:include page="sezionelaterale.jsp" />
        <!--
            Uso il parametro passato via URL "clienteLoggedIn" che può avere 3 valori:
            - true: l'autenticazione del cliente è andata a buon fine
            - false: si è autenticato un venditore, in quanto al momento del login setto questa variabile a false
            - null: non si è autenticato nessuno
        -->
        <c:choose>
            <c:when test="${sessionScope['clienteLoggedIn'] == null}">
                <div class="content">
                    <div class="error">
                        <p>Autenticazione non effettuata</p>
                    </div>
                </div>
            </c:when>
            <c:when test="${sessionScope['clienteLoggedIn'] == false}">
                <div class="content">
                   <div class="error">
                        <p>Autenticazione fallita</p>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                
                <c:choose>
                    <c:when test="${param['id'] != null}">
                        <div class="content">
                            <h3>Riepilogo oggetto:</h3>
                            <table>
                                <tr>
                                    <th>Nome</th>
                                    <th>Foto</th>
                                    <th>Prezzo</th>
                                    <th>Conferma acquisto</th>
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
                                    <td>
                                        <form method="POST" action="cliente.html">
                                            <input type="hidden" name="id" value="${param['id']}">
                                            <input type="submit" name="Submit" value="Conferma">
                                        </form>

                                    </td>
                                </tr>
                            </table>
                        </div>
                    </c:when> 
                    <c:when test="${param['confermato'] == true}"> <!-- se è true, stampo il riepilogo dell'oggetto -->
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
                        <a href="cliente.html">Torna alla pagina dedicata al cliente</a>
                    </c:when>
                    <c:when test="${param['confermato'] == false}"> <!-- se è false, stampo un messaggio d'errore -->
                        <p>Acquisto non completato, il tuo saldo non &egrave; sufficiente.</p>
                        <a href="cliente.html">Torna alla pagina dedicata al cliente</a>
                    </c:when>
                    <c:otherwise> <!-- caso base: stampa tabella oggetti -->
                        <div class="content">
                            
                            <label for="Filtra" id="etichettaRicerca">Ricerca</label>
                            <input class="barraRicerca" type="text" name="Filtra" id="Ricerca"/>
                            <div class="error" id="messaggioErrore"></div>
                            <table id="tabellaOggetti" class="tabellaOggetti">
                                <tr>
                                    <th>Nome</th>
                                    <th>Foto</th>
                                    <th>Quantit&agrave; disponibile</th>
                                    <th>Prezzo</th>
                                    <th>Aggiungi al carrello</th>
                                </tr>

                                <c:forEach var="oggetto" items="${oggetti}">
                                    <tr>
                                        <td>${oggetto.getNome()}</td>
                                        <td>
                                            <img title="${oggetto.getNome()}" 
                                                 alt="${oggetto.getNome()}" 
                                                 src="${oggetto.getUrlImmagine()}" 
                                                 width="100" height="100">
                                        </td>
                                        <td>${oggetto.getQuantitaDisponibile()}</td>
                                        <td>${oggetto.getPrezzo()}</td>
                                        <td>
                                            <a href="cliente.jsp?id=${oggetto.getId()}&nome=${oggetto.getNome()}&img=${oggetto.getUrlImmagine()}&prezzo=${oggetto.getPrezzo()}"> Aggiungi al carrello </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </c:otherwise>
                </c:choose>
                
            </c:otherwise>
            
        </c:choose>
        <jsp:include page="footer.jsp" />
    
</html>

