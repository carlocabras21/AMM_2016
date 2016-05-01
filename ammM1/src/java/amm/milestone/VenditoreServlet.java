/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.milestone;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Carlo
 */
@WebServlet(name = "VenditoreServlet", urlPatterns = {"/venditore.html"})
public class VenditoreServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        /*
        Nel caso l’utente non sia autenticato o non sia un venditore, 
        deve mostrare un messaggio di accesso negato
        */
        HttpSession session = request.getSession(false);
        
        if(request.getParameter("Submit") != null){ //se hanno premuto il tasto di aggiunta oggetto
            /*
                imposto questa variabile a true: se essa non sarà modiicata durante la ricerca degli errori,
                allora sarà salvato il fatto che l'oggetto è stato aggiunto.
            */
            request.setAttribute("oggettoAggiunto", true);  
            
            TennisObjectSale nuovoOggetto = new TennisObjectSale(); //creo nuovo oggetto dove caricare i dati
            
            //VALIDAZIONE DELL'INPUT: in caso di errore accodo la stringa con i parametri d'errore che saranno poi letti da venditore.jsp
            
            //VALIDAZIONE NOME
            if ( (request.getParameter("nome")).equals("")==true ){  //il nome non può essere vuoto
                request.setAttribute("nomeVuoto", true); //nel caso fosse vuoto, imposto la variabile a true segnalando così l'errore nella pagina del venditore
                request.setAttribute("oggettoAggiunto", false); //la imposto a false così più avanti non salvo l'oggetto aggiunto in quanto i dati non sono corretti
            }
            else{
                request.setAttribute("nomeVuoto", false); //imposto la variabile a false per evitare future riletture di un eventuale valore true
                nuovoOggetto.setNome(request.getParameter("nome")); //salvo il nome
            }
            
            nuovoOggetto.setDescrizione(request.getParameter("descrizione")); //la descrizione può anche essere vuota
            
            //VALIDAZIONE QUANTITA' DISPONIBILE
             /* Il metodo boolean matches(String regex) della classe String 
                restituisce true se la stringa rispetta l'espressione regolare.
                La userò per validare l'input della quantita e del prezzo */
            if ( ((request.getParameter("disponibile")).matches("[1-9][0-9]*"))==false ){ //la quantità dev'essere un numero >0
                request.setAttribute("quantitaSbagliata", true);
                request.setAttribute("oggettoAggiunto", false); 
            }
            else{
                request.setAttribute("quantitaSbagliata", false);
                nuovoOggetto.setQuantitaDisponibile(Integer.parseInt(request.getParameter("disponibile")));
            }

            //VALIDAZIONE PREZZO
            if ( ((request.getParameter("prezzo")).matches("[0-9]*\\.?[0-9]*"))==false ){ //il prezzo dev'essere un numero, anche senza la virgola va bene
                request.setAttribute("prezzoSbagliato", true);
                request.setAttribute("oggettoAggiunto", false); 
            }
            else {
                nuovoOggetto.setPrezzo(Double.parseDouble(request.getParameter("prezzo")));   
                request.setAttribute("prezzoSbagliato", false);
                request.setAttribute("nuovoOggetto", nuovoOggetto);
            }
            
            //se oggettoAggiunto è true vuol dire che non ci sono stati errori nell'invio dei dati, posso salvare l'oggetto
            if ((boolean)request.getAttribute("oggettoAggiunto")==true)
                request.setAttribute("nuovoOggetto", nuovoOggetto);
        }
        
        request.getRequestDispatcher("venditore.jsp?").forward(request, response); //redirezione
            
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
