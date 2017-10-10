package ucdb.br.appcomanda.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ucdb.br.appcomanda.R;
import ucdb.br.appcomanda.adapter.viewHolder.PizzaViewHolder;
import ucdb.br.appcomanda.modelDTO.PizzaComanda;

/**
 * Created by Lucas on 31/07/2017.
 */

public class PizzaComandaAdapter extends RecyclerView.Adapter<PizzaViewHolder>{
    List<PizzaComanda> pizzas;

    public PizzaComandaAdapter(List<PizzaComanda> pizzas) {
        this.pizzas = pizzas;
    }

    @Override
    public PizzaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cardapio_pizza, parent, false);

        return new PizzaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PizzaViewHolder holder, int position) {
         PizzaComanda pizzaComanda = pizzas.get(position);
        holder.sabor.setText(pizzaComanda.getSaborPizza());
        holder.idPizza.setText(String.valueOf(pizzaComanda.getId()));
    }

    @Override
    public int getItemCount() {
        return pizzas.size();
    }
}
