package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.transform.ErrorListener;

import static com.example.myapplication.R.drawable.error;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //se relacionan los textview del xml al java
        final TextView direccion = (TextView) findViewById(R.id.direccion_dinamica);
        final TextView correo = (TextView) findViewById(R.id.correo_dinamico);
        final TextView nombre = (TextView) findViewById(R.id.nombre_dinamico);
        final TextView celular = (TextView) findViewById(R.id.celular_dinamico);
        //final TextView json_object_2 = (TextView) findViewById(R.id.json_object);
        final ImageView imagen_tienda = (ImageView) findViewById(R.id.imagen_tienda);
        final String[] url_imagen = {""};
        final MainActivity ctx = this;
        //se crea el request queue, volley maneja las solicitudes

        RequestQueue queue = Volley.newRequestQueue(this);
        String url_form="https://benelliraul.pythonanywhere.com/formulario_app";
        final String nombre_form= "nombre desde app";
        final String direccion_form= "direccion desde app";
        final String correo_form = "correo desde app";
        final String contrasena_form= "contrasena lallal";
        final String celular_form= "celular desde app";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url_form,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                        Toast.makeText(MainActivity.this,response.toString(), Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError errorr) {
                        // error
                        Log.d("Error.Response", String.valueOf(errorr));
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("nomnbre", nombre_form);
                params.put("direccion", direccion_form);
                params.put("celular", celular_form);
                params.put("correo", correo_form);
                params.put("contrasena", contrasena_form);

                return params;
            }
        };
        queue.add(postRequest);
        String url_1 = "https://benelliraul.pythonanywhere.com/json_object";


        //solicitud a url_1, esperamos un jsonobject
        /*
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url_1, (String) null, new Response.Listener<JSONObject>() {

                    @Override
                    //se coloca la respuesta en el primer text view
                    public void onResponse(JSONObject response) {
                        textView.setText(response.toString());

                    }
                }, new Response.ErrorListener() {
            //manejo de errores ( no se que hace exactamente
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        textView.setText("Hubo algun error!!!");

                    }
                });
        //se agrega la respuesta a la cola, para despues manejarla creo, pero no se bien como
        queue.add(jsonObjectRequest);
         */

        String url_2 = "https://benelliraul.pythonanywhere.com/json_una_tienda";
        //se hace la peticion a la url_2, esperamos un jason_object de una tineda ( la 04)
        JsonObjectRequest jsonObjectRequest_2 = new JsonObjectRequest
                (Request.Method.GET, url_2, new JSONObject(), new Response.Listener<JSONObject>() {

                    @Override
                    //se coloca la respuesta en el tercer textview
                    public void onResponse(JSONObject response) {
                        //json_object_2.setText(response.toString());
                        try {//aca se accede al valor asociado a la clave
                            String valor = response.getString("nombre_tienda");
                            String valor_2 = response.getString("contacto");
                            String valor_3 = response.getString("correo_electronico");
                            String valor_4 = response.getString("direccion_tienda");
                            String valor_5 = response.getString("categoria_tienda");
                            //recupera la ruta de la imagen y la completa
                            url_imagen[0] = "https://benelliraul.pythonanywhere.com"+response.getString( "imagen_portada_tienda");
                            //se coloca el nombre de la tienda en el textview nombre
                            nombre.setText(valor);
                            celular.setText(valor_2);
                            direccion.setText(valor_4);
                            correo.setText(valor_3);
                            //arregla unas cosas de la direccin
                            final String url_img = url_imagen[0].replaceAll("\\\\","");
                            //pide la imagen y la asocia al textview
                            Picasso.with(ctx)
                                    .load(url_img)
                                    .error(error)
                                    .fit()
                                    .centerInside()
                                    .into(imagen_tienda);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Toast.makeText(MainActivity.this,error.toString(), Toast.LENGTH_SHORT).show();

                    }
                });
        queue.add(jsonObjectRequest_2);
        /*
        String url_3 = "http://benelliraul.pythonanywhere.com/json_array";
        // se hace una peticion donde se espera recibir un json array
        JsonArrayRequest arrayRequest = new JsonArrayRequest(
                Request.Method.GET, url_3, (String) null, new Response.Listener<JSONArray>() {
                    @Override
            public void onResponse (JSONArray response){
                        //se agrega el json al textview
                        textView_2.setText(response.toString());
                    }
        }, new Response.ErrorListener(){
                    @Override
            public void onErrorResponse(VolleyError error){
                        textView_2.setText("mas erroresssss!");
                    }
        }
        );
        queue.add(arrayRequest);
        final String url_4 = url_imagen[0].replaceAll("\\\\","");

         */


      }
    public void registro_activity(View view){
        Intent cambiar = new Intent(this, Registro.class);startActivity(cambiar);
    }
    }



