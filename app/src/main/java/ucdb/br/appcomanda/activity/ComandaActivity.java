package ucdb.br.appcomanda.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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
import ucdb.br.appcomanda.modelDTO.PizzaComandaDTO;
import ucdb.br.appcomanda.util.MascaraMonetaria;


/**
 * Created by lucas on 26/09/2016.
 */
public class ComandaActivity extends AppCompatActivity {

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

    boolean validarRetorno;
    private ComandaDTO comandaDTO;

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_comanda);
        ButterKnife.bind(this);

        if (ComandaHelper.getMesaDTO().isComandaAberta()) {
            buscarComanda(ComandaHelper.getMesaDTO(), this);
            numeroDaMesa.setText("Mesa: " + ComandaHelper.getMesaDTO().getNumeroDaMesa());
        } else {
            criarComanda(ComandaHelper.getMesaDTO(), this);
            numeroDaMesa.setText("Mesa: " + ComandaHelper.getMesaDTO().getNumeroDaMesa());
        }

    }


    private void buscarComanda(MesaDTO mesaDTO, final Context context) {
        Rotas apiRotas = ApiRetrofit.buildRetrofit();

        Call<ComandaDTO> call = apiRotas.buscarComanda(mesaDTO.getId());

        call.enqueue(new Callback<ComandaDTO>() {
            @Override
            public void onResponse(Call<ComandaDTO> call, Response<ComandaDTO> response) {
                if (response.code() == 200) {
                    ComandaHelper.setComandaDTO(response.body());
                    preencheBebidaComanda(context);
                    preenchePizzasComanda(context);
                    valorComanda.setText("Valor da Comanda: " + String.valueOf(ComandaHelper.somaValorComanda()));
                } else
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
    public void chamar_cardapio_bebida() {
        Intent tela_cardapio_bebida = new Intent(ComandaActivity.this, BebidaComandaActivity.class);
        startActivity(tela_cardapio_bebida);
    }

    @Override
    protected void onResume() {
        if (ComandaHelper.getComandaDTO().getPizzaDTOs() != null) {
            preenchePizzasComanda(this);
        } else
            mLyPizzaComanda.setVisibility(View.INVISIBLE);

        if (ComandaHelper.getComandaDTO().getBebidaDTOs() != null) {
            preencheBebidaComanda(this);
        } else
            mLyBebidaComanda.setVisibility(View.INVISIBLE);

        valorComanda.setText("Valor da Comanda: " + String.valueOf(ComandaHelper.somaValorComanda()));
        super.onResume();
    }


    private void criarComanda(MesaDTO mesaDTO, final Context context) {
        Rotas apiRotas = ApiRetrofit.buildRetrofit();

        Call<ComandaDTO> call = apiRotas.criaComanda(mesaDTO);

        call.enqueue(new Callback<ComandaDTO>() {
            @Override
            public void onResponse(Call<ComandaDTO> call, Response<ComandaDTO> response) {
                ComandaDTO comandaDTO = response.body();

                if (comandaDTO != null) {
                    ComandaHelper.setComandaDTO(comandaDTO);
                    Toast.makeText(context, "criou comandaDTO!", Toast.LENGTH_LONG);

                } else
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

    public void preencheBebidaComanda(Context context) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        bebidaComanda.setLayoutManager(layoutManager);
        BebidaComandaApater bebidaComandaApater = new BebidaComandaApater(ComandaHelper.getComandaDTO().getBebidaDTOs());
        bebidaComanda.setAdapter(bebidaComandaApater);
        bebidaComandaApater.notifyDataSetChanged();
        mLyBebidaComanda.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.id_btn_enviar_para_cozinha)
    public void salvaComanda() {
        Rotas apiRotas = ApiRetrofit.buildRetrofit();

        Call<ComandaDTO> call = apiRotas.salvaComanda(ComandaHelper.getComandaDTO());

        call.enqueue(new Callback<ComandaDTO>() {
            @Override
            public void onResponse(Call<ComandaDTO> call, Response<ComandaDTO> response) {
                ComandaDTO comandaDTO = response.body();

                if (comandaDTO != null) {
                    ComandaHelper.setComandaDTO(comandaDTO);
                }

            }

            @Override
            public void onFailure(Call<ComandaDTO> call, Throwable t) {

            }
        });


    }

    @OnClick(R.id.id_btn_finalizar_comanda)
    public void btnfinalizarComanda() {
        if (ComandaHelper.getComandaDTO().getBebidaDTOs() != null && ComandaHelper.getComandaDTO().getBebidaDTOs().size() > 0 ||
                ComandaHelper.getComandaDTO().getPizzaDTOs() != null && ComandaHelper.getComandaDTO().getPizzaDTOs().size() > 0) {

            if (verificaEntrega()) {
                Toast.makeText(this, "Ainda existem Pizzas a serem entregues", Toast.LENGTH_SHORT).show();
            } else {
                Double valorFinal = ComandaHelper.somaValorComanda();
                AlertDialog.Builder alert = new AlertDialog.Builder(ComandaActivity.this);
                alert.setMessage("Valor da comanda = " + MascaraMonetaria.mascaraReal(valorFinal));
                alert.setTitle("Deseja realmente fechar a comanda?!");
                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finalizarComanda();
                        if (ComandaHelper.getComandaDTO().isComandaFinalizada())
                            finish();
                        else {
                            AlertDialog.Builder alert = new AlertDialog.Builder(ComandaActivity.this);
                            alert.setMessage("Algo deu errado na comunicação, tente novamente!");
                            alert.setTitle("Atenção!");
                            alert.setNegativeButton("Não", null);
                            alert.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            });
                            alert.show();
                        }

                    }
                });
                alert.show();
            }
        } else {
            AlertDialog.Builder alert = new AlertDialog.Builder(ComandaActivity.this);
            alert.setTitle("Ops!");
            alert.setMessage("Você precisa ter pizzas ou bebidas para poder finalizar uma comanda");
            alert.setNegativeButton("Não", null);
            alert.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            alert.show();

        }
    }


    public void finalizarComanda() {
        Rotas rotas = ApiRetrofit.buildRetrofit();

        Call<ComandaDTO> call = rotas.finalizarComanda(ComandaHelper.getComandaDTO());

        call.enqueue(new Callback<ComandaDTO>() {
            @Override
            public void onResponse(Call<ComandaDTO> call, Response<ComandaDTO> response) {
                if (response.code() == 200) {
                    ComandaHelper.limpa();
                    ComandaHelper.setComandaDTO(response.body());
                } else
                    validarRetorno = false;
            }

            @Override
            public void onFailure(Call<ComandaDTO> call, Throwable t) {
                validarRetorno = false;
            }
        });


    }

    public boolean verificaEntrega() {
        for (PizzaComandaDTO pizzaComandaDTO : ComandaHelper.getComandaDTO().getPizzaDTOs()) {
            if (!pizzaComandaDTO.isEntreguePelaCozinha()) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        ComandaHelper.setMesaDTO(null);
        ComandaHelper.setComandaDTO(null);
        System.gc();
        super.onDestroy();
    }

}
