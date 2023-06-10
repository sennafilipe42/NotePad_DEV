package com.example.notepad_dev;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.notepad_dev.databinding.ActivityCreateAccountBinding;
import com.example.notepad_dev.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class CreateAccount_Activity extends AppCompatActivity {
    ActivityCreateAccountBinding  binding;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        binding = ActivityCreateAccountBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        setContentView(view);

        mAuth = FirebaseAuth.getInstance();


        binding.buttonCriarEntrar.setOnClickListener(v->criarConta());
        binding.botaoBack.setOnClickListener(v->abrirLogin());
    }

    public void criarConta(){
            String email = binding.editTextTextEmailAddress.getText().toString();
            String password = binding.editTextTextPassword.getText().toString();
            String confirmPassword = binding.editTextTextPassword2.getText().toString();

            boolean dadosConferidos = conferirDados(email,password,confirmPassword);
            if(!dadosConferidos){
                return;
            }

            criarContaFirebase(email,password);
    }

    void criarContaFirebase(String email,String password){
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(CreateAccount_Activity.this,
                task -> {
                    if (task.isSuccessful()){
                        //Criação concluida
                        Utilidades.showToast(CreateAccount_Activity.this, "Conta criada com sucesso!");
                        Objects.requireNonNull(mAuth.getCurrentUser()).sendEmailVerification();
                        mAuth.signOut();
                        abrirLogin();

                    }else{
                        //Falha na criação
                        Utilidades.showToast(CreateAccount_Activity.this, Objects.requireNonNull(task.getException()).getLocalizedMessage());
                    }

                }
        );
    }
    boolean conferirDados(String email, String password, String confirmPassword){
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.editTextTextEmailAddress.setError("E-mail não é valido!");
            return false;
        }
        if(password.length()<6){
            binding.editTextTextPassword.setError("Senha precisa ser a partir de 6 caracteres!");
            return false;
        }
        if (!password.equals(confirmPassword)){
            binding.editTextTextPassword2.setError("Senhas não conferem!");
            return false;
        }
        return true;
    }

    public void abrirLogin(){
        Intent intent = new Intent(getApplicationContext(), Login_Activity.class );
        startActivity(intent);
    }
}