package ucdb.br.appcomanda.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ucdb.br.appcomanda.R;
import ucdb.br.appcomanda.adapter.viewHolder.BebidaViewHolder;
import ucdb.br.appcomanda.modelDTO.BebidaDTO;

/**
 * Created by lucas on 17/11/2017.
 */

public class CardapioBebidaAdapter extends RecyclerView.Adapter<BebidaViewHolder> {

    private List<BebidaDTO> bebidaDTOS;



    public CardapioBebidaAdapter(List<BebidaDTO> bebidaDTOS) {
        this.bebidaDTOS = bebidaDTOS;
    }


    @Override
    public BebidaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_bebida_cardapio, parent, false);

        return new BebidaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BebidaViewHolder holder, int position) {
        BebidaDTO bebidaDTO = bebidaDTOS.get(position);

        holder.id_bebida.setText(String.valueOf(position+1));
        holder.descricao.setText(bebidaDTO.getDescricao());
        holder.valor.setText(String.valueOf(bebidaDTO.getValor()));

    }

    public BebidaDTO getItem(int position){
        return bebidaDTOS.get(position);
    }

    @Override
    public int getItemCount() {

        return bebidaDTOS.size();

    }
}
