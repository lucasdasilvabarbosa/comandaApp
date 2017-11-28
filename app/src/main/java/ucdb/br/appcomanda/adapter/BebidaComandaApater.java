package ucdb.br.appcomanda.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ucdb.br.appcomanda.R;
import ucdb.br.appcomanda.adapter.viewHolder.BebidaViewHolder;
import ucdb.br.appcomanda.modelDTO.BebidaComandaDTO;

/**
 * Created by lucas on 17/11/2017.
 */

public class BebidaComandaApater extends RecyclerView.Adapter<BebidaViewHolder> {

    private List<BebidaComandaDTO> bebidaComandaDTOS;

    public BebidaComandaApater(List<BebidaComandaDTO> bebidaComandaDTOS){
       this.bebidaComandaDTOS = bebidaComandaDTOS;

    }

    @Override
    public BebidaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_bebida_cardapio, parent, false);

        return new BebidaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BebidaViewHolder holder, int position) {
        holder.id_bebida.setText(String.valueOf(position+1));
        holder.descricao.setText(bebidaComandaDTOS.get(position).getDescricaoBebida());
        holder.valor.setText(String.valueOf(bebidaComandaDTOS.get(position).getValorBebida()));
    }

    @Override
    public int getItemCount() {
        return bebidaComandaDTOS.size();
    }
}
