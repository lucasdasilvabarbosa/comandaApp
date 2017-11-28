package ucdb.br.appcomanda.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import ucdb.br.appcomanda.modelDTO.BebidaDTO;
import ucdb.br.appcomanda.modelDTO.ComandaDTO;
import ucdb.br.appcomanda.modelDTO.MesaDTO;
import ucdb.br.appcomanda.modelDTO.PizzaDTO;

/**
 * Created by Lucas on 30/05/2017.
 */

public interface Rotas {

    @GET("pizza/lista")
    Call<List<PizzaDTO>> getPizzas();

    @GET("bebida/lista")
    Call<List<BebidaDTO>> getBebidas();

    @GET("mesa/lista")
    Call<List<MesaDTO>> getMesas();

    @POST("comanda/cria")
    Call<ComandaDTO> criaComanda(@Body MesaDTO mesaDTO);

    @POST("comanda/salvar")
    Call<ComandaDTO> salvaComanda(@Body ComandaDTO comandaDTO);

    @GET("comanda/buscarcomanda/{idMesa}")
    Call<ComandaDTO> buscarComanda(@Path("idMesa") int idMesa);
}
