package com.venta.realidadaumentada.conceptos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.venta.realidadaumentada.R;
import com.venta.realidadaumentada.cuestionarios.EvaMonoActivity;
import com.venta.realidadaumentada.cuestionarios.EvaPanelActivity;

public class PanelActivity extends AppCompatActivity {

    Button btnEval;
    FloatingActionButton btnAntena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel);

        btnEval = findViewById(R.id.btnEvaPanel);
        btnAntena = findViewById(R.id.btnAntenaPanel);

        btnEval.setOnClickListener(view -> {
            finish();
            Intent intent = new Intent(this, EvaPanelActivity.class);
            startActivity(intent);
        });
    }
}