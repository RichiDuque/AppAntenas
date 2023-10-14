package com.venta.realidadaumentada;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.venta.realidadaumentada.adaptador.EstudiantesAdapter;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class EstudiantesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estudiantes);

        Intent intent = getIntent();
        String codigoClase = intent.getStringExtra("codigo_clase");

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://apiantennascrud-production.up.railway.app/getStudents/" + codigoClase)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseData = response.body().string();
                    Type listType = new TypeToken<List<Student>>(){}.getType();
                    List<Student> students = new Gson().fromJson(responseData, listType);

                    runOnUiThread(() -> {
                        ListView listView = findViewById(R.id.listViewEstudiantes);
                        EstudiantesAdapter adapter = new EstudiantesAdapter(EstudiantesActivity.this, students);
                        listView.setAdapter(adapter);
                    });
                } else {
                    runOnUiThread(() -> Toast.makeText(getApplicationContext(), "Error al obtener los datos de los estudiantes", Toast.LENGTH_SHORT).show());
                }
            }
        });
    }
}
