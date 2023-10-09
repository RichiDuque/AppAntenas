package com.venta.realidadaumentada.cuestionarios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioGroup;

import com.venta.realidadaumentada.FragmentsActivity;
import com.venta.realidadaumentada.R;

public class EvaDipoActivity extends AppCompatActivity {

    Button btnFinalizarDipolo;
    RadioGroup groupPregunta1Dipolo, groupPregunta2Dipolo, groupPregunta3Dipolo, groupPregunta4Dipolo, groupPregunta5Dipolo;
    SharedPreferences sharedPreferences; // SharedPreferences para guardar la nota

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eva_dipo);

        btnFinalizarDipolo = findViewById(R.id.btnFinalizarDipolo);
        groupPregunta1Dipolo = findViewById(R.id.GroupPregunta1Dipolo);
        groupPregunta2Dipolo = findViewById(R.id.GroupPregunta2Dipolo);
        groupPregunta3Dipolo = findViewById(R.id.GroupPregunta3Dipolo);
        groupPregunta4Dipolo = findViewById(R.id.GroupPregunta4Dipolo);
        groupPregunta5Dipolo = findViewById(R.id.GroupPregunta5Dipolo);

        sharedPreferences = getSharedPreferences("Notas", MODE_PRIVATE); // Nombre de SharedPreferences

        btnFinalizarDipolo.setOnClickListener(view -> {
            int notaTotalDipolo = calcularNotaDipolo();

            // Guardar la nota total en SharedPreferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("nota_Dipolo", notaTotalDipolo);
            editor.apply();

            finish();
            Intent intent = new Intent(this, FragmentsActivity.class);
            startActivity(intent);
        });
    }

    private int calcularNotaDipolo() {
        int notaDipolo = 0;

        // Comprobar las respuestas seleccionadas y compararlas con las respuestas correctas
        notaDipolo += calcularPreguntaDipolo(groupPregunta1Dipolo, R.id.rButtonC1Dipolo);
        notaDipolo += calcularPreguntaDipolo(groupPregunta2Dipolo, R.id.rButtonC2Dipolo);
        notaDipolo += calcularPreguntaDipolo(groupPregunta3Dipolo, R.id.rButtonC3Dipolo);
        notaDipolo += calcularPreguntaDipolo(groupPregunta4Dipolo, R.id.rButtonB4Dipolo);
        notaDipolo += calcularPreguntaDipolo(groupPregunta5Dipolo, R.id.rButtonB5Dipolo);

        return notaDipolo;
    }

    private int calcularPreguntaDipolo(RadioGroup group, int respuestaCorrectaId) {
        int notaDipolo = 0;

        // Obtener el RadioButton seleccionado
        int selectedId = group.getCheckedRadioButtonId();
        if (selectedId == respuestaCorrectaId) {
            notaDipolo = 1; // La respuesta es correcta, sumar 1 punto
        }

        return notaDipolo;
    }
}