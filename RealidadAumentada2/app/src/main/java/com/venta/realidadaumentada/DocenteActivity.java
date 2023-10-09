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
    EditText txtCodigo;
    List<Student> studentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docente);

        btnIngresar = findViewById(R.id.btnIngresarD);
        txtCodigo = findViewById(R.id.editContrasena);
        btnIngresar.setOnClickListener(view -> {
            obtenerDatosDeEstudiantes();
            //Intent intent = new Intent(this, DocenteActivity.class);
            //startActivity(intent);
        });
    }

    private void obtenerDatosDeEstudiantes() {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://apiantennascrud-production.up.railway.app/students")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // Manejar errores de la solicitud HTTP (por ejemplo, falta de conexión)
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseBody = response.body().string();
                    // Procesar la respuesta JSON aquí y actualizar la interfaz de usuario con los datos
                    // Puedes utilizar una biblioteca de análisis JSON como Gson para facilitar el análisis.

                    // Por ejemplo, si estás utilizando Gson, puedes analizar la respuesta de esta manera:
                    Gson gson = new Gson();
                    StudentListWrapper studentListWrapper = gson.fromJson(responseBody, StudentListWrapper.class);

                    // Asegúrate de que StudentListWrapper tenga la estructura adecuada
                    studentList = studentListWrapper.getItems();
                    // Actualiza la interfaz de usuario en el hilo principal
                    validarCodigoClase();
                }
            }
        });
    }

    private void validarCodigoClase() {
        String codigoIngresado = txtCodigo.getText().toString();
        boolean codigoValido = false;

        for (Student student : studentList) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(DocenteActivity.this, "Código de clase "+student.getCodigoClase(), Toast.LENGTH_SHORT).show();
                }
            });
            System.out.print("Aqui "+student.getCodigoClase());
            if (String.valueOf(student.getCodigoClase()).equals(codigoIngresado)) {
                codigoValido = true;
                break; // Código encontrado, no es necesario seguir buscando
            }
        }

        if (codigoValido) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(DocenteActivity.this, "Código de clase válido", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(DocenteActivity.this, "Código de clase no válido. Por favor, inténtalo de nuevo.", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}