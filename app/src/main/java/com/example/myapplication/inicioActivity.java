package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class inicioActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        inicioActivity ctx = this;
        SharedPreferences sharedPref = getSharedPreferences("teinda_logueada",this.MODE_PRIVATE);
        String id_actual =sharedPref.getString("id_tienda","null");
        SharedPreferences.Editor editor = sharedPref.edit();

        /*if (id_actual.equals("crear")){
            //Toast.makeText(ctx,"Bienvenido!!",Toast.LENGTH_LONG).show();

        }else {
            ir_a_logueado(ctx.getCurrentFocus());
        }

         */
    }
    @Override
    public void onRestart() {

        super.onRestart();
        Button boton_reg_carga = (Button)findViewById(R.id.boton_registro);
        Button boton_login_out = (Button) findViewById(R.id.iniciar_sesion);
        Button boton_invitado = (Button) findViewById(R.id.btn_invitado);
        SharedPreferences sharedPref = getSharedPreferences("teinda_logueada",this.MODE_PRIVATE);
        String id_actual =sharedPref.getString("id_tienda","null");
        if(id_actual.equals("crear")){

        }else{
            boton_invitado.setText("Mi Tienda");
            boton_reg_carga.setText("Agregar producto");
            boton_login_out.setText("Cerrar sesion");
        }
    }


    /* Boton Inicio sesion */
    public void inicio_activity(View view){
        Intent cambiarn = new Intent(this, Registro.class); /* llevar a clase login*/
        startActivity(cambiarn);
    }

    /* Boton soy visitante, lleva al main activity */
    public void iniciarSesion_activity(View view){
        SharedPreferences sharedPref = getSharedPreferences("teinda_logueada",this.MODE_PRIVATE);
        String id_actual =sharedPref.getString("id_tienda","crear");
        SharedPreferences.Editor editor = sharedPref.edit();
        if(id_actual.equals("crear")){
            Intent cambiarn = new Intent(this, Registro.class);
            startActivity(cambiarn);
        }else{
            editor.putString("nombre", "null");
            editor.putString("celular", "null");
            editor.putString("correo", "null");
            editor.putString("direccion", "null");
            editor.putString("categoria", "null");
            editor.putString("imagen", "null");
            editor.putString("id_tienda","crear");
            editor.apply();
            editor.commit();
            Intent reiniciar = new Intent(this,this.getClass());
            startActivity(reiniciar);
            this.finish();

        }

    }
    /* Boton visitante */
    public void Visitante(View view){
        SharedPreferences sharedPref = getSharedPreferences("teinda_logueada",this.MODE_PRIVATE);
        String id_actual =sharedPref.getString("id_tienda","crear");
        if(id_actual.equals("crear")){
            Intent cambiarn = new Intent(this, Visitante_inicio.class);
            startActivity(cambiarn);
        }else{
            Intent logueado = new Intent(this,usuario_logeado.class);
            startActivity(logueado);
        }

    }
    public void ir_a_logueado (View view){
        Intent logueado = new Intent(this,usuario_logeado.class);
        startActivity(logueado);
    }
    public  void crear_tienda (View view){
        SharedPreferences sharedPref = getSharedPreferences("teinda_logueada",this.MODE_PRIVATE);
        String id_actual =sharedPref.getString("id_tienda","crear");
        if(id_actual.equals("crear")){
            Intent crear = new Intent(this,Crear_tienda.class);
            startActivity(crear);
        }else{
            Intent agregar_prod = new Intent(this,addProducto_Activity.class);
            startActivity(agregar_prod);
        }

    }
}