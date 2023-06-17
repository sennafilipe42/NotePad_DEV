package com.example.notepad_dev;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;

import com.example.notepad_dev.databinding.ActivityCreateAccountBinding;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class CreateAccount_Activity extends AppCompatActivity {
    ActivityCreateAccountBinding  binding;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflar o layout da atividade
        binding = ActivityCreateAccountBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        setContentView(view);

        // Inicializar a instância do FirebaseAuth
        mAuth = FirebaseAuth.getInstance();

        // Configurar o clique do botão de criar/entrar conta
        binding.buttonCriarEntrar.setOnClickListener(v->criarConta());

        // Configurar o clique do botão de voltar para a tela de login
        binding.botaoBack.setOnClickListener(v->abrirLogin());
    }

    // Método para criar a conta do usuário
    public void criarConta(){
            String email = binding.editTextTextEmailAddress.getText().toString();
            String password = binding.editTextTextPassword.getText().toString();
            String confirmPassword = binding.editTextTextPassword2.getText().toString();

             // Conferir os dados inseridos pelo usuário
            boolean dadosConferidos = conferirDados(email,password,confirmPassword);
            if(!dadosConferidos){
                return;
            }

        // Criar a conta no Firebase
        criarContaFirebase(email,password);
    }

    // Método para criar a conta no Firebase Authentication
    void criarContaFirebase(String email,String password){
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(CreateAccount_Activity.this,
                task -> {
                    if (task.isSuccessful()){

                        // Criação concluída com sucesso
                        Utilidades.showToast(CreateAccount_Activity.this, "Conta criada com sucesso!");
                        Objects.requireNonNull(mAuth.getCurrentUser()).sendEmailVerification();
                        mAuth.signOut();
                        abrirLogin();

                    }else{

                        // Falha na criação da conta
                        Utilidades.showToast(CreateAccount_Activity.this, Objects.requireNonNull(task.getException()).getLocalizedMessage());
                    }

                }
        );
    }
    // Método para conferir os dados inseridos pelo usuário
    boolean conferirDados(String email, String password, String confirmPassword){
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){

            // Verificar se o e-mail inserido é válido
            binding.editTextTextEmailAddress.setError("E-mail não é valido!");
            return false;
        }
        if(password.length()<6){

            // Verificar se a senha possui pelo menos 6 caracteres
            binding.editTextTextPassword.setError("Senha precisa ser a partir de 6 caracteres!");
            return false;
        }
        if (!password.equals(confirmPassword)){

            // Verificar se as senhas coincidem
            binding.editTextTextPassword2.setError("Senhas não conferem!");
            return false;
        }
        return true;
    }

    // Método para abrir a tela de login
    public void abrirLogin(){
        Intent intent = new Intent(getApplicationContext(), Login_Activity.class );
        startActivity(intent);
    }
}