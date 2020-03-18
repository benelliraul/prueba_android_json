package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.example.myapplication.R.drawable.error;

public class Crear_tienda extends AppCompatActivity {
    ImageView imagen;
    EditText nombre;
    EditText direccion;
    EditText correo;
    EditText celular;
    EditText contrasena;
    Bitmap bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_tienda);
        imagen = (ImageView) findViewById(R.id.imagen_cargada);
        nombre = (EditText) findViewById(R.id.Nombre_ingresado);
        direccion = (EditText)findViewById(R.id.Direccion_ingresada);
        celular = (EditText)findViewById(R.id.Telefono_ingresado);
        correo = (EditText)findViewById(R.id.Correo_ingresado);
        contrasena = (EditText)findViewById(R.id.Contrasena_ingresada);


    }

    public void onClick(View view) {
        cargar_imagen();
    }

    private void cargar_imagen() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent,"Elija la aplicacion"),10);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK){
            Uri path =data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),path);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Picasso.with(Crear_tienda.this)
                    .load(path)
                    .error(error)
                    .fit()
                    .centerInside()
                    .into(imagen);
        }
    }

    public void Envair_datos(View view){Envair_los_datos();}

    private void Envair_los_datos() {
        if(nombre.getText()!=null && direccion.getText() != null && celular != null && correo != null && contrasena != null){
            RequestQueue queue = Volley.newRequestQueue(this);
            String url_form="http://benelliraul.pythonanywhere.com/formulario_app";
            StringRequest request = new StringRequest(Request.Method.POST, url_form, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(getApplicationContext(),response, Toast.LENGTH_LONG).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(),"La imagen supera los 100 mb",Toast.LENGTH_LONG).show();
                }
            })
            {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    String nombre_nuevo = nombre.getText().toString();
                    String direccion_nueva = direccion.getText().toString();
                    String celular_nuevo = celular.getText().toString();
                    String correo_nuevo = correo.getText().toString();
                    String contrasena_nueva= contrasena.getText().toString();
                    String imagen_nueva = convertir_img_string(bitmap);

                    Map<String,String> parametros = new HashMap<>();
                    parametros.put("nombre",nombre_nuevo);
                    parametros.put("direccion",direccion_nueva);
                    parametros.put("celular",celular_nuevo);
                    parametros.put("correo",correo_nuevo);
                    parametros.put("contrasena",contrasena_nueva);
                    parametros.put("imagen_b64",imagen_nueva);

                    return parametros;
                }
            };
            queue.add(request);

        }

    }

    private String convertir_img_string(Bitmap bitmap) {
        ByteArrayOutputStream array = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,array);
        byte[] imagenByte= array.toByteArray();
        String imagenStrin = Base64.encodeToString(imagenByte,Base64.DEFAULT);

        return imagenStrin;
    }

    {

    }
}
