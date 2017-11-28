package ucdb.br.appcomanda.adapter.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import ucdb.br.appcomanda.R;

/**
 * Created by lucas on 17/11/2017.
 */

public class BebidaViewHolder extends RecyclerView.ViewHolder {
    public TextView id_bebida;
    public TextView descricao;
    public TextView valor;

    public BebidaViewHolder(View view) {
        super(view);

        id_bebida = (TextView) view.findViewById(R.id.id_bebida_comanda);
        descricao = (TextView) view.findViewById(R.id.id_descricao_bebida);
        valor = (TextView) view.findViewById(R.id.id_valor_bebida);

    }
}
