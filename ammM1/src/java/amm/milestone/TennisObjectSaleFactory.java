/*
Task 4
Create una classe che istanzi oggetti della classe ObjectSale. Chiamate la classe ObjectSaleFactory. 
Questa classe deve contenere un metodo che restituisca una lista di ObjectSale, per il momento riempiti con valori “inventati”.

Nota. Anche se a prima vista il fatto di non istanziare direttamente gli oggetti della classe ObjectSale può sembrare un po’ artificioso, 
ci tornerà utile quando introdurremo i database. 
In particolare, questa classe sarebbe bene implementarla applicando il pattern factory e singleton.
 
Factory: https://it.wikipedia.org/wiki/Factory_method
Singleton: https://it.wikipedia.org/wiki/Singleton
 */
package amm.milestone;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carlo
 */
public class TennisObjectSaleFactory {
    /*
        tutte le parti di codice con la variabile instance sono state prese da internet,
        probabilmente non la sto usando correttamente, forse non ho capito bene il concetto.
    */
    
    // Attributi
    private static TennisObjectSaleFactory singleton;
    String connectionString;
    ArrayList<TennisObjectSale> listaOggetti = new ArrayList<TennisObjectSale>();
    
    public static TennisObjectSaleFactory getInstance() {
        if (singleton == null) {
            singleton = new TennisObjectSaleFactory();
        }
        return singleton;
    }
    
    private TennisObjectSaleFactory(){
        TennisObjectSale oggetto1 = new TennisObjectSale();
        oggetto1.setId(0);
        oggetto1.setNome("Babolat Pure Strike 100");
        oggetto1.setDescrizione("racchetta");
        oggetto1.setUrlImmagine("images/babolat.png");
        oggetto1.setQuantitaDisponibile(3);
        oggetto1.setPrezzo(88.9);
        listaOggetti.add(oggetto1);
        
        TennisObjectSale oggetto2 = new TennisObjectSale();
        oggetto2.setId(1);
        oggetto2.setNome("Roger Zoom Vapor 9.5 Tour Clay");
        oggetto2.setDescrizione("scarpe");
        oggetto2.setUrlImmagine("images/nike.png");
        oggetto2.setQuantitaDisponibile(11);
        oggetto2.setPrezzo(103.9);
        listaOggetti.add(oggetto2);
        
        TennisObjectSale oggetto3 = new TennisObjectSale();
        oggetto3.setId(2);
        oggetto3.setNome("Dunlop Fort All Court 3er");
        oggetto3.setDescrizione("palline");
        oggetto3.setUrlImmagine("images/dunlop.png");
        oggetto3.setQuantitaDisponibile(21);
        oggetto3.setPrezzo(4.9);
        listaOggetti.add(oggetto3);
        
        TennisObjectSale oggetto4 = new TennisObjectSale();
        oggetto4.setId(3);
        oggetto4.setNome("Luxilon Alu Power Feel 220m");
        oggetto4.setDescrizione("corde");
        oggetto4.setUrlImmagine("images/luxilon.png");
        oggetto4.setQuantitaDisponibile(6);
        oggetto4.setPrezzo(233.9);
        listaOggetti.add(oggetto4);
        
        TennisObjectSale oggetto5 = new TennisObjectSale();
        oggetto5.setId(5);
        oggetto5.setNome("Wilson Pro Staff 97LS (besaitet)");
        oggetto5.setDescrizione("racchetta");
        oggetto5.setUrlImmagine("images/wilson.png");
        oggetto5.setQuantitaDisponibile(17);
        oggetto5.setPrezzo(179.9);
        listaOggetti.add(oggetto5);
    }
    
    public ArrayList<TennisObjectSale> getOggettiList(){
        return listaOggetti;
    }
    
    public ArrayList<TennisObjectSale> getOggettiListFromDatabse(){
        listaOggetti = new ArrayList<TennisObjectSale>();

        try (Connection conn = DriverManager.getConnection(connectionString, "carlocabras", "0")) { // Mi connetto al database           
            try (PreparedStatement stmt = conn.prepareStatement("select * from oggetto")) { // preparo la query
                // Mando in esecuzione la query
                ResultSet res = stmt.executeQuery();

                // In set ci deve essere un solo oggetto
                while(res.next()) {
                    // Creo un nuovo oggetto, da valorizzare e poi restituire
                    TennisObjectSale c = new TennisObjectSale();
                    c.setId(res.getInt("id"));
                    c.setNome(res.getString("nome"));
                    c.setDescrizione(res.getString("descrizione"));
                    c.setUrlImmagine(res.getString("url_immagine"));
                    c.setQuantitaDisponibile(res.getInt("quantita"));
                    c.setPrezzo(res.getDouble("prezzo"));

                    listaOggetti.add(c);
                }
            }
        }
        catch(SQLException e) {
            System.out.println("__________________________________________________");
            System.out.println("__________________________________________________");
            System.out.println("__________________________________________________");
            System.out.println(e.toString()+"_________");
            for (Throwable s : e.getNextException()) {
                System.out.println(s.toString() + "_________________________");
            }
            System.out.println("__________________________________________________");
            System.out.println("__________________________________________________");
            System.out.println("__________________________________________________");
        }
        
        return listaOggetti;
    }
    
