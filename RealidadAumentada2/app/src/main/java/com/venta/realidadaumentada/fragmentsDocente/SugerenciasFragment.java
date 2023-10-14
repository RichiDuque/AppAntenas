package com.venta.realidadaumentada.fragmentsDocente;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.venta.realidadaumentada.R;
import com.venta.realidadaumentada.Sugerencia;
import com.venta.realidadaumentada.adaptador.SugerenciaAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SugerenciasFragment extends Fragment {

    private ListView listViewSugerencias;
    private List<Sugerencia> sugerenciasList;
    private SugerenciaAdapter adapter;
    private OkHttpClient client;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sugerencias, container, false);

        listViewSugerencias = rootView.findViewById(R.id.listViewSugerencias);
        sugerenciasList = new ArrayList<>();
        adapter = new SugerenciaAdapter(getActivity(), sugerenciasList);
        listViewSugerencias.setAdapter(adapter);
        client = new OkHttpClient();

        if (getActivity() != null) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    fetchSugerencias();
                }
            });
        }


        return rootView;
    }

    private void fetchSugerencias() {
        Request request = new Request.Builder()
                .url("https://apiantennascrud-production.up.railway.app/getComentary")
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
                    try {
                        JSONObject jsonResponse = new JSONObject(responseData);
                        JSONArray items = jsonResponse.getJSONArray("items");
                        for (int i = 0; i < items.length(); i++) {
                            JSONObject sugerenciaObject = items.getJSONObject(i);
                            String titulo = sugerenciaObject.getString("nombre");
                            String descripcion = sugerenciaObject.getString("descripción");
                            Sugerencia sugerencia = new Sugerencia(titulo, descripcion);
                            sugerenciasList.add(sugerencia);
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

}
