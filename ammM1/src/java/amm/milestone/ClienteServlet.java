/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.milestone;

import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "ClienteServlet", urlPatterns = {"/cliente.html"})
public class ClienteServlet extends HttpServlet {

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
        
        HttpSession session = request.getSession(false);
        
        if(request.getParameter("Submit") != null){
            //recupero il prezzo per controllare se il cliente ha abbastanza soldi
            double prezzo = Double.parseDouble(request.getParameter("prezzo"));
            
            //recupero il cliente dalla sessione
            Cliente cliente = (Cliente)session.getAttribute("cliente");
           
            //se il cliente esiste (dovrebbe esistere in quanto se sta comprando è perché è loggato) ed ha abbastanza soldi
            if (cliente!=null && cliente.getSaldo().getSaldo() > prezzo){
                //conferma acquisto
                TennisObjectSale o = new TennisObjectSale();
                o.setId(Integer.parseInt((String)request.getParameter("id")));
                
                ClienteFactory.getInstance().compra(prezzo, cliente);
                
                //invio alla pagina compra.jsp i dati dell'acquisto così si può mostrare un riepilogo
                request.getRequestDispatcher("cliente.jsp?confermato=true&nome="+
                                              request.getParameter("nome")+"&img="+
                                              request.getParameter("img")+"&prezzo="+
                                              request.getParameter("prezzo")).forward(request, response);
            }
            else { //nega acquisto
                //invio alla pgina compra.jsp il parameotro "confermato" settato a false per segnalare l'errore
                request.getRequestDispatcher("cliente.jsp?confermato=false").forward(request, response);
            }
        }
        
        //non è stato premuto alcun submit di conferma, semplicemente rimando a cliente.jsp
        request.getRequestDispatcher("cliente.jsp").forward(request, response);//rimando a cliente.jsp
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
