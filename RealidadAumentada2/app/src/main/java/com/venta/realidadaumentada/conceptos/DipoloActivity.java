package com.venta.realidadaumentada.conceptos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.venta.realidadaumentada.R;
import com.venta.realidadaumentada.cuestionarios.EvaDipoActivity;
import com.venta.realidadaumentada.cuestionarios.EvaMonoActivity;

public class DipoloActivity extends AppCompatActivity {

    Button btnEval;
    FloatingActionButton btnAntena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dipolo);

        btnEval = findViewById(R.id.btnEvaDipo);
        btnAntena = findViewById(R.id.btnAntenaDipo);

        btnEval.setOnClickListener(view -> {
            finish();
            Intent intent = new Intent(this, EvaDipoActivity.class);
            startActivity(intent);
        });
    }
}