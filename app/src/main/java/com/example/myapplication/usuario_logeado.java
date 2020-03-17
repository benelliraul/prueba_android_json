package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import static com.example.myapplication.R.drawable.error;

public class usuario_logeado extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_logeado);
        final TextView direccion = (TextView) findViewById(R.id.direccion_dinamica);
        final TextView correo = (TextView) findViewById(R.id.correo_dinamico);
        final TextView nombre = (TextView) findViewById(R.id.nombre_dinamico);
        final TextView celular = (TextView) findViewById(R.id.celular_dinamico);
        final ImageView imagen_tienda = (ImageView) findViewById(R.id.imagen_tienda);
        final usuario_logeado ctx = this;
        //SharedPreferences sharedPref = ctx.getPreferences(Context.MODE_PRIVATE);
        //SharedPreferences.Editor editor = sharedPref.edit();
        SharedPreferences sharedPref = ctx.getSharedPreferences("teinda_logueada",MODE_PRIVATE);
        String nombre_rec=sharedPref.getString("nombre","null");
        String direccion_rec =sharedPref.getString("direccion","null");
        String correo_rec=sharedPref.getString("correo","null");
        String celular_rec=sharedPref.getString("celular","null");
        String ruta_imagen=sharedPref.getString("imagen","null");
        String categoria_rec=sharedPref.getString("categoria","null");
        nombre.setText(nombre_rec);
        direccion.setText(direccion_rec);
        correo.setText(correo_rec);
        celular.setText(celular_rec);
        Toast.makeText(ctx, ruta_imagen, Toast.LENGTH_LONG).show();
        Picasso.with(usuario_logeado.this)
                .load(ruta_imagen)
                .error(error)
                .fit()
                .centerInside()
                .into(imagen_tienda);

    }

}
