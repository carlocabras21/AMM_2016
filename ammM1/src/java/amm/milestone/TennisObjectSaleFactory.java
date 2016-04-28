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

import java.util.ArrayList;

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
    
    public ArrayList<TennisObjectSale> getOggettiList()
    {
        return listaOggetti;
    }
    
    public TennisObjectSale getOggetto(int id){
        for (TennisObjectSale i : listaOggetti){
            if (i.getId() == id) return i;
        }
        
        return null;
    }
    
}
