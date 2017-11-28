package ucdb.br.appcomanda.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ucdb.br.appcomanda.R;
import ucdb.br.appcomanda.adapter.MesasAdapter;
import ucdb.br.appcomanda.adapter.RecyclerTouchListener;
import ucdb.br.appcomanda.api.ApiRetrofit;
import ucdb.br.appcomanda.api.Rotas;
import ucdb.br.appcomanda.helper.ComandaHelper;
import ucdb.br.appcomanda.modelDTO.ComandaDTO;
import ucdb.br.appcomanda.modelDTO.MesaDTO;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;


    private Rotas apiRotas;
    private MesasAdapter mesasAdapter;
    private List<MesaDTO> mesaDTOS;
    ComandaDTO comandaDTO = new ComandaDTO();

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        RecyclerView.LayoutManager LayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(LayoutManager);
        getMesas(this);


        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                comandaDTO.setIdMesa(mesasAdapter.getItem(position).getId());
                ComandaHelper.setComandaDTO(comandaDTO);
                ComandaHelper.setMesaDTO(mesasAdapter.getItem(position));
                Intent irParaMesa = new Intent(MainActivity.this, ComandaActivity.class);
                startActivity(irParaMesa);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));


    }

    @Override
    protected void onResume(){
        getMesas(this);
        super.onResume();

    }

    public void getMesas(final Context context){
        apiRotas = ApiRetrofit.buildRetrofit();


        Call<List<MesaDTO>> call = apiRotas.getMesas();

        call.enqueue(new Callback<List<MesaDTO>>() {
            @Override
            public void onResponse(Call<List<MesaDTO>> call, Response<List<MesaDTO>> response) {
                mesaDTOS = response.body();
                if(mesaDTOS != null){

                    mesasAdapter = new MesasAdapter(mesaDTOS);
                    mesasAdapter.notifyDataSetChanged();
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(mesasAdapter);

                }

            }

            @Override
            public void onFailure(Call<List<MesaDTO>> call, Throwable t) {
                Toast.makeText(context, "Sem conexao", Toast.LENGTH_LONG);            }
        });

    }




}
