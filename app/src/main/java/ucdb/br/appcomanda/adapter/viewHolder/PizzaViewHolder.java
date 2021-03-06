package ucdb.br.appcomanda.adapter.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import ucdb.br.appcomanda.R;


/**
 * Created by Lucas on 29/08/2017.
 */

public class PizzaViewHolder extends RecyclerView.ViewHolder {
    public TextView sabor;
    public TextView idPizza;
    public TextView descricao;
    public TextView valor;
    public ImageView status;

    public PizzaViewHolder(View view) {
        super(view);
        sabor = (TextView) view.findViewById(R.id.sabor);
        idPizza = (TextView) view.findViewById(R.id.id_pizza_comanda);
        descricao = (TextView) view.findViewById(R.id.id_descricao_pizza);
        valor = (TextView) view.findViewById(R.id.id_valor_pizza);
        status = (ImageView) view.findViewById(R.id.id_status);

    }

}


