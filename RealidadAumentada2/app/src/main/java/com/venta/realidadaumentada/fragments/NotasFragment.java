package com.venta.realidadaumentada.fragments;

import android.content.Context;
import android.content.SharedPreferences;
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

public class NotasFragment extends Fragment {

    TextView NotasConB, NotasMono, NotasDipo, NotasPanel, NotaTotal;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_notas, container, false);

        NotasConB = rootView.findViewById(R.id.textDetailConcep);
        NotasMono = rootView.findViewById(R.id.textDetailMono);
        NotasDipo = rootView.findViewById(R.id.textDetailDipolo);
        NotasPanel = rootView.findViewById(R.id.textDetailPanel);
        NotaTotal = rootView.findViewById(R.id.textDefini);

        // Leer las notas individuales de SharedPreferences y mostrarlas en las TextView correspondientes
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Notas", Context.MODE_PRIVATE);
        NotasConB.setText("Nota: " + sharedPreferences.getInt("nota_Concepto", 0));
        NotasMono.setText("Nota: " + sharedPreferences.getInt("nota_Monopolo", 0));
        NotasDipo.setText("Nota: " + sharedPreferences.getInt("nota_Dipolo", 0));
        NotasPanel.setText("Nota: " + sharedPreferences.getInt("nota_Panel", 0));

        // Calcular y mostrar el promedio total de las notas
        int totalNotas = sharedPreferences.getInt("nota_Concepto", 0) +
                sharedPreferences.getInt("nota_Monopolo", 0) +
                sharedPreferences.getInt("nota_Dipolo", 0) +
                sharedPreferences.getInt("nota_Panel", 0);
        double promedio = totalNotas / 4.0; // Dividir por 4 para obtener el promedio
        NotaTotal.setText("Nota definitiva: " + promedio);

        return rootView;
    }

}