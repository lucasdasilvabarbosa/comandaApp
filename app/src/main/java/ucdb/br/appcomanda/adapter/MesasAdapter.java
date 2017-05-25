package ucdb.br.appcomanda.adapter;

import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
        public TextView title;
        public ImageView icone;

        public MyViewHolder(View view) {
            super(view);
            icone = (ImageView) view.findViewById(R.id.icon);
            title = (TextView) view.findViewById(R.id.title);

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

        holder.title.setText(String.valueOf(mesa.getNumeroDaMesa()));
        holder.icone.setImageResource(mesa.getImagem());

    }

    @Override
    public int getItemCount() {
        return mesas.size();
    }
}
