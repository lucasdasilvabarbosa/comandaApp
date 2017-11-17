package ucdb.br.appcomanda.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ucdb.br.appcomanda.R;
import ucdb.br.appcomanda.adapter.viewHolder.PizzaViewHolder;
import ucdb.br.appcomanda.modelDTO.Pizza;
import ucdb.br.appcomanda.modelDTO.PizzaComanda;

/**
 * Created by lucas on 16/11/2016.
 */
public class CardapioPizzaAdapter extends RecyclerView.Adapter<PizzaViewHolder>  {
    private List<Pizza> pizzas;



    public CardapioPizzaAdapter(List<Pizza> pizzas) {
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
        Pizza pizza = pizzas.get(position);

        holder.sabor.setText(pizza.getSabor());
        holder.idPizza.setText(String.valueOf(pizza.getId()));
        holder.descricao.setText("calabresa bacon e outras coisas");
        holder.valor.setText(String.valueOf(pizza.getValor()));

    }

    public Pizza getItem(int position){
        return pizzas.get(position);
    }

    @Override
    public int getItemCount() {

            return pizzas.size();

    }
}
