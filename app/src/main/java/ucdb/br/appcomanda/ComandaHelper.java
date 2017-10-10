package ucdb.br.appcomanda;

import java.util.List;

import ucdb.br.appcomanda.modelDTO.Comanda;
import ucdb.br.appcomanda.modelDTO.Mesa;
import ucdb.br.appcomanda.modelDTO.PizzaComanda;

/**
 * Created by Lucas on 13/09/2017.
 */

public class ComandaHelper {
    private static Comanda comanda;

    public ComandaHelper(){
        comanda = new Comanda();
    }

    public static Comanda getComanda() {
        return comanda;
    }

    public static void setComanda(Comanda comanda) {
        ComandaHelper.comanda = comanda;
    }
}
