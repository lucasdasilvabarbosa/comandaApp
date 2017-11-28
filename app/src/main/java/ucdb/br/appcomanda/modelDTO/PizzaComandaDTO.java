package ucdb.br.appcomanda.modelDTO;

import java.io.Serializable;

/**
 * Created by lucas on 26/09/2016.
 */
public class PizzaComandaDTO implements Serializable {
    private int id;
    private int idPizza;
    private String saborPizza;
    private int idComanda;
    private Double valorPizza;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPizza() {
        return idPizza;
    }

    public void setIdPizza(int idPizza) {
        this.idPizza = idPizza;
    }

    public String getSaborPizza() {
        return saborPizza;
    }

    public void setSaborPizza(String saborPizza) {
        this.saborPizza = saborPizza;
    }

    public int getIdComanda() {
        return idComanda;
    }

    public void setIdComanda(int idComanda) {
        this.idComanda = idComanda;
    }

    public Double getValorPizza() {
        return valorPizza;
    }

    public void setValorPizza(Double valorPizza) {
        this.valorPizza = valorPizza;
    }
}
