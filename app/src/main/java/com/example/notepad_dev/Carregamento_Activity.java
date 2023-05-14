package com.example.notepad_dev;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Carregamento_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carregamento);

        int tempo = 1000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Depois de 1s carrega a Main
                startActivity(new Intent(Carregamento_Activity.this, MainActivity.class));
                finish(); // Encerra a atividade atual para que o usuário não possa voltar a ela pressionando o botão "Voltar"
            }
        }, tempo); //Tempo de carregamento
    }
}