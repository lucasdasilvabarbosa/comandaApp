package ucdb.br.appcomanda.adapter;


import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ucdb.br.appcomanda.R;
import ucdb.br.appcomanda.modelDTO.MesaDTO;

/**
 * Created by lucas on 24/10/2016.
 */



public class MesasAdapter extends RecyclerView.Adapter<MesasAdapter.MyViewHolder> {
    private List<MesaDTO> mesaDTOS;

    public MesasAdapter(List<MesaDTO> mesaDTOS) {
        this.mesaDTOS = mesaDTOS;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView id_mesa;
        public TextView statusMesa;

        public MyViewHolder(View view) {
            super(view);
            id_mesa = (TextView) view.findViewById(R.id.id_mesa);
            statusMesa = (TextView) view.findViewById(R.id.statusMesa);
        }

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mesas_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        MesaDTO mesaDTO = mesaDTOS.get(position);

        holder.id_mesa.setText(String.valueOf(mesaDTO.getNumeroDaMesa()));
        if(mesaDTO.isComandaAberta()){
            holder.statusMesa.setText("Aberta");
            holder.statusMesa.setTextColor(Color.parseColor("#303F9F"));
        }else{
            holder.statusMesa.setText("Livre");
            holder.statusMesa.setTextColor(Color.parseColor("#04f104"));
        }

    }

    public MesaDTO getItem(int position){
        return  mesaDTOS.get(position);
    }

    @Override
    public int getItemCount() {
        return mesaDTOS.size();
    }
}
