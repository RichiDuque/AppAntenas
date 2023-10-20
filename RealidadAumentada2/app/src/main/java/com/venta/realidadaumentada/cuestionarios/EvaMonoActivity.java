package com.venta.realidadaumentada.cuestionarios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioGroup;

import com.venta.realidadaumentada.FragmentsActivity;
import com.venta.realidadaumentada.R;

public class EvaMonoActivity extends AppCompatActivity {

    Button btnFinal;
    RadioGroup groupPregunta1, groupPregunta2, groupPregunta3, groupPregunta4, groupPregunta5;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eva_mono);

        btnFinal = findViewById(R.id.btnFinMonopolo);
        groupPregunta1 = findViewById(R.id.GroupPregunta1Monopolo);
        groupPregunta2 = findViewById(R.id.GroupPregunta2Monopolo);
        groupPregunta3 = findViewById(R.id.GroupPregunta3Monopolo);
        groupPregunta4 = findViewById(R.id.GroupPregunta4Monopolo);
        groupPregunta5 = findViewById(R.id.GroupPregunta5Monopolo);

        sharedPreferences = getSharedPreferences("Notas", MODE_PRIVATE);

        btnFinal.setOnClickListener(view -> {
            int notaTotal = calcularNota();

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("nota_Monopolo", notaTotal);
            editor.apply();

            finish();
        });
    }

    private int calcularNota() {
        int nota = 0;

        nota += calcularPregunta(groupPregunta1, R.id.rButtonB1Monopolo);
        nota += calcularPregunta(groupPregunta2, R.id.rButtonA2Monopolo);
        nota += calcularPregunta(groupPregunta3, R.id.rButtonB3Monopolo);
        nota += calcularPregunta(groupPregunta4, R.id.rButtonC4Monopolo);
        nota += calcularPregunta(groupPregunta5, R.id.rButtonC5Monopolo);

        return nota;
    }

    private int calcularPregunta(RadioGroup group, int respuestaCorrectaId) {
        int nota = 0;

        int selectedId = group.getCheckedRadioButtonId();
        if (selectedId == respuestaCorrectaId) {
            nota = 1;
        }

        return nota;
    }
}