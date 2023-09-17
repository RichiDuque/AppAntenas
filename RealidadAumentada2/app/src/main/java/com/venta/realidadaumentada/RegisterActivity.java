package com.venta.realidadaumentada;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {

    Button btnCrear;
    TextView textIngresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnCrear = findViewById(R.id.btnCrear);
        textIngresar = findViewById(R.id.textIngresar);

        btnCrear.setOnClickListener(view -> {
            Intent intent = new Intent(this, FragmentsActivity.class);
            startActivity(intent);
        });

        textIngresar.setOnClickListener(view -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        });
    }
}