package com.example.myapplication;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Obtener_dataset {
    private String la_clase;
    private Context ctx;
    private String url;
    private ArrayList <Tiendas> unDataset;

    public Obtener_dataset(String la_clase, Context ctx, String url, ArrayList<Tiendas> unDataset) {
        this.la_clase = la_clase;
        this.ctx = ctx;
        this.url = url;
        this.unDataset = unDataset;
    }


    private void request() {
        ArrayList<Tiendas> datos;
        RequestQueue queue = Volley.newRequestQueue(ctx);
        JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject objeto = response.getJSONObject(i);
                        String nombre = objeto.getString("nombre_tienda");
                        String direccion = objeto.getString("direccion_tienda");
                        String categoria = objeto.getString("categoria_tienda");
                        String ruta = "https://benelliraul.pythonanywhere.com" + objeto.getString("ruta_imagen");
                        String ruta_img = ruta.replaceAll("\\\\", "");
                        String id = objeto.getString("id_tienda");
                        String correo = objeto.getString("correo_tienda");
                        String celular = objeto.getString("celular_tienda");
                        //mDataset.add(new Tiendas(nombre,direccion,celular,correo,id,categoria,ruta_img,0,0));

                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ctx, "Error al cargar los datos", Toast.LENGTH_SHORT).show();
                ;
            }
        });

        queue.add(request);

    }

    private void enviar_datos(ArrayList<Tiendas> datos) {

    }

    private ArrayList<Tiendas> generar_array_tiendas(ArrayList<Tiendas> mDataset, JSONArray response) {
        try {
            for (int i = 0; i < response.length(); i++) {
                JSONObject objeto = response.getJSONObject(i);
                String nombre = objeto.getString("nombre_tienda");
                String direccion = objeto.getString("direccion_tienda");
                String categoria = objeto.getString("categoria_tienda");
                String ruta = "https://benelliraul.pythonanywhere.com" + objeto.getString("ruta_imagen");
                String ruta_img = ruta.replaceAll("\\\\", "");
                String id = objeto.getString("id_tienda");
                String correo = objeto.getString("correo_tienda");
                String celular = objeto.getString("celular_tienda");
                mDataset.add(new Tiendas(nombre,direccion,celular,correo,id,categoria,ruta_img,0,0));

            }


        } catch (Exception e) {
            e.printStackTrace();
        }return mDataset;
    }

    private void generar_array_productos( ArrayList<Object> mDataset, JSONArray response) {
        try {
            for (int i = 0; i < response.length(); i++) {
                JSONObject objeto = response.getJSONObject(i);
                String precio = objeto.getString("precio_producto");
                String nombre = objeto.getString("nombre_producto");
                String descripcion = objeto.getString("descripcion_producto");
                String ruta = "https://benelliraul.pythonanywhere.com" + objeto.getString("imagen_producto");
                String ruta_img = ruta.replaceAll("\\\\", "");
                mDataset.add(new producto(nombre, precio, descripcion, ruta_img));
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

