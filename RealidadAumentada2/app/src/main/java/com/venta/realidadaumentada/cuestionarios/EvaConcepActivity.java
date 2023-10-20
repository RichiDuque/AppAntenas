package com.venta.realidadaumentada.cuestionarios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.venta.realidadaumentada.FragmentsActivity;
import com.venta.realidadaumentada.LoginActivity;
import com.venta.realidadaumentada.R;

public class EvaConcepActivity extends AppCompatActivity {

    Button btnFinal;
    RadioGroup groupPregunta1, groupPregunta2, groupPregunta3, groupPregunta4, groupPregunta5;
    SharedPreferences sharedPreferences; // SharedPreferences para guardar la nota

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eva_concep);

        btnFinal = findViewById(R.id.btnFin);
        groupPregunta1 = findViewById(R.id.GroupPregunta1);
        groupPregunta2 = findViewById(R.id.GroupPregunta2);
        groupPregunta3 = findViewById(R.id.GroupPregunta3);
        groupPregunta4 = findViewById(R.id.GroupPregunta4);
        groupPregunta5 = findViewById(R.id.GroupPregunta5);

        sharedPreferences = getSharedPreferences("Notas", MODE_PRIVATE); // Nombre de SharedPreferences

        btnFinal.setOnClickListener(view -> {
            int notaTotal = calcularNota();

            // Guardar la nota total en SharedPreferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("nota_Concepto", notaTotal);
            editor.apply();

            finish();
        });
    }

    private int calcularNota() {
        int nota = 0;

        // Comprobar las respuestas seleccionadas y compararlas con las respuestas correctas
        nota += calcularPregunta(groupPregunta1, R.id.rButtonB);
        nota += calcularPregunta(groupPregunta2, R.id.rButtonC2);
        nota += calcularPregunta(groupPregunta3, R.id.rButtonC3);
        nota += calcularPregunta(groupPregunta4, R.id.rButtonC4);
        nota += calcularPregunta(groupPregunta5, R.id.rButtonC5);

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
