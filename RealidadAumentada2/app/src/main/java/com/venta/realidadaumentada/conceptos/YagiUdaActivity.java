package com.venta.realidadaumentada.conceptos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.venta.realidadaumentada.antenas.AntenaConcepActivity;
import com.venta.realidadaumentada.cuestionarios.EvaConcepActivity;
import com.venta.realidadaumentada.R;
import com.venta.realidadaumentada.cuestionarios.EvaYagiUdaActivity;

public class YagiUdaActivity extends AppCompatActivity {

    Button btnEval;
    View btnAntenaYagiUda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yagi_uda);

        btnEval = findViewById(R.id.btnEvaConcepYagiUda);
        btnAntenaYagiUda = findViewById(R.id.btnAntenaYagiUda);

        btnEval.setOnClickListener(view -> {
            finish();
            Intent intent = new Intent(this, EvaYagiUdaActivity.class);
            startActivity(intent);
        });

        btnAntenaYagiUda.setOnClickListener(view -> {
            Intent intent = new Intent(this, AntenaConcepActivity.class);
            startActivity(intent);
        });
    }
}
