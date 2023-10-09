package com.venta.realidadaumentada.cuestionarios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioGroup;

import com.venta.realidadaumentada.FragmentsActivity;
import com.venta.realidadaumentada.R;

public class EvaMIMOActivity extends AppCompatActivity {

    Button btnFinalMIMO;
    RadioGroup groupPregunta1MIMO, groupPregunta2MIMO, groupPregunta3MIMO, groupPregunta4MIMO, groupPregunta5MIMO;
    SharedPreferences sharedPreferences; // SharedPreferences para guardar la nota

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eva_mimo);

        btnFinalMIMO = findViewById(R.id.btnFinMIMO);
        groupPregunta1MIMO = findViewById(R.id.GroupPregunta1MIMO);
        groupPregunta2MIMO = findViewById(R.id.GroupPregunta2MIMO);
        groupPregunta3MIMO = findViewById(R.id.GroupPregunta3MIMO);
        groupPregunta4MIMO = findViewById(R.id.GroupPregunta4MIMO);
        groupPregunta5MIMO = findViewById(R.id.GroupPregunta5MIMO);

        sharedPreferences = getSharedPreferences("Notas", MODE_PRIVATE); // Nombre de SharedPreferences

        btnFinalMIMO.setOnClickListener(view -> {
            int notaTotal = calcularNota();

            // Guardar la nota total en SharedPreferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("nota_AntenaMIMO", notaTotal);
            editor.apply();

            finish();
            Intent intent = new Intent(this, FragmentsActivity.class);
            startActivity(intent);
        });
    }

    private int calcularNota() {
        int nota = 0;

        // Comprobar las respuestas seleccionadas y compararlas con las respuestas correctas
        nota += calcularPregunta(groupPregunta1MIMO, R.id.rButtonC1MIMO);
        nota += calcularPregunta(groupPregunta2MIMO, R.id.rButtonC2MIMO);
        nota += calcularPregunta(groupPregunta3MIMO, R.id.rButtonC3MIMO);
        nota += calcularPregunta(groupPregunta4MIMO, R.id.rButtonB4MIMO);
        nota += calcularPregunta(groupPregunta5MIMO, R.id.rButtonB5MIMO);

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
