package com.example.store.adapterdata;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.store.R;
import com.example.store.model.Ropa;

import java.util.ArrayList;

/**
 * This class is a RecyclerView.Adapter that uses a ViewHolderDatos to display data
 */
public class DataAdapterRopa extends RecyclerView.Adapter<DataAdapterRopa.ViewHolderData>  {
    ArrayList<Ropa> listData;
    public DataAdapterRopa(ArrayList<Ropa> listData){
        this.listData = listData;
    }
    @NonNull
    @Override
    public DataAdapterRopa.ViewHolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem,null,false);
        return new ViewHolderData(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataAdapterRopa.ViewHolderData holder, int position) {
       //fill data
        holder.screenCodigo.setText("Codigo: "+listData.get(position).getCodigo());
        holder.screenNombre.setText("Prenda: "+listData.get(position).getNombre());
        holder.screenCantidad.setText("Cantidad disponible: "+listData.get(position).getStock());
        holder.screenTalla.setText("Talla: "+listData.get(position).getTalla());
        holder.screenPrecio.setText(String.valueOf("Precio: "+listData.get(position).getPrecio()));
     //   holder.imgRopa.setImageResource(R.id.);
        //holder.imgRopa.setImageResource(Integer.parseInt(listData.get(position).getImagen())); //Vericar Fuente de imagen Aun
    }

    @Override
    public int getItemCount() {
         return listData.size();
    }

    public class ViewHolderData extends RecyclerView.ViewHolder {
        TextView screenCodigo, screenNombre, screenCantidad, screenTalla, screenPrecio;
        ImageView imgRopa;
        public ViewHolderData(@NonNull View itemView) {
            super(itemView);
            //REalted items xml
            screenCodigo = (TextView) itemView.findViewById(R.id.screenCodigo);
            screenNombre = (TextView) itemView.findViewById(R.id.screenNombre);
            screenCantidad = (TextView) itemView.findViewById(R.id.screenCantidad);
            screenTalla = (TextView) itemView.findViewById(R.id.screenTalla);
            screenPrecio = (TextView) itemView.findViewById(R.id.screenPrecio);
            imgRopa = (ImageView) itemView.findViewById(R.id.imgRopa);



        }
    }
}
