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
@WebServlet(name = "VenditoreServlet", urlPatterns = {"/Venditore"})
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
            Double prezzo; //usato per recuperare il prezzo inserito ed effettuarci il controllo
            /*
                la variabile "error" verrà accodata al link per la redirezione, se non ci sono errori
                allora il link rimarrà invariato, altrimenti ci sarà la stringa contenente l'errore
            */
            String error = new String(""); 
            
            //variabile necessaria per far funzionare il messaggio in venditore.jsp
            //session.setAttribute("venditoreLoggedIn", true); //magata per correggere errori, non dovrebbe essere più necessaria
            
            //creo nuovo oggetto e ci carico i dati
            TennisObjectSale nuovoOggetto = new TennisObjectSale();
            nuovoOggetto.setDescrizione(request.getParameter("descrizione"));
            nuovoOggetto.setNome(request.getParameter("nome"));
            nuovoOggetto.setQuantitaDisponibile(Integer.parseInt(request.getParameter("disponibile")));
            
            /*
                Il metodo boolean matches(String regex) della classe String 
                restituisce true se la stringa rispetta l'espressione regolare.
                La userò per validare l'input del prezzo
            */
            if ( ((request.getParameter("prezzo")).matches("[0-9]*\\.?[0-9]*"))==false ) 
                error += "?prezzoSbagliato=true";
            else {
                nuovoOggetto.setPrezzo(Double.parseDouble(request.getParameter("prezzo")));            
                request.setAttribute("nuovoOggetto", nuovoOggetto);
                //imposto a true la seguente variabile, usata in venditore.jsp per stampare il messaggio
                session.setAttribute("oggettoAggiunto", true);
            }
            
            //redirezione con eventuale stringa di errore
            request.getRequestDispatcher("venditore.jsp"+error).forward(request, response);
        }
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
