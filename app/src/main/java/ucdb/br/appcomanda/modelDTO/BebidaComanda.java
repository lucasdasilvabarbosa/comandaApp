package ucdb.br.appcomanda.modelDTO;

import java.io.Serializable;

/**
 * Created by lucas on 26/09/2016.
 */
public class BebidaComanda implements Serializable {
    private int id;
    private int idBebida;
    private String descricaoBebida;
    private int idComanda;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdBebida() {
        return idBebida;
    }

    public void setIdBebida(int idBebida) {
        this.idBebida = idBebida;
    }

    public String getDescricaoBebida() {
        return descricaoBebida;
    }

    public void setDescricaoBebida(String descricaoBebida) {
        this.descricaoBebida = descricaoBebida;
    }

    public int getIdComanda() {
        return idComanda;
    }

    public void setIdComanda(int idComanda) {
        this.idComanda = idComanda;
    }

}
