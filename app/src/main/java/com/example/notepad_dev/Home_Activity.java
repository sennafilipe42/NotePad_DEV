package com.example.notepad_dev;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.notepad_dev.databinding.ActivityHomeBinding;
import com.example.notepad_dev.databinding.ActivityLoginBinding;
import com.google.firebase.auth.FirebaseAuth;

public class Home_Activity extends AppCompatActivity {

    ActivityHomeBinding binding;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        binding = ActivityHomeBinding.inflate(getLayoutInflater());

        View view = binding.getRoot();

        setContentView(view);

        binding.botaoSair.setOnClickListener(view1 -> {
            FirebaseAuth.getInstance().signOut();
            finish();
            abrirLogin();
        });
    }

    private void abrirLogin(){
        Intent intent = new Intent(getApplicationContext(), Login_Activity.class );
        startActivity(intent);
    }
}