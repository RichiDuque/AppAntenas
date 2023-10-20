package com.venta.realidadaumentada.conceptos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.venta.realidadaumentada.R;
import com.venta.realidadaumentada.antenas.AntenaDipoActivity;
import com.venta.realidadaumentada.cuestionarios.EvaDipoActivity;

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
            if(isEvaluationCompleted()==6) {
                finish();
                Intent intent = new Intent(this, EvaDipoActivity.class);
                startActivity(intent);
            }else{
                Toast.makeText(this, "Esta evaluación ya fue realizada", Toast.LENGTH_SHORT).show();
            }
        });

        btnAntena.setOnClickListener(view -> {
            Intent intent = new Intent(this, AntenaDipoActivity.class);
            startActivity(intent);
        });
    }
    private int isEvaluationCompleted() {
        SharedPreferences sharedPref = getSharedPreferences("Notas", Context.MODE_PRIVATE);
        return sharedPref.getInt("nota_Dipolo", 6);
    }
}