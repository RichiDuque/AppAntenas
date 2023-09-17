package com.venta.realidadaumentada.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.venta.realidadaumentada.conceptos.ConceptosActivity;
import com.venta.realidadaumentada.R;
import com.venta.realidadaumentada.conceptos.MonoPoloActivity;

public class HomeFragment extends Fragment {

    Button btnIngresar, btnMono;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        btnIngresar = rootView.findViewById(R.id.btnIntro);
        btnMono = rootView.findViewById(R.id.btnMono);

        btnIngresar.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), ConceptosActivity.class);
            startActivity(intent);
        });

        btnMono.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), MonoPoloActivity.class);
            startActivity(intent);
        });

        return rootView;


    }
}