package com.venta.realidadaumentada.conceptos;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.venta.realidadaumentada.antenas.AntenaConcepActivity;
import com.venta.realidadaumentada.cuestionarios.EvaConcepActivity;
import com.venta.realidadaumentada.R;
import com.venta.realidadaumentada.cuestionarios.EvaParabolaActivity;

public class ParabolicaActivity extends AppCompatActivity {

    Button btnEval;
    FloatingActionButton btnAntena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parabolica);

        btnEval = findViewById(R.id.btnEvaParabolica);
        btnAntena = findViewById(R.id.btnAntenaParabolica);

        btnEval.setOnClickListener(view -> {
            finish();
            Intent intent = new Intent(this, EvaParabolaActivity.class);
            startActivity(intent);
        });

        btnAntena.setOnClickListener(view -> {
            Intent intent = new Intent(this, AntenaConcepActivity.class);
            startActivity(intent);
        });
    }
}
