package ucdb.br.appcomanda.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.http.Path;
import ucdb.br.appcomanda.modelDTO.Comanda;
import ucdb.br.appcomanda.modelDTO.Pizza;

/**
 * Created by lucas on 06/10/2016.
 */
public class ApiWeb {

    public static final String url_api = "http://192.168.0.15:8080/ws";


    public static Rotas rotasApi;

    public static Rotas getRotas() {
        if (rotasApi == null) {

            //Serializador Client  Json
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                    .create();

            //Inicializa o Rest Client
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .setConverter(new GsonConverter(gson))
                    .setEndpoint(String.format("%s", url_api))
                    .build();

            //Objeto rest
            rotasApi = restAdapter.create(Rotas.class);
        }

        return rotasApi;
    }


    public interface Rotas{

        @POST("/comanda/salvar")
        public void salvarComanda(@Body Comanda comanda, Callback<Response> callbackc);

        @GET("/buscarComanda/{idMesa}")
        public void buscarComandaMesa(@Path("idMesa") int idMesa, Callback<Response> callback);

        @GET("/pizza/lista")
        public void buscarPizzas(Callback<List<Pizza>> callback);
    }

}
