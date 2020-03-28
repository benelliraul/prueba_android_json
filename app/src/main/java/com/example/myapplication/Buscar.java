package com.example.myapplication;

import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Buscar extends AppCompatActivity {
    Editable palabra;
    EditText ingresado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);
        ingresado = (EditText) findViewById(R.id.palabra_a_buscar);
        Button buscar = (Button) findViewById(R.id.btn_buscar);
        
    }
    public void buscar (View view){
        palabra = ingresado.getText();
        Intent buscar = new Intent(this,Visitante.class);
        buscar.putExtra("url","https://benelliraul.pythonanywhere.com/buscar_app/"+palabra);
        startActivity(buscar);
    }
}
