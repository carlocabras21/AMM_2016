<%-- 
    Document   : descrizione
    Created on : 23-apr-2016, 16.58.18
    Author     : Carlo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <title> CabrasTennis - descrizione</title>
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
                    <li><a href="login.jsp">Login</a></li>
                    <!--<a href="../index.html"> Torna alla home </a>-->
                </ul>
            </nav>

            <h1 id="descrizione">CabrasTennis</h1>
        </header>
        
        <jsp:include page="sezionelaterale.jsp" />
        
        <div class="content">
            <p> Panoramica generale sul sito. </p>

            <nav>
                <ol>
                    <li><a href="#cosaVendiamo">Cosa vendiamo</a></li>
                    <li><a href="#funzionalitaCliente">Cosa pu&ograve; fare il cliente</a></li>
                    <li><a href="#funzionalitaVenditore">Cosa pu&ograve; fare il venditore</a></li>
                </ol>
            </nav>

            <div>
                <h2 id="cosaVendiamo"> Cosa vendiamo </h2>
                <ol>
                    <li> Abbigliamento
                        <ul>
                            <li> Tute </li>
                            <li> Pantaloni </li>
                            <li> Shorts </li>
                            <li> Magliette </li>
                            <li> Felpe </li>
                            <li> Scarpe </li>
                        </ul>
                    </li>

                    <li> Racchette </li>
                    <li>  Palline </li>
                    <li> Accessori 
                        <ul>
                            <li> Portachiavi </li>
                            <li> Polsini </li>
                            <li> Borsoni </li>
                            <li> Corde </li>
                        </ul>
                    </li>
                </ol>
            </div>

            <div>
                <h2 id="funzionalitaCliente"> Cosa pu&ograve; fare il cliente </h2>
                <h3> Comprare </h3>
                <p> Il cliente pu&ograve; sfogliare tra gli oggetti e scegliere cosa mettere
                    nel carrello per poter poi procedere agli acquisti. </p>

                <h3> Ricaricare </h3>
                <p> Il cliente pu&ograve; ricaricare la sua carta di credito virtuale. </p>
            </div>

            <div>
                <h2 id="funzionalitaVenditore"> Cosa pu&ograve; fare il venditore </h2>
                <h3> Vendere </h3>
                <p> Il venditore pu&ograve; vendere i propri oggetti.</p>

                <h3> Inserire o rimuovere </h3>
                <p> Il venditore pu&ograve; a suo piacimento aggiungere oggetti nuovi e toglierne
                    se vecchi o esauriti. </p>
            </div>
        </div>
        
        <jsp:include page="footer.jsp" />
    </body>
</html>

