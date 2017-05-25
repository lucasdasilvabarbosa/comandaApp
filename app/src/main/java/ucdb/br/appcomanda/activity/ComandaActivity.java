package ucdb.br.appcomanda.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

import ucdb.br.appcomanda.R;


/**
 * Created by lucas on 26/09/2016.
 */
public class ComandaActivity extends AppCompatActivity {


    @Override
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_comanda);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.cardapio)
    public void chamar_cardapio(){
        Intent tela_cardapio = new Intent(this, CardapioPizzaActivity.class);
        startActivity(tela_cardapio);
    }


}
