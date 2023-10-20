package com.venta.realidadaumentada.cuestionarios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioGroup;

import com.venta.realidadaumentada.FragmentsActivity;
import com.venta.realidadaumentada.R;

public class EvaYagiUdaActivity extends AppCompatActivity {

    Button btnFinalYagiUda;
    RadioGroup groupPregunta1YagiUda, groupPregunta2YagiUda, groupPregunta3YagiUda, groupPregunta4YagiUda, groupPregunta5YagiUda;
    SharedPreferences sharedPreferences; // SharedPreferences para guardar la nota

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eva_yagi_uda);

        btnFinalYagiUda = findViewById(R.id.btnFinYagiUda);
        groupPregunta1YagiUda = findViewById(R.id.GroupPregunta1YagiUda);
        groupPregunta2YagiUda = findViewById(R.id.GroupPregunta2YagiUda);
        groupPregunta3YagiUda = findViewById(R.id.GroupPregunta3YagiUda);
        groupPregunta4YagiUda = findViewById(R.id.GroupPregunta4YagiUda);
        groupPregunta5YagiUda = findViewById(R.id.GroupPregunta5YagiUda);

        sharedPreferences = getSharedPreferences("Notas", MODE_PRIVATE); // Nombre de SharedPreferences

        btnFinalYagiUda.setOnClickListener(view -> {
            int notaTotalYagiUda = calcularNotaYagiUda();

            // Guardar la nota total en SharedPreferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("nota_YagiUda", notaTotalYagiUda);
            editor.apply();

            finish();
        });
    }

    private int calcularNotaYagiUda() {
        int notaYagiUda = 0;

        // Comprobar las respuestas seleccionadas y compararlas con las respuestas correctas
        notaYagiUda += calcularPreguntaYagiUda(groupPregunta1YagiUda, R.id.rButtonC1YagiUda);
        notaYagiUda += calcularPreguntaYagiUda(groupPregunta2YagiUda, R.id.rButtonC2YagiUda);
        notaYagiUda += calcularPreguntaYagiUda(groupPregunta3YagiUda, R.id.rButtonA3YagiUda);
        notaYagiUda += calcularPreguntaYagiUda(groupPregunta4YagiUda, R.id.rButtonB4YagiUda);
        notaYagiUda += calcularPreguntaYagiUda(groupPregunta5YagiUda, R.id.rButtonC5YagiUda);

        return notaYagiUda;
    }

    private int calcularPreguntaYagiUda(RadioGroup group, int respuestaCorrecta) {
        int notaPregunta = 0;
        int radioButtonID = group.getCheckedRadioButtonId();

        if (radioButtonID == respuestaCorrecta) {
            notaPregunta = 2; // Pregunta correcta
        }

        return notaPregunta;
    }
}
