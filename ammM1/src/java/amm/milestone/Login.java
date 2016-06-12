/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.milestone;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
//@WebServlet(name = "Login", urlPatterns = {"/login.html"})
@WebServlet(name = "Login", urlPatterns = {"/login.html"}, loadOnStartup = 0 )
public class Login extends HttpServlet {
    private static final String JDBC_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    private static final String DB_CLEAN_PATH = "../../web/WEB-INF/db/ammdb";
    private static final String DB_BUILD_PATH = "WEB-INF/db/ammdb";

    @Override 
    public void init(){
        String dbConnection = "jdbc:derby:" + this.getServletContext().getRealPath("/") + DB_BUILD_PATH;
        //String dbConnection = "jdbc:derby:" + this.getServletContext().getRealPath("/") + DB_CLEAN_PATH;
        //System.out.println(dbConnection);
        
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        ClienteFactory.getInstance().setConnectionString(dbConnection);
        VenditoreFactory.getInstance().setConnectionString(dbConnection);
        TennisObjectSaleFactory.getInstance().setConnectionString(dbConnection);

    }
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
        
        //controllo che un cliente o un venditore siano loggati, in tal caso li rispedisco alla loro rispettiva pagina.
        if (session.getAttribute("clienteLoggedIn")!=null && (boolean)session.getAttribute("clienteLoggedIn")==true) //controllo il cliente
            response.sendRedirect("cliente.html");//redirezione
        
        else if (session.getAttribute("venditoreLoggedIn")!=null && (boolean)session.getAttribute("venditoreLoggedIn")==true)//controllo il venditore
            response.sendRedirect("venditore.html");//redirezione
        
       else{ //nessun accesso è statoe effettuato
            Boolean autenticazioneRiuscita = false; //flag che uso per inviare un parametro con il quale mostrerò un messaggio d'errore

            if(request.getParameter("Submit") != null){
                // Preleva i dati inviati
                String username = request.getParameter("Username");
                String password = request.getParameter("Password");

                //carico la lista degli oggetti
                //ArrayList<TennisObjectSale> listaOggetti = TennisObjectSaleFactory.getInstance().getOggettiList();
                ArrayList<TennisObjectSale> listaOggetti = null;
                
                Cliente c = ClienteFactory.getInstance().getCliente(username, password);
                
                if (c != null){ //trovato il cliente
                    listaOggetti = TennisObjectSaleFactory.getInstance().getOggettiListFromDatabse();
                    //listaOggetti = TennisObjectSaleFactory.getInstance().getOggettiList();
                    session.setAttribute("loggedIn", true); //un generico utente si è loggato, potrebbe servirmi
                    session.setAttribute("nome",c.getNome() + " " + c.getCognome());
                    session.setAttribute("clienteLoggedIn", true);//un cliente si è loggato
                    autenticazioneRiuscita=true;
                    session.setAttribute("id", c.getCodiceFiscale()); //variabile di sessione
                    session.setAttribute("cliente", c);//metto nell'attributo "cliente" della sessione i dati del cliente loggato
                    request.setAttribute("cliente", c);//metto nell'attributo "cliente" i dati del cliente loggato
                    session.setAttribute("oggetti", listaOggetti);//metto in "oggetti" la lista degli oggetti
                    //request.getRequestDispatcher("cliente.jsp").forward(request, response);
                    response.sendRedirect("cliente.html");
                    //rimando a cliente.html che sarà letto dalla servlet
                }
                
                
                Venditore v = VenditoreFactory.getInstance().getVenditore(username, password);
                
                if (v!=null){ 
                    //carico la lista degli oggetti
                    listaOggetti = VenditoreFactory.getInstance().getOggettiListByVenditoreID(v.getId());
                    
                    //salvo in sessione e in request le variabili necessarie per il funzionamento
                    session.setAttribute("nome",v.getNome() + " " + v.getCognome());
                    session.setAttribute("loggedIn", true);
                    session.setAttribute("venditoreLoggedIn", true);
                    autenticazioneRiuscita=true;
                    session.setAttribute("id", v.getId());
                    session.setAttribute("oggetti", listaOggetti);//metto in "oggetti" la lista degli oggetti
                    request.setAttribute("venditore", v);
                    session.setAttribute("venditore", v);
                    //request.getRequestDispatcher("venditore.html").forward(request, response);
                    response.sendRedirect("venditore.html");
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

            else //nel caso non sia stato inviato nessun form, rimando alla stessa pagina.
                request.getRequestDispatcher("login.jsp").forward(request, response);
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
