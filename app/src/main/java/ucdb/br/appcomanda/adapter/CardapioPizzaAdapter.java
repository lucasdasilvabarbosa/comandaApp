package ucdb.br.appcomanda.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ucdb.br.appcomanda.R;
import ucdb.br.appcomanda.adapter.viewHolder.PizzaViewHolder;
import ucdb.br.appcomanda.modelDTO.PizzaDTO;

/**
 * Created by lucas on 16/11/2016.
 */
public class CardapioPizzaAdapter extends RecyclerView.Adapter<PizzaViewHolder>  {
    private List<PizzaDTO> pizzaDTOS;



    public CardapioPizzaAdapter(List<PizzaDTO> pizzaDTOS) {
        this.pizzaDTOS = pizzaDTOS;
    }


    @Override
    public PizzaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cardapio_pizza, parent, false);

        return new PizzaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PizzaViewHolder holder, int position) {
        PizzaDTO pizzaDTO = pizzaDTOS.get(position);

        holder.sabor.setText(pizzaDTO.getSabor());
        holder.idPizza.setText(String.valueOf(pizzaDTO.getId()));
        holder.descricao.setText(pizzaDTO.getDescricao());
        holder.valor.setText(String.valueOf(pizzaDTO.getValor()));

    }

    public PizzaDTO getItem(int position){
        return pizzaDTOS.get(position);
    }

    @Override
    public int getItemCount() {

            return pizzaDTOS.size();

    }
}
