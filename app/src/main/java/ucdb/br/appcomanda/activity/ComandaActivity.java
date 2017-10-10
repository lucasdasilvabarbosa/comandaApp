package ucdb.br.appcomanda.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ucdb.br.appcomanda.ComandaHelper;
import ucdb.br.appcomanda.R;
import ucdb.br.appcomanda.adapter.PizzaComandaAdapter;
import ucdb.br.appcomanda.api.ApiRetrofit;
import ucdb.br.appcomanda.api.Rotas;
import ucdb.br.appcomanda.modelDTO.Comanda;
import ucdb.br.appcomanda.modelDTO.Mesa;


/**
 * Created by lucas on 26/09/2016.
 */
public class ComandaActivity extends AppCompatActivity {

    @BindView(R.id.pizzas_comanda)
    RecyclerView pizzasComanda;

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_comanda);
        ButterKnife.bind(this);
        criarComanda(ComandaHelper.getComanda().getMesa(),this);

        if(ComandaHelper.getComanda().getPizzaDTOs() != null){
           preenchePizzasComanda(this);
        }

    }

    @OnClick(R.id.cardapio)
    public void chamar_cardapio() {
        Intent tela_cardapio_pizza = new Intent(ComandaActivity.this, CardapioPizzaActivity.class);
        startActivity(tela_cardapio_pizza);
    }

    private void criarComanda(Mesa mesa, final Context context) {
        Rotas apiRotas = ApiRetrofit.buildRetrofit();

        Call<Comanda> call = apiRotas.criaComanda(mesa);

        call.enqueue(new Callback<Comanda>() {
            @Override
            public void onResponse(Call<Comanda> call, Response<Comanda> response) {
                Comanda comanda = response.body();

                if(comanda != null){
                    ComandaHelper.setComanda(comanda);
                    Toast.makeText(context, "criou comanda!", Toast.LENGTH_LONG);

                }else
                    Toast.makeText(context, "falhou comunicação!!", Toast.LENGTH_LONG);
            }

            @Override
            public void onFailure(Call<Comanda> call, Throwable t) {

            }
        });

    }

    private void preenchePizzasComanda(Context context) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        pizzasComanda.setLayoutManager(layoutManager);
        PizzaComandaAdapter pizzaComandaAdapter = new PizzaComandaAdapter(ComandaHelper.getComanda().getPizzaDTOs());
        pizzasComanda.setAdapter(pizzaComandaAdapter);
        pizzaComandaAdapter.notifyDataSetChanged();
    }


}
