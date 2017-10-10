package ucdb.br.appcomanda.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import ucdb.br.appcomanda.R;

/**
 * Created by Lucas on 31/07/2017.
 */

public class CardapioActivity extends AppCompatActivity {


    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_cardapio);
        ButterKnife.bind(this);

    }


}
