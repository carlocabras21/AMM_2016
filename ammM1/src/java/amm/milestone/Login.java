/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.milestone;

import java.io.IOException;
import java.util.ArrayList;
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
@WebServlet(name = "Login", urlPatterns = {"/login.html"})
public class Login extends HttpServlet {

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
        
        HttpSession session = request.getSession(true); //recupero la sessione corrente
        Boolean autenticazioneRiuscita = false; //flag che uso per inviare un parametro con il quale mostrerò un messaggio d'errore
        
        if(request.getParameter("Submit") != null){
            // Preleva i dati inviati
            String username = request.getParameter("Username");
            String password = request.getParameter("Password");
            
            //carico la lista degli oggetti
            ArrayList<TennisObjectSale> listaOggetti = TennisObjectSaleFactory.getInstance().getOggettiList();
            
            //request.getRequestDispatcher("cliente.jsp").forward(request, response); //per test veloci
            
            //carico la lista dei clienti con il quale controllare user e psw
            ArrayList<Cliente> listaClienti = ClienteFactory.getInstance().getClientiList();
            for (Cliente u : listaClienti){
                if (u.getUsr().equals(username) &&u.getPsw().equals(password)){ //trovato il cliente
                    session.setAttribute("loggedIn", true); //un generico utente si è loggato, potrebbe servirmi
                    session.setAttribute("clienteLoggedIn", true);//un cliente si è loggato
                    autenticazioneRiuscita=true;
                    session.setAttribute("id", u.getCodiceFiscale()); //variabile di sessione
                    session.setAttribute("cliente", u);//metto nell'attributo "cliente" della sessione i dati del cliente loggato
                    request.setAttribute("cliente", u);//metto nell'attributo "cliente" i dati del cliente loggato
                    session.setAttribute("oggetti", listaOggetti);//metto in "oggetti" la lista degli oggetti
                    //request.getRequestDispatcher("cliente.jsp").forward(request, response);
                    response.sendRedirect("cliente.html");
                    //rimando a cliente.html che sarà letto dalla servlet
                }               
            }
            
            //carico la lista dei venditori con il quale controllare user e psw
            //stesso funzionamento del caso del cliente
            ArrayList<Venditore> listaVenditori = VenditoreFactory.getInstance().getVenditoriList();
            for (Venditore u : listaVenditori){
                if (u.getUsr().equals(username) && u.getPsw().equals(password)){
                    session.setAttribute("loggedIn", true);
                    session.setAttribute("venditoreLoggedIn", true);
                    autenticazioneRiuscita=true;
                    session.setAttribute("id", u.getCodiceFiscale());
                    request.setAttribute("venditore", u);
                    session.setAttribute("venditore", u);
                    //request.getRequestDispatcher("venditore.html").forward(request, response);
                    response.sendRedirect("venditore.html");
                }               
            }
            
            //se sono qua è perché l'autenticazione è fallita e non c'è stata alcuna redirezione verso le pagine del cliente/venditore
            if (autenticazioneRiuscita==false){
                //setto a false tutte le variabili che tengono conto degli utenti loggati
                /*
                    essendo settate a false, vuol dire che ci hanno provato ma non ci sono riusciti => autenticazione fallita
                    se invece neanche ci provano, il parametro non viene creato e la sua ricerca mi darà null,
                    distimguendo i casi di autenticazione fallita e non effettuata
                */
                session.setAttribute("clienteLoggedIn", false);                   
                session.setAttribute("venditoreLoggedIn", false);
                session.setAttribute("loggedIn", false);

                //rimando a login.jsp con la variabile che mi permette di stampare un messaggio di errore
                request.getRequestDispatcher("login.jsp?autenticazioneFallita=true").forward(request, response);
            }
        }
        
        else 
            request.getRequestDispatcher("login.jsp").forward(request, response);
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
