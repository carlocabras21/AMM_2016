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
public class VenditoreFactory {
    // Attributi
    private static VenditoreFactory singleton;
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
    
    public ArrayList<Venditore> getVenditoriList()
    {
        return listaVenditori;
    }
    
}

