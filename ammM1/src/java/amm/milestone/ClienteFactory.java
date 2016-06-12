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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carlo
 */
public class ClienteFactory {
    
    private static ClienteFactory singleton;
    String connectionString; 
    
    ArrayList<Cliente> listaClienti = new ArrayList<Cliente>();
    
    public static ClienteFactory getInstance() {
        if (singleton == null) {
            singleton = new ClienteFactory();
        }
        return singleton;
    }
    
    private ClienteFactory(){
        Cliente cliente1 = new Cliente();
        cliente1.setNome("Carlo");
        cliente1.setCognome("Cabras");
        cliente1.setDataNascita("26/11/1993");
        cliente1.setCodiceFiscale("CBRCRL93S26V234A");
        cliente1.setUsr("carlocabras");
        cliente1.setPsw("0");
        Saldo saldo1=new Saldo();
        saldo1.setSaldo(1000.0);
        cliente1.setSaldo(saldo1);
        listaClienti.add(cliente1);
        
        Cliente cliente2 = new Cliente();
        cliente2.setNome("Federica");
        cliente2.setCognome("Locci");
        cliente2.setDataNascita("15/03/1982");
        cliente2.setCodiceFiscale("LCCFDR82R55S971F");
        cliente2.setUsr("federicalocci");
        cliente2.setPsw("0");
        Saldo saldo2=new Saldo();
        saldo2.setSaldo(0.0);
        cliente2.setSaldo(saldo2);
        listaClienti.add(cliente2);
    }
    
    public ArrayList<Cliente> getClientiList(){
        return listaClienti;
    }
    
    
    public Cliente getCliente(String usr, String psw) {
        try (Connection conn = DriverManager.getConnection(connectionString, "carlocabras", "0")) {  // Mi connetto al database
            // Preparo la query con cui ricerco tutti i clienti e filtro per usr e psw
            String queryRicerca = "select * from cliente where " + "username = ? and password = ?";
            // Setto le due stringhe ? dello statement con i valori passati al metodo
            try (PreparedStatement stmt = conn.prepareStatement(queryRicerca)) {
                // Setto le due stringhe ? dello statement con i valori passati al metodo
                stmt.setString(1, usr);
                stmt.setString(2, psw);

                // Mando in esecuzione la query
                ResultSet res = stmt.executeQuery();


                // In set ci deve essere un solo elemento (cliente) associato a quell'username e password. Mi assicuro che ci sia
                if(res.next()) {
                    // Creo un nuovo Cliente, da valorizzare e poi restituire
                    Cliente c = new Cliente();
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
        }catch(SQLException e) {
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
    

    public boolean acquisto(TennisObjectSale oggettoDaVendere, Cliente c){
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean risultato = false;
        String query;
        int idVenditore = -1;
        double saldoVenditore = 0;
        
        try {
            // Mi connetto al database
            conn = DriverManager.getConnection(connectionString, "carlocabras", "0");
            
            //****OPERAZIONI IN LETTURA CHE NON ALTERANO LO STATO DEL DATABASE****
            
            //mi serve l'id del venditore per aggiornargli il saldo, lo prendo dall'oggetto in questione
            query = "SELECT id_venditore FROM oggetto WHERE id=?"; 
            stmt = conn.prepareStatement(query);
            int idOggetto=oggettoDaVendere.getId();
            stmt.setInt(1, idOggetto);
            ResultSet res = stmt.executeQuery();
            if(res.next()) idVenditore=(res.getInt("id_venditore"));
            stmt.close();
            
            //uso l'id del venditore per prendere il suo saldo
            query = "SELECT saldo FROM venditore WHERE id=?"; 
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, idVenditore);
            res = stmt.executeQuery();
            if(res.next()) saldoVenditore=(res.getDouble("saldo"));
            stmt.close();
            
            
            //****OPERAZIONI IN SCRITTURA, TRANSAZIONE****
            conn.setAutoCommit(false);
            
            //rimuovo l'oggetto dalla lista
            query = "DELETE FROM oggetto WHERE id=?"; 
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, oggettoDaVendere.getId());
            if (stmt.executeUpdate()!=1)
                conn.rollback();
            stmt.close();
            
            // aggiorno il saldo del cliente
            query = "UPDATE cliente SET saldo = ? WHERE id = ?";
            stmt = conn.prepareStatement(query);
            stmt.setDouble(1, c.getSaldo().getSaldo()-oggettoDaVendere.getPrezzo());
            stmt.setInt(2, c.getId());
            // Mando in esecuzione la query
            stmt.executeUpdate();
            stmt.close();
            
            //adesso posso aggiornare il saldo del venditore
            query = "UPDATE venditore SET saldo = ? WHERE id = ?";
            stmt = conn.prepareStatement(query);
            stmt.setDouble(1, saldoVenditore+oggettoDaVendere.getPrezzo());
            stmt.setInt(2, idVenditore);
            // Mando in esecuzione la query
            stmt.executeUpdate();
            stmt.close();
            
            
            //FINE TRANSAZIONE
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
    
    public void setConnectionString(String s){
	this.connectionString = s;
    }
    
    public String getConnectionString(){
        return this.connectionString;
    } 
    
}
