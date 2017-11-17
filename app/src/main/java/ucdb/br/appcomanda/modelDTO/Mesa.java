package ucdb.br.appcomanda.modelDTO;

import java.io.Serializable;

/**
 * Created by lucas on 26/09/2016.
 */
public class Mesa implements Serializable {
    private int id;
    private int numeroDaMesa;
    private boolean comandaAberta;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumeroDaMesa() {
        return numeroDaMesa;
    }

    public void setNumeroDaMesa(int numeroDaMesa) {
        this.numeroDaMesa = numeroDaMesa;
    }

    public boolean isComandaAberta() {
        return comandaAberta;
    }

    public void setComandaAberta(boolean comandaAberta) {
        this.comandaAberta = comandaAberta;
    }


}
