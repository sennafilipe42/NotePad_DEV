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

        // Infla a View usando o binding
        binding = ActivityTemaBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // Obtém a referência para o SharedPreferences
        sharedPreferences = getSharedPreferences("MODE", Context.MODE_PRIVATE);

        // Obtém o valor atual do modo noturno
        nightMODE = sharedPreferences.getBoolean("night", false);

        if (nightMODE) {
            // Se o modo noturno estiver ativado, define o tema noturno
            binding.botaoSwitch.setChecked(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }

        // Define o listener para o botão de alternar o modo
        binding.botaoSwitch.setOnClickListener(view1 -> {
            if (nightMODE) {
                // Se o modo noturno estiver ativado, desativa o modo noturno
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                editor = sharedPreferences.edit();
                editor.putBoolean("night", false);
            } else {
                // Se o modo noturno estiver desativado, ativa o modo noturno
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                editor = sharedPreferences.edit();
                editor.putBoolean("night", true);
            }
            // Aplica as alterações no SharedPreferences
            editor.apply();
        });

        // Define o listener para o botão de logout
        binding.toolbarTema.botaoLogout.setOnClickListener(v -> {
            // Realiza o logout do usuário
            FirebaseAuth.getInstance().signOut();
            finish();
            abrirLogin();
        });

        // Define o listener para o botão de home
        binding.toolbarTema.botaoHome.setOnClickListener(v -> {
            finish();
            abrirHome();
        });
    }

    // Método para abrir a tela de login
    private void abrirLogin() {
        Intent intent = new Intent(getApplicationContext(), Login_Activity.class);
        startActivity(intent);
    }

    // Método para abrir a tela inicial
    private void abrirHome() {
        Intent intent = new Intent(getApplicationContext(), Home_Activity.class);
        startActivity(intent);
    }
}
