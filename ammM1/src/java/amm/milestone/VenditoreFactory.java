/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
public class VenditoreFactory {
    // Attributi
    private static VenditoreFactory singleton;    
    String connectionString; 
    ArrayList<TennisObjectSale> listaOggetti = null;

    ArrayList<Venditore> listaVenditori = new ArrayList<Venditore>();
    
    public static VenditoreFactory getInstance() {
        if (singleton == null) {
            singleton = new VenditoreFactory();
        }
        return singleton;
    }

    private VenditoreFactory(){
        Venditore venditore1 = new Venditore();
        venditore1.setNome("Mario");
        venditore1.setCognome("Puddu");
        venditore1.setDataNascita("25/04/1965");
        venditore1.setCodiceFiscale("PDDMRA65A04E324Q");
        venditore1.setUsr("mariopuddu");
        venditore1.setPsw("0");
        Saldo saldo1=new Saldo();
        saldo1.setSaldo(0.0);
        venditore1.setSaldo(saldo1);
        listaVenditori.add(venditore1);
        
        Venditore venditore2 = new Venditore();
        venditore2.setNome("Fabrizio");
        venditore2.setCognome("Murru");
        venditore2.setDataNascita("25/04/1981");
        venditore2.setCodiceFiscale("MRRFBR81A25S740Z");
        venditore2.setUsr("fabriziomurru");
        venditore2.setPsw("0");
        Saldo saldo2=new Saldo();
        saldo2.setSaldo(0.0);
        venditore2.setSaldo(saldo2);
        listaVenditori.add(venditore2);
        
    }
    
    
    // Restituisce il cliente relativo ai dati usr e psw, se esiste
    public Venditore getVenditore(String usr, String psw){
        try (Connection conn = DriverManager.getConnection(connectionString, "carlocabras", "0")) {
            // Preparo la query con cui ricerco il venditore in base al suo username e password
            String queryRicerca = "select * from venditore where " + "username = ? and password = ?";
            try (PreparedStatement stmt = conn.prepareStatement(queryRicerca)) {
                // Setto le due stringhe ? dello statement con i valori passati al metodo
                stmt.setString(1, usr);
                stmt.setString(2, psw);

                // Mando in esecuzione la query
                ResultSet res = stmt.executeQuery();


                // In set ci deve essere un solo elemento (cliente) associato a quell'username e password. Mi assicuro che ci sia
                if(res.next()) {
                    // Creo un nuovo Cliente, da valorizzare e poi restituire
                    Venditore c = new Venditore();
                    c.setId(res.getInt("id"));
                    c.setNome(res.getString("nome"));
                    c.setCognome(res.getString("cognome"));
                    c.setUsr(res.getString("username"));
                    c.setPsw(res.getString("password"));
                    c.setDataNascita(res.getString("data_nascita"));
                    c.setSaldo(new Saldo(res.getDouble("saldo")));
                    c.setCodiceFiscale(res.getString("codice_fiscale"));

                    return c;
                }
            }
        } catch(SQLException e) {
            System.out.println("__________________________________________________");
            System.out.println("__________________________________________________");
            System.out.println("__________________________________________________");
            System.out.println(e.toString()+"_________");
            for (Throwable s : e.getNextException()) 
                System.out.println(s.toString() + "_________________________");
            System.out.println("__________________________________________________");
            System.out.println("__________________________________________________");
            System.out.println("__________________________________________________");
            
        }
        return null; 
    }
    
    
    public ArrayList<TennisObjectSale> getOggettiListByVenditoreID(int id){
        listaOggetti = new ArrayList<TennisObjectSale>();

        try (Connection conn = DriverManager.getConnection(connectionString, "carlocabras", "0")) { // Mi connetto al database
            // Preparo la query con cui ricerco il gli oggetti di un venditore
            String queryRicerca = "select * from oggetto where id_venditore = ?";

            try (PreparedStatement stmt = conn.prepareStatement(queryRicerca)) {
                stmt.setInt(1, id);

                // Mando in esecuzione la query
                ResultSet res = stmt.executeQuery();

                // In set c'è l'insieme degli oggetti di quel venditore
                while(res.next()) {
                    // Creo un nuovo oggetto, da valorizzare e poi aggiungere alla lista
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
            for (Throwable s : e.getNextException()) 
                System.out.println(s.toString() + "_________________________");
            System.out.println("__________________________________________________");
            System.out.println("__________________________________________________");
            System.out.println("__________________________________________________");
            
        }
        
        return listaOggetti;
    }
      
    
    
    void addObjectToDatabase(TennisObjectSale o, int idVenditore) throws SQLException{
        PreparedStatement stmt = null;
        Connection conn = null;
        
        try {
            // Mi connetto al database
            conn = DriverManager.getConnection(connectionString, "carlocabras", "0");
            conn.setAutoCommit(false);
            // Preparo la query con cui inserisco i dati di un oggetto
            String query = "INSERT INTO oggetto(id, nome, descrizione, url_immagine, quantita, prezzo, id_venditore) "+
                                  "VALUES(default, ?, ?, ?, ?, ?, ?)";

            stmt = conn.prepareStatement(query);
            stmt.setString(1, o.getNome());
            stmt.setString(2, o.getDescrizione());
            stmt.setString(3, o.getUrlImmagine());
            stmt.setInt(4, o.getQuantitaDisponibile());
            stmt.setDouble(5, o.getPrezzo());
            stmt.setInt(6, idVenditore);
            
            // Mando in esecuzione la query
            stmt.executeUpdate();
            
         } catch(SQLException e) {
            if (conn != null)
                conn.rollback();
            
            System.out.println("__________________________________________________");
            System.out.println("__________________________________________________");
            System.out.println("__________________________________________________");
            System.out.println(e.toString()+"_________");
            for (Throwable s : e.getNextException()) 
                System.out.println(s.toString() + "_________________________");
            System.out.println("__________________________________________________");
            System.out.println("__________________________________________________");
            System.out.println("__________________________________________________");
        } finally{
            if (stmt != null)
                stmt.close();
            if (conn != null){
                conn.setAutoCommit(true);
                conn.close();
            }
        }
    }
    

    public boolean elimina(int idOggetto){
        Connection conn = null;
        try {
            // Mi connetto al database per eseguire l'aggiornamento 
            conn = DriverManager.getConnection(connectionString, "carlocabras", "0");
            
            conn.setAutoCommit(false);
            //rimuovo l'oggetto dalla lista
            String query = "DELETE FROM oggetto WHERE id=?"; 
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, idOggetto);
            if (stmt.executeUpdate()!=1)
                conn.rollback();
            stmt.close();
            
            conn.setAutoCommit(true);
            conn.close();            
            
            } catch(SQLException e) {
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
    
    
    
    void addObjectToList(TennisObjectSale t){
        listaOggetti.add(t);
    }
    
    public ArrayList<Venditore> getVenditoriList()    {
        return listaVenditori;
    }
    
    public void setConnectionString(String s){
	this.connectionString = s;
    }
    
    public String getConnectionString(){
        return this.connectionString;
    } 

    
}

