package com.venta.realidadaumentada.conceptos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.venta.realidadaumentada.AntenaConcepActivity;
import com.venta.realidadaumentada.AntenaMonoActivity;
import com.venta.realidadaumentada.EvaConcepActivity;
import com.venta.realidadaumentada.R;
import com.venta.realidadaumentada.cuestionarios.EvaMonoActivity;

public class MonoPoloActivity extends AppCompatActivity {

    Button btnEval;
    FloatingActionButton btnAntena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mono_polo);

        btnEval = findViewById(R.id.btnEvaMono);
        btnAntena = findViewById(R.id.btnAntenaMono);

        btnEval.setOnClickListener(view -> {
            finish();
            Intent intent = new Intent(this, EvaMonoActivity.class);
            startActivity(intent);
        });
        btnAntena.setOnClickListener(view -> {
            Intent intent = new Intent(this, AntenaMonoActivity.class);
            startActivity(intent);
        });
    }
}