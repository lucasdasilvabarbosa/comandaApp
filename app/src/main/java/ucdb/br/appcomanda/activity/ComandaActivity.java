package ucdb.br.appcomanda.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ucdb.br.appcomanda.adapter.BebidaComandaApater;
import ucdb.br.appcomanda.helper.ComandaHelper;
import ucdb.br.appcomanda.R;
import ucdb.br.appcomanda.adapter.PizzaComandaAdapter;
import ucdb.br.appcomanda.api.ApiRetrofit;
import ucdb.br.appcomanda.api.Rotas;
import ucdb.br.appcomanda.modelDTO.ComandaDTO;
import ucdb.br.appcomanda.modelDTO.MesaDTO;


/**
 * Created by lucas on 26/09/2016.
 */
public class ComandaActivity extends AppCompatActivity  {

    @BindView(R.id.pizzas_comanda)
    RecyclerView pizzasComanda;

    @BindView(R.id.id_ly_pizza_comanda)
    LinearLayout mLyPizzaComanda;

    @BindView(R.id.bebida_comanda)
    RecyclerView bebidaComanda;

    @BindView(R.id.id_ly_bebida_comanda)
    LinearLayout mLyBebidaComanda;

    @BindView(R.id.id_valor_comanda)
    TextView valorComanda;

    @BindView(R.id.id_numero_da_mesa)
    TextView numeroDaMesa;

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_comanda);
        ButterKnife.bind(this);

        if(ComandaHelper.getMesaDTO().isComandaAberta()){
            buscarComanda(ComandaHelper.getMesaDTO(), this);
            numeroDaMesa.setText("Mesa: "+ComandaHelper.getMesaDTO().getNumeroDaMesa());
        }else {
            criarComanda(ComandaHelper.getMesaDTO(), this);
            numeroDaMesa.setText("Mesa: "+ComandaHelper.getMesaDTO().getNumeroDaMesa());
        }

    }


    private void buscarComanda(MesaDTO mesaDTO, final Context context) {
        Rotas apiRotas = ApiRetrofit.buildRetrofit();

        Call<ComandaDTO> call = apiRotas.buscarComanda(mesaDTO.getId());

        call.enqueue(new Callback<ComandaDTO>() {
            @Override
            public void onResponse(Call<ComandaDTO> call, Response<ComandaDTO> response) {
                if(response.code() == 200) {
                    ComandaHelper.setComandaDTO(response.body());
                    preencheBebidaComanda(context);
                    preenchePizzasComanda(context);
                    valorComanda.setText("Valor da Comanda: "+String.valueOf(ComandaHelper.somaValorComanda()));
                }else
                    Toast.makeText(context, "Hove um erro na comunicação!", Toast.LENGTH_SHORT);
            }

            @Override
            public void onFailure(Call<ComandaDTO> call, Throwable t) {
                Toast.makeText(context, "Hove um erro na comunicação!", Toast.LENGTH_SHORT);
            }
        });

    }

    @OnClick(R.id.cardapio_pizza)
    public void chamar_cardapio() {
        Intent tela_cardapio_pizza = new Intent(ComandaActivity.this, CardapioPizzaActivity.class);
        startActivity(tela_cardapio_pizza);
    }

    @OnClick(R.id.cardapio_bebida)
    public void chamar_cardapio_bebida(){
        Intent tela_cardapio_bebida = new Intent(ComandaActivity.this, BebidaComandaActivity.class);
        startActivity(tela_cardapio_bebida);
    }

    @Override
    protected void onResume() {
        if(ComandaHelper.getComandaDTO().getPizzaDTOs() != null){
            preenchePizzasComanda(this);
        }else
            mLyPizzaComanda.setVisibility(View.INVISIBLE);

        if(ComandaHelper.getComandaDTO().getBebidaDTOs() != null){
            preencheBebidaComanda(this);
        }else
            mLyBebidaComanda.setVisibility(View.INVISIBLE);

        valorComanda.setText("Valor da Comanda: "+String.valueOf(ComandaHelper.somaValorComanda()));
        super.onResume();
    }



    private void criarComanda(MesaDTO mesaDTO, final Context context) {
        Rotas apiRotas = ApiRetrofit.buildRetrofit();

        Call<ComandaDTO> call = apiRotas.criaComanda(mesaDTO);

        call.enqueue(new Callback<ComandaDTO>() {
            @Override
            public void onResponse(Call<ComandaDTO> call, Response<ComandaDTO> response) {
                ComandaDTO comandaDTO = response.body();

                if(comandaDTO != null){
                    ComandaHelper.setComandaDTO(comandaDTO);
                    Toast.makeText(context, "criou comandaDTO!", Toast.LENGTH_LONG);

                }else
                    Toast.makeText(context, "falhou comunicação!!", Toast.LENGTH_LONG);
            }

            @Override
            public void onFailure(Call<ComandaDTO> call, Throwable t) {

            }
        });

    }

    private void preenchePizzasComanda(Context context) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        pizzasComanda.setLayoutManager(layoutManager);
        PizzaComandaAdapter pizzaComandaAdapter = new PizzaComandaAdapter(ComandaHelper.getComandaDTO().getPizzaDTOs());
        pizzasComanda.setAdapter(pizzaComandaAdapter);
        pizzaComandaAdapter.notifyDataSetChanged();
        mLyPizzaComanda.setVisibility(View.VISIBLE);
    }

    public void preencheBebidaComanda(Context context){
       RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
       bebidaComanda.setLayoutManager(layoutManager);
       BebidaComandaApater bebidaComandaApater = new BebidaComandaApater(ComandaHelper.getComandaDTO().getBebidaDTOs());
       bebidaComanda.setAdapter(bebidaComandaApater);
       bebidaComandaApater.notifyDataSetChanged();
       mLyBebidaComanda.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.id_btn_enviar_para_cozinha)
    public void salvaComanda(){
        Rotas apiRotas = ApiRetrofit.buildRetrofit();

        Call<ComandaDTO> call = apiRotas.salvaComanda(ComandaHelper.getComandaDTO());

        call.enqueue(new Callback<ComandaDTO>() {
            @Override
            public void onResponse(Call<ComandaDTO> call, Response<ComandaDTO> response) {
                ComandaDTO comandaDTO = response.body();

                if (comandaDTO != null){
                    ComandaHelper.setComandaDTO(comandaDTO);
                }

            }

            @Override
            public void onFailure(Call<ComandaDTO> call, Throwable t) {

            }
        });



    }

    @Override
    protected void onDestroy() {
        ComandaHelper.setMesaDTO(null);
        ComandaHelper.setComandaDTO(null);
        System.gc();
        super.onDestroy();
    }

}
