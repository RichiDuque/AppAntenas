package com.venta.realidadaumentada.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.venta.realidadaumentada.R;
import com.venta.realidadaumentada.Student;
import com.venta.realidadaumentada.StudentListWrapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NotasFragment extends Fragment {

    TextView NotasConB, NotasMono, NotasDipo, NotasParab, NotasYagi, NotasMicro, NotasMimo, NotasHilo, NotaTotal;
    Button btnNotas;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_notas, container, false);

        NotasConB = rootView.findViewById(R.id.textDetailConcep);
        NotasMono = rootView.findViewById(R.id.textDetailMono);
        NotasDipo = rootView.findViewById(R.id.textDetailDipolo);
        NotasParab = rootView.findViewById(R.id.textDetailParabolica);
        NotasYagi = rootView.findViewById(R.id.textDetailYagi);
        NotasMicro = rootView.findViewById(R.id.textDetailMicro);
        NotasMimo = rootView.findViewById(R.id.textDetailMIMO);
        NotasHilo = rootView.findViewById(R.id.textDetailHilo);
        NotaTotal = rootView.findViewById(R.id.textDefini);
        btnNotas = rootView.findViewById(R.id.btnEnviar);

        // Leer las notas individuales de SharedPreferences y mostrarlas en las TextView correspondientes
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Notas", Context.MODE_PRIVATE);
        NotasConB.setText("Nota: " + sharedPreferences.getInt("nota_Concepto", 0));
        NotasMono.setText("Nota: " + sharedPreferences.getInt("nota_Monopolo", 0));
        NotasDipo.setText("Nota: " + sharedPreferences.getInt("nota_Dipolo", 0));
        NotasParab.setText("Nota: " + sharedPreferences.getInt("nota_Parabolica", 0));
        NotasYagi.setText("Nota: " + sharedPreferences.getInt("nota_YagiUda", 0));
        NotasMicro.setText("Nota: " + sharedPreferences.getInt("nota_Microstrip", 0));
        NotasMimo.setText("Nota: " + sharedPreferences.getInt("nota_MIMO", 0));
        NotasHilo.setText("Nota: " + sharedPreferences.getInt("nota_Hilo", 0));

        // Calcular y mostrar el promedio total de las notas
        int totalNotas = sharedPreferences.getInt("nota_Concepto", 0) +
                sharedPreferences.getInt("nota_Monopolo", 0) +
                sharedPreferences.getInt("nota_Dipolo", 0) +
                sharedPreferences.getInt("nota_Parabolica", 0) +
                sharedPreferences.getInt("nota_YagiUda", 0) +
                sharedPreferences.getInt("nota_Microstrip", 0) +
                sharedPreferences.getInt("nota_MIMO", 0) +
                sharedPreferences.getInt("nota_Hilo", 0);
        double promedio = totalNotas / 8.0; // Dividir por 8 para obtener el promedio
        NotaTotal.setText("Nota definitiva: " + promedio);

        btnNotas.setOnClickListener(view -> {
            // Obtener el nombre de usuario y el código de clase de SharedPreferences
            SharedPreferences sharedPreferencesUser = getActivity().getSharedPreferences("Usuario", Context.MODE_PRIVATE);
            String nombreUsuario = sharedPreferencesUser.getString("usuario", "");
            String codigoClase = sharedPreferencesUser.getString("codigo_clase", "");

            // Obtener las notas individuales de SharedPreferences
            List<Float> notasList = new ArrayList<>();
            notasList.add((float) sharedPreferences.getInt("nota_Concepto", 0));
            notasList.add((float) sharedPreferences.getInt("nota_Monopolo", 0));
            notasList.add((float) sharedPreferences.getInt("nota_Dipolo", 0));
            notasList.add((float) sharedPreferences.getInt("nota_Parabolica", 0));
            notasList.add((float) sharedPreferences.getInt("nota_YagiUda", 0));
            notasList.add((float) sharedPreferences.getInt("nota_Microstrip", 0));
            notasList.add((float) sharedPreferences.getInt("nota_MIMO", 0));
            notasList.add((float) sharedPreferences.getInt("nota_Hilo", 0));

            // Crear el objeto JSON para la solicitud POST
            JSONObject jsonRequest = new JSONObject();
            try {
                jsonRequest.put("name", nombreUsuario);
                jsonRequest.put("codigo_clase", Integer.parseInt(codigoClase));
                jsonRequest.put("notas", new JSONArray(notasList));
            } catch (JSONException e) {
                e.printStackTrace();
            }

            // Crear la solicitud POST con OkHttp
            OkHttpClient client = new OkHttpClient();
            RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonRequest.toString());
            Request request = new Request.Builder()
                    .url("https://apiantennascrud-production.up.railway.app/addStudent")
                    .post(body)
                    .build();

            // Ejecutar la solicitud de forma asíncrona
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    // Manejar el error en caso de que falle la solicitud
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    // Manejar la respuesta en caso de que la solicitud sea exitosa
                    String responseData = response.body().string();

                    mostrarMensaje("Notas subidas correctamente");
                    Log.d("RESPONSE_DATA", responseData);
                    // Puedes hacer algo con la respuesta si es necesario
                }
            });
        });

        return rootView;
    }
    private void mostrarMensaje(final String mensaje) {
        if (getActivity() != null) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getActivity().getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}