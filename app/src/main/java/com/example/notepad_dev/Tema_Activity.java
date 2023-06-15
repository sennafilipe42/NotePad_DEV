package com.example.notepad_dev;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.notepad_dev.databinding.ActivityTemaBinding;
import com.google.firebase.auth.FirebaseAuth;


public class Tema_Activity extends AppCompatActivity {

    ActivityTemaBinding binding;
    boolean nightMODE;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTemaBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        sharedPreferences = getSharedPreferences("MODE", Context.MODE_PRIVATE);
        nightMODE = sharedPreferences.getBoolean("night", false);

        if (nightMODE) {
            binding.botaoSwitch.setChecked(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        }

        binding.botaoSwitch.setOnClickListener(view1 -> {
            if (nightMODE) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                editor = sharedPreferences.edit();
                editor.putBoolean("night", false);

            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                editor = sharedPreferences.edit();
                editor.putBoolean("night", true);
            }

            editor.apply();
        });

        binding.toolbarTema.botaoLogout.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            finish();
            abrirLogin();
        });


        binding.toolbarTema.botaoHome.setOnClickListener(v -> {
            finish();
            abrirHome();
        });

    }

    private void abrirLogin() {
        Intent intent = new Intent(getApplicationContext(), Login_Activity.class);
        startActivity(intent);
    }

    private void abrirHome() {
        Intent intent = new Intent(getApplicationContext(), Home_Activity.class);
        startActivity(intent);
    }

}