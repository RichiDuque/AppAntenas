package com.venta.realidadaumentada;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.venta.realidadaumentada.fragments.NotasFragment;
import com.venta.realidadaumentada.fragments.HomeFragment;
import com.venta.realidadaumentada.fragments.NotificationFragment;

public class FragmentsActivity extends AppCompatActivity {

    HomeFragment firstFragment = new HomeFragment();
    NotasFragment secondFragment = new NotasFragment();
    NotificationFragment thirdFragment = new NotificationFragment();
    Button btnIntro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragments);

        BottomNavigationView navigation = findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        loadFragment(firstFragment);
    }
    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.navigation_modulos:
                    loadFragment(firstFragment);
                    return true;
                case R.id.navigation_calificaciones:
                    loadFragment(secondFragment);
                    return true;
                case R.id.navigation_configuracion:
                    loadFragment(thirdFragment);
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