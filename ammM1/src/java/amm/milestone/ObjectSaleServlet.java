/*
Task 5
Create una Servlet che invochi il metodo della classe ObjectSaleFactory per farsi restituire una lista di oggetti da visualizzare nella pagina, 
sotto forma di tabella, come fatto per la pagina dell’acquirente della prima milestone (potete fare un copia-incolla del codice html) 
e modificarlo per renderlo dinamico. Non preoccupatevi degli stili per ora.

Definite la URL di accesso alla vostra servlet, impostando l’attributo @WebServlet per la classe.
 */

/*
--------------------------------------------------------------------------------
--------------------------------------------------------------------------------
--------------------------------------------------------------------------------
--------------------------------------------------------------------------------
--------------------------------------------------------------------------------

NON E' USATA NELLA MILESTONE

--------------------------------------------------------------------------------
--------------------------------------------------------------------------------
--------------------------------------------------------------------------------
--------------------------------------------------------------------------------
--------------------------------------------------------------------------------

*/
package amm.milestone;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Carlo
 */
@WebServlet(name = "ObjectSaleServlet", urlPatterns = {"/cliente.html"})
public class ObjectSaleServlet extends HttpServlet {

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
        
        ArrayList<TennisObjectSale> arrayObjects = new ArrayList<>();
        response.setContentType("text/html;charset=UTF-8");        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");

            out.println("<html>");
            out.println("    <head>");
            out.println("        <title>CabrasTennis - cliente</title>");
            out.println("        <meta charset=\"UTF-8\">");
            out.println("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
            out.println("        <meta name=\"author\" content=\"Carlo Cabras\">");
            out.println("        <meta name=\"keywords\" content=\"tennis, shop\">");
            out.println("        <link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\" media=\"screen\">");
            out.println("    </head>");
            out.println("    <body>");
            out.println("        <header>");
            out.println("            <nav  class=\"headerNav\">");
            out.println("               <ul>");
            out.println("                    <li><a href=\"descrizione.html\">Descrizione</a></li>");
            out.println("                    <li><a href=\"login.html\">Login</a></li>");
            out.println("                </ul>");
            out.println("            </nav>");
            out.println("            <h1>Cliente</h1>");
            out.println("        </header>");

            out.println("        <aside class=\"sezioneLateraleDestra\">");
            out.println("            sezione laterale in manutenzione");
            out.println("        </aside>");
            out.println("        <aside class=\"sezioneLateraleSinistra\">");
            out.println("            sezione laterale in manutenzione");
            out.println("        </aside>");

            out.println("       <div class=\"content\">");
            out.println("            <p> Puoi scegliere che oggetto aggiungere al carrello per poi comprarlo. </p>");

            out.println("            <table>");
            out.println("                <tr>");
            out.println("                    <th>Nome</th>");
            out.println("                    <th>Foto</th>");
            out.println("                    <th>Quantit&agrave; disponibile</th>");
            out.println("                    <th>Prezzo</th>");
            out.println("                    <th>Aggiungi al carrello</th>");
            out.println("                </tr>");
            out.println("                <tr>");
            /*
            out.println("                    <td>Babolat Pure Strike 100</td>");
            out.println("                    <td><img title=\"Babolat Pure Strike 100\" alt=\"Foto di Babolat Pure Strike 100\" src=\"images/babolat.png\" width=\"100\" height=\"100\"/></td>");
            out.println("                    <td>3</td>");
            out.println("                    <td>88.90 &euro;</td>");
            out.println("                    <td><a href=\"cliente.html\"> Aggiungi al carrello </a></td>");
            */
            out.println("                    <td>"+arrayObjects.get(0).getNome()+"</td>");
            out.println("                    <td><img title=\""+arrayObjects.get(1).getNome()+"\" alt=\"Foto di"+arrayObjects.get(1).getNome()+"\" src=\""+arrayObjects.get(1).getUrlImmagine()+"\" width=\"100\" height=\"100\"/></td>");
            
            out.println("                </tr>");
            
            out.println("                <tr>");
            out.println("                   <td>Roger Zoom Vapor 9.5 Tour Clay</td>");
            out.println("                    <td><img title=\"Roger Zoom Vapor 9.5 Tour Clay\" alt=\"Foto di Roger Zoom Vapor 9.5 Tour Clay\" src=\"images/nike.png\" width=\"100\" height=\"100\"/></td>");
            out.println("                    <td>11</td>");
            out.println("                    <td>103.90 &euro;</td>");
            out.println("                    <td><a href=\"cliente.html\"> Aggiungi al carrello </a></td>");
            out.println("                </tr>");
            out.println("                <tr>");
            out.println("                    <td>Dunlop Fort All Court 3er</td>");
            out.println("                    <td><img title=\"Dunlop Fort All Court 3er\" alt=\"Foto di Dunlop Fort All Court 3er\" src=\"images/dunlop.png\" width=\"100\" height=\"100\"/></td>");
            out.println("                    <td>21</td>");
            out.println("                    <td>4.90 &euro;</td>");
            out.println("                    <td><a href=\"cliente.html\"> Aggiungi al carrello </a></td>");
            out.println("                </tr>");
            out.println("                <tr>");
            out.println("                    <td>Luxilon Alu Power Feel 220m</td>");
            out.println("                    <td><img title=\"Luxilon Alu Power Feel 220m\" alt=\"Foto di Luxilon Alu Power Feel 220m\" src=\"images/luxilon.png\" width=\"100\" height=\"100\"/></td>");
            out.println("                    <td>6</td>");
            out.println("                    <td>233.90 &euro;</td>");
            out.println("                    <td><a href=\"cliente.html\"> Aggiungi al carrello </a></td>");
            out.println("                </tr>");
            out.println("                <tr>");
            out.println("                    <td>Wilson Pro Staff 97LS (besaitet)</td>");
            out.println("                    <td><img title=\"Wilson Pro Staff 97LS (besaitet)\" alt=\"Foto di Wilson Pro Staff 97LS (besaitet)\" src=\"images/wilson.png\" width=\"100\" height=\"100\"/></td>");
            out.println("                    <td>17</td>");
            out.println("                    <td>179.90 &euro;</td>");
            out.println("                    <td><a href=\"cliente.html\"> Aggiungi al carrello </a></td>");
            out.println("                </tr>");
            out.println("            </table>");
            out.println("        </div>");

            out.println("        <footer>");
            out.println("            Created by Carlo Cabras, carlocabras21@gmail.com");
            out.println("        </footer>");
            out.println("    </body>");
            out.println("</html>");

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
