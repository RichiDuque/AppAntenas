package com.venta.realidadaumentada.conceptos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.venta.realidadaumentada.AntenaConcepActivity;
import com.venta.realidadaumentada.EvaConcepActivity;
import com.venta.realidadaumentada.R;

public class ConceptosActivity extends AppCompatActivity {

    Button btnEval;
    FloatingActionButton btnAntena;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conceptos);

        btnEval = findViewById(R.id.btnEvaConcep);
        btnAntena = findViewById(R.id.btnAntena);

        btnEval.setOnClickListener(view -> {
            finish();
            Intent intent = new Intent(this, EvaConcepActivity.class);
            startActivity(intent);
        });
        btnAntena.setOnClickListener(view -> {
            Intent intent = new Intent(this, AntenaConcepActivity.class);
            startActivity(intent);
        });
    }
}