<%-- 
    Document   : venditore
    Created on : 23-apr-2016, 16.52.06
    Author     : Carlo

    Desciriozne: contiene le operazioni effettuabili da un venditore, ovvero aggiungere un oggetto.
                 pagina divisa in più parti:
                 - messaggi di errore nel caso di autenticazoine non effettuata/fallita (si è loggato un utente)
                 - riepilogo oggetto aggiunto
                 - form inserimento nuovo oggetto
                 
                 non mi fa scrivere commenti all'interno del <c:choose>, li scriverò qua:
                 
                 riga 43:
                    Uso il parametro passato via URL "venditoreLoggedIn" che può avere 3 valori:
                    - true: l'autenticazione del cliente è andata a buob fine
                    - false: si è autenticato un cliente, in quanto al momento del login setto questa variabile a false
                    - null: non si è autenticato nessuno
                
                riga 67:
                    Uso il parametro "oggettoAggiunto" per stampare il riepilogo dell'oggetto nel caso esso sia true

                riga 100:
                    form invio nuovo oggetto

                riga 122:
                    Uso il parametro "prezzoSbagliato" per segnalare all'utente il caso in cui esso inserisca
                    il prezzo in un formato sbagliato.

--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>
    <head>
        <title>CabrasTennis - venditore</title>
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
                    <li><a href="login.jsp">Login </a></li>
                </ul>
            </nav>
            <h1>Venditore</h1>
        </header>
        
        <jsp:include page="sezionelaterale.jsp" />
                
        <c:choose>
            <c:when test="${sessionScope['venditoreLoggedIn'] == null}">
                <div class="content">
                    <div class="error">
                        <p>Autenticazione non effettuata</p>
                    </div>
                </div>
            </c:when>
            <c:when test="${sessionScope['venditoreLoggedIn'] == false}">
                <div class="content">
                    <div class="error">
                        <p>Autenticazione fallita</p>
                    </div>
                </div>
            </c:when>
            <c:when test="${requestScope['oggettoAggiunto'] == true}">
                <p>Oggetto Aggiunto! Caratteristiche: </p>
                <div class="content">
                    <table>
                        <tr>
                            <th>Nome</th>
                            <th>Foto</th>
                            <th>Quantit&agrave; disponibile</th>
                            <th>Prezzo</th>
                        </tr>
                        <tr>
                            <td>${nuovoOggetto.getNome()}</td>
                            <td>
                                <img title="${nuovoOggetto.getNome()}" 
                                     alt="Foto di ${nuovoOggetto.getNome()}" 
                                     src="${nuovoOggetto.getUrlImmagine()}" 
                                     width="100" height="100">
                            </td>
                            <td>${nuovoOggetto.getQuantitaDisponibile()}</td>
                            <td>${nuovoOggetto.getPrezzo()}</td>
                        </tr>
                      

                    </table>
                        
                        Torna alla <a href="venditore.html">pagina dedicata al venditore</a>
                        
                </div>
            </c:when>
            <c:otherwise>
            <div class="content">
                <p> Puoi aggiungere un oggetto che sar&agrave; successivamente venduto. </p>

                <div class="form">
                    <form method="POST" action="venditore.html">
                        <div>
                             <c:if test="${requestScope['nomeVuoto'] == true}">
                                <div class="error">
                                    Il campo "Nome" non può essere vuoto.
                                </div>
                            </c:if>
                            <label for="nome">Nome</label>
                            <input type="text" name="nome" id="nome" value="nome" />
                        </div>
                        <div>
                            <label for="urlimg">URL immagine descrittiva</label>
                            <input type="text" name="urlimg" id="urlimg" value="URL immagine" />
                        </div>
                        <div>
                            <label for="descrizione">Descrizione</label>
                            <textarea rows="6" cols="40" name="descrizione" id="descrizione">descrizione</textarea>
                        </div>
                        <div>
                             <c:if test="${requestScope['prezzoSbagliato'] == true}">
                                <div class="error">
                                    Il campo "Prezzo" deve contenere solo numeri. Usa il punto come separatore tra interi e decimali.
                                </div>
                            </c:if>
                            
                            <label for="prezzo">Prezzo</label>
                            <input type="text" name="prezzo" id="prezzo" value="0.00" />
                        </div>
                        <div>
                             <c:if test="${requestScope['quantitaSbagliata'] == true}">
                                <div class="error">
                                    Il campo "Quantit&agrave;" deve contenere un numero intero >0.
                                </div>
                            </c:if>
                            <label for="disponibile">Quantit&agrave; disponibile</label>
                            <input type="number" name="disponibile" id="disponibile" value="1" min="1"/>
                        </div>
                        <div class="buttons">
                            <input type="reset" value="Reset" />
                            <input type="submit" name="Submit" value="Invia" />
                        </div>
                    </form>
                </div>
            </div>
            </c:otherwise>
        </c:choose>
        
        
        <jsp:include page="footer.jsp" />
    </body>
</html>

