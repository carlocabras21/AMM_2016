/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.milestone;

import java.util.ArrayList;

/**
 *
 * @author Carlo
 */
public class ClienteFactory {
    
    private static ClienteFactory singleton;
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
    
}
