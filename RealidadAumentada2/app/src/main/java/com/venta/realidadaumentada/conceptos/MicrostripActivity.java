package com.venta.realidadaumentada.conceptos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.venta.realidadaumentada.antenas.AntenaConcepActivity;
import com.venta.realidadaumentada.cuestionarios.EvaConcepActivity;
import com.venta.realidadaumentada.R;
import com.venta.realidadaumentada.cuestionarios.EvaMicrostripActivity;

public class MicrostripActivity extends AppCompatActivity {

    Button btnEval;
    View btnAntenaMicrostrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_microstrip);

        btnEval = findViewById(R.id.btnEvaConcepMicrostrip);
        btnAntenaMicrostrip = findViewById(R.id.btnAntenaMicrostrip);

        btnEval.setOnClickListener(view -> {
            finish();
            Intent intent = new Intent(this, EvaMicrostripActivity.class);
            startActivity(intent);
        });

        btnAntenaMicrostrip.setOnClickListener(view -> {
            Intent intent = new Intent(this, AntenaConcepActivity.class);
            startActivity(intent);
        });
    }
}
