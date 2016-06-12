$(document).ready(function () {
    $("#Ricerca").keyup(function () {
        // Preleva il valore
        var text = $("#Ricerca").val();

        $.ajax({
            url: "filter.json",
            data: {
                cmd: "search",
                text: text
            },
            dataType: 'json',
            success: function (data, state) {
                aggiornaListaOggetti(data);
            },
            error: function (data, state) {
            }
        });

        // Funzione che viene richiamata in caso di successo
        function aggiornaListaOggetti(listaOggetti) {
            // Cancella la tabella
            $("#tabellaOggetti").empty();
            if(listaOggetti.length < 1) {
                $("#messaggioErrore").empty();
                $("#messaggioErrore").append(document.createTextNode("Nessun oggetto trovato"));
                //$("#messaggioErrore").show();
            }

            else {
                $("#messaggioErrore").empty();
                //$("#messaggioErrore").hide();
                var newtr = document.createElement("tr");

                var newth1 = document.createElement("th");
                var text1 = document.createTextNode("Nome");
                newth1.appendChild(text1);
                newtr.appendChild(newth1);

                var newth2 = document.createElement("th");
                var text2 = document.createTextNode("Foto");
                newth2.appendChild(text2);
                newtr.appendChild(newth2);

                var newth3 = document.createElement("th");
                var text3 = document.createTextNode("Quantità disponibile");
                newth3.appendChild(text3);
                newtr.appendChild(newth3);

                var newth4 = document.createElement("th");
                var text4 = document.createTextNode("Prezzo");
                newth4.appendChild(text4);
                newtr.appendChild(newth4);

                var newth5 = document.createElement("th");
                var text5 = document.createTextNode("Aggiungi al carrello");
                newth5.appendChild(text5);
                newtr.appendChild(newth5);
                
                //newThead.appendChild(newtr);
                $("#tabellaOggetti").append(newtr);
                //var listaOggetti = JSON.parse(listaOggettiData);
                // Per ogni alunno trovato dal database
                for (var oggetto in listaOggetti) {
                    //var tbody = document.createElement("tbody");
                    var newtr = document.createElement("tr");

                    var newtd1 = document.createElement("td");
                    newtd1.appendChild(document.createTextNode(listaOggetti[oggetto].nome));
                    newtr.appendChild(newtd1);

                    var newtd2 = document.createElement("td");
                    var imgNode = document.createElement("img");
                    imgNode.setAttribute("title", listaOggetti[oggetto].nome);
                    imgNode.setAttribute("alt", listaOggetti[oggetto].nome);
                    imgNode.setAttribute("src", listaOggetti[oggetto].urlimg);
                    imgNode.setAttribute("width", 100);
                    imgNode.setAttribute("height", 100);
                    newtd2.appendChild(imgNode);
                    newtr.appendChild(newtd2);

                    var newtd3 = document.createElement("td");
                    newtd3.appendChild(document.createTextNode(listaOggetti[oggetto].disponibile));
                    newtr.appendChild(newtd3);

                    var newtd4 = document.createElement("td");
                    newtd4.appendChild(document.createTextNode(listaOggetti[oggetto].prezzo));
                    newtr.appendChild(newtd4);

                    var newtd5 = document.createElement("td");
                    var link = document.createElement("a");
                    link.appendChild(document.createTextNode("Aggiungi al carrello"));
                    link.setAttribute("href", "cliente.jsp?id=" + listaOggetti[oggetto].id + "&nome=" + listaOggetti[oggetto].nome + "&img=" + listaOggetti[oggetto].urlimg + "&prezzo=" + listaOggetti[oggetto].prezzo);
                    newtd5.appendChild(link);
                    newtr.appendChild(newtd5);
                    
                    //tbody.appendChild(newtr);
                    $("#tabellaOggetti").append(newtr);
                    
               
                }
            }
        }
    });
});