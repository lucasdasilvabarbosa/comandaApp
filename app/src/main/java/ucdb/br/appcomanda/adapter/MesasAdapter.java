package ucdb.br.appcomanda.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ucdb.br.appcomanda.R;
import ucdb.br.appcomanda.modelDTO.Mesa;

/**
 * Created by lucas on 24/10/2016.
 */



public class MesasAdapter extends RecyclerView.Adapter<MesasAdapter.MyViewHolder> {
    private List<Mesa> mesas;

    public MesasAdapter(List<Mesa> mesas) {
        this.mesas = mesas;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView id_mesa;


        public MyViewHolder(View view) {
            super(view);
            id_mesa = (TextView) view.findViewById(R.id.id_mesa);

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
        Mesa mesa = mesas.get(position);

        holder.id_mesa.setText(String.valueOf(mesa.getNumeroDaMesa()));


    }

    public Mesa getItem(int position){
        return  mesas.get(position);
    }

    @Override
    public int getItemCount() {
        return mesas.size();
    }
}
