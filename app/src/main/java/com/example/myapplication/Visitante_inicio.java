package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Visitante_inicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitante_inicio);
        Button ver_todas = (Button) findViewById(R.id.ver_todas_tiendas);
        Button busca = (Button) findViewById(R.id.buscar_por_contenido);
        Button buscar_cercanas = (Button) findViewById(R.id.buscar_cercanas);
        Button registrse = (Button) findViewById(R.id.crear_tienda);
        Button login = (Button) findViewById(R.id.iniciar_sesion);

    }
    public void ver_todas_tiendas (View view){
        Intent ver_todas = new Intent(this,Visitante.class);
        startActivity(ver_todas);
    }
    public void registrarse_desde_visitante (View view){
        Intent registrarse = new Intent (this,Crear_tienda.class);
        startActivity(registrarse);
    }
    public void login_desde_visitante (View view){
        Intent login = new Intent(this, Registro.class);
        startActivity(login);
    }
    public void buscar_cerca_intent ( View view){
        Intent buscar_cerca = new Intent (this, Buscar_cerca.class);
        startActivity(buscar_cerca);
    }
    public void buscar_intent (View view){
        Intent buscar = new Intent(this,Buscar.class);
        startActivity(buscar);
    }


}
