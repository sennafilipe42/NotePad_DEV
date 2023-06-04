package com.example.notepad_dev;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.notepad_dev.databinding.ActivityHomeBinding;
import com.example.notepad_dev.databinding.ActivityNewNotePadBinding;
import com.google.firebase.auth.FirebaseAuth;

public class NewNotePad_Activity extends AppCompatActivity {
    ActivityNewNotePadBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note_pad);
        binding = ActivityNewNotePadBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(R.layout.activity_new_note_pad);
        setContentView(view);

        binding.toolbarNewNotePad.botaoInfoCriadorAPP.setOnClickListener(v->{
            abrirInfo();
            finish();
        });

        binding.toolbarNewNotePad.botaoWallpapers.setOnClickListener(v->{
            abrirWallpapers();
            finish();
        });

        binding.toolbarNewNotePad.botaoLogout.setOnClickListener(v->{
            FirebaseAuth.getInstance().signOut();
            finish();
            abrirLogin();
        });

        binding.notePadTitulo.setOnClickListener(v->{

        });

        binding.notePadTexto.setOnClickListener(v->{

        });

        binding.botaoSalvarNotePad.setOnClickListener(v->{

        });

    }

    private void salvarNotePad(){
        String titulo = binding.notePadTitulo.getText().toString();
        String texto =  binding.notePadTexto.getText().toString();

        if (titulo==null||titulo.isEmpty()){
            binding.notePadTitulo.setError("Necessário inserir um titulo.");
            return;
        }


    }
    private void abrirLogin() {
        Intent intent = new Intent(getApplicationContext(), Login_Activity.class);
        startActivity(intent);
    }

    public void abrirWallpapers() {
        Intent intent = new Intent(getApplicationContext(), Wallpapers_Activity.class);
        startActivity(intent);
    }

    public void abrirInfo() {
        Intent intent = new Intent(getApplicationContext(), Info__Activity.class);
        startActivity(intent);
    }
}