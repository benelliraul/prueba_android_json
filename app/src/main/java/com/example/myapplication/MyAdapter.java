package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.example.myapplication.R.drawable.error;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private ArrayList<producto> mDataset;
    private Context ctx;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public CardView tarjeta;
        public TextView nombre_producto;
        public TextView descripcion_producto;
        public TextView precio_producto;
        public ImageView imagen_producto;
        public MyViewHolder(View v) {
            super(v);
            tarjeta = (CardView) v.findViewById(R.id.la_tarjeta);
            nombre_producto =(TextView) v.findViewById(R.id.nombre_prod);
            descripcion_producto = (TextView) v.findViewById(R.id.descripcion_prod);
            precio_producto = (TextView) v.findViewById(R.id.precio_prod);
            imagen_producto = (ImageView) v.findViewById(R.id.imagen_prod);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(ArrayList<producto> myDataset, Context context) {
        mDataset = myDataset;
        ctx = context ;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_producto, parent, false);


        return new MyViewHolder(v);
    }


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        //Toast.makeText(ctx, "llegamos hasta aca: "+ mDataset.get(position).getRuta_imagen(), Toast.LENGTH_LONG).show();
        holder.nombre_producto.setText(mDataset.get(position).getNombre_prod());
        holder.descripcion_producto.setText(mDataset.get(position).getDescripcion_prod());
        holder.precio_producto.setText(mDataset.get(position).getPrecio_rod());
        try {
            Picasso.with(ctx)
                    .load(mDataset.get(position).getRuta_imagen())
                    .resize(230,230)
                    .error(error)
                    .centerInside()
                    .into(holder.imagen_producto);
        } catch (Exception e) {
            Toast.makeText(ctx, "Erorrrrrrr: "+ e.toString(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }


    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
