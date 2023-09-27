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
import com.venta.realidadaumentada.conceptos.DipoloActivity;
import com.venta.realidadaumentada.conceptos.MonoPoloActivity;
import com.venta.realidadaumentada.conceptos.PanelActivity;

public class HomeFragment extends Fragment {

    Button btnIngresar, btnMono, btnDipo, btnPanel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        btnIngresar = rootView.findViewById(R.id.btnIntro);
        btnMono = rootView.findViewById(R.id.btnMono);
        btnDipo = rootView.findViewById(R.id.btnDipolo);
        btnPanel = rootView.findViewById(R.id.btnPanel);

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

        btnPanel.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), PanelActivity.class);
            startActivity(intent);
        });

        return rootView;


    }


}