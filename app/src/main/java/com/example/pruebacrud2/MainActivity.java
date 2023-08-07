package com.example.pruebacrud2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText etNombre, etEmail, etPassword, etPhone;
    Button btnEnviar;

    RequestQueue requestQueue;

//    private static final String URLI ="http://192.168.1.107/android/save.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestQueue = Volley.newRequestQueue(this);

        etNombre = findViewById(R.id.editNombre);
        etEmail = findViewById(R.id.editEmail);
        etPassword = findViewById(R.id.editPassword);
        etPhone = findViewById(R.id.editPhone);
        btnEnviar = findViewById(R.id.btnEnviar);


        etNombre.setHint("Escribe aquí el nombre...");
        etEmail.setHint("Escribe aquí el email...");
        etPassword.setHint("Escribe aquí contra...");
        etPhone.setHint("Escribe aquí el numero...");


        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*
                //Tomar datos del edittext
                String value = etMarca.getText().toString();

                // Realizar la redirección a MainActivity2

                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("VALUE_KEY", value);
                startActivity(intent);
*/

                regristrarDatos();
            }
        });


    }
    private void regristrarDatos() {
        String nombre = etNombre.getText().toString().trim();
        String correo = etEmail.getText().toString().trim();
        String contra = etPassword.getText().toString().trim();
        String celular = etPhone.getText().toString().trim();

        if (nombre.isEmpty()) {
            Toast.makeText(this, "Ingresa el nombre", Toast.LENGTH_SHORT).show();
        } else if (correo.isEmpty()) {
            Toast.makeText(this, "Ingresa el correo", Toast.LENGTH_SHORT).show();
        } else if (contra.isEmpty()) {
            Toast.makeText(this, "Ingresa la contraseña", Toast.LENGTH_SHORT).show();
        } else if (celular.isEmpty()) {
            Toast.makeText(this, "Ingresa el celular", Toast.LENGTH_SHORT).show();
        } else {
            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Cargando...");
            progressDialog.show();

            StringRequest request = new StringRequest(Request.Method.POST, "http://192.168.1.107/android/save.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            String trimmedResponse = response.trim();

                            if (trimmedResponse.equalsIgnoreCase("No Insertado")){
                                Toast.makeText(MainActivity.this, "Error al registrar", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();

                            } else {

                                Toast.makeText(MainActivity.this, "Registrado correctamente", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                finish();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(MainActivity.this, "Error en la solicitud: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }) {
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("nombre", nombre);
                    params.put("email", correo);
                    params.put("password", contra);
                    params.put("phone", celular);

                    return params;
                }
            };

            RequestQueue requestQueue1 = Volley.newRequestQueue(MainActivity.this);
            requestQueue1.add(request);
        }
    }


    @Override
    public void onClick(View view) {

    }
}
/*
    @Override
    public void onClick(View v) {
        int id =v.getId();

        if( id == R.id.btnEnviar){
            String marca= etMarca.getText().toString().trim();

            String modelo= etModelo.getText().toString().trim();


            String kilometraje= etKilometraje.getText().toString().trim();


            String motivo= etMotivo.getText().toString().trim();


            crearCoche(marca, modelo, kilometraje, motivo);
        } /*else if( id == R.id.btn2){

        }
    }
*/



/*
    private void crearCoche (final String marca, final  String modelo, final String kilometraje, final String motivo){


        StringRequest stringRequest = new StringRequest(

        Request.Method.POST,
        URLI,
        new Response.Listener<String>(){
            public  void onResponse(String response){
                Toast.makeText(MainActivity.this, "Correcto", Toast.LENGTH_SHORT).show();
            }
        },
        new Response.ErrorListener(){
            public void onErrorResponse(VolleyError error){

            }
        }
        ){
            protected Map<String, String> getParams() throws AuthFailureError{

               Map<String, String> params = new HashMap<>();
                params.put("marca", marca);
                params.put("modelo", modelo);
                params.put("kilometraje", kilometraje);
                params.put("motivo", motivo);


                return super.getParams();
            }
        };

    requestQueue.add(stringRequest);
    }
*/
