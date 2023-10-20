package com.venta.realidadaumentada.fragmentsDocente;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.venta.realidadaumentada.CodigoClase;
import com.venta.realidadaumentada.EstudiantesActivity;
import com.venta.realidadaumentada.R;
import com.venta.realidadaumentada.adaptador.CodigoAdapter;

import org.jetbrains.annotations.NotNull;
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

public class CodigosFragment extends Fragment implements CodigoAdapter.OnCodigoDeletedListener {

    private ListView listView;
    private List<CodigoClase> codigosList;
    private CodigoAdapter adapter;
    private OkHttpClient client;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_codigos, container, false);

        listView = rootView.findViewById(R.id.listView);
        codigosList = new ArrayList<>();
        adapter = new CodigoAdapter(getActivity(), codigosList);
        adapter.setOnCodigoDeletedListener(this);
        listView.setAdapter(adapter);

        client = new OkHttpClient();

        if (getActivity() != null) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    fetchCodes();
                }
            });
        }

        FloatingActionButton fab = rootView.findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            // Mostrar ventana emergente para ingresar el código de clase
            AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
            builder.setTitle("Crear nuevo código de clase");
            final EditText input = new EditText(requireContext());
            input.setInputType(InputType.TYPE_CLASS_NUMBER);
            builder.setView(input);

            builder.setPositiveButton("Crear", (dialog, which) -> {
                String codigoNuevo = input.getText().toString();
                // Lógica para hacer POST y crear un nuevo código de clase
                validarCodigo(codigoNuevo);
            });
            builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.cancel());

            builder.show();
        });

        return rootView;
    }

    @Override
    public void onCodigoDeleted() {
        if (getActivity() != null) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    fetchCodes();
                }
            });
        }
    }

    private void fetchCodes() {
        Request request = new Request.Builder()
                .url("https://apiantennascrud-production.up.railway.app/getCodes")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseData = response.body().string();
                    try {
                        JSONObject jsonResponse = new JSONObject(responseData);
                        JSONArray items = jsonResponse.getJSONArray("items");

                        codigosList.clear();

                        for (int i = 0; i < items.length(); i++) {
                            JSONObject codeObject = items.getJSONObject(i);
                            int codeValue = codeObject.getInt("codigo_clase");
                            CodigoClase codigoClase = new CodigoClase(codeValue); // Asegúrate de que el constructor de CodigoClase acepte un entero
                            codigosList.add(codigoClase);
                        }

                        if (getActivity() != null) {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    adapter.notifyDataSetChanged();
                                }
                            });
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
    private void crearNuevoCodigo(String codigo) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("codigo_clase", Integer.parseInt(codigo));

            MediaType JSON = MediaType.parse("application/json; charset=utf-8");
            RequestBody requestBody = RequestBody.create(jsonObject.toString(), JSON);

            Request request = new Request.Builder()
                    .url("https://apiantennascrud-production.up.railway.app/addCodSal")
                    .post(requestBody)
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    if (response.isSuccessful()) {
                        getActivity().runOnUiThread(() -> Toast.makeText(getContext(), "Código de clase creado exitosamente", Toast.LENGTH_SHORT).show());
                        // Actualizar la lista de códigos de clase
                        fetchCodes();
                    } else {
                        getActivity().runOnUiThread(() -> Toast.makeText(getContext(), "Error al crear el código de clase", Toast.LENGTH_SHORT).show());
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private void validarCodigo(String codigo) {
        String codigoIngresado = codigo;

        // Validar longitud del código y que no comience con cero
        if (codigoIngresado.length() != 7 || codigoIngresado.startsWith("0")) {
            // Si el código no cumple con las condiciones, muestra un mensaje de error
            getActivity().runOnUiThread(() -> Toast.makeText(getContext(), "El código debe tener 7 dígitos y no puede empezar con 0", Toast.LENGTH_SHORT).show());
            return;
        }else{
            crearNuevoCodigo(codigoIngresado);
        }
    }
}
