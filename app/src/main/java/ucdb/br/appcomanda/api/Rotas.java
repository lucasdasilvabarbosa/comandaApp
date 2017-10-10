package ucdb.br.appcomanda.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import ucdb.br.appcomanda.modelDTO.Comanda;
import ucdb.br.appcomanda.modelDTO.Mesa;
import ucdb.br.appcomanda.modelDTO.Pizza;

/**
 * Created by Lucas on 30/05/2017.
 */

public interface Rotas {

    @GET("pizza/lista")
    Call<List<Pizza>> getPizzas();

    @GET("mesa/lista")
    Call<List<Mesa>> getMesas();

    @POST("comanda/cria")
    Call<Comanda> criaComanda(@Body Mesa mesa);

    @POST("comanda/salvar")
    Call<Comanda> salvaComanda(@Body Comanda comanda);
}
