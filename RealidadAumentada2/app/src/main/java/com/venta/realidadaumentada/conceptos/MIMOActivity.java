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
import com.venta.realidadaumentada.cuestionarios.EvaMIMOActivity;

public class MIMOActivity extends AppCompatActivity {

    Button btnEval;
    FloatingActionButton btnAntenaMIMO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mimo);

        btnEval = findViewById(R.id.btnEvaConcepMIMO);
        btnAntenaMIMO = findViewById(R.id.btnAntenaMIMO);

        btnEval.setOnClickListener(view -> {
            finish();
            Intent intent = new Intent(this, EvaMIMOActivity.class);
            startActivity(intent);
        });

        btnAntenaMIMO.setOnClickListener(view -> {
            Intent intent = new Intent(this, AntenaConcepActivity.class);
            startActivity(intent);
        });
    }
}
