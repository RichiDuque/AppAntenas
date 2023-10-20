package com.venta.realidadaumentada.conceptos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.venta.realidadaumentada.antenas.AntenaYagiUdaActivity;
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
            if(isEvaluationCompleted()==6) {
                finish();
                Intent intent = new Intent(this, EvaYagiUdaActivity.class);
                startActivity(intent);
            }else{
                Toast.makeText(this, "Esta evaluación ya fue realizada", Toast.LENGTH_SHORT).show();
            }
        });

        btnAntenaYagiUda.setOnClickListener(view -> {
            Intent intent = new Intent(this, AntenaYagiUdaActivity.class);
            startActivity(intent);
        });
    }
    private int isEvaluationCompleted() {
        SharedPreferences sharedPref = getSharedPreferences("Notas", Context.MODE_PRIVATE);
        return sharedPref.getInt("nota_YagiUda", 6);
    }
}
