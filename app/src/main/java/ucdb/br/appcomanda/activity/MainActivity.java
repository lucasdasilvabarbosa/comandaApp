package ucdb.br.appcomanda.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ucdb.br.appcomanda.ComandaHelper;
import ucdb.br.appcomanda.R;
import ucdb.br.appcomanda.adapter.DividerItemDecoration;
import ucdb.br.appcomanda.adapter.MesasAdapter;
import ucdb.br.appcomanda.adapter.RecyclerTouchListener;
import ucdb.br.appcomanda.api.ApiRetrofit;
import ucdb.br.appcomanda.api.Rotas;
import ucdb.br.appcomanda.modelDTO.Mesa;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;


    private Rotas apiRotas;
    private MesasAdapter mesasAdapter;
    private List<Mesa> mesas;
    ComandaHelper comandaHelper = new ComandaHelper();

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        apiRotas = ApiRetrofit.buildRetrofit();


        RecyclerView.LayoutManager LayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(LayoutManager);
        getMesas(this);





        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                comandaHelper.getComanda().setMesa(mesasAdapter.getItem(position));
                Intent irParaMesa = new Intent(MainActivity.this, ComandaActivity.class);
                startActivity(irParaMesa);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));


    }


    public void getMesas(final Context context){


        Call<List<Mesa>> call = apiRotas.getMesas();

        call.enqueue(new Callback<List<Mesa>>() {
            @Override
            public void onResponse(Call<List<Mesa>> call, Response<List<Mesa>> response) {
                mesas = response.body();
                if(mesas != null){

                    mesasAdapter = new MesasAdapter(mesas);
                    mesasAdapter.notifyDataSetChanged();
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(mesasAdapter);

                }

            }

            @Override
            public void onFailure(Call<List<Mesa>> call, Throwable t) {
                Toast.makeText(context, "Sem conexao", Toast.LENGTH_LONG);            }
        });

    }




}
