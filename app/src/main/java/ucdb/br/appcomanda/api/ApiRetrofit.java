package ucdb.br.appcomanda.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Lucas on 30/05/2017.
 */

public class ApiRetrofit {

    private static final String url = "http://192.168.0.13:8080/ws/";

    public static Rotas apiRotas;

    public static Rotas buildRetrofit(){

        if(apiRotas == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(url)
                    .build();

            apiRotas = retrofit.create(Rotas.class);
        }

        return apiRotas;
    }
}
