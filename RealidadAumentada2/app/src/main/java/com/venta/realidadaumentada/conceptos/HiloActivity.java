package com.venta.realidadaumentada.conceptos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.venta.realidadaumentada.antenas.AntenaHiloActivity;
import com.venta.realidadaumentada.R;
import com.venta.realidadaumentada.cuestionarios.EvaHiloActivity;

public class HiloActivity extends AppCompatActivity {

    Button btnEval;
    FloatingActionButton btnAntena;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hilo);

        btnEval = findViewById(R.id.btnEvaHilo);
        btnAntena = findViewById(R.id.btnAntenaHilo);

        btnEval.setOnClickListener(view -> {
            if(isEvaluationCompleted()==6) {
                finish();
                Intent intent = new Intent(this, EvaHiloActivity.class);
                startActivity(intent);
            }else{
                    Toast.makeText(this, "Esta evaluación ya fue realizada", Toast.LENGTH_SHORT).show();
                }
        });
        btnAntena.setOnClickListener(view -> {
            Intent intent = new Intent(this, AntenaHiloActivity.class);
            startActivity(intent);
        });
    }
    private int isEvaluationCompleted() {
        SharedPreferences sharedPref = getSharedPreferences("Notas", Context.MODE_PRIVATE);
        return sharedPref.getInt("nota_Hilo", 6);
    }
}
