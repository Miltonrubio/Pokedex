package com.example.pruebacrud2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class EditarCrud extends AppCompatActivity {

    EditText etMotivo, etMarca, etModelo, etKilometraje;
    Button btnEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_crud);




        etMarca = findViewById(R.id.editMarca2);
        etModelo = findViewById(R.id.editModelo2);
        etMotivo = findViewById(R.id.editMotivo2);
        etKilometraje = findViewById(R.id.editKilometraje2);
        btnEnviar = findViewById(R.id.btnEnviar2);


        etMarca.setHint("Escribe aquí la marca...");
        etModelo.setHint("Escribe aquí el modelo...");
        etMotivo.setHint("Escribe aquí el motivo...");
        etKilometraje.setHint("Escribe aquí el kilometraje...");
    }
}