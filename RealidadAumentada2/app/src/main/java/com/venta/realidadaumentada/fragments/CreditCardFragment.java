package com.venta.realidadaumentada.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.venta.realidadaumentada.R;
import com.venta.realidadaumentada.Student;
import com.venta.realidadaumentada.StudentListWrapper;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CreditCardFragment extends Fragment {

    TextView NotasConB, NotasMono, NotasDipo, NotasPanel, NotaTotal;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_credit_card, container, false);

        NotasConB = rootView.findViewById(R.id.textDetailIntro);
        NotasMono = rootView.findViewById(R.id.textDetailMono);
        NotasDipo = rootView.findViewById(R.id.textDetailDipolo);
        NotasPanel = rootView.findViewById(R.id.textDetailPanel);
        NotaTotal = rootView.findViewById(R.id.textDefini);

        String nombreEstudianteDeseado = "Ricardo Anres Duque Mora"; // Cambia esto al nombre que desees
        obtenerDatosDeEstudiantes(nombreEstudianteDeseado);

        return rootView;
    }

    private void obtenerDatosDeEstudiantes(String nombreEstudiante) {
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
                    // Buscar el estudiante deseado por su nombre
                    List<Student> studentList = studentListWrapper.getItems();
                    Student estudianteDeseado = null;
                    for (Student student : studentList) {
                        if (student.getName().equals(nombreEstudiante)) {
                            estudianteDeseado = student;
                            break;
                        }
                    }

                    // Actualiza la interfaz de usuario en el hilo principal
                    if (estudianteDeseado != null) {
                        Student finalEstudianteDeseado = estudianteDeseado;
                        if (getActivity() != null) {
                            getActivity().runOnUiThread(() -> {
                                // Mostrar las notas del estudiante deseado en los TextView correspondientes
                                formatNotas(finalEstudianteDeseado.getNotas());
                            });
                        }
                    }
                }
            }
        });
    }

    // Método para formatear las notas y omitir la última nota
    private void formatNotas(List<Double> notas) {

        Double Total = (notas.get(0)+notas.get(1)+notas.get(2)+notas.get(3))/4;
        NotasConB.setText("Nota: "+notas.get(0));
        NotasMono.setText("Nota: "+notas.get(1));
        NotasDipo.setText("Nota: "+notas.get(2));
        NotasPanel.setText("Nota: "+notas.get(3));
        NotaTotal.setText("Nota total: "+Total.toString());
    }

}