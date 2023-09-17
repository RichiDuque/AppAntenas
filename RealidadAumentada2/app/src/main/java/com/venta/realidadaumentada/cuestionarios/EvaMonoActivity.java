package com.venta.realidadaumentada.cuestionarios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.venta.realidadaumentada.FragmentsActivity;
import com.venta.realidadaumentada.R;

public class EvaMonoActivity extends AppCompatActivity {

    Button btnFinal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eva_mono);

        btnFinal = findViewById(R.id.btnFinMono);

        btnFinal.setOnClickListener(view -> {
            finish();
            Intent intent = new Intent(this, FragmentsActivity.class);
            startActivity(intent);
        });

    }
}