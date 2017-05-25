package ucdb.br.appcomanda.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ucdb.br.appcomanda.R;
import ucdb.br.appcomanda.modelDTO.Pizza;

/**
 * Created by lucas on 16/11/2016.
 */
public class CardapioPizzaAdapter extends RecyclerView.Adapter<CardapioPizzaAdapter.MyViewHolder>  {
    private List<Pizza> pizzas;


    public CardapioPizzaAdapter(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView sabor;

        public MyViewHolder(View view) {
            super(view);
            sabor = (TextView) view.findViewById(R.id.sabor);

        }

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pizza_linha_cardapio, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Pizza pizza = pizzas.get(position);

        holder.sabor.setText(pizza.getSabor());
    }

    @Override
    public int getItemCount() {
        return pizzas.size();
    }
}
