package ucdb.br.appcomanda.modelDTO;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lucas on 26/09/2016.
 */
public class Comanda implements Serializable {
    private int id;
    private int id_mesa;
    private List<PizzaComanda> pizzas;
    private List<BebidaComanda> bebidas;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_mesa() {
        return id_mesa;
    }

    public void setId_mesa(int id_mesa) {
        this.id_mesa = id_mesa;
    }

    public List<PizzaComanda> getPizzaDTOs() {
        return pizzas;
    }

    public void setPizzaDTOs(List<PizzaComanda> pizzas) {
        this.pizzas = pizzas;
    }

    public List<BebidaComanda> getBebidaDTOs() {
        return bebidas;
    }

    public void setBebidaDTOs(List<BebidaComanda> bebidas) {
        this.bebidas = bebidas;
    }

}
