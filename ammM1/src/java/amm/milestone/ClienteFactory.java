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
    
    
    // Restituisce il cliente relativo ai dati usr e psw, se esiste
    public Cliente getCliente(String usr, String psw)
    {
        try {
            // Mi connetto al database
            Connection conn = DriverManager.getConnection(connectionString, "carlocabras", "0");
            
            // Preparo la query con cui ricerco tutti i clienti e filtro per usr e psw
            String queryRicerca = "select * from cliente where " + "username = ? and password = ?";
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
                Cliente c = new Cliente();
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
                
                return c; 
             
            }
            
            stmt.close();
            conn.close();
            
            
        } catch(SQLException e) {
            Cliente p = new Cliente();
            p.setNome(this.connectionString + e.toString() + "_________________________");
            //System.out.println(e.toString()+"_________");
            for (Throwable s : e.getNextException()) {
                p.setNome(p.getNome() + s.toString() + "_________________________");
                //System.out.println(s.toString() + "_________________________");
            }
            return p;
        }
        
        return null;
    }
    
    public boolean compra(double prezzoOggetto, Cliente c){
        try {
            // Mi connetto al database
            Connection conn = DriverManager.getConnection(connectionString, "carlocabras", "0");
            conn.setAutoCommit(false);
            // Preparo la query con cui ricerco tutti i clienti e filtro per usr e psw
            String query = "UPDATE cliente SET saldo = ? WHERE id = ?";

            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setDouble(1, c.getSaldo().getSaldo()-prezzoOggetto);
            stmt.setInt(2, c.getId());
            
            // Mando in esecuzione la query
            stmt.executeUpdate();
            
            stmt.close();
            conn.close();
            
            conn.setAutoCommit(true);
            
            return true;
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
        
        return false;
    }
    
    public void setConnectionString(String s){
	this.connectionString = s;
    }
    
    public String getConnectionString(){
        return this.connectionString;
    } 
    
}
