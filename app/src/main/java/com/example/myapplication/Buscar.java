package com.example.myapplication;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);
        EditText palabra = (EditText) findViewById(R.id.palabra_a_buscar);
        Button buscar = (Button) findViewById(R.id.btn_buscar);
        
    }
    public void buscar (View view){
        Intent buscar = new Intent(this,Visitante.class);
        buscar.putExtra("url","la url desde buscar");
        startActivity(buscar);
    }
}
