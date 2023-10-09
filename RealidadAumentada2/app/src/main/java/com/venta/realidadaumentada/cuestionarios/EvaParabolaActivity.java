package com.venta.realidadaumentada.cuestionarios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioGroup;

import com.venta.realidadaumentada.FragmentsActivity;
import com.venta.realidadaumentada.R;

public class EvaParabolaActivity extends AppCompatActivity {

    Button btnFinalAntena;
    RadioGroup groupPregunta1Antena, groupPregunta2Antena, groupPregunta3Antena, groupPregunta4Antena, groupPregunta5Antena;
    SharedPreferences sharedPreferences; // SharedPreferences para guardar la nota


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eva_parabola);
        btnFinalAntena = findViewById(R.id.btnFinAntena);
        groupPregunta1Antena = findViewById(R.id.GroupPregunta1Antena);
        groupPregunta2Antena = findViewById(R.id.GroupPregunta2Antena);
        groupPregunta3Antena = findViewById(R.id.GroupPregunta3Antena);
        groupPregunta4Antena = findViewById(R.id.GroupPregunta4Antena);
        groupPregunta5Antena = findViewById(R.id.GroupPregunta5Antena);

        sharedPreferences = getSharedPreferences("Notas", MODE_PRIVATE); // Nombre de SharedPreferences

        btnFinalAntena.setOnClickListener(view -> {
            int notaTotal = calcularNota();

            // Guardar la nota total en SharedPreferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("nota_AntenaParabolica", notaTotal);
            editor.apply();

            finish();
            Intent intent = new Intent(this, FragmentsActivity.class);
            startActivity(intent);
        });
    }

    private int calcularNota() {
        int nota = 0;

        // Comprobar las respuestas seleccionadas y compararlas con las respuestas correctas
        nota += calcularPregunta(groupPregunta1Antena, R.id.rButtonC1Antena);
        nota += calcularPregunta(groupPregunta2Antena, R.id.rButtonC2Antena);
        nota += calcularPregunta(groupPregunta3Antena, R.id.rButtonB3Antena);
        nota += calcularPregunta(groupPregunta4Antena, R.id.rButtonA4Antena);
        nota += calcularPregunta(groupPregunta5Antena, R.id.rButtonB5Antena);

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