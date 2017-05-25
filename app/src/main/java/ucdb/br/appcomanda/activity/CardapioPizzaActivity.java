package ucdb.br.appcomanda.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import ucdb.br.appcomanda.R;
import ucdb.br.appcomanda.adapter.CardapioPizzaAdapter;
import ucdb.br.appcomanda.adapter.DividerItemDecoration;
import ucdb.br.appcomanda.api.ApiWeb;
import ucdb.br.appcomanda.modelDTO.Pizza;

/**
 * Created by lucas on 16/11/2016.
 */
public class CardapioPizzaActivity extends AppCompatActivity {

    List<Pizza> pizzas;
    private RecyclerView recyclerView;
    private CardapioPizzaAdapter cardapioPizzaAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardapio_pizza);
        ButterKnife.bind(this);

        recyclerView = (RecyclerView) findViewById(R.id.cardapio_pizza);



        ApiWeb.getRotas().buscarPizzas(new Callback<List<Pizza>>() {
            @Override
            public void success(List<Pizza> pizzas_rest, Response response) {
                pizzas = pizzas_rest;
                cardapioPizzaAdapter.notifyDataSetChanged();

            }

            @Override
            public void failure(RetrofitError error) {

            }
        });

        cardapioPizzaAdapter = new CardapioPizzaAdapter(pizzas);
        RecyclerView.LayoutManager LayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(LayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(cardapioPizzaAdapter);


    }

//    public void carregar_pizzas(){
//        ApiWeb.getRotas().buscarPizzas(new Callback<List<Pizza>>() {
//            @Override
//            public void success(List<Pizza> pizzas_rest, Response response) {
//                pizzas = pizzas_rest;
//            }
//
//            @Override
//            public void failure(RetrofitError error) {
//
//            }
//        });
//    }
}