    public TennisObjectSale getOggetto(int id){
        for (TennisObjectSale i : listaOggetti){
            if (i.getId() == id) return i;
        }
        
        return null;
    }
    
    TennisObjectSale getOggettoFromDatabase(int id){
        TennisObjectSale c = null;
        
        try (Connection conn = DriverManager.getConnection(connectionString, "carlocabras", "0")) { // Mi connetto al database
            // Preparo la query con cui ricerco l'oggetto con quel id
            String queryRicerca = "select * from oggetto where id=?";
            try (PreparedStatement stmt = conn.prepareStatement(queryRicerca)) {
                stmt.setInt(1, id);

                // Mando in esecuzione la query
                ResultSet res = stmt.executeQuery();

                // In set ci deve essere un solo oggetto
                if(res.next()) {
                    // Creo un nuovo oggetto, da valorizzare e poi restituire
                    c = new TennisObjectSale();
                    c.setId(res.getInt("id"));
                    c.setNome(res.getString("nome"));
                    c.setDescrizione(res.getString("descrizione"));
                    c.setUrlImmagine(res.getString("url_immagine"));
                    c.setQuantitaDisponibile(res.getInt("quantita"));
                    c.setPrezzo(res.getDouble("prezzo"));
                }
            }
        }
        catch(SQLException e) {
            System.out.println("__________________________________________________");
            System.out.println("__________________________________________________");
            System.out.println("__________________________________________________");
            System.out.println(e.toString()+"_________");
            for (Throwable s : e.getNextException()) {
                System.out.println(s.toString() + "_________________________");
            }
            System.out.println("__________________________________________________");
            System.out.println("__________________________________________________");
            System.out.println("__________________________________________________");
            
            return null;
        }
        
        return c;
    
    }
    
    boolean modificaOggetto(TennisObjectSale nuovoOggetto){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(connectionString, "carlocabras", "0");
            
            // aggiorno i dati dell'oggetto
            String query = "UPDATE oggetto SET nome=?, descrizione=?, url_immagine=?, quantita=?, prezzo=? WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, nuovoOggetto.getNome());
                stmt.setString(2, nuovoOggetto.getDescrizione());
                stmt.setString(3, nuovoOggetto.getUrlImmagine());
                stmt.setInt(4, nuovoOggetto.getQuantitaDisponibile());
                stmt.setDouble(5, nuovoOggetto.getPrezzo());
                stmt.setInt(6, nuovoOggetto.getId());
                
                // Mando in esecuzione la query
                if(stmt.executeUpdate()!=1){
                    conn.rollback();
                    return false;
                }
            }
            
        } catch (SQLException e) {
            System.out.println("__________________________________________________");
            System.out.println("__________________________________________________");
            System.out.println("__________________________________________________");
            System.out.println(e.toString()+"_________");
            for (Throwable s : e.getNextException()) {
                System.out.println(s.toString() + "_________________________");
            }
            System.out.println("__________________________________________________");
            System.out.println("__________________________________________________");
            System.out.println("__________________________________________________");
            
            if (conn != null){
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    Logger.getLogger(ClienteFactory.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            System.out.println("__________________________________________________");
            System.out.println("__________________________________________________");
            System.out.println("__________________________________________________");
            System.out.println(e.toString()+"_________");
            for (Throwable s : e.getNextException()) 
                System.out.println(s.toString() + "_________________________");
            System.out.println("__________________________________________________");
            System.out.println("__________________________________________________");
            System.out.println("__________________________________________________");
            
            return false;
            
        }
        return true;
    }
    
    public void setConnectionString(String s){
	this.connectionString = s;
    }
    
    public String getConnectionString(){
        return this.connectionString;
    } 

    ArrayList<TennisObjectSale> getOggetti(String text) {
        listaOggetti = new ArrayList();
        
        try (Connection conn = DriverManager.getConnection(connectionString, "carlocabras", "0");) { // Mi connetto al database
            String queryRicerca = "SELECT * FROM oggetto WHERE upper(nome) LIKE ?"; // Preparo la query con cui ricerco tutti gli oggetti
            try (PreparedStatement stmt = conn.prepareStatement(queryRicerca)) {
                text = "%"+text.toUpperCase()+"%";
                stmt.setString(1, text);
                
                ResultSet res = stmt.executeQuery(); // Mando in esecuzione la query
                while (res.next()) {
                    // Creo un nuovo oggetto, da valorizzare e aggiungere alla lista
                    TennisObjectSale c = new TennisObjectSale();
                    c.setId(res.getInt("id"));
                    c.setNome(res.getString("nome"));
                    c.setDescrizione(res.getString("descrizione"));
                    c.setUrlImmagine(res.getString("url_immagine"));
                    c.setQuantitaDisponibile(res.getInt("quantita"));
                    c.setPrezzo(res.getDouble("prezzo"));
                    
                    listaOggetti.add(c);
                }
            }
            
        } catch(SQLException e) {
            System.out.println("__________________________________________________");
            System.out.println("__________________________________________________");
            System.out.println("__________________________________________________");
            System.out.println(e.toString()+"_________");
            for (Throwable s : e.getNextException()) {
                System.out.println(s.toString() + "_________________________");
            }
            System.out.println("__________________________________________________");
            System.out.println("__________________________________________________");
            System.out.println("__________________________________________________");
        }
        if (listaOggetti.size()>0)
            return listaOggetti; 
        return null;
    }
}
