package ucdb.br.appcomanda.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;

import ucdb.br.appcomanda.R;
import ucdb.br.appcomanda.adapter.DividerItemDecoration;
import ucdb.br.appcomanda.adapter.MesasAdapter;
import ucdb.br.appcomanda.adapter.RecyclerTouchListener;
import ucdb.br.appcomanda.modelDTO.Mesa;

public class MainActivity extends AppCompatActivity {
    private List<Mesa> mesaList = new ArrayList<>();
    private RecyclerView recyclerView;
    private MesasAdapter mesasAdapter;


    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mesasAdapter = new MesasAdapter(mesaList);
        RecyclerView.LayoutManager LayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(LayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mesasAdapter);


        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent irParaMesa = new Intent(MainActivity.this, ComandaActivity.class);
                startActivity(irParaMesa);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        preparaMesas();

    }


    public void preparaMesas() {

        for (int i = 1; i <= 6; i++) {
            Mesa mesa = new Mesa();
            mesa.setId(i);
            mesa.setNumeroDaMesa(i);
            mesa.setImagem(R.drawable.mesa);
            mesaList.add(mesa);
        }

        mesasAdapter.notifyDataSetChanged();

    }


}
