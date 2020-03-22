package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class Visitante extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MyAdapter mAdapter;
    private Adaptador_tiendas adaptador_tiendas;
    private RecyclerView.LayoutManager layoutManager;
    public Visitante ctx = Visitante.this;
    final String url = "https://benelliraul.pythonanywhere.com/lista_productos/30";
    final String url_tiendas = "https://benelliraul.pythonanywhere.com/visitantes_app";
    public String clase = "tienda";




    //private  MyAdapter  mi_adapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitante);
        final ArrayList<Tiendas> mDataset = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        //recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        RequestQueue queue = Volley.newRequestQueue(ctx);
        JsonArrayRequest request = new JsonArrayRequest(url_tiendas, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject objeto = response.getJSONObject(i);
                        String nombre = objeto.getString("nombre_tienda");
                        String direccion = objeto.getString("direccion_tienda");
                        String categoria = objeto.getString("categoria_tienda");
                        String ruta = "https://benelliraul.pythonanywhere.com" + objeto.getString("imagen_portada_tienda");
                        String ruta_img = ruta.replaceAll("\\\\", "");
                        String id = objeto.getString("id_tienda");
                        String correo = objeto.getString("correo_electronico");
                        String celular = objeto.getString("contacto");
                        mDataset.add(new Tiendas(nombre, direccion, celular, correo, id, categoria, ruta_img, 0, 0));

                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }iniciar_vista(mDataset);

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ctx, "Error al cargar los datos", Toast.LENGTH_SHORT).show();

            }
        });

        queue.add(request);


    }

    private void iniciar_vista(ArrayList<Tiendas> datastet) {

        Adaptador_tiendas mAdapter = new Adaptador_tiendas(datastet,ctx);
        recyclerView.setAdapter(mAdapter);
    }
}