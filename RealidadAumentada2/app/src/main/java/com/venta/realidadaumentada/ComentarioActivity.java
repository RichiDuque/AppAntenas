package com.venta.realidadaumentada;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.venta.realidadaumentada.conceptos.ConceptosActivity;
import com.venta.realidadaumentada.fragments.NotificationFragment;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ComentarioActivity extends AppCompatActivity {

    EditText editTitleSug, editTextComentario;
    Button btnSugerencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comentario);

        editTitleSug = findViewById(R.id.editTitleSug);
        editTextComentario = findViewById(R.id.editTextComentario);
        btnSugerencia = findViewById(R.id.btnSugerencia);

        btnSugerencia.setOnClickListener(v -> {
            enviarSugerencia(editTextComentario.getText().toString(),editTitleSug.getText().toString() );
            Intent intent = new Intent(this, FragmentsActivity.class);
            startActivity(intent);
        });
    }
    private void enviarSugerencia(String descripcion, String titulo) {
        // Crea el JSON de la solicitud
        JSONObject jsonRequest = new JSONObject();
        try {
            jsonRequest.put("descripción", descripcion);
            jsonRequest.put("nombre", titulo);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Realiza la solicitud POST
        OkHttpClient client = new OkHttpClient();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, jsonRequest.toString());
        Request request = new Request.Builder()
                .url("https://apiantennascrud-production.up.railway.app/addComentario")
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    // Muestra el mensaje "notas subidas correctamente"
                    String responseData = response.body().string();
                    Log.d("RESPONSE_DATA", responseData);
                    // Utiliza el método adecuado para mostrar mensajes en tu interfaz de usuario
                    runOnUiThread(() -> {
                        Toast.makeText(ComentarioActivity.this, "Sugerencia enviada", Toast.LENGTH_SHORT).show();
                    });
                }
            }
        });
    }
}
