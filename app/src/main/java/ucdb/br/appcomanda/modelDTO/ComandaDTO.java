package ucdb.br.appcomanda.modelDTO;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lucas on 26/09/2016.
 */
public class ComandaDTO implements Serializable {
    private int id;
    private int idMesa;
    private Double valorComanda;
    private List<PizzaComandaDTO> pizzaDTOs;
    private List<BebidaComandaDTO> bebidaDTOs;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(int idMesa) {
        this.idMesa = idMesa;
    }

    public List<PizzaComandaDTO> getPizzaDTOs() {
        return pizzaDTOs;
    }

    public void setPizzaDTOs(List<PizzaComandaDTO> pizzas) {
        this.pizzaDTOs = pizzas;
    }

    public List<BebidaComandaDTO> getBebidaDTOs() {
        return bebidaDTOs;
    }

    public void setBebidaDTOs(List<BebidaComandaDTO> bebidas) {
        this.bebidaDTOs = bebidas;
    }

    public Double getValorComanda() {
        return valorComanda;
    }

    public void setValorComanda(Double valorComanda) {
        this.valorComanda = valorComanda;
    }
}
