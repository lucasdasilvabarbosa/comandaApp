package ucdb.br.appcomanda.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ucdb.br.appcomanda.ComandaHelper;
import ucdb.br.appcomanda.R;
import ucdb.br.appcomanda.adapter.CardapioPizzaAdapter;
import ucdb.br.appcomanda.adapter.DividerItemDecoration;
import ucdb.br.appcomanda.adapter.MesasAdapter;
import ucdb.br.appcomanda.adapter.RecyclerTouchListener;
import ucdb.br.appcomanda.api.ApiRetrofit;
import ucdb.br.appcomanda.api.Rotas;
import ucdb.br.appcomanda.modelDTO.Comanda;
import ucdb.br.appcomanda.modelDTO.Pizza;
import butterknife.BindView;
import ucdb.br.appcomanda.modelDTO.PizzaComanda;

/**
 * Created by lucas on 16/11/2016.
 */
public class CardapioPizzaActivity extends AppCompatActivity {

    List<Pizza> pizzas;

    @BindView(R.id.cardapio_pizza)
    RecyclerView recyclerView;

    private List<PizzaComanda> pizzaComandas = new ArrayList<>();

    private CardapioPizzaAdapter cardapioPizzaAdapter;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardapio_pizza);
        ButterKnife.bind(this);

        iniciarRecyclerView(this);


        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                PizzaComanda pizzaComanda = new PizzaComanda();
                pizzaComanda.setIdComanda(ComandaHelper.getComanda().getId());
                pizzaComanda.setIdPizza(cardapioPizzaAdapter.getItem(position).getId());
                pizzaComanda.setSaborPizza(cardapioPizzaAdapter.getItem(position).getSabor());
                pizzaComandas.add(pizzaComanda);
                ComandaHelper.getComanda().setPizzaDTOs(pizzaComandas);

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    public void iniciarRecyclerView(Context context){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        getPizzas();
    }


    public void getPizzas(){
        Rotas apiRotas = ApiRetrofit.buildRetrofit();

        Call<List<Pizza>> call = apiRotas.getPizzas();

        call.enqueue(new Callback<List<Pizza>>() {
            @Override
            public void onResponse(Call<List<Pizza>> call, Response<List<Pizza>> response) {
                List<Pizza> pizzas = response.body();
                cardapioPizzaAdapter = new CardapioPizzaAdapter(pizzas);
                recyclerView.setAdapter(cardapioPizzaAdapter);
                cardapioPizzaAdapter.notifyDataSetChanged();
                salvaComanda();
                finish();

            }

            @Override
            public void onFailure(Call<List<Pizza>> call, Throwable t) {

            }
        });
    }


    public void salvaComanda(){
        Rotas apiRotas = ApiRetrofit.buildRetrofit();

        Call<Comanda> call = apiRotas.salvaComanda(ComandaHelper.getComanda());

        call.enqueue(new Callback<Comanda>() {
            @Override
            public void onResponse(Call<Comanda> call, Response<Comanda> response) {
                Comanda comanda = response.body();

                if (comanda != null){
                    ComandaHelper.setComanda(comanda);
                }

            }

            @Override
            public void onFailure(Call<Comanda> call, Throwable t) {

            }
        });



    }
}
