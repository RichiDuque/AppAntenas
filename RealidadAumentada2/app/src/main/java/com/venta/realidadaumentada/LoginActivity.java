package com.venta.realidadaumentada;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    Button btnIngresar;
    TextView textCrear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnIngresar = findViewById(R.id.btnIngresar);
        textCrear = findViewById(R.id.textCrear);

        btnIngresar.setOnClickListener(view -> {
            Intent intent = new Intent(this, FragmentsActivity.class);
            startActivity(intent);
        });

        textCrear.setOnClickListener(view -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        });
    }
}