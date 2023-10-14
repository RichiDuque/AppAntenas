package com.venta.realidadaumentada.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.venta.realidadaumentada.conceptos.ConceptosActivity;
import com.venta.realidadaumentada.R;
import com.venta.realidadaumentada.conceptos.DipoloActivity;
import com.venta.realidadaumentada.conceptos.HiloActivity;
import com.venta.realidadaumentada.conceptos.MIMOActivity;
import com.venta.realidadaumentada.conceptos.MicrostripActivity;
import com.venta.realidadaumentada.conceptos.MonoPoloActivity;
import com.venta.realidadaumentada.conceptos.ParabolicaActivity;
import com.venta.realidadaumentada.conceptos.YagiUdaActivity;

public class HomeFragment extends Fragment {

    Button btnIngresar, btnMono, btnDipo, btnParabolica, btnYagi, btnMicro, btnMimo, btnHilo;
    TextView txtTitulo;
    SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        btnIngresar = rootView.findViewById(R.id.btnIntro);
        btnMono = rootView.findViewById(R.id.btnMono);
        btnDipo = rootView.findViewById(R.id.btnDipolo);
        btnParabolica = rootView.findViewById(R.id.btnParabolica);
        btnYagi = rootView.findViewById(R.id.btnYagiUda);
        btnMicro = rootView.findViewById(R.id.btnMicrostrip);
        btnMimo = rootView.findViewById(R.id.btnMIMO);
        btnHilo = rootView.findViewById(R.id.btnHilo);
        txtTitulo = rootView.findViewById(R.id.txtTitulo);

        sharedPreferences = requireContext().getSharedPreferences("Usuario", Context.MODE_PRIVATE);
        // Recuperar el nombre de usuario guardado
        String usuarioGuardado = sharedPreferences.getString("usuario", "");

        if (!usuarioGuardado.isEmpty()) {
            // Si hay un nombre de usuario guardado, establece el valor en el TextView
            txtTitulo.setText("Hola " + usuarioGuardado + "!"); // Concatena el nombre de usuario
        }

        btnIngresar.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), ConceptosActivity.class);
            startActivity(intent);
        });

        btnMono.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), MonoPoloActivity.class);
            startActivity(intent);
        });

        btnDipo.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), DipoloActivity.class);
            startActivity(intent);
        });

        btnParabolica.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), ParabolicaActivity.class);
            startActivity(intent);
        });

        btnYagi.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), YagiUdaActivity.class);
            startActivity(intent);
        });

        btnMicro.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), MicrostripActivity.class);
            startActivity(intent);
        });

        btnMimo.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), MIMOActivity.class);
            startActivity(intent);
        });

        btnHilo.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), HiloActivity.class);
            startActivity(intent);
        });

        return rootView;


    }


}