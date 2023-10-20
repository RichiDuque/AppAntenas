package com.venta.realidadaumentada.cuestionarios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioGroup;

import com.venta.realidadaumentada.FragmentsActivity;
import com.venta.realidadaumentada.R;

public class EvaMicrostripActivity extends AppCompatActivity {

    Button btnFinalMicrostrip;
    RadioGroup groupPregunta1Microstrip, groupPregunta2Microstrip, groupPregunta3Microstrip, groupPregunta4Microstrip, groupPregunta5Microstrip;
    SharedPreferences sharedPreferences; // SharedPreferences para guardar la nota

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eva_microstrip);

        btnFinalMicrostrip = findViewById(R.id.btnFinMicrostrip);
        groupPregunta1Microstrip = findViewById(R.id.GroupPregunta1Microstrip);
        groupPregunta2Microstrip = findViewById(R.id.GroupPregunta2Microstrip);
        groupPregunta3Microstrip = findViewById(R.id.GroupPregunta3Microstrip);
        groupPregunta4Microstrip = findViewById(R.id.GroupPregunta4Microstrip);
        groupPregunta5Microstrip = findViewById(R.id.GroupPregunta5Microstrip);

        sharedPreferences = getSharedPreferences("Notas", MODE_PRIVATE); // Nombre de SharedPreferences

        btnFinalMicrostrip.setOnClickListener(view -> {
            int notaTotal = calcularNota();

            // Guardar la nota total en SharedPreferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("nota_Microstrip", notaTotal);
            editor.apply();

            finish();
        });
    }

    private int calcularNota() {
        int nota = 0;

        // Comprobar las respuestas seleccionadas y compararlas con las respuestas correctas
        nota += calcularPregunta(groupPregunta1Microstrip, R.id.rButtonC1Microstrip);
        nota += calcularPregunta(groupPregunta2Microstrip, R.id.rButtonC2Microstrip);
        nota += calcularPregunta(groupPregunta3Microstrip, R.id.rButtonC3Microstrip);
        nota += calcularPregunta(groupPregunta4Microstrip, R.id.rButtonC4Microstrip);
        nota += calcularPregunta(groupPregunta5Microstrip, R.id.rButtonA5Microstrip);

        return nota;
    }

    private int calcularPregunta(RadioGroup group, int respuestaCorrectaId) {
        int nota = 0;

        // Obtener el RadioButton seleccionado
        int selectedId = group.getCheckedRadioButtonId();
        if (selectedId == respuestaCorrectaId) {
            nota = 1; // La respuesta es correcta, sumar 1 punto
        }

        return nota;
    }
}
