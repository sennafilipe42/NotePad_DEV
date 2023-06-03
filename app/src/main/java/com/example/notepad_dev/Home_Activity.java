package com.example.notepad_dev;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.notepad_dev.databinding.ActivityHomeBinding;
import com.google.firebase.auth.FirebaseAuth;

public class Home_Activity extends AppCompatActivity {

    ActivityHomeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(R.layout.activity_home);
        setContentView(view);

        binding.toolbarHome.botaoLogout.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            finish();
            abrirLogin();
        });
        binding.toolbarHome.botaoWallpapers.setOnClickListener(v -> {
            finish();
            abrirWallpapers();
        });

        binding.toolbarHome.botaoInfoCriadorAPP.setOnClickListener(v -> {
            finish();
            abrirInfo();
        });

        binding.addNotePAD.setOnClickListener(v -> {
            finish();
            abrirNewNotePad();
        });

    }

    private void abrirLogin() {
        Intent intent = new Intent(getApplicationContext(), Login_Activity.class);
        startActivity(intent);
    }

    private void abrirWallpapers() {
        Intent intent = new Intent(getApplicationContext(), Wallpapers_Activity.class);
        startActivity(intent);
    }

    private void abrirInfo() {
        Intent intent = new Intent(getApplicationContext(), Info__Activity.class);
        startActivity(intent);
    }
    private void abrirNewNotePad() {
        Intent intent = new Intent(getApplicationContext(), NewNotePad_Activity.class);
        startActivity(intent);
    }
}
