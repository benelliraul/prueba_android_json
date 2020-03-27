package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ComponentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Buscar_cerca extends AppCompatActivity {
    Editable el_rango;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_cerca);
        EditText rango = (EditText)findViewById(R.id.rango_metros);
        Button buscar = (Button)findViewById(R.id.btn_buscar_cerca);
        el_rango = rango.getText();
    }
    public void buscar_cerca_intent (View view){
        Intent buscar = new Intent (this,Visitante.class);
        buscar.putExtra("url","https://benelliraul.pythonanywhere.com"+el_rango);
        startActivity(buscar);

    }
}
