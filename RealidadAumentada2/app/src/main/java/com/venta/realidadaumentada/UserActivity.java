package com.venta.realidadaumentada;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UserActivity extends AppCompatActivity {

    Button btnCrear;
    EditText editUsuario;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        btnCrear = findViewById(R.id.btnCrear);
        editUsuario = findViewById(R.id.editUsario);

        // Inicializa el SharedPreferences
        sharedPreferences = getSharedPreferences("Usuario", Context.MODE_PRIVATE);

        // Verifica si ya existe un nombre de usuario guardado
        String usuarioGuardado = sharedPreferences.getString("usuario", "");
        if (!usuarioGuardado.isEmpty()) {
            // Si ya existe un nombre de usuario guardado, establece el valor en el EditText
            editUsuario.setText(usuarioGuardado);
        }

        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtiene el nombre de usuario ingresado
                String usuario = editUsuario.getText().toString();

                // Guarda el nombre de usuario en SharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("usuario", usuario);
                editor.apply(); // Guarda los cambios

                // Inicia la siguiente actividad
                Intent intent = new Intent(UserActivity.this, FragmentsActivity.class);
                startActivity(intent);
            }
        });
    }
}