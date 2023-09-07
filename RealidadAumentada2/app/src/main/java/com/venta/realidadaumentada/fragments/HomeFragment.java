package com.venta.realidadaumentada.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.venta.realidadaumentada.R;

public class HomeFragment extends Fragment {

    Button btnIngresar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        btnIngresar = rootView.findViewById(R.id.btnIntro);

        btnIngresar.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setAction("com.venta.realidadaumentada");
            startActivity(intent);
        });

        return rootView;


    }
}