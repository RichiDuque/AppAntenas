package com.venta.realidadaumentada.conceptos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.venta.realidadaumentada.antenas.AntenaMicrostripActivity;
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
            if(isEvaluationCompleted()==6) {
                finish();
                Intent intent = new Intent(this, EvaMicrostripActivity.class);
                startActivity(intent);
            }else{
                Toast.makeText(this, "Esta evaluación ya fue realizada", Toast.LENGTH_SHORT).show();
            }
        });

        btnAntenaMicrostrip.setOnClickListener(view -> {
            Intent intent = new Intent(this, AntenaMicrostripActivity.class);
            startActivity(intent);
        });
    }
    private int isEvaluationCompleted() {
        SharedPreferences sharedPref = getSharedPreferences("Notas", Context.MODE_PRIVATE);
        return sharedPref.getInt("nota_Microstrip", 6);
    }
}
