package com.venta.realidadaumentada;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DocenteActivity extends AppCompatActivity {

    Button btnIngresar;
    EditText txtDocente, txtContrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docente);

        btnIngresar = findViewById(R.id.btnIngresarD);
        txtDocente = findViewById(R.id.editDocente);
        txtContrasena = findViewById(R.id.editContrasena);
        btnIngresar.setOnClickListener(view -> {
            String usuario = "admin";
            String contrasena = "12345";

            String usuarioIngresado = txtDocente.getText().toString();
            String contrasenaIngresada = txtContrasena.getText().toString();

            if (usuarioIngresado.equals(usuario) && contrasenaIngresada.equals(contrasena)) {
                // Acceso permitido, redirige a la siguiente actividad
                Intent intent = new Intent(DocenteActivity.this, FragmentsDocenteActivity.class);
                startActivity(intent);
            } else {
                // Muestra un mensaje de error si los datos ingresados no son correctos
                Toast.makeText(DocenteActivity.this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
            }
        });
    }
}