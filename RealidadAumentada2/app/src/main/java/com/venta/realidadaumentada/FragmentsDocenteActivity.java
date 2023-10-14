package com.venta.realidadaumentada;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.venta.realidadaumentada.fragments.HomeFragment;
import com.venta.realidadaumentada.fragments.NotasFragment;
import com.venta.realidadaumentada.fragments.NotificationFragment;
import com.venta.realidadaumentada.fragmentsDocente.CodigosFragment;
import com.venta.realidadaumentada.fragmentsDocente.SugerenciasFragment;

public class FragmentsDocenteActivity extends AppCompatActivity {

    CodigosFragment firstFragment = new CodigosFragment();
    SugerenciasFragment secondFragment = new SugerenciasFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragments_docente);

        BottomNavigationView navigation = findViewById(R.id.bottom_navigation_docente);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        loadFragment(firstFragment);
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.navigation_codigos:
                    loadFragment(firstFragment);
                    return true;
                case R.id.navigation_sugerencias:
                    loadFragment(secondFragment);
                    return true;
            }
            return false;
        }
    };

    public void loadFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(android.R.id.content, fragment);
        transaction.commit();
    }
}