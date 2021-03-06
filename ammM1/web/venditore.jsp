<%-- 
    Document   : venditore
    Created on : 23-apr-2016, 16.52.06
    Author     : Carlo

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
                    <li><a href="login.html">Login </a></li>
                </ul>
            </nav>
            <h1>Venditore</h1>
        </header>
        
        <jsp:include page="sezionelaterale.jsp" />
        <!--
            Uso il parametro passato via URL "venditoreLoggedIn" che può avere 3 valori:
               - true: l'autenticazione del venditore è andata a buon fine
               - false: si è autenticato un venditore, in quanto al momento del login setto questa variabile a false
               - null: non si è autenticato nessuno
       -->  
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
            <c:when test="${param['id'] != null}">
                    <div class="content">
                        <p>In questa sezione puoi modificare l'oggetto:</p>
                    <form method="POST" action="venditore.html">
                        <div>
                             <c:if test="${requestScope['nomeVuoto'] == true}">
                                <div class="error">
                                    Il campo "Nome" non può essere vuoto.
                                </div>
                            </c:if>
                            <label for="nome">Nome</label>
                            <input type="text" name="nome" id="nome" value="${param['nome']}" />
                        </div>
                        <div>
                            <label for="urlimg">URL immagine descrittiva</label>
                            <input type="text" name="urlimg" id="urlimg" value="${param['img']}" />
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
                            <input type="text" name="prezzo" id="prezzo" value="${param['prezzo']}" />
                        </div>
                        <div>
                             <c:if test="${requestScope['quantitaSbagliata'] == true}">
                                <div class="error">
                                    Il campo "Quantit&agrave;" deve contenere un numero intero >0.
                                </div>
                            </c:if>
                            <label for="disponibile">Quantit&agrave; disponibile</label>
                            <input type="text" name="disponibile" id="disponibile" value="${param['quantita']}"/>
                        </div>
                         <input type="hidden" name="id" value="${param['id']}">
                        <div class="buttons">
                            <input type="reset" value="Reset" />
                            <input type="submit" name="Modifica" value="Invia" />
                        </div>
                    </form>          
                    </div>
                </c:when> 
            <c:when test="${param['eliminazioneConfermata'] == true}">
                <div class="content">
                <p>Eliminazione CONFERMATA!</p>
                <h3>Riepilogo oggetto ELIMINATO:</h3>
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
                <a href="venditore.html">Torna alla pagina dedicata al venditore</a>
                </div>
            </c:when>
            <c:when test="${param['eliminazioneConfermata'] == false}">
                <p>Non &egrave; stato possibile eliminare l'oggetto.</p>
                <a href="venditore.html">Torna alla pagina dedicata al venditore</a>
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
                
                <p> La lista dei tuoi oggetti: </p>
                <table>
                    <tr>
                        <th>Nome</th>
                        <th>Foto</th>
                        <th>Quantit&agrave; disponibile</th>
                        <th>Prezzo</th>
                        <th>Modifica</th>
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
                                <form method="POST" action="venditore.html">
                                    <input type="hidden" name="id" value="${oggetto.getId()}">
                                    <input type="submit" name="Elimina" value="Elimina">
                                </form>
                                <a href="venditore.jsp?id=${oggetto.getId()}&nome=${oggetto.getNome()}&img=${oggetto.getUrlImmagine()}&prezzo=${oggetto.getPrezzo()}&quantita=${oggetto.getQuantitaDisponibile()}">Modifica</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            </c:otherwise>
        </c:choose>
        
        
        <jsp:include page="footer.jsp" />
    </body>
</html>

