package ucdb.br.appcomanda.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ucdb.br.appcomanda.helper.ComandaHelper;
import ucdb.br.appcomanda.R;
import ucdb.br.appcomanda.adapter.CardapioPizzaAdapter;
import ucdb.br.appcomanda.adapter.RecyclerTouchListener;
import ucdb.br.appcomanda.api.ApiRetrofit;
import ucdb.br.appcomanda.api.Rotas;
import ucdb.br.appcomanda.modelDTO.PizzaDTO;
import butterknife.BindView;
import ucdb.br.appcomanda.modelDTO.PizzaComandaDTO;

/**
 * Created by lucas on 16/11/2016.
 */
public class CardapioPizzaActivity extends AppCompatActivity {

    private List<PizzaDTO> pizzaDTOS;

    @BindView(R.id.cardapio_pizza)
    RecyclerView recyclerView;

    private List<PizzaComandaDTO> pizzaComandaDTOS = new ArrayList<>();

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
                PizzaComandaDTO pizzaComandaDTO = new PizzaComandaDTO();
                pizzaComandaDTO.setIdComanda(ComandaHelper.getComandaDTO().getId());
                int a = cardapioPizzaAdapter.getItem(position).getId();
                pizzaComandaDTO.setIdPizza(a);
                pizzaComandaDTO.setSaborPizza(cardapioPizzaAdapter.getItem(position).getSabor());
                pizzaComandaDTO.setValorPizza(cardapioPizzaAdapter.getItem(position).getValor());

                if(ComandaHelper.getComandaDTO().getPizzaDTOs() != null)
                    ComandaHelper.getComandaDTO().getPizzaDTOs().add(pizzaComandaDTO);
                else{
                    pizzaComandaDTOS.add(pizzaComandaDTO);
                    ComandaHelper.getComandaDTO().setPizzaDTOs(pizzaComandaDTOS);
                }


                finish();

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

        Call<List<PizzaDTO>> call = apiRotas.getPizzas();

        call.enqueue(new Callback<List<PizzaDTO>>() {
            @Override
            public void onResponse(Call<List<PizzaDTO>> call, Response<List<PizzaDTO>> response) {
                List<PizzaDTO> pizzaDTOS = response.body();
                cardapioPizzaAdapter = new CardapioPizzaAdapter(pizzaDTOS);
                recyclerView.setAdapter(cardapioPizzaAdapter);
                cardapioPizzaAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<PizzaDTO>> call, Throwable t) {

            }
        });
    }



}
