package ucdb.br.appcomanda.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ucdb.br.appcomanda.helper.ComandaHelper;
import ucdb.br.appcomanda.R;
import ucdb.br.appcomanda.adapter.CardapioBebidaAdapter;
import ucdb.br.appcomanda.adapter.RecyclerTouchListener;
import ucdb.br.appcomanda.api.ApiRetrofit;
import ucdb.br.appcomanda.api.Rotas;
import ucdb.br.appcomanda.modelDTO.BebidaDTO;
import ucdb.br.appcomanda.modelDTO.BebidaComandaDTO;

/**
 * Created by lucas on 17/11/2017.
 */

public class BebidaComandaActivity extends AppCompatActivity {

    @BindView(R.id.cardapio_bebida)
    RecyclerView recyclerView;

    CardapioBebidaAdapter cardapioBebidaAdapter;

    private List<BebidaComandaDTO> bebidaComandaDTOS = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardapio_bebida);
        ButterKnife.bind(this);

        iniciarRecyclerView(this);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                BebidaComandaDTO bebidaComandaDTO = new BebidaComandaDTO();
                bebidaComandaDTO.setIdComanda(ComandaHelper.getComandaDTO().getId());
                bebidaComandaDTO.setIdBebida(cardapioBebidaAdapter.getItem(position).getId());
                bebidaComandaDTO.setDescricaoBebida(cardapioBebidaAdapter.getItem(position).getDescricao());
                bebidaComandaDTO.setValorBebida(cardapioBebidaAdapter.getItem(position).getValor());

                if (ComandaHelper.getComandaDTO().getBebidaDTOs() != null)
                    ComandaHelper.getComandaDTO().getBebidaDTOs().add(bebidaComandaDTO);
                else {
                    bebidaComandaDTOS.add(bebidaComandaDTO);
                    ComandaHelper.getComandaDTO().setBebidaDTOs(bebidaComandaDTOS);
                }


                finish();

            }

            @Override
            public void onLongClick(View view, int position) {

            }

        }));
    }

    private void iniciarRecyclerView(Context context) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        getBebidas(context);
    }

    public void getBebidas(final Context context) {
        Rotas apiRotas = ApiRetrofit.buildRetrofit();

        Call<List<BebidaDTO>> call = apiRotas.getBebidas();

        call.enqueue(new Callback<List<BebidaDTO>>() {
            @Override
            public void onResponse(Call<List<BebidaDTO>> call, Response<List<BebidaDTO>> response) {
                if(response.code() == 200){
                    cardapioBebidaAdapter = new CardapioBebidaAdapter(response.body());
                    recyclerView.setAdapter(cardapioBebidaAdapter);
                    cardapioBebidaAdapter.notifyDataSetChanged();
                }else
                    Toast.makeText(context, "criou comanda!", Toast.LENGTH_LONG);
            }

            @Override
            public void onFailure(Call<List<BebidaDTO>> call, Throwable t) {
                Toast.makeText(context, "criou comanda!", Toast.LENGTH_LONG);
            }
        });


    }
}
