package com.venta.realidadaumentada.conceptos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.venta.realidadaumentada.cuestionarios.EvaConcepActivity;
import com.venta.realidadaumentada.R;

public class ConceptosActivity extends AppCompatActivity {

    Button btnEval;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conceptos);

        btnEval = findViewById(R.id.btnEvaConcep);

        btnEval.setOnClickListener(view -> {
            if(isEvaluationCompleted()==6) {
                finish();
                Intent intent = new Intent(this, EvaConcepActivity.class);
                startActivity(intent);
            }else{
                Toast.makeText(this, "Esta evaluación ya fue realizada", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private int isEvaluationCompleted() {
        SharedPreferences sharedPref = getSharedPreferences("Notas", Context.MODE_PRIVATE);
        return sharedPref.getInt("nota_Concepto", 6);
    }
}