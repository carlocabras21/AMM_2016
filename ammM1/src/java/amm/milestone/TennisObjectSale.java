package amm.milestone;

import java.util.List;

/**
 *
 * @author Carlo
 */
public class TennisObjectSale{
    private int id;
    private String nome;
    private String descrizione;
    private String urlImmagine;
    private Integer quantitaDisponibile;
    private Double prezzo;

    public TennisObjectSale() {
        this.id = 0;
        this.nome = "";
        this.descrizione = "";
        this.urlImmagine = "";
        this.quantitaDisponibile = 0;
        this.prezzo = 0.0;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the descrizione
     */
    public String getDescrizione() {
        return descrizione;
    }

    /**
     * @param descrizione the descrizione to set
     */
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    /**
     * @return the urlImmagine
     */
    public String getUrlImmagine() {
        return urlImmagine;
    }

    /**
     * @param urlImmagine the urlImmagine to set
     */
    public void setUrlImmagine(String urlImmagine) {
        this.urlImmagine = urlImmagine;
    }

    /**
     * @return the quantitaDisponibile
     */
    public Integer getQuantitaDisponibile() {
        return quantitaDisponibile;
    }

    /**
     * @param quantitaDisponibile the quantitaDisponibile to set
     */
    public void setQuantitaDisponibile(Integer quantitaDisponibile) {
        this.quantitaDisponibile = quantitaDisponibile;
    }

    /**
     * @return the prezzo
     */
    public Double getPrezzo() {
        return prezzo;
    }

    /**
     * @param prezzo the prezzo to set
     */
    public void setPrezzo(Double prezzo) {
        this.prezzo = prezzo;
    }

    
    
    
}
