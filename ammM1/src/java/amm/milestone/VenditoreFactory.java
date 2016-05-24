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
        try {
            // Mi connetto al database
            Connection conn = DriverManager.getConnection(connectionString, "carlocabras", "0");
            conn.setAutoCommit(false);
            
            // Preparo la query con cui ricerco tutti i clienti e filtro per usr e psw
            String queryRicerca = "select * from venditore where " + "username = ? and password = ?";
            PreparedStatement stmt = conn.prepareStatement(queryRicerca);
            
            // Setto le due stringhe ? dello statement con i valori passati al metodo
            stmt.setString(1, usr);
            stmt.setString(2, psw);
            
            // Mando in esecuzione la query
            ResultSet res = stmt.executeQuery();
            
            /*
            //accesso diretto come cliente, per debug
            Statement stmt = conn.createStatement();
            String sql = "select * from cliente where username='carlocabras' and password='0'";
            ResultSet res = stmt.executeQuery(sql);
            */
            
            
            
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
                
                
                /*stmt.close();
                conn.close();
                
                //prova aggiunta elemento                
                conn = DriverManager.getConnection(connectionString, "carlocabras", "0");
                stmt = conn.prepareStatement("INSERT INTO cliente (id, nome, cognome, codice_fiscale, data_nascita, saldo, username, password) " +
                                             "VALUES (default, 'Provaa', 'provaa', 'CBRCRL93S26V234A','1993-11-26',1000,'provaaprovaa','0')");
                stmt.executeUpdate();
                */
                
                stmt.close();
                conn.close();
                
                conn.setAutoCommit(true);
                return c; 
             
            }
            
            stmt.close();
            conn.close();
        return null; 
            
        } catch(SQLException e) {
            Venditore p = new Venditore();
            p.setNome(this.connectionString + e.toString() + "_________________________");
            //System.out.println(e.toString()+"_________");
            for (Throwable s : e.getNextException()) {
                p.setNome(p.getNome() + s.toString() + "_________________________");
                //System.out.println(s.toString() + "_________________________");
            }
            return p;
        }
    }
    
    
    public ArrayList<TennisObjectSale> getOggettiListByVenditoreID(int id){
        if (listaOggetti != null)
            return listaOggetti;
        
        try {
            listaOggetti = new ArrayList<TennisObjectSale>();
            // Mi connetto al database
            Connection conn = DriverManager.getConnection(connectionString, "carlocabras", "0");
            
            // Preparo la query con cui ricerco tutti i clienti e filtro per usr e psw
            String queryRicerca = "select * from oggetto where id_venditore = ?";

            PreparedStatement stmt = conn.prepareStatement(queryRicerca);
            stmt.setInt(1, id);
            
            // Mando in esecuzione la query
            ResultSet res = stmt.executeQuery();
            
            // In set ci deve essere un solo elemento (cliente) associato a quell'username e password. Mi assicuro che ci sia
            while(res.next()) {
                // Creo un nuovo Cliente, da valorizzare e poi restituire
                TennisObjectSale c = new TennisObjectSale();
                c.setId(res.getInt("id"));
                c.setNome(res.getString("nome"));
                c.setDescrizione(res.getString("descrizione"));
                c.setUrlImmagine(res.getString("url_immagine"));
                c.setQuantitaDisponibile(res.getInt("quantita"));
                c.setPrezzo(res.getDouble("prezzo"));
                
                listaOggetti.add(c);
            }
            
            stmt.close();
            conn.close();
            
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
        
        return listaOggetti;
    }
    
    void addObjectToDatabase(TennisObjectSale o, int idVenditore){
        try {
            // Mi connetto al database
            Connection conn = DriverManager.getConnection(connectionString, "carlocabras", "0");
            
            // Preparo la query con cui ricerco tutti i clienti e filtro per usr e psw
            String queryRicerca = "INSERT INTO oggetto(id, nome, descrizione, url_immagine, quantita, prezzo, id_venditore) "+
                                  "VALUES(?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement stmt = conn.prepareStatement(queryRicerca);
            stmt.setInt(1, o.getId());
            stmt.setString(2, o.getNome());
            stmt.setString(3, o.getDescrizione());
            stmt.setString(4, o.getUrlImmagine());
            stmt.setInt(5, o.getQuantitaDisponibile());
            stmt.setDouble(6, o.getPrezzo());
            stmt.setInt(7, idVenditore);
            
            // Mando in esecuzione la query
            stmt.executeUpdate();
            
            stmt.close();
            conn.close();
            
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

