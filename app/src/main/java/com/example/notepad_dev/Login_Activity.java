package com.example.notepad_dev;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notepad_dev.databinding.ActivityLoginBinding;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class Login_Activity extends AppCompatActivity {
    ActivityLoginBinding binding;
    GoogleSignInClient googleLogin;
    private FirebaseAuth mAuth;


    @SuppressLint("SetTextI18n")
    @Override

    public void onStart(){
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        try {
            Toast.makeText(getApplicationContext(), "Usuário: " + Objects.requireNonNull(currentUser).getEmail() + " Logado.", Toast.LENGTH_LONG).show();
            abrirHome();
            finish();
        }catch(Exception ignored) {


        }
    }

    private void updateUI(FirebaseUser currentUser) {
    }

    @SuppressLint("SetTextI18n")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //vincula o Back end com Front End
        binding = ActivityLoginBinding.inflate(getLayoutInflater());

        View view = binding.getRoot();

        setContentView(view);

        mAuth = FirebaseAuth.getInstance();

        //muda o nome do botão do Login do google
        TextView text_botao_logar_google = (TextView) binding.botaoGoogle.getChildAt(0);

        text_botao_logar_google.setText("Fazer login com Google Account");


        binding.botaoEntrar.setOnClickListener(v -> {
                try {
                    loginUsuarioESenha(
                        binding.editUser.getText().toString(),
                        binding.editPassword.getText().toString());

                }catch(Exception e){
                    Toast.makeText(getApplicationContext(), "Campos vazios, verifique o Usuário e a Senha!", Toast.LENGTH_LONG).show();
                }

            }
        );

    }

    private void loginUsuarioESenha(String usuario, String senha){
        mAuth.signInWithEmailAndPassword(usuario, senha)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        //Log.d(TAG, "signInWithCredential:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        Toast.makeText(getApplicationContext(), "Login efetuado com sucesso!", Toast.LENGTH_LONG).show();
                        abrirHome();
                        finish();
                        //updateUI(user);
                    } else {
                        // If sign in fails, display a message to the user.
                        //Log.w(TAG, "signInWithCredential:failure", task.getException());
                        Toast.makeText(getApplicationContext(), "Erro para efetuar o Login.", Toast.LENGTH_LONG).show();
                        //updateUI(null);
                    }
                });

    }

    private void abrirHome(){
        binding.editUser.setText("");
        binding.editPassword.setText("");
        Intent intent = new Intent(getApplicationContext(), Home_Activity.class );
        startActivity(intent);
    }
}