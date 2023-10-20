package com.venta.realidadaumentada;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private EditText editCodigo;
    private Button btnIngresar;
    TextView txtDocente;
    private SharedPreferences sharedPreferences, sharedPreferences2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editCodigo = findViewById(R.id.editUsario);
        btnIngresar = findViewById(R.id.btnIngresar);
        txtDocente = findViewById(R.id.txtDocente);

        sharedPreferences = getSharedPreferences("Usuario", Context.MODE_PRIVATE);
        sharedPreferences2 = getSharedPreferences("Notas", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        SharedPreferences.Editor editor2 = sharedPreferences2.edit();
        editor.clear(); // Borra todos los datos en el SharedPreferences
        editor2.clear();
        editor.apply();
        editor2.apply();

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarCodigo();
            }
        });

        txtDocente.setOnClickListener(view -> {
            finish();
            Intent intent = new Intent(this, DocenteActivity.class);
            startActivity(intent);
        });
    }

    private void validarCodigo() {
        String codigoIngresado = editCodigo.getText().toString();

        // Realizar una solicitud HTTP para obtener la lista de códigos de clase
        String url = "https://apiantennascrud-production.up.railway.app/getCodes";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // Obtenemos el arreglo JSON "items" del objeto de respuesta
                            JSONArray itemsArray = response.getJSONArray("items");

                            // Ahora puedes iterar sobre el arreglo "items" para obtener los códigos de clase
                            for (int i = 0; i < itemsArray.length(); i++) {
                                JSONObject item = itemsArray.getJSONObject(i);
                                int codigoClase = item.getInt("codigo_clase");

                                // Realiza la lógica de validación aquí con el código de clase si es necesario
                                if (codigoIngresado.equals(String.valueOf(codigoClase))) {
                                    // El código de clase es válido, puedes permitir el acceso
                                    // Por ejemplo, iniciar la siguiente actividad
                                    Toast.makeText(LoginActivity.this, "Código Válido", Toast.LENGTH_SHORT).show();
                                    finish();
                                    Intent intent = new Intent(LoginActivity.this, UserActivity.class);
                                    startActivity(intent);
                                    // Guarda el código de usuario en SharedPreferences
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString("codigo_clase", codigoIngresado);
                                    editor.apply();
                                    return; // Salir del bucle si se encuentra una coincidencia
                                }
                            }

                            // Si llegamos aquí, el código de clase no es válido
                            Toast.makeText(LoginActivity.this, "Código de clase no válido", Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            // Manejar errores de análisis JSON aquí
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Manejar errores de solicitud HTTP
                        Toast.makeText(LoginActivity.this, "Error al obtener la lista de códigos", Toast.LENGTH_SHORT).show();
                        error.printStackTrace(); // Mostrar información detallada del error en el logcat
                    }
                });

        // Agregar la solicitud a la cola de solicitudes
        Volley.newRequestQueue(this).add(jsonObjectRequest);
    }
}
