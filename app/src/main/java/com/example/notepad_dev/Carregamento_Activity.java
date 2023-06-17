package com.example.notepad_dev;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.notepad_dev.databinding.ActivityCarregamentoBinding;

public class Carregamento_Activity extends AppCompatActivity {

    ActivityCarregamentoBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflar o layout da atividade utilizando ViewBinding
        binding = ActivityCarregamentoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int tempo = 1000;
        new Handler().postDelayed(() -> {

            // Após 1 segundo, a ação é executada
            finish();

            // Encerra a atividade atual para que o usuário não possa voltar a ela pressionando o botão "Voltar"
            abrirLogin();

        }, tempo);// Tempo de carregamento definido como 1000 milissegundos
    }

    //Método criado para abrir o Login
    private void abrirLogin(){
        Intent intent = new Intent(getApplicationContext(), Login_Activity.class );
        startActivity(intent);
    }
}