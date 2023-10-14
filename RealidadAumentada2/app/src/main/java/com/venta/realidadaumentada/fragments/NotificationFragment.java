package com.venta.realidadaumentada.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.venta.realidadaumentada.ComentarioActivity;
import com.venta.realidadaumentada.R;
import com.venta.realidadaumentada.conceptos.ConceptosActivity;


public class NotificationFragment extends Fragment {

    Button btnPQRS, btnReferencias, btnSalir;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_notification, container, false);

        btnPQRS = rootView.findViewById(R.id.btnComentarios);
        btnReferencias = rootView.findViewById(R.id.btnReferencias);
        btnSalir = rootView.findViewById(R.id.btnCerrar);

        btnPQRS.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), ComentarioActivity.class);
            startActivity(intent);
        });

        btnReferencias.setOnClickListener(view -> {

        });

        btnSalir.setOnClickListener(view -> {

        });

        return rootView;
    }
}