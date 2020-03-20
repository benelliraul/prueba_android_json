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
    private RecyclerView.LayoutManager layoutManager;
    String g;
    Visitante ctx = Visitante.this;




    //private  MyAdapter  mi_adapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitante);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        //recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        String url = "https://benelliraul.pythonanywhere.com/lista_productos/30";
        final ArrayList <producto> myDataset = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i=0; i<response.length(); i++){
                        JSONObject objeto = response.getJSONObject(i);
                        String precio =objeto.getString("precio_producto");
                        String nombre =objeto.getString("nombre_producto");
                        String descripcion = objeto.getString("descripcion_producto");
                        String ruta= "http://benelliraul.pythonanywhere.com"+objeto.getString("imagen_producto");
                        String ruta_img = ruta.replaceAll("\\\\","");
                        myDataset.add(new producto(nombre,precio,descripcion,ruta_img));
                    }
                    g= myDataset.get(3).getNombre_prod();
                    Toast.makeText(Visitante.this, "Se creo dataset"+ g, Toast.LENGTH_LONG).show();


                } catch (Exception e) {
                    Toast.makeText(Visitante.this, "Error en try", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
                iniciar_vista(myDataset);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Visitante.this, "Error al cargar los datos", Toast.LENGTH_SHORT).show();;
            }
        });

        queue.add(request);

    }

    private void iniciar_vista(ArrayList<producto> myDataset) {
        MyAdapter mAdapter = new MyAdapter(myDataset,ctx);
        recyclerView.setAdapter(mAdapter);
    }
}
